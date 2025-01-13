package com.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.activties.MovieInteractionListener
import com.bumptech.glide.Glide
import com.data.MovieList
import com.example.myapplication.R
import com.example.myapplication.databinding.RowBinding


class CustomAdapter (private val movieList:List<MovieList>, private val listener:MovieInteractionListener) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val itemView= LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=movieList[position]
        holder.apply {
           binding.textview1.text= currentItem.title
         //  binding.textview2.text = currentItem.overview
            binding.voteAverage.text = "${currentItem.vote_average}"
            Glide.with(itemView.context)
                
                .load("https://image.tmdb.org/t/p/w500${currentItem.poster_path}")
                .into(binding.imageView)
          binding.root.setOnClickListener{listener.onClickItem(currentItem)}

        }
    }

    override fun getItemCount() = movieList.size
    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=RowBinding.bind(itemView)
    }


}


