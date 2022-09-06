package com.example.singleuserretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singleuserretrofit.Adapter.RecyclerAdapter
import com.example.singleuserretrofit.Model.User
import com.example.singleuserretrofit.R
import com.example.singleuserretrofit.Service.UserAPI
import com.example.singleuserretrofit.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var user: User
    lateinit var recyclerAdapter: RecyclerAdapter
    private val BASE_URL= "https://reqres.in/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable = CompositeDisposable()
       binding.recyclerview.layoutManager= LinearLayoutManager(this)
        loadData()
    }

    private fun loadData() {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            //.create(UserAPI::class.java)

        val service = retrofit.create(UserAPI::class.java)
        val call = service.getData()

        call.enqueue(object :Callback<User>{

            override fun onResponse(call: Call<User>,response: Response<User>) {
                if (response.isSuccessful){
                    response.body().let {
                        user = it!!
                        recyclerAdapter = RecyclerAdapter(it)
                        binding.recyclerview.adapter = recyclerAdapter
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


        /*
        compositeDisposable.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{userlist->ResponseHandler(userlist)}
        )

         */
    }

    /*
    private fun ResponseHandler(userlist: List<User>?) {
        userList = ArrayList(userlist)
        userlist.let {
            RecyclerAdapter = RecyclerAdapter(userList)
            RecyclerView.adapter = RecyclerAdapter
        }

    }

     */
    /*
    private fun ResponseHandler(userlist: List<User>){
       userList = ArrayList(userlist)
        userlist.let {
            RecyclerAdapter = RecyclerAdapter(userList)
            RecyclerView.adapter = RecyclerAdapter
        }

    }

     */
}