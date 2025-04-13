package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.viewmodel.PresentViewModel


class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var presentViewModel: PresentViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        presentViewModel = ViewModelProvider(this)[PresentViewModel::class.java];
        // Layout
        binding.recyclerAllGuestsPresence.layoutManager = LinearLayoutManager(context);

        // Adapter
        binding.recyclerAllGuestsPresence.adapter = adapter;

        presentViewModel.getAllPresence();

        delete();
        observer();
    }

    override fun onResume() {
        super.onResume()
        presentViewModel.getAllPresence()
    }

    fun observer() {
        presentViewModel.guetsPresence.observe(viewLifecycleOwner) {
            adapter.updateGuests(it);
        }
    }

    private fun delete() {
        adapter.setOnDeleteClickListener { id ->
            presentViewModel.delete(id);
            presentViewModel.getAllPresence();
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}