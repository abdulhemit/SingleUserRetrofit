package com.example.singleuserretrofit.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.singleuserretrofit.Model.User
import com.example.singleuserretrofit.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso

class RecyclerAdapter(var user: User):RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        Picasso.get().load(user.data.avatar).into(holder.binding.image)
        holder.binding.email.setText(user.data.email)
        holder.binding.fistName.setText(user.data.first_name + " ")
        holder.binding.lastName.setText(user.data.last_name)
        holder.binding.supportText.setText(user.support.text)
    }

    override fun getItemCount(): Int {
        return 1
    }
}