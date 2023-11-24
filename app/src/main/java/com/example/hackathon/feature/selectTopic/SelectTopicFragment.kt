package com.example.hackathon.feature.selectTopic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.R
import com.example.hackathon.databinding.SelectTopicBinding
import com.example.hackathon.domain.UITopic


class SelectTopicFragment : Fragment() {
    lateinit var binding: SelectTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SelectTopicBinding.inflate(layoutInflater, container, false)
        val adapter = TaskAdapter()
        binding.viewPager2.adapter = adapter
        adapter.submitList(listOf<UITopic>(UITopic()))
        return binding.root
    }
}