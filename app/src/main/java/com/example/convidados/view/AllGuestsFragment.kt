package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.databinding.FragmentAllguestsBinding
import com.example.convidados.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllguestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllguestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        allGuestsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}