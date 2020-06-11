package at.ac.fhcampuswien.corostats.stats;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.corostats.data.repository.NetworkState
import at.ac.fhcampuswien.corostats.data.vo.GeneralCases
import io.reactivex.disposables.CompositeDisposable

class GeneralCasesViewModel (private val generalCasesRepository : GeneralCasesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val generalCases : LiveData<GeneralCases> by lazy {
        generalCasesRepository.fetchGeneralCases(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        generalCasesRepository.getGeneralStatsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
