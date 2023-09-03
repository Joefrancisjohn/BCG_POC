package com.example.myapplication.topnews

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNewsListBinding
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.Result
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var rvAdapter: RvAdapter



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val topNewsViewModel by viewModels<TopNewsViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvList.layoutManager = layoutManager

        println("JOE_TAG 1st frag newsViewModelViewModel : ${topNewsViewModel.toString()}")

        topNewsViewModel.getTopNews()

        topNewsViewModel.response.observe(viewLifecycleOwner){ response ->
            binding.progressBar.visibility = View.GONE
            when(response) {
                is NetworkResult.Success -> {
                   loadRvWithNews(response.data.results)
                    topNewsViewModel.tempResults = response.data.results
                }
                is NetworkResult.Error -> {
                    println("Joe_Tag in VIEW Error : ${response.message}")
                    Toast.makeText(
                        activity,
                        "Error : ${response.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.tvErrorMsg.text = response.message
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Exception -> {
                    println("Joe_Tag in VIEW Exception ${response.e.message} ")
                    binding.tvErrorMsg.text = response.e.message
                    Toast.makeText(
                        activity,
                        "Exception: ${response.e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun loadRvWithNews(results: List<Result>) {
        rvAdapter = RvAdapter(this.requireContext(), results)
        binding.rvList.adapter = rvAdapter
        binding.rvList.visibility = View.VISIBLE


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}