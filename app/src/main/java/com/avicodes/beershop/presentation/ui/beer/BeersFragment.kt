package com.avicodes.beershop.presentation.ui.beer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.databinding.FragmentBeersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BeersFragment : Fragment() {

    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: BeersFragmentViewModelFactory

    @Inject
    lateinit var beerAdapter: BeersAdapter

    lateinit var viewModel: BeersFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(requireActivity(), factory)[BeersFragmentViewModel::class.java]

        initRecyelerView()
        getBeers()

    }

    private fun initRecyelerView() {
        binding.rvBeers.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getBeers() {
        viewModel.getAllBeers(1)
        viewModel.beerList.observe(requireActivity(), Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    val data = resource.data
                    data?.let {
                        beerAdapter.differ.submitList(it.toList())
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    Log.e("Beers Fragment Error", resource.message.toString())
                }
            }
        })

        beerAdapter.setOnItemClickListener {
            val action = BeersFragmentDirections.actionBeersFragmentToBeerDetailFragment(beerItem = it)
            requireView().findNavController().navigate(action)
        }
    }

    private fun showProgressBar() {
        binding.progCons.visibility = View.VISIBLE
        binding.mainCons.visibility = View.GONE
    }


    private fun hideProgressBar() {
        binding.progCons.visibility = View.GONE
        binding.mainCons.visibility = View.VISIBLE
    }

}