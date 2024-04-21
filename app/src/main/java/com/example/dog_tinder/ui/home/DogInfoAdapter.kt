package com.example.dog_tinder.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_tinder.R
import com.bumptech.glide.Glide

class DogInfoAdapter(private val dogInfoList: List<HomeFragment.DogInfo>) : RecyclerView.Adapter<DogInfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog_info, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDogInfo = dogInfoList[position]
        //trying to debug why it is not showing the image
        Log.d("DogTinder", "Binding item at position $position, Dog Info: $currentDogInfo")
        holder.bind(currentDogInfo)
    }


    override fun getItemCount(): Int {
        return dogInfoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)
        private val bredForTextView: TextView = itemView.findViewById(R.id.bredForTextView)
        private val breedGroupTextView: TextView = itemView.findViewById(R.id.breedGroupTextView)
        private val dogImageView: ImageView = itemView.findViewById(R.id.dogImg)

        fun bind(currentDogInfo: HomeFragment.DogInfo) {
            Glide.with(itemView)
                .load(currentDogInfo.url)
                .centerCrop()
                .into(dogImageView)

            breedNameTextView.text = currentDogInfo.breedName
            bredForTextView.text = currentDogInfo.bredFor
            breedGroupTextView.text = currentDogInfo.breedGroup
        }

    }

}



