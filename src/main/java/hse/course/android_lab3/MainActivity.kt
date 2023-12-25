package hse.course.android_lab3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_activity_layout)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = NewsAdapter(ArrayList())

        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {

            val search: EditText = findViewById(R.id.search)
            val title: String = search.text.toString()

            if (title.isEmpty()) {
                return@setOnClickListener
            }

            val newsApi = NewsApi.create()
            newsApi.get(
                "pub_343025d34b5606a3793708f95ed60d3991aa7",
                title,
                "ru"
            )
                .enqueue(object : Callback<Result> {

                    override fun onResponse(
                        call: Call<Result>,
                        response: Response<Result>
                    ) {
                        val result: Result? = response.body()
                        if (result?.results != null) {
                            recyclerView.adapter = NewsAdapter(result.results)
                        }
                    }

                    override fun onFailure(
                        call: Call<Result>,
                        t: Throwable
                    ) {
                    }
                })
        }
    }
}
