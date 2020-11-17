package com.newapp.fruitsy.view.fragments.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.newapp.fruitsy.R

class DataBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("imageResource")
        public fun setImageResource(view: ImageView, imageResource: Int?) {
            if (imageResource == null) {
                view.setImageResource(R.drawable.ic_photo);
            } else {
                view.setImageResource(imageResource);
            }
        }
    }
}