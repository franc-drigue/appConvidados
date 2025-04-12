package com.example.convidados.view.viewholder

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel

// Esta é a classe que recebe o item na classe GuestsAdapter
class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {
    private val context: Context = bind.root.context
    private val builder = AlertDialog.Builder(context)

    // passando o nome para o layout
    fun bind(guest: GuestModel, onDeleteClick: (Int) -> Unit) {
        bind.name.text = guest.name

        bind.iconDelete.setOnClickListener {
            builder.setTitle("Deletar convidado")
            builder.setMessage("Deseja deletar esse convidado?")
            builder.setPositiveButton("Continuar") { _, _ ->
                onDeleteClick(guest.id) // Passa o ID do convidado ao clicar
            }
            builder.setNegativeButton("Cancelar") {dialog, _ ->
                dialog.dismiss()
            }.create().show()
        }
    }
}