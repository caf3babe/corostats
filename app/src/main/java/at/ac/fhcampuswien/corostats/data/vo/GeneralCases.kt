package at.ac.fhcampuswien.corostats.data.vo


import com.google.gson.annotations.SerializedName

data class GeneralCases(
    @SerializedName("data")
    val `data`: GeneralCasesData,
    @SerializedName("status")
    val status: String
)