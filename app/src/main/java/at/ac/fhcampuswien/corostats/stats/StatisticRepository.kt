package at.ac.fhcampuswien.corostats.stats

import androidx.lifecycle.LiveData
import at.ac.fhcampuswien.corostats.data.api.CvsInterface
import at.ac.fhcampuswien.corostats.data.repository.CvsNetworkDataSource
import at.ac.fhcampuswien.corostats.data.repository.NetworkState
import at.ac.fhcampuswien.corostats.data.vo.StatsResponse
import io.reactivex.disposables.CompositeDisposable

class StatisticRepository (private val apiService: CvsInterface) {
    lateinit var cvsNetworkDataSource: CvsNetworkDataSource

    fun fetchGeneralCases(compositeDisposable: CompositeDisposable) : LiveData<StatsResponse>{
        cvsNetworkDataSource =
            CvsNetworkDataSource(
                apiService,
                compositeDisposable
            )
        cvsNetworkDataSource.fetchCoronaVirusStats()
        return cvsNetworkDataSource.downloadedCoronaVirusStats
    }

    fun getGeneralStatsNetworkState(): LiveData<NetworkState> {
        return cvsNetworkDataSource.networkState
    }
}