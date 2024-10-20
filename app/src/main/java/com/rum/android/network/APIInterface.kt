package com.rum.android.network

import com.rum.android.models.Customer
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("customer/all")
    fun getAllCustomers(): Call<List<Customer>>

   // @GET("product/all")
   // fun getAllProducts(): Call<List<Product>>

    @POST("customer/add")
    fun addCustomer(@Body customer: Customer): Call<Customer>

  //  @POST("product/add")
  //  fun addProduct(@Body product: Product): Call<Product>

    @PUT("customer/edit")
    fun editCustomer(@Body customer: Customer): Call<Customer>

  //  @PUT("product/edit")
    // fun editProduct(@Body product: Product): Call<Product>

    @DELETE("customer/delete/{id}")
    fun deleteCustomer(@Path("id") id: Int): Call<Void>

  //  @DELETE("product/delete/{id}")
 //   fun deleteProduct(@Path("id") id: Int): Call<Void>
}
