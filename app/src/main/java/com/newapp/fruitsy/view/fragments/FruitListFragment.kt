package com.newapp.fruitsy.view.fragments

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.newapp.fruitsy.R
import com.newapp.fruitsy.appstats.FruitAppStats
import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.view.fragments.adapters.FruitListAdapter
import com.newapp.fruitsy.viewmodel.FruitViewModel
import kotlinx.android.synthetic.main.fragment_fruit_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FruitListFragment : BaseFragment(), FruitItemClickListener {
    private val fruitViewModel: FruitViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fruit_list, container, false)
        requireActivity().title = getString(R.string.app_name);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        initRecyclerView()
        initObserver()
        getListOfFruits()
    }

    private fun initObserver() {
        fruitViewModel.fruits.observe(viewLifecycleOwner, {
            hideProgressBar()
            swipe_refresh_layout.isRefreshing = false
            recycler_view_fruit.adapter = FruitListAdapter(it, this)
        })
    }

    private fun getListOfFruits() {
        fruitViewModel.getListOfFruits()
    }

    private fun hideProgressBar() {
        progress_spinner_fruit.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress_spinner_fruit.visibility = View.VISIBLE
    }

    private fun initRecyclerView() {
        recycler_view_fruit.layoutManager = LinearLayoutManager(this.context)
        recycler_view_fruit.setHasFixedSize(true)

        swipe_refresh_layout.setOnRefreshListener {
            fruitViewModel.refreshListOfFruits()
            }
            //TODO: Add Refresh Action on Action Bar
    }

    override fun onFruitItemClick(fruit: Fruit) {
        FruitAppStats.startDisplayTimer()
        val action = FruitListFragmentDirections.actionFruitListToDetail(fruit)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                fruitViewModel.refreshListOfFruits()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

interface FruitItemClickListener {
    fun onFruitItemClick(fruit: Fruit)
}