package com.example.itemapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private val list: List<Item>):RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.itemId_textVIew)
        val uname: TextView = itemView.findViewById(R.id.itemName_textVIew)
        val amount: TextView = itemView.findViewById(R.id.itemAmount_textVIew)
        val desc: TextView = itemView.findViewById(R.id.itemDescription_textVIew)
        val type: TextView = itemView.findViewById(R.id.itemType_textVIew)
        val created: TextView = itemView.findViewById(R.id.itemCreated_textVIew)
        val currency: TextView = itemView.findViewById(R.id.itemCurrency_textVIew)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.id.text = list[position].id
        holder.uname.text = list[position].name
        holder.amount.text = list[position].amount.toString()
        holder.desc.text = list[position].description
        holder.type.text = list[position].type
        holder.created.text = list[position].created_at.toString()
        holder.currency.text = list[position].currency
    }

    override fun getItemCount(): Int {
        return list.size
    }
}