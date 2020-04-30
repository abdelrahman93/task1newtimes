package com.comapny.task1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comapny.task1.R
import com.comapny.task1.adapters.NewsAdapter
import com.comapny.task1.models.NewsResults
import com.comapny.task1.networkLayer.Requests.NewsRequest
import com.comapny.task1.networkLayer.Requests.NewsRequestInterface
import com.comapny.task1.viewmodels.NewsViewModel
import com.comapny.task1.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment(), MainActivityInterface {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private lateinit var rv_news: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_home, container, false)
        rv_news = root.findViewById(R.id.rv_news)
        setupViewModel()
        initRecyclerView()

        return root
    }


    private fun initRecyclerView() {


        adapter = NewsAdapter(viewModel.news.value ?: emptyList(), this)
        rv_news.layoutManager = LinearLayoutManager(activity)
        rv_news.adapter = adapter

    }


    private fun setupViewModel() {
        val newsRequestInterface: NewsRequestInterface = NewsRequest()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(newsRequestInterface)
        ).get(NewsViewModel::class.java)

        viewModel.news.observe(viewLifecycleOwner, renderNews)



        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)

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
        viewModel.loadNews()
    }

    override fun navigateToNewsDetails(
        title: String,
        desc: String,
        by: String,
        date: String,
        url: String
    ) {

        val intent = Intent(activity, NewsDetailsActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("desc", desc)
        intent.putExtra("by", by)
        intent.putExtra("date", date)
        intent.putExtra("url", url)
        startActivity(intent)
    }


}
