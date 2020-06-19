package at.ac.fhcampuswien.corostats.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import at.ac.fhcampuswien.corostats.data.api.CvsInterface
import at.ac.fhcampuswien.corostats.data.vo.StatsResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class CvsNetworkDataSource(private val apiService: CvsInterface, private val compositeDisposable: CompositeDisposable) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState


    private val _downloadedCoronaVirusStats = MutableLiveData<StatsResponse>()
    val downloadedCoronaVirusStats: LiveData<StatsResponse>
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