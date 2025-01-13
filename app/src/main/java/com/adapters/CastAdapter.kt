package com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.CastList
import com.example.myapplication.R
import com.example.myapplication.databinding.CastBinding


class CastAdapter (private val castList:List<CastList>,private val context:Context) : RecyclerView.Adapter<CastAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val itemView= LayoutInflater.from(parent.context).inflate(R.layout.cast,parent,false)
        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=castList[position]
        holder.apply {
           binding.castName.text= currentItem.name
            binding.characterName.text = currentItem.character
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${currentItem.profile_path}")
                .placeholder(R.drawable.person)
                .into(binding.profilePath)

        }
    }

    override fun getItemCount() = castList.size
    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
  val binding= CastBinding.bind(itemView)
    }


}


