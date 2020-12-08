package com.project.android.covid19_trackerapp.screens.affectedCountries

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBinderMapperImpl
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.android.covid19_trackerapp.databinding.ListCountryFlagsBinding
import com.project.android.covid19_trackerapp.network.CountryShortData
import com.project.android.covid19_trackerapp.network.DatabaseModel
import java.sql.DatabaseMetaData
import java.util.*
import kotlin.collections.ArrayList


class CountryListAdapter(
    private var mLstUser: MutableList<DatabaseModel>, private val onClickListener: OnClickListener
) :
    ListAdapter<DatabaseModel, CountryListAdapter.CountryViewHolder>(DiffCallback), Filterable {

    var mFilteredList: MutableList<DatabaseModel> = ArrayList()
    var mOldUser: MutableList<DatabaseModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ListCountryFlagsBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)

        val data = CountryShortData(
            item.country,
            item.flag,
            item.cases,
            item.todayCases,
            item.deaths,
            item.todayDeaths,
            item.recovered,
            item.todayRecovered,
            item.active,
            item.critical
        )

        holder.bind(data)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DatabaseModel>() {
        override fun areItemsTheSame(oldItem: DatabaseModel, newItem: DatabaseModel): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: DatabaseModel, newItem: DatabaseModel): Boolean {
            return oldItem == newItem
        }

    }

    class CountryViewHolder(private var binding: ListCountryFlagsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryShortData) {
            binding.countryDataModel = item
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (CountryData: DatabaseModel) -> Unit) {
        fun onClick(CountryData: DatabaseModel) = clickListener(CountryData)

    }

    @Suppress("UNCHECKED_CAST")
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()
                mFilteredList.clear()

                if (charString.isBlank() || charString.isEmpty() || charString == "") {
                    mFilteredList = mOldUser
                    mLstUser = mOldUser
                } else {
                    val filterPattern =
                        charSequence.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                    val filteredList: MutableList<DatabaseModel> = ArrayList()
                    try {
                        mLstUser = mOldUser
                        for (item in 0..mLstUser.size) {
                            if (mLstUser[item].country.toLowerCase(Locale.ROOT)
                                    .contains(filterPattern)
                            ) {
                                filteredList.add(mLstUser[item])
                            }
                        }
                    } catch (e: Exception) {
                        Log.i("filter", "Error: $e mLstUser: $mLstUser")
                    }

                    mFilteredList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mFilteredList
               // Log.d("LISTCHECK", mFilteredList.toString())

                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                submitList(filterResults.values as MutableList<DatabaseModel>)
                Log.d("LISTCHECK",(filterResults.values as List<DatabaseModel>).toString())
                notifyDataSetChanged()
            }
        }
    }
}
