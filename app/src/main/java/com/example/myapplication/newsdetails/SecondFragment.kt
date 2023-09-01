package com.example.myapplication.newsdetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.Result
import com.example.myapplication.topnews.RvAdapter
import com.example.myapplication.topnews.TopNewsViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newsViewModelViewModel by viewModels<NewsDetailsViewModel> { viewModelFactory }

    private val topNewsViewModel by viewModels<TopNewsViewModel> { viewModelFactory }

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
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("JOE_TAG newsViewModelViewModel : ${newsViewModelViewModel.toString()}")

        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/

        // load data to language list
        loadLanguage()

        // create layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

        binding.rvList.layoutManager = layoutManager
        // initialize the adapter,
        // and pass the required argument
        rvAdapter = RvAdapter(newsList)

        // attach adapter to the recycler view
        binding.rvList.adapter = rvAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadLanguage() {
       /* newsList = listOf(
            Language("Java" , "Exp : 3 years"),
            Language("Kotlin" , "Exp : 2 years"),
            Language("Python" , "Exp : 4 years"),
            Language("JavaScript" , "Exp : 6 years"),
            Language("PHP" , "Exp : 1 years"),
            Language("CPP" , "Exp : 8 years"),
        )*/

        topNewsViewModel.getTopNews()

        topNewsViewModel.response.observe(viewLifecycleOwner){ response ->
            when(response) {
                is NetworkResult.Success -> {
                    println("Joe_Tag in VIEW Success : ${response.data.copyright}")
                    newsList = response.data.results
                    rvAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Error -> {
                    println("Joe_Tag in VIEW Error : ${response.message}")
                    Toast.makeText(
                        activity,
                        "Error : ${response.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    println("Joe_Tag in VIEW LOADING SCREEN ")
                }
                is NetworkResult.Exception -> {
                    println("Joe_Tag in VIEW Exception ${response.e.message} ")
                    Toast.makeText(
                        activity,
                        "Exception: ${response.e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}