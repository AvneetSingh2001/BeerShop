package com.avicodes.beershop.presentation.ui.landing

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
import com.avicodes.beershop.data.models.BeerItem
import com.avicodes.beershop.data.utils.Resource
import com.avicodes.beershop.databinding.FragmentLandingBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingFragment : Fragment() {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: LandingFragmentViewModelFactory

    lateinit var viewModel: LandingFragmentViewModel

    var randomBeer: BeerItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(requireActivity(), factory)[LandingFragmentViewModel::class.java]
        populateRandomBeer()

        binding.apply {
            cvRecommended.setOnClickListener {
                navigateToDetailsFragment()
            }
        }
    }

    fun populateRandomBeer() {
        viewModel.getRandomBeer()
        viewModel.randomBeer.observe(requireActivity(), Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    val data = resource.data
                    data?.let { beers ->
                        binding.run {
                            beers[0]?.let {
                                randomBeer = beers[0]
                                tvName.text = it.name
                                tvTagline.text = it.tagline
                                Glide.with(requireContext())
                                    .load(it.image_url)
                                    .into(ivBeers)
                            }

                        }
                    }
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    Log.e("Landing fragment Error", resource.message.toString())
                }
            }
        })
    }

    fun navigateToDetailsFragment() {
        randomBeer?.let {
            val action = LandingFragmentDirections.actionLandingFragmentToBeerDetailFragment(
                beerItem = randomBeer!!
            )
            requireView().findNavController().navigate(action)
        }

    }

}