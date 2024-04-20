package com.example.dog_tinder.ui.home

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
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return ViewHolder(itemView)
    }

    /*override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDogInfo = dogInfoList[position]
        holder.breedNameTextView.text = currentDogInfo.breeds.firstOrNull()?.name ?: "Unknown"
        holder.bredForTextView.text = currentDogInfo.breeds.firstOrNull()?.bred_for ?: "Unknown"
        holder.breedGroupTextView.text = currentDogInfo.breeds.firstOrNull()?.breed_group ?: "Unknown"
        val imageURL = getImageURLForReferenceId(breed?.reference_image_id)

    // Use Glide to load the image into the ImageView
        Glide.with(holder.itemView.context)
            .load(imageURL)
            .into(holder.dogImageView)

        // Bind more data as needed
        }
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDogInfo = dogInfoList[position]
        holder.bind(currentDogInfo)
    }


    override fun getItemCount(): Int {
        return dogInfoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)
        val bredForTextView: TextView = itemView.findViewById(R.id.bredForTextView)
        val breedGroupTextView: TextView = itemView.findViewById(R.id.breedGroupTextView)
        val dogImageView: ImageView = itemView.findViewById(R.id.dogImg)

        fun bind(currentDogInfo: HomeFragment.DogInfo) {
            Glide.with(itemView)
                .load(currentDogInfo.url)
                .centerCrop()
                .into(dogImageView)

            breedNameTextView.text = currentDogInfo.breeds.firstOrNull()?.name ?: "Unknown"
            bredForTextView.text = currentDogInfo.breeds.firstOrNull()?.bred_for ?: "Unknown"
            breedGroupTextView.text = currentDogInfo.breeds.firstOrNull()?.breed_group ?: "Unknown"
        }

    }

}



