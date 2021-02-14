package com.evangelidis.t_tnews.models.api

import com.evangelidis.t_tnews.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {
    companion object {

        val client: Retrofit
            get() {
                return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
    }
}
