package at.ac.fhcampuswien.corostats.stats;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.corostats.data.repository.NetworkState
import at.ac.fhcampuswien.corostats.data.vo.StatsResponse
import io.reactivex.disposables.CompositeDisposable

class StatisticViewModel (private val statisticRepository : StatisticRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val statsResponse : LiveData<StatsResponse> by lazy {
        statisticRepository.fetchGeneralCases(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        statisticRepository.getGeneralStatsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
