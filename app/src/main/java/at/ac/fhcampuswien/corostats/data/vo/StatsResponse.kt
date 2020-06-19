package at.ac.fhcampuswien.corostats.data.vo


import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("data")
    val `data`: Statistic
)
