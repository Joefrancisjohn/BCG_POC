package com.example.myapplication.topnews

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.NewsItemBinding
import com.example.myapplication.models.DataForDetails
import com.example.myapplication.models.Result


class RvAdapter(
    private val context: Context,
    var newsList: List<Result>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this

    inner class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var dataForDetails : DataForDetails

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var navController: NavController? = null
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(FitCenter(), RoundedCorners(16))
        with(holder){
            with(newsList[position]){
                binding.tvLangName.text = this.title
                binding.tvExp.text = this.published_date
                Glide.with(context)
                    .load(this.multimedia[0].url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .apply(requestOptions)
                    .onlyRetrieveFromCache(true)
                    .into(binding.ivNewsImg)
            }

            itemView.setOnClickListener {
                with(newsList[position]){
                    dataForDetails = DataForDetails(this.title , this.abstract, this.multimedia[0].url)
                }
                navController = Navigation.findNavController(itemView)
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(dataForDetails, position)
                navController!!.navigate(action)
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return newsList.size
    }
}
