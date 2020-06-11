package at.ac.fhcampuswien.corostats.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import at.ac.fhcampuswien.corostats.R
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsClient
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsInterface
import at.ac.fhcampuswien.corostats.data.vo.GeneralCases


class StatsFragment : Fragment() {

    private lateinit var viewModel: GeneralCasesViewModel
    private lateinit var generalCasesRepository: GeneralCasesRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_stats, container, false)
        val apiService : CoronaVirusStatsInterface = CoronaVirusStatsClient.getClient()
        generalCasesRepository = GeneralCasesRepository(apiService)
        viewModel = getViewModel()
        viewModel.generalCases.observe(viewLifecycleOwner, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer{
            //TODO("Not yet implemented ")
        })
        // Inflate the layout for this fragment
        return view
    }

    private fun bindUI(generalCases: GeneralCases?) {
        if (generalCases != null) {
            val deathCasesTextView : TextView = requireView().findViewById<TextView>(
                R.id.deathCasesValue
            )
            deathCasesTextView.text = generalCases.data.deathCases

            val totalCasesTextView : TextView = requireView().findViewById<TextView>(
                R.id.totalCasesValue
            )
            totalCasesTextView.text = generalCases.data.totalCases
        }
    }

    private fun getViewModel(): GeneralCasesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                GeneralCasesViewModel(generalCasesRepository) as T
        })[GeneralCasesViewModel::class.java]

    }
}
