package com.example.dog_tinder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_tinder.R

class DogInfoAdapter(private val dogInfoList: List<HomeFragment.DogInfo>) : RecyclerView.Adapter<DogInfoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)
        val bredForTextView: TextView = itemView.findViewById(R.id.bredForTextView)
        val breedGroupTextView: TextView = itemView.findViewById(R.id.breedGroupTextView)
        // Add more views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDogInfo = dogInfoList[position]
        holder.breedNameTextView.text = currentDogInfo.breeds.firstOrNull()?.name ?: "Unknown"
        holder.bredForTextView.text = currentDogInfo.breeds.firstOrNull()?.bred_for ?: "Unknown"
        holder.breedGroupTextView.text = currentDogInfo.breeds.firstOrNull()?.breed_group ?: "Unknown"
        // Bind more data as needed
    }

    override fun getItemCount(): Int {
        return dogInfoList.size
    }
}
