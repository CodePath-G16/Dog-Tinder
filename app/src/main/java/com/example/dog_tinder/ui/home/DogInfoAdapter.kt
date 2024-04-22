import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_tinder.R
import com.example.dog_tinder.ui.home.HomeFragment

class DogInfoAdapter(private val dogInfoList: List<HomeFragment.DogInfo>) : RecyclerView.Adapter<DogInfoAdapter.ViewHolder>() {

    // Define a callback interface for long-click events
    interface OnDogImageLongClickListener {
        fun onLongClick(breedId: String)
    }

    private var longClickListener: OnDogImageLongClickListener? = null

    fun setOnDogImageLongClickListener(listener: OnDogImageLongClickListener) {
        longClickListener = listener

        Log.d("GOING TO PROFILE PAGE", "navigate")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog_info, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDogInfo = dogInfoList[position]
        holder.bind(currentDogInfo)

        // Set a long click listener on the dog image view
        holder.dogImageView.setOnLongClickListener {
            longClickListener?.onLongClick(currentDogInfo.id)
            true // Consume the long click event

        }

    }

    override fun getItemCount(): Int {
        return dogInfoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)
        private val dogWeight: TextView = itemView.findViewById(R.id.weight)
        private val dogHeight: TextView = itemView.findViewById(R.id.height)
        val dogImageView: ImageView = itemView.findViewById(R.id.dogImg)

        fun bind(currentDogInfo: HomeFragment.DogInfo) {
            Glide.with(itemView)
                .load(currentDogInfo.url)
                .centerCrop()
                .into(dogImageView)

            breedNameTextView.text = currentDogInfo.breedName
            dogWeight.text = currentDogInfo.weight
            dogHeight.text = currentDogInfo.height
        }
    }
}
