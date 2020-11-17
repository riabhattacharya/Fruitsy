package com.newapp.fruitsy.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.newapp.fruitsy.appstats.FruitAppStats

open class BaseFragment : Fragment() {
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        FruitAppStats.stopAndLogDisplayTimer()
    }
}