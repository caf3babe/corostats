package at.ac.fhcampuswien.corostats.data.api

import at.ac.fhcampuswien.corostats.data.vo.GeneralCases
import io.reactivex.Single
import retrofit2.http.GET

interface CoronaVirusStatsInterface {

    // https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats
    // https://corona-virus-stats.herokuapp.com/api/v1/cases/countries-search

    @GET("https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats")
    fun getGeneralCases(): Single<GeneralCases>


}