package at.ac.fhcampuswien.corostats.stats

import androidx.lifecycle.LiveData
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsInterface
import at.ac.fhcampuswien.corostats.data.repository.CoronaVirusStatsNetworkDataSource
import at.ac.fhcampuswien.corostats.data.repository.NetworkState
import at.ac.fhcampuswien.corostats.data.vo.GeneralCases
import io.reactivex.disposables.CompositeDisposable

class GeneralCasesRepository (private val apiService: CoronaVirusStatsInterface) {
    lateinit var coronaVirusStatsNetworkDataSource: CoronaVirusStatsNetworkDataSource

    fun fetchGeneralCases(compositeDisposable: CompositeDisposable) : LiveData<GeneralCases>{
        coronaVirusStatsNetworkDataSource =
            CoronaVirusStatsNetworkDataSource(
                apiService,
                compositeDisposable
            )
        coronaVirusStatsNetworkDataSource.fetchCoronaVirusStats()
        return coronaVirusStatsNetworkDataSource.downloadedCoronaVirusStats
    }

    fun getGeneralStatsNetworkState(): LiveData<NetworkState> {
        return coronaVirusStatsNetworkDataSource.networkState
    }
}