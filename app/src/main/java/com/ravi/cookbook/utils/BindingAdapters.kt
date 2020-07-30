package com.ravi.cookbook.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ravi.cookbook.R
import com.ravi.cookbook.utils.extension.getParentActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, imageUrl: String?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        Log.d("TAG", "loadImage: "+ imageUrl)
        imageUrl?.let {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_solid_white)
                .error(R.drawable.ic_solid_white)
                .into(view)
        }
/*
        Picasso.get()
            .load(imageUrl)
            .into(view, object : Callback {
                override fun onSuccess() {
                    Log.d("TAG", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("TAG", "error "+ e?.message)
                }
            })
*/
    }
}