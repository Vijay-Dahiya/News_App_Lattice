package com.vijay.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vijay.newsapp.R
import com.vijay.newsapp.databinding.ActivityMainBinding
import com.vijay.newsapp.model.Repo
import com.vijay.newsapp.model.remote.ArticlesItem
import com.vijay.newsapp.view.adapter.RecyclerViewAdapter
import com.vijay.newsapp.viewmodel.TheViewModel
import com.vijay.newsapp.viewmodel.ViewModelFactory


/**
 * This is our main activity where we do all ui operations
 * Inside onCreate we are observing viewModel and getting data
 * Binding object is used for Data binding
 * List Size is used to set size of list on our main Screen
 */

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TheViewModel
    private lateinit var repo: Repo
    private lateinit var listSize: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        repo = Repo()

        /**
         * Here we have subscribed View Model to get lifecycle aware data
         * This data will be used by our recycler view to populate on main screen.
         */
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(TheViewModel::class.java)

        var dataList = ArrayList<ArticlesItem>()
        viewModel.getData().observe(this@MainActivity, Observer {
            val list = it as ArrayList<ArticlesItem>
            dataList=list

            /**
             * Following three lines will get size of list and set it to our main screen.
             */
            listSize="${list.size} News"
            binding.count.text = listSize
            setRecyclerView(list)
        })
        /**
         * Following is our searchView Listener whenever we change text in searchView it will awake
         * and start filtering data from our list.
         */

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val l=ArrayList<ArticlesItem>()
                dataList.forEach {
                    if(it.title!!.contains(p0!!)){
                        l.add(it)
                    }
                }
                setRecyclerView(l)
                listSize = "${l.size} News"
                binding.count.text = listSize
                return false
            }



            override fun onQueryTextChange(p0: String?): Boolean {
                val l=ArrayList<ArticlesItem>()
                dataList.forEach {
                    if(it.title!!.contains(p0!!)){
                        l.add(it)
                    }
                }
                setRecyclerView(l)
                listSize="${l.size} News"
                binding.count.text = listSize
                return false
            }

        })
    }


    /**
     * Here we are passing data to recyclerView which we got from viewModel.
     * And giving context to Layout Manager.
     */
    private fun setRecyclerView(list: ArrayList<ArticlesItem>) {
        binding.progressBar.visibility = View.GONE
        val recyclerAdapter = RecyclerViewAdapter(list)
        binding.recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }



}