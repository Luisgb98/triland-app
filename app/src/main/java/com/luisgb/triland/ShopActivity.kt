package com.luisgb.triland

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shop.*
import retrofit2.Call
import retrofit2.Response

class ShopActivity : AppCompatActivity() {
    private lateinit var apiService: APIService
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shop)

        setSupportActionBar(toolbar)
        apiService = APIConfig.getRetrofitClient(this).create(APIService::class.java)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.blueTriland))

        swipeRefreshLayout.isRefreshing = true

        // assign a layout manager to the recycler view
        products_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        getProducts()

    }


    fun getProducts() {
        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                print(t.message)
                Log.d("Data error", t!!.message!!)
                Toast.makeText(this@ShopActivity, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                swipeRefreshLayout.isRefreshing = false
                products = response.body()!!

                productAdapter = ProductAdapter(this@ShopActivity, products)

                products_recyclerview.adapter = productAdapter
                productAdapter.notifyDataSetChanged()

            }

        })
    }

}