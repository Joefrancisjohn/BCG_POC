package com.example.myapplication.topnews

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.TestClass
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.models.NetworkResult
import com.google.gson.Gson
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var testval : TestClass

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val topNewsViewModel by viewModels<TopNewsViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        println("Joe_Tag testval 1st frag = ${testval.testVar}")
        testval.testVar = 9

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        topNewsViewModel.getTopNews()

        topNewsViewModel.response.observe(viewLifecycleOwner){ response ->
            when(response) {
                is NetworkResult.Success -> {
                    println("Joe_Tag in VIEW Success : ${response.data.copyright}")
                    binding.textviewFirst.text = response.data.copyright
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


   /* private fun getUserList() {
        val response = apiInterface.getTopStories()
        lifecycleScope.launchWhenCreated {
            try {

                if (response.isSuccessful()) {
                    var json = Gson().toJson(response.body())
                    if (response.body()?.results?.size!! <= 0) {
                        Toast.makeText(
                            activity,
                            "No Data ",
                            Toast.LENGTH_LONG
                        ).show()
                        println("Joe_Tag FETCH ERROR HAPPENED SIZE ZERO")
                    } else {
                        val result = response.body()?.copyright
                        binding.textviewFirst.text = result
                    }

                    //new
                    *//* if(response?.body()!!.support.text.contains("Harshita")){
                         Toast.makeText(
                             this@MainActivity,
                             "Hello Retrofit",
                             Toast.LENGTH_LONG
                         ).show()
                     }*//*

                    // var getNEsteddata=response.body().data.get(0).suport.url

                } else {
                    Toast.makeText(
                        activity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                //Ex.localizedMessage?.let { Log.e("Error", it) }
                Ex.localizedMessage?.let { println("Joe_Tag FETCH Exception HAPPENED  $it") }
            }
        }

    }*/
}