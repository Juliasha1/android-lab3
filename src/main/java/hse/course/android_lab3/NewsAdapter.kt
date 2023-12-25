package hse.course.android_lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val newsData: ArrayList<News>) :

    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val content: TextView

        init {
            title = view.findViewById(R.id.news_title)
            content = view.findViewById(R.id.news_content)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item, viewGroup, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(newsViewHolder: NewsViewHolder, position: Int) {
        newsViewHolder.title.text = newsData[position].title
        newsViewHolder.content.text = newsData[position].content
    }

    override fun getItemCount() = newsData.size

}