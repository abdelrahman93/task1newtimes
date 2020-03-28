package com.comapny.task1.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.comapny.task1.R
import com.comapny.task1.adapters.NewsAdapter
import com.comapny.task1.models.NewsResults
import com.comapny.task1.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_list_item.*
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
