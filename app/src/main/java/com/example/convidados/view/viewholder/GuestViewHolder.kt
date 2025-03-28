package com.example.convidados.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel

// Esta é a classe que recebe o item na classe GuestsAdapter
class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    // passando o nome para o layout
    fun bind(guest: GuestModel, onDeleteClick: (Int) -> Unit) {
        bind.name.text = guest.name

        bind.iconDelete.setOnClickListener {
            onDeleteClick(guest.id) // Passa o ID do convidado ao clicar
        }
    }
}