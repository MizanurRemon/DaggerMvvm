package com.example.daggermvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.Adapter.ProductAdapter
import com.example.daggermvvm.ViewModel.MainViewModel
import com.example.daggermvvm.ViewModel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var itemView: RecyclerView
    lateinit var adapter: ProductAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_view()

        (application as FakerApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.productsLiveData.observe(this, Observer {
            //Log.d("dataxx", it.size.toString())

            for (i in 1..it.size-2) {
                Log.d("dataxx", it.get(i).image.toString())
            }
            adapter = ProductAdapter(it)
            adapter.setOnItemClickListener(object : ProductAdapter.OnTapListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(applicationContext, it.get(position).title.toString(), Toast.LENGTH_SHORT).show()
                }

            })
            itemView.adapter = adapter
            //it.joinToString { x -> x.title.toString() }+" "+
        })
    }

    private fun init_view() {
        itemView = findViewById(R.id.itemView)
        itemView.setHasFixedSize(true)
        itemView.layoutManager = GridLayoutManager(this, 2)
    }
}