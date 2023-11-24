package com.example.hackathon.feature.selectTopic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.databinding.SelectTopicBinding
import com.example.hackathon.domain.UITopic

class TaskAdapter(

) : ListAdapter<UITopic, TaskListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val topic: UITopic = getItem(position)
        val binding = holder.binding
        val context = binding.root.context
        with(binding) {
            topicImageView.setImageResource(topic.imageRes)
            topicTitle.text = topic.title
            topicText.text = topic.text
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UITopic>() {
    override fun areItemsTheSame(oldItem: UITopic, newItem: UITopic): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: UITopic, newItem: UITopic): Boolean {
        return oldItem == newItem
    }
}

class TaskListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.select_topic, parent, false)
) {
    val binding: SelectTopicBinding = SelectTopicBinding.bind(itemView)
}