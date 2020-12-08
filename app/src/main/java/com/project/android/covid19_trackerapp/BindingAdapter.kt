package com.project.android.covid19_trackerapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.android.covid19_trackerapp.network.DatabaseModel
import com.project.android.covid19_trackerapp.screens.affectedCountries.AffectedCountriesViewModel.CountryApiStatus
import com.project.android.covid19_trackerapp.screens.affectedCountries.CountryListAdapter


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<DatabaseModel>?
) {
    val adapter = recyclerView.adapter as CountryListAdapter
    adapter.submitList(data)
}

@BindingAdapter("countryApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: CountryApiStatus?
) {
    when (status) {
        CountryApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CountryApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_terrain_24)
        }
        CountryApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
        }
    }
}