package com.example.dog_tinder.ui.home

import DogInfoAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dog_tinder.databinding.FragmentHomeBinding
import android.util.Log
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.dog_tinder.R
import com.example.dog_tinder.ui.notifications.NotificationsFragment
import okhttp3.Headers
import org.json.JSONException


class HomeFragment : Fragment() {

    //USe FragmentHomeBinding for view binding
    private var _binding: FragmentHomeBinding? = null
    private lateinit var dogInfoAdapter: DogInfoAdapter
    private val dogInfoList = mutableListOf<DogInfo>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        //Inflate the layout and initialize the binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        dogInfoAdapter = DogInfoAdapter(dogInfoList)
        recyclerView.adapter = dogInfoAdapter


        //VerticalView
        /* val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
         */

        // Use PagerSnapHelper so that it automatically fixes into place
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)



        // Observe the dogInfoList LiveData from HomeViewModel
        homeViewModel.dogInfoList.observe(viewLifecycleOwner) { dogInfoList ->
            // Update the adapter with the new dogInfoList
            this.dogInfoList.clear()
            this.dogInfoList.addAll(dogInfoList)
            dogInfoAdapter.notifyDataSetChanged()
        }

        setupLongClickHandling()

        getDogInfo()
        return root
    }

    private fun setupLongClickHandling() {
        dogInfoAdapter.setOnDogImageLongClickListener(object : DogInfoAdapter.OnDogImageLongClickListener {
            override fun onLongClick(breedId: String) {
                navigateToNotificationsFragment(breedId)
            }
        })
    }



    // to pass the breed id to notifications fragment aka dog profile
    // Inside HomeFragment class
    private fun navigateToNotificationsFragment(breedId: String) {
        // Create a bundle to pass the breedId to the NotificationsFragment
        val bundle = Bundle()
        bundle.putString("BREED_ID", breedId)

        // Log the contents of the bundle
        for (key in bundle.keySet()) {
            val value = bundle.get(key)
            Log.d("IN HOME Bundle Contents", "$key: $value")
        }

        // Get the NavController from the Fragment's view
        val navController = view?.findNavController()

        // Pop the back stack to remove any existing instances of NotificationsFragment
        navController?.popBackStack()

        // Navigate to the NotificationsFragment with the bundle
        navController?.navigate(R.id.navigation_notifications, bundle)
    }


    data class DogInfo(
        val breeds: List<Breed>,
        val id: String,
        val url: String,
        val weight: String,
        val height: String,
        val breedName: String,
        val bredFor: String,
        val breedGroup: String

    ) {

    }

    data class Breed(
        val weight: String,
        val height: String,
        val id: Int,
        val name: String,
        val bred_for: String,
        val breed_group: String,
        val life_span: String,
        val temperament: String,
        val reference_image_id: String
    )

    data class Weight(
        val imperial: String,
        val metric: String
    )

    data class Height(
        val imperial: String,
        val metric: String
    )

    private fun getDogInfo() {
        val client = AsyncHttpClient()
        val apiKey = "live_YfLcN5wasmrJjW4EFSjbhBFZvqUxTGRMYAYCDl68ZfmJs7Pk06jGE3T7hsmSUJh6"
        val url = "https://api.thedogapi.com/v1/images/search?api_key=$apiKey&limit=25&has_breeds=1"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e("Dog Error", "Failed to fetch dog info: ${throwable?.message}")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, response: JSON?) {
                try {
                    val jsonArray = response?.jsonArray
                    jsonArray?.let {
                        for (i in 0 until it.length()) {
                            val dog = it.getJSONObject(i)
                            val breedsArray = dog.getJSONArray("breeds")
                            if (breedsArray.length() > 0) {
                                val breedObject = breedsArray.getJSONObject(0)
                                val breedName = breedObject.getString("name")
                                val bredFor = breedObject.optString("bred_for", "Unknown")
                                val breedGroup = breedObject.optString("breed_group", "Unknown")
                                val height = breedObject.getJSONObject("height").getString("metric")
                                val weight = breedObject.getJSONObject("weight").getString("metric")
                                val url = dog.getString("url")

                                // Create a DogInfo object and add it to the list
                                val dogInfo = DogInfo(
                                    breeds = emptyList(),
                                    id = dog.getString("id"),
                                    url = url,
                                    weight = weight,
                                    height = height,
                                    breedName = breedName,
                                    bredFor = bredFor,
                                    breedGroup = breedGroup
                                )
                                dogInfoList.add(dogInfo)
                            }
                        }
                        // Notify the adapter of the data change
                        dogInfoAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e("DogTinder", "Error parsing JSON: ${e.message}")
                }
            }

        })
    }

    override fun onDestroyView() {
        Log.d("HOME FRAG DESTROYED", "calling_onDestroy")
        super.onDestroyView()
        _binding = null
    }
}