package com.newapp.fruitsy.view.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newapp.fruitsy.BR
import com.newapp.fruitsy.databinding.FruitListItemBinding
import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.view.fragments.FruitItemClickListener

class FruitListAdapter(
    private var listOfFruits: List<Fruit>,
    private val itemClickListener: FruitItemClickListener
) : RecyclerView.Adapter<FruitListAdapter.FruitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding =
            FruitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.bind(listOfFruits.get(position))
        holder.itemView.setOnClickListener {
            itemClickListener.onFruitItemClick(
                listOfFruits.get(
                    position
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return listOfFruits.size
    }

    public fun updateDataSet(updatedList: List<Fruit>) {
        listOfFruits = updatedList
        notifyDataSetChanged()
    }

    open class FruitViewHolder(private val binding: FruitListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit) {
            binding.setVariable(BR.fruit, fruit)
            binding.executePendingBindings()
        }
    }
}