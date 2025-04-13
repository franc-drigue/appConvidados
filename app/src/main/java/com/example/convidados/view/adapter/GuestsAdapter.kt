package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    // Variável que vai armazenar a lista que vem da ViewModel e que por sua vez vem do Repository
    private var guestList: List<GuestModel> = listOf();
    private var onDeleteClick: ((Int) -> Unit)? = null;

    // função que carrega o layout inflado de cada item da lista e retorna o layout pronto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return GuestViewHolder(item);
    }

    // função que pega e vincula cada item da lista com o layout já inflado
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
       holder.bind(guestList[position], onDeleteClick ?: {});
    }

    // função que conta cada item da lista
    override fun getItemCount(): Int {
        return guestList.count();
    }


    //-----Métodos que disponibilizam essas funcionalidades nas Activity/Fragment//


    // função que vai receber cada item da lista e passar para o questList
      // Atualiza a lista usando a Classe DiffUtil
    fun updateGuests(newList: List<GuestModel>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = guestList.size
            override fun getNewListSize() = newList.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                guestList[oldPos].id == newList[newPos].id
            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                guestList[oldPos] == newList[newPos]
        })
        guestList = newList
        diffResult.dispatchUpdatesTo(this)
    }


    fun setOnDeleteClickListener(listener: (Int) -> Unit) {
        onDeleteClick = listener
    }
}