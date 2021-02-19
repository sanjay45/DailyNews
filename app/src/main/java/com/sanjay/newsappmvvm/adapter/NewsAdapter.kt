package com.sanjay.newsappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanjay.newsappmvvm.Article
import com.sanjay.newsappmvvm.R
import kotlinx.android.synthetic.main.item_article_preview.view.*

/** Every time you want to add an article then you add it to the list and
 * you call adapter.notifydatasetchanged() but that is very inefficient and
 * we won't do this in this part because by using notifydatasetchanged()
 the recycler view adapter will always update its whole items even the items
 that didn't change and to solve that problem we can use what is called
 diffutil and as the name says the diffutil calculates the differences between two
 lists and enables us to only update those items that were different and
 another advantage of that is that it will actually happen in the background
 so we don't block our main thread with that and to start with that we need to create
 a callback for our AsyncListDiffer so the asynclistdiffer will be the tool
 that compares our two lists and then only updates those items that would
 change and as you can hear AsynclistDiffer  is asynchronous so it will
 execute that on a background thread.
*/

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

   inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       fun bind(article: Article) = with(itemView) {
           Glide.with(this).load(article.urlToImage).into(ivArticleImage)
           tvSource.text = article.source.name
           tvTitle.text = article.title
           tvDescription.text = article.description
           tvPublishedAt.text = article.publishedAt

           setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
           }

       }

   }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}