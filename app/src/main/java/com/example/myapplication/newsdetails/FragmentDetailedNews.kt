package com.example.myapplication.newsdetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.models.Result

import com.example.myapplication.topnews.RvAdapter
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentDetailedNews : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val args: FragmentDetailedNewsArgs by navArgs()


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newsViewModelViewModel by viewModels<NewsDetailsViewModel> { viewModelFactory }

    private lateinit var rvAdapter: RvAdapter
    private lateinit var newsList : List<Result>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        println("JOE_TAG args : ${args.selectedItem}")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitleMsg.text = args.detailsData.title
        binding.tvErrorMsg.text = args.detailsData.abstract
        Glide.with(this.requireContext())
            .load(args.detailsData.img_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            //.onlyRetrieveFromCache(true)
            .into(binding.ivNewsImg)

        println("JOE_TAG 2nd frag img_url : ${args.detailsData.img_url}")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}