package at.ac.fhcampuswien.corostats.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import at.ac.fhcampuswien.corostats.R
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsClient
import at.ac.fhcampuswien.corostats.data.api.CvsInterface
import at.ac.fhcampuswien.corostats.data.vo.StatisticEntry
import at.ac.fhcampuswien.corostats.data.vo.StatsResponse
import kotlinx.android.synthetic.main.fragment_stats.*


class StatisticFragment : Fragment() {

    private lateinit var viewModel: StatisticViewModel
    private lateinit var statisticRepository: StatisticRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_stats, container, false)
        val apiService : CvsInterface = CoronaVirusStatsClient.getClient()
        statisticRepository = StatisticRepository(apiService)
        viewModel = getViewModel()
        viewModel.statsResponse.observe(viewLifecycleOwner, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer{
            //TODO("Not yet implemented ")
        })
        // Inflate the layout for this fragment
        return view
    }

    private fun bindUI(statsResponse: StatsResponse?) {
        if (statsResponse != null) {
            val activeCasesCriticalPercentage =
                StatisticEntry()
            activeCasesCriticalPercentage.name = "Prozentsatz kritischer aktiven Fälle"
            activeCasesCriticalPercentage.value = statsResponse.data.activeCasesCriticalPercentage

            val activeCasesMildPercentage =
                StatisticEntry()
            activeCasesMildPercentage.name = "Prozentsatz milder aktiven Fälle"
            activeCasesMildPercentage.value = statsResponse.data.activeCasesMildPercentage

            val casesWithOutcome =
                StatisticEntry()
            casesWithOutcome.name = "Fälle ohne Ausbruch"
            casesWithOutcome.value = statsResponse.data.casesWithOutcome

            val closedCasesDeathPercentage =
                StatisticEntry()
            closedCasesDeathPercentage.name = "Prozentsatz Todesfälle"
            closedCasesDeathPercentage.value = statsResponse.data.closedCasesDeathPercentage

            val closedCasesRecoveredPercentage =
                StatisticEntry()
            closedCasesRecoveredPercentage.name = "Prozentsatz Genesungsfälle"
            closedCasesRecoveredPercentage.value = statsResponse.data.closedCasesRecoveredPercentage

            val criticalConditionActiveCases =
                StatisticEntry()
            criticalConditionActiveCases.name = "Aktive Fälle in kritischen Zustand"
            criticalConditionActiveCases.value = statsResponse.data.criticalConditionActiveCases

            val currentlyInfected =
                StatisticEntry()
            currentlyInfected.name = "Aktuell Infizierte"
            currentlyInfected.value = statsResponse.data.currentlyInfected

            val deathCases =
                StatisticEntry()
            deathCases.name = "Todesfälle"
            deathCases.value = statsResponse.data.deathCases

            val deathClosedCases =
                StatisticEntry()
            deathClosedCases.name = "Geschlossene Todesfälle"
            deathClosedCases.value = statsResponse.data.deathClosedCases

            val generalDeathRate =
                StatisticEntry()
            generalDeathRate.name = "Allgemeine Todesrate"
            generalDeathRate.value = statsResponse.data.generalDeathRate

            val lastUpdate =
                StatisticEntry()
            lastUpdate.name = "Letztes Update"
            lastUpdate.value = statsResponse.data.lastUpdate

            val mildConditionActiveCases =
                StatisticEntry()
            mildConditionActiveCases.name = "Aktive Fälle in milden Zustand"
            mildConditionActiveCases.value = statsResponse.data.mildConditionActiveCases

            val recoveredClosedCases =
                StatisticEntry()
            recoveredClosedCases.name = "Geschlossene Genesungsfälle"
            recoveredClosedCases.value = statsResponse.data.recoveredClosedCases

            val recoveryCases =
                StatisticEntry()
            recoveryCases.name = "Genesungsfälle"
            recoveryCases.value = statsResponse.data.recoveryCases

            val totalCases =
                StatisticEntry()
            totalCases.name = "Gesamtanzahl Fälle"
            totalCases.value = statsResponse.data.totalCases

            val statsEntryAdapter = StatisticEntryAdapter()
            stats_list.adapter = statsEntryAdapter
            statsEntryAdapter.data = listOf(activeCasesCriticalPercentage, activeCasesMildPercentage, casesWithOutcome, closedCasesDeathPercentage, closedCasesRecoveredPercentage, criticalConditionActiveCases, currentlyInfected, deathCases, deathClosedCases, generalDeathRate, lastUpdate, mildConditionActiveCases, recoveredClosedCases, recoveryCases, totalCases)

        }
    }

    private fun getViewModel(): StatisticViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                StatisticViewModel(statisticRepository) as T
        })[StatisticViewModel::class.java]

    }
}
