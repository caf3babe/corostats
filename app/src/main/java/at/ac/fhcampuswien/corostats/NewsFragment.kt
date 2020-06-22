package at.ac.fhcampuswien.corostats

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import at.ac.fhcampuswien.corostats.data.api.CoronaVirusStatsClient
import at.ac.fhcampuswien.corostats.data.api.CvsInterface
import at.ac.fhcampuswien.corostats.data.vo.StatisticEntry
import at.ac.fhcampuswien.corostats.data.vo.StatsResponse
import at.ac.fhcampuswien.corostats.stats.StatisticRepository
import at.ac.fhcampuswien.corostats.stats.StatisticViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var statisticRepository: StatisticRepository
    private lateinit var viewModel: StatisticViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_news, container, false)
        val apiService: CvsInterface = CoronaVirusStatsClient.getClient()
        statisticRepository = StatisticRepository(apiService)
        viewModel = getViewModel()
        viewModel.statsResponse.observe(viewLifecycleOwner, Observer {
            setPieChart(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            //TODO("Not yet implemented ")
        })

        return view
    }

    private fun getViewModel(): StatisticViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                StatisticViewModel(statisticRepository) as T
        })[StatisticViewModel::class.java]

    }

    private fun setPieChart(statsResponse: StatsResponse?) {
        if (statsResponse != null) {
            val totalCases = StatisticEntry()
            val actualCases = StatisticEntry()
            val actualDeaths = StatisticEntry()

            totalCases.value = statsResponse.data.totalCases
            actualCases.value = statsResponse.data.currentlyInfected
            actualDeaths.value = statsResponse.data.deathCases

            val pieChartView = this.pieChartView
            pieChartView.isDrawHoleEnabled

            val entries = ArrayList<PieEntry>()

            entries.add(PieEntry((totalCases.value.replace(",", "").toFloat()), "Total Cases"))
            entries.add(
                PieEntry(
                    (actualCases.value.replace(",", "").toFloat()),
                    "Actual Infections"
                )
            )
            entries.add(PieEntry((actualDeaths.value.replace(",", "").toFloat()), "Actual Deaths"))
            val pieDataSet = PieDataSet(entries, "Aktuelle Zahlen")

            val colors = ArrayList<Int>()
            colors.add(Color.rgb(30, 144, 255))
            colors.add(Color.rgb(78, 238, 148))
            colors.add(Color.rgb(178, 34, 34))

            pieDataSet.setColors(colors)

            val data = PieData(pieDataSet)
            data.setValueFormatter(PercentFormatter())
            pieChartView.data = data // set the data and list of lables into chart

            pieChartView.animateY(5000)
        }


    }

}




