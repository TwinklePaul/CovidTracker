package com.project.android.covid19_trackerapp.network

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class CountryShortData(
    val country: String?,
    @Json(name = "flag")
    val flag: String?,
    val cases: Long,
    val todayCases: Int,
    val deaths: Long,
    val todayDeaths: Int,
    val recovered: Long,
    val todayRecovered: Int,
    val active: Long,
    val critical: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(flag)
        parcel.writeLong(cases)
        parcel.writeInt(todayCases)
        parcel.writeLong(deaths)
        parcel.writeInt(todayDeaths)
        parcel.writeLong(recovered)
        parcel.writeInt(todayRecovered)
        parcel.writeLong(active)
        parcel.writeLong(critical)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryShortData> {
        override fun createFromParcel(parcel: Parcel): CountryShortData {
            return CountryShortData(parcel)
        }

        override fun newArray(size: Int): Array<CountryShortData?> {
            return arrayOfNulls(size)
        }
    }
}