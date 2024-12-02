package com.example.android

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.android.databinding.FragmentFirstBinding
import java.time.LocalDate
import java.time.Period

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottom.setOnClickListener{
            calculateAge()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge() {
        val day = binding.editTextDay.text.toString().toIntOrNull()
        val month = binding.editTextMonth.text.toString().toIntOrNull()
        val year = binding.editTextYear.text.toString().toIntOrNull()

        if (day == null || month == null || year == null) {
            Toast.makeText(requireContext(), "Please enter a valid date", Toast.LENGTH_SHORT).show()
            return
        }

        if (month !in 1..12) {
            Toast.makeText(requireContext(), "Please enter a valid month (1-12)", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidDate(day, month, year)) {
            Toast.makeText(requireContext(), "Please enter a valid date", Toast.LENGTH_SHORT).show()
            return
        }

        val birthDate = LocalDate.of(year, month, day)
        val currentDate = LocalDate.now()
        val period = Period.between(birthDate, currentDate)

        binding.numeroanys.text = period.years.toString()
        binding.mesesnumero.text = period.months.toString()
        binding.diasnumero.text = period.days.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isValidDate(day: Int, month: Int, year: Int): Boolean {
        return try {
            LocalDate.of(year, month, day)
            true
        } catch (e: Exception) {
            false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}