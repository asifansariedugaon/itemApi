package com.example.itemapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ItemApiResponse {

        @GET("items")
        fun getItems(@HeaderMap header: HashMap<String, String>): Observable<ItemModel>

        @POST("items")
        fun postItems(@HeaderMap header: HashMap<String,String>, @Body post: PostItemModel): Observable<ItemModel>

companion object Factory{
    fun createRetrofit(): ItemApiResponse{
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.razorpay.com/v1/")
            .build()
            .create(ItemApiResponse::class.java)
        return retrofit
    }
}
}