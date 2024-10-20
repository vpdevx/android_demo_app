package com.rum.android.network

import okhttp3.OkHttpClient
import com.datadog.android.okhttp.DatadogInterceptor
import com.datadog.android.okhttp.DatadogEventListener
import com.datadog.android.okhttp.trace.TracingInterceptor
import com.datadog.android.trace.TracingHeaderType

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private var retrofit: Retrofit? = null


    val tracedHosts = mapOf("lab4ever.xyz" to setOf(TracingHeaderType.DATADOG,
        TracingHeaderType.TRACECONTEXT))

    fun getClient(): Retrofit {
        if (retrofit == null) {
            // Criar o OkHttpClient com os interceptors do Datadog

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(DatadogInterceptor.Builder(tracedHosts).build())
                .addNetworkInterceptor(TracingInterceptor.Builder(tracedHosts).build())
                .eventListenerFactory(DatadogEventListener.Factory())
                .build()

            // Configurar o Retrofit com o OkHttpClient
            retrofit = Retrofit.Builder()
                .baseUrl("https://api-rum-teste.k8s.lab4ever.xyz/")
                .client(okHttpClient)  // Adiciona o OkHttpClient configurado
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }
}

