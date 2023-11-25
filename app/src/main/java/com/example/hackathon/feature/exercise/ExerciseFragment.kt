package com.example.hackathon.feature.exercise

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hackathon.R
import com.example.hackathon.databinding.FragmentExerciseBinding
import com.example.hackathon.domain.WordEmphasis
import com.example.hackathon.domain.testData
import com.example.hackathon.dpToPx
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordList: List<WordEmphasis>
    private var currentWordIndex = 0
    private var errorCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // реализовать через бек
        wordList = testData
        binding.excNumber.text = "1"
        binding.excNumber.text = "0/${wordList.size}"
        // Добавляем чипам буквы
        setChipLetters()

        // Костыль. .
        for (i in 0 until binding.chipGroupContainer.childCount) {
            val chip = binding.chipGroupContainer.getChildAt(i) as? MaterialButton
            chip?.setOnClickListener {
                onChipClicked(chip)
            }
        }

        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("ResourceType")
    private fun setChipLetters() {

        val currentWord = wordList[currentWordIndex].word

        for (i in currentWord.indices) {

            val chip: Button = MaterialButton(requireContext())
            chip.id = View.generateViewId()
            chip.text = currentWord[i].toString()
            chip.isClickable = true
            binding.chipGroupContainer.addView(chip)
            val childAt = binding.chipGroupContainer.getChildAt(i)
            val layoutParams = childAt?.layoutParams
            val marginLayoutParams = childAt.layoutParams as? ViewGroup.MarginLayoutParams
            marginLayoutParams?.rightMargin = 12.dpToPx()
            layoutParams?.width = 64.dpToPx()
            layoutParams?.height = 64.dpToPx()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun onChipClicked(clickedChip: MaterialButton?) {
        clickedChip?.let {
            val selectedLetterIndex = binding.chipGroupContainer.indexOfChild(clickedChip)
            val emphasisIndex = wordList[currentWordIndex].emphasisIndex

            if (selectedLetterIndex == emphasisIndex) {
                // Правильный выбор
                showToast("Верный выбор!")
                clickedChip.setBackgroundColor(clickedChip.context.resources.getColor(R.color.green))

            } else {
                // Неправильный выбор
                clickedChip.setBackgroundColor(clickedChip.context.resources.getColor(R.color.red))
                errorCount++ // Увеличиваем счетчик ошибок
                binding.mistakeCount.text = "Ошибок: $errorCount"
            }

            lifecycleScope.launch {
                delay(700)
                goToNextWord()
                for (i in 0 until binding.chipGroupContainer.childCount) {
                    val chip = binding.chipGroupContainer.getChildAt(i) as? MaterialButton
                    chip?.setOnClickListener {
                        onChipClicked(chip)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun goToNextWord() {
        if (currentWordIndex < wordList.size - 1) {
            currentWordIndex++
            binding.chipGroupContainer.removeAllViews()
            setChipLetters()
            lifecycleScope.launch {
                delay(20)
                binding.scroll.smoothScrollTo(0, 0)
            }
            binding.excNumber.text = (currentWordIndex + 1).toString() + "/${wordList.size}"
        } else {
            val bundle = Bundle()
            bundle.putInt("errorCount", errorCount)
            findNavController().navigate(R.id.action_exerciseFragment_to_exerciseResultFragment, bundle)
        }
    }


    private fun showToast(message: String) {
        val snackbar = Snackbar
            .make(binding.root, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

