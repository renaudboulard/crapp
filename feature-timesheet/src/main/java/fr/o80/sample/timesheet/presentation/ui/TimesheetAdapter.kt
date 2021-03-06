package fr.o80.sample.timesheet.presentation.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.usecase.model.EntryDiff
import fr.o80.sample.timesheet.usecase.model.EntryViewModel

/**
 * @author Olivier Perez
 */
class TimesheetAdapter(
        val onClick: (EntryViewModel) -> Unit,
        val onTimeAdded: (EntryViewModel) -> Unit,
        val onTimeRemoved: (EntryViewModel) -> Unit
) : RecyclerView.Adapter<TimesheetAdapter.EntryViewHolder>() {

    private var entries: List<EntryViewModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder =
            EntryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_timesheet, parent, false))

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val timeEntry = entries[position]
        holder.bind(timeEntry)
    }

    override fun getItemCount(): Int
            = entries.size

    fun setEntries(entries: List<EntryViewModel>) {
        val diffResult = DiffUtil.calculateDiff(EntryDiff(this.entries, entries), true)
        this.entries = entries
        diffResult.dispatchUpdatesTo(this)
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val projectName: TextView = itemView.findViewById(R.id.project_name) as TextView
        val projectCode: TextView = itemView.findViewById(R.id.project_code) as TextView
        val addTime: TextView = itemView.findViewById(R.id.add_time) as TextView
        val removeTime: TextView = itemView.findViewById(R.id.remove_time) as TextView

        fun bind(timeEntry: EntryViewModel) {
            with(timeEntry) {
                projectName.text = label
                projectCode.text = code
                addTime.text = itemView.context.getString(R.string.hours, hours)
            }
            itemView.setOnClickListener { onClick(timeEntry) }
            addTime.setOnClickListener { onTimeAdded(timeEntry) }
            removeTime.setOnClickListener { onTimeRemoved(timeEntry) }
        }
    }
}
