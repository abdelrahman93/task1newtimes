package com.comapny.task1.adapters


import android.graphics.drawable.Drawable
import com.comapny.task1.models.NewsResults
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.comapny.task1.R
import com.comapny.task1.ui.MainActivityInterface

class NewsAdapter(private var newsList:List<NewsResults>,private var mainActivityView:MainActivityInterface):RecyclerView.Adapter<NewsAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val news= newsList[position]

        //render
        vh.tv_title.text= news.title
        vh.tv_by.text= news.byline
        vh.tv_date.text= news.published_date

        //Progress dialog until image loading
if(news.media.size>0){
    if(news.media.get(0).media_metadata.size>0){
        Glide.with(vh.iv_news.context).load(news.media.get(0).media_metadata.get(2).url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return true
                     }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    vh.pg_loading.visibility= View.GONE

                    return false
                }

            })
            .into(vh.iv_news)

    }


    //Click action
    vh.main_relative.setOnClickListener(View.OnClickListener {
        mainActivityView.navigateToNewsDetails(news.title,news.abstract,news.byline,news.published_date,news.media.get(0).media_metadata.get(1).url)
    })
}



    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun update(data:List<NewsResults>){
        newsList= data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val main_relative: RelativeLayout = view.findViewById(R.id.main_relative)

        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_by: TextView = view.findViewById(R.id.tv_by)
        val tv_date: TextView = view.findViewById(R.id.tv_date)
        val iv_news: ImageView = view.findViewById(R.id.iv_news)
        val pg_loading: ProgressBar = view.findViewById(R.id.pg_loading)



    }
}