package com.example.hackathon.feature.selectTopic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R

class AdapterSelectTopic(private val topics: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<AdapterSelectTopic.TopicViewHolder>() {

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicTextView: TextView = itemView.findViewById(R.id.topicTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics[position]
        holder.topicTextView.text = topic

        holder.itemView.setOnClickListener {
            onItemClick(topic)
        }
    }

    override fun getItemCount(): Int {
        return topics.size
    }
}
