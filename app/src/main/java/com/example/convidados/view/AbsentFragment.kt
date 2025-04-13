package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentAbsentBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.viewmodel.AbsentViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null;
    private val binding get() = _binding!!;
    private lateinit var absentViewModel: AbsentViewModel;
    private val adapter = GuestsAdapter();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsentBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        absentViewModel = ViewModelProvider(this)[AbsentViewModel::class.java];

        // Layout
        binding.recyclerAllGuestsAbsent.layoutManager = LinearLayoutManager(context);

        // Adapter
        binding.recyclerAllGuestsAbsent.adapter = adapter;

        absentViewModel.getAllAbsent();

        observer();
        delete();
    }

    override fun onResume() {
        super.onResume();
        absentViewModel.getAllAbsent();
    }

    fun observer() {
        absentViewModel.guestAbsent.observe(viewLifecycleOwner) {
            adapter.updateGuests(it);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    private fun delete() {
        adapter.setOnDeleteClickListener { id ->
            absentViewModel.delete(id);
            absentViewModel.getAllAbsent();
        }
    }
}