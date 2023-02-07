package com.avicodes.beershop.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.avicodes.beershop.R
import com.avicodes.beershop.databinding.FragmentBeerDetailBinding
import com.bumptech.glide.Glide

class BeerDetailFragment : Fragment() {
    private var _binding : FragmentBeerDetailBinding? = null
    private val binding get() = _binding!!

    val args: BeerDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val beer = args.beerItem

        binding.apply {
            Glide.with(requireContext())
                .load(beer.image_url)
                .into(ivBeer)

            tvName.text = beer.name
            tvDesc.text = beer.description
            tvTips.text = beer.brewers_tips
        }
    }

}