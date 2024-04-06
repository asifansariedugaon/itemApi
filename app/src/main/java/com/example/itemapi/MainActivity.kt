package com.example.itemapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var floatActionBtn: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var itemAdapter: ItemAdapter
    var itemList = ArrayList<Item>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatActionBtn = findViewById(R.id.addButton_floatingActionButton)
        recyclerView = findViewById(R.id.itemList_recyclerview)

        floatActionBtn.setOnClickListener {
            startActivity(Intent(this, PostItemActivity::class.java))
        }

    }
    @SuppressLint("CheckResult")
    fun getData(){
        val apikey = "rzp_test_IHo8oE7yKPbPb7"
        val apiPassword = "2kvUwgEIB8ROWYfYjuRIXTQz"

        val authHeader =
            "Basic " + Base64.encodeToString("$apikey:$apiPassword".toByteArray(), Base64.NO_WRAP)
        val hashMap = HashMap<String, String>()
        hashMap["Authorization"] = authHeader

        ItemApiResponse.createRetrofit().getItems(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                val allDAta = result.items
                itemList.clear()
                if (allDAta != null) {
                    itemList.addAll(allDAta)
                }
                Toast.makeText(this, "${result.count}", Toast.LENGTH_SHORT).show()
                itemAdapter = ItemAdapter(this, itemList)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = itemAdapter
            }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }
}