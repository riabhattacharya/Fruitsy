package com.newapp.fruitsy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.newapp.fruitsy.BR
import com.newapp.fruitsy.R
import com.newapp.fruitsy.appstats.FruitAppStats
import com.newapp.fruitsy.databinding.FragmentFruitDetailBinding

class FruitDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentFruitDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFruitDetailBinding.inflate(LayoutInflater.from(context), container, false)
        requireActivity().title = getString(R.string.fruit_details);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: FruitDetailsFragmentArgs by navArgs()
        val fruitDetail = safeArgs.fruitDetail
        binding.setVariable(BR.fruit, fruitDetail)
    }
}