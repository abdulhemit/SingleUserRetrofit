package com.example.singleuserretrofit.Service

import com.example.singleuserretrofit.Model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface UserAPI {

    @GET("/api/users/2")
    //fun getData():Observable<List<User>>
    fun getData():Call<User>
}