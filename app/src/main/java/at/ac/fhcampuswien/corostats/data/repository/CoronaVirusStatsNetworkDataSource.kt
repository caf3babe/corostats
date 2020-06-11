package at.ac.fhcampuswien.corostats.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsInterface
import at.ac.fhcampuswien.corostats.data.repository.NetworkState
import at.ac.fhcampuswien.corostats.data.vo.GeneralCases
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class CoronaVirusStatsNetworkDataSource(private val apiService: CoronaVirusStatsInterface, private val compositeDisposable: CompositeDisposable) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState


    private val _downloadedCoronaVirusStats = MutableLiveData<GeneralCases>()
    val downloadedCoronaVirusStats: LiveData<GeneralCases>
    get() = _downloadedCoronaVirusStats

    fun fetchCoronaVirusStats(){
        _networkState.postValue(NetworkState.LOADING)

        try{
            compositeDisposable.add(
                apiService.getGeneralCases().subscribeOn(Schedulers.io()).subscribe({
                    _downloadedCoronaVirusStats.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)

                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    Log.e("CoronaVirusStatsNetwork", it.message)
                })
            )
        } catch (e: Exception){
            Log.e("CoronaVirusStatsNetwork", e.message)
        }

    }
}