package at.ac.fhcampuswien.corostats.stats

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhcampuswien.corostats.R
import at.ac.fhcampuswien.corostats.data.vo.StatisticEntry
import com.google.android.material.card.MaterialCardView

class StatisticEntryViewHolder(val materialCard: MaterialCardView): RecyclerView.ViewHolder(materialCard){
    val cardTitleTextView: TextView = itemView.findViewById(R.id.cardTitle)
    val cardValueTextView: TextView = itemView.findViewById(R.id.cardValue)

    fun bindData(statisticEntry: StatisticEntry){
        cardTitleTextView.text = statisticEntry.name
        cardValueTextView.text = statisticEntry.value
    }
}