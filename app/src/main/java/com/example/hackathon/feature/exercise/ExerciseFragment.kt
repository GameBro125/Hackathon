package com.example.hackathon.feature.exercise

import com.example.hackathon.domain.WordEmphasis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.databinding.FragmentExerciseBinding
import com.example.hackathon.domain.*
import com.google.android.material.chip.Chip



class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordList: List<WordEmphasis>
    private var currentWordIndex = 0
    private var errorCount = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // реализовать через бек
        wordList = testData
        binding.excNumber.text = "1"
        binding.excNumber.text = "0/${wordList.size}"
        // Добавляем чипам буквы
        setChipLetters()
        binding.nextButton.isInvisible = true
        // Устанавливаем обработчик для ChipGroup
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = group.findViewById<Chip>(checkedId)
            onChipSelected(selectedChip)
        }

        // Костыль. .
        for (i in 0 until binding.chipGroup.childCount) {
            val chip = binding.chipGroup.getChildAt(i) as? Chip
            chip?.setOnClickListener {
                onChipClicked(chip)
            }
        }

        // Добавляем обработчик для кнопки перехода к следующему слову
        binding.nextButton.setOnClickListener {
            goToNextWord()
            for (i in 0 until binding.chipGroup.childCount) {
                val chip = binding.chipGroup.getChildAt(i) as? Chip
                chip?.setOnClickListener {
                    onChipClicked(chip)
                }
            }
        }

    }
    private fun setChipLetters() {

        val currentWord = wordList[currentWordIndex].word



        for (i in currentWord.indices) {
            val chip = Chip(requireContext())
            chip.id = View.generateViewId()
            chip.text = currentWord[i].toString()
            chip.isClickable = true
            binding.chipGroup.addView(chip)
        }
    }

    private fun onChipSelected(selectedChip: Chip?) {
        binding.nextButton.isInvisible = true
    }

    private fun onChipClicked(clickedChip: Chip?) {
        clickedChip?.let {
            val selectedLetterIndex = binding.chipGroup.indexOfChild(clickedChip)
            val emphasisIndex = wordList[currentWordIndex].emphasisIndex

            if (selectedLetterIndex == emphasisIndex) {
                // Правильный выбор
                showToast("Верный выбор!")
                clickedChip.setChipBackgroundColorResource(R.color.green)

            } else {
                // Неправильный выбор
                showToast("Неправильный выбор!")
                clickedChip.setChipBackgroundColorResource(R.color.red)
                errorCount++ // Увеличиваем счетчик ошибок
                binding.mistakeCount.text = "Ошибок: $errorCount"
            }
            binding.nextButton.isInvisible = false

        }
    }

    private fun goToNextWord() {
        if (currentWordIndex < wordList.size - 1) {
            currentWordIndex++
            binding.chipGroup.removeAllViews()
            setChipLetters()
            binding.excNumber.text = (currentWordIndex + 1).toString() + "/${wordList.size}"
            binding.nextButton.isInvisible = false
        } else {
            val bundle = Bundle()
            bundle.putInt("errorCount", errorCount)
            findNavController().navigate(R.id.action_exerciseFragment_to_exerciseResultFragment, bundle)
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

