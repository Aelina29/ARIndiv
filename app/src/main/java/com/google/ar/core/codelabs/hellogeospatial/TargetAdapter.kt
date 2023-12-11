package com.google.ar.core.codelabs.hellogeospatial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TargetAdapter(val onclick:(target:Target)->Unit, val onDelete:(target:Target)->Unit) :
    RecyclerView.Adapter<TargetAdapter.TargetViewHolder>() {
     var list: ArrayList<Target> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_target, parent, false)
        return TargetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TargetViewHolder, position: Int) {
        val target = list[position]
        holder.textViewTarget.text = "${target.name}"
        holder.textViewTarget.setOnClickListener {
            onclick(target)
        }
        holder.buttonDelete.setOnClickListener {
            onDelete(target)
        }
    }

    override fun getItemCount() = list.size

    class TargetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTarget: TextView = itemView.findViewById(R.id.textViewTarget)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
    }

}