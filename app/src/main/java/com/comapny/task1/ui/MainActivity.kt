package com.comapny.task1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comapny.task1.R
import com.comapny.task1.adapters.NewsAdapter
import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.Requests.NewsRequest
import com.comapny.task1.networkLayer.Requests.NewsRequestInterface
import com.comapny.task1.viewmodels.NewsViewModel
import com.comapny.task1.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityInterface {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()

        initRecyclerView()

    }

    private fun initRecyclerView() {


        adapter = NewsAdapter(viewModel.news.value ?: emptyList(),this)
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = adapter

    }


    private fun setupViewModel() {
         val newsRequestInterface: NewsRequestInterface = NewsRequest()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(newsRequestInterface)).get(NewsViewModel::class.java)
        viewModel.news.observe(this, renderNews)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)

    }

    //observers
    private val renderNews = Observer<List<NewsResults>> {
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }


    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.loadMuseums()
    }

    override fun navigateToNewsDetails(title: String, desc: String,by: String, date: String, url: String) {

        val intent = Intent(this@MainActivity,NewsDetailsActivity::class.java)
        intent.putExtra("title",title)
        intent.putExtra("desc",desc)
        intent.putExtra("by",by)
        intent.putExtra("date",date)
        intent.putExtra("url",url)
        startActivity(intent)

    }


}
