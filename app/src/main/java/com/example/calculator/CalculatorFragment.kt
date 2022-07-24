package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

open class CalculatorFragment : Fragment() {

    lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButton()
    }

    private fun onClickButton() {
        binding.button0.setOnClickListener { setTextOperation("0") }
        binding.button1.setOnClickListener { setTextOperation("1") }
        binding.button2.setOnClickListener { setTextOperation("2") }
        binding.button3.setOnClickListener { setTextOperation("3") }
        binding.button4.setOnClickListener { setTextOperation("4") }
        binding.button5.setOnClickListener { setTextOperation("5") }
        binding.button6.setOnClickListener { setTextOperation("6") }
        binding.button7.setOnClickListener { setTextOperation("7") }
        binding.button8.setOnClickListener { setTextOperation("8") }
        binding.button9.setOnClickListener { setTextOperation("9") }

        binding.buttonDot.setOnClickListener { setTextOperation(".") }
        binding.buttonPlus.setOnClickListener { setTextOperation("+") }
        binding.buttonMinus.setOnClickListener { setTextOperation("-") }
        binding.buttonOpenBracket.setOnClickListener { setTextOperation("(") }
        binding.buttonClosingBracket.setOnClickListener { setTextOperation(")") }
        binding.buttonMultiply.setOnClickListener { setTextOperation("*") }
        binding.buttonDivide.setOnClickListener { setTextOperation("/") }

        binding.buttonEquals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.operationText.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    binding.resultText.text = longRes.toString()
                } else {
                    binding.resultText.text = result.toString()
                }

            } catch (e: Exception) {
                Log.d("Ошибка", "сщобщение:${e.message}")
            }
        }

        binding.buttonAc.setOnClickListener {
            binding.operationText.text = ""
            binding.resultText.text = ""
        }

        binding.buttonBack.setOnClickListener {
            val srt = binding.operationText.text.toString()
            if (srt.isNotEmpty()) {
                binding.operationText.text = srt.substring(0, srt.length - 1)
                binding.resultText.text = ""
            }
        }
    }

    private fun setTextOperation(srt: String) {

        val resultText = binding.resultText.text.toString()

        if (resultText != "") {
            binding.operationText.text = resultText
            binding.resultText.text = ""
        }
        binding.operationText.append(srt)
    }
}