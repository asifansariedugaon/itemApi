package com.example.itemapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostItemActivity : AppCompatActivity() {

    lateinit var postName:EditText
    lateinit var postDesc:EditText
    lateinit var postCurrency:EditText
    lateinit var postAmount:EditText
    lateinit var postBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_item)

        postName = findViewById(R.id.postName_editText)
        postDesc = findViewById(R.id.postDescription_editText)
        postCurrency = findViewById(R.id.postCurrency_editText)
        postAmount = findViewById(R.id.postAmount_editText)
        postBtn = findViewById(R.id.postBtn_button)

        postBtn.setOnClickListener {
            postItem()
        }

    }

    @SuppressLint("CheckResult")
    private fun postItem() {
        val name = postName.text.toString()
        val desc = postDesc.text.toString()
        val amount = postAmount.text.toString().toInt()
//        val currency = postCurrency.text.toString()
        val postData = PostItemModel(name,amount,desc,"INR")

        val apiKey = "rzp_test_IHo8oE7yKPbPb7"
        val apiPassword = "2kvUwgEIB8ROWYfYjuRIXTQz"

        val authHeader = "Basic " + Base64.encodeToString("$apiKey:$apiPassword".toByteArray(),Base64.NO_WRAP)
        val headerMap =HashMap<String, String>()
        headerMap["Authorization"] = authHeader

        ItemApiResponse.createRetrofit().postItems(headerMap,postData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                finish()
            }
//            .subscribe({result ->
//                // Success block
//                Toast.makeText(this, " Item added", Toast.LENGTH_SHORT).show()
//                finish()
//            }, { error ->
//                // Error block
//                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
//                Log.e("API Error", "Error updating customer item", error)
//
//            })
    }
}