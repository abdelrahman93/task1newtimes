package com.comapny.task1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.comapny.task1.R
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_list_item.tv_title

class NewsDetailsActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val title=intent.getStringExtra("title")
        val desc=intent.getStringExtra("desc")
        val by=intent.getStringExtra("by")
        val date=intent.getStringExtra("date")
        val url=intent.getStringExtra("url")

        tv_title.text=title
        tv_desc.text=desc
        tv_by_details.text=by
        tv_date_details.text=date
        tv_title.text=title
        Glide.with(iv_news_details.context).load(url)
            .into(iv_news_details)


    }




}
