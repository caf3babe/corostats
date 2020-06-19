package at.ac.fhcampuswien.corostats.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhcampuswien.corostats.R
import at.ac.fhcampuswien.corostats.data.vo.StatisticEntry
import com.google.android.material.card.MaterialCardView

class StatisticEntryAdapter: RecyclerView.Adapter<StatisticEntryViewHolder>(){
    var data = listOf(StatisticEntry())
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticEntryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card, parent, false) as MaterialCardView
        return StatisticEntryViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: StatisticEntryViewHolder, position: Int) {
        val item = data[position]
        holder.bindData(item)
    }

}