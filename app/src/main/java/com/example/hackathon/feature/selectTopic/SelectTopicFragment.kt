package com.example.hackathon.feature.selectTopic


import com.example.hackathon.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectTopicFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterSelectTopic

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_topic, container, false)

        recyclerView = view.findViewById(R.id.topicRecyclerView)
        val topics = listOf("Орфоэпия", "Будет в будущем", "Будет в будущем")
        adapter = AdapterSelectTopic(topics) { selectedTopic ->
            findNavController().navigate(R.id.action_selectTopicFragment_to_exerciseFragment)
            // Обработка выбора темы
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }

    private fun navigateToNextFragment(selectedTopic: String) {

    }
}
