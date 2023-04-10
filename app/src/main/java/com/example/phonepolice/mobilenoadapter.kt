package com.example.phonepolice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class adapter(private val examplelist: List<exampleitem>)  : RecyclerView.Adapter<adapter.exampleview>()
{
    class exampleview( ItemView : View) : RecyclerView.ViewHolder(ItemView)
    {
        val textView: TextView =itemView.findViewById(R.id.itemview)
        val textView3: TextView = itemView.findViewById(R.id.itemview2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exampleview {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view_recycler_view,parent,false)
        return exampleview(itemView)
    }

    override fun onBindViewHolder(holder: exampleview, position: Int) {
        val currentitem=examplelist[position]
        holder.textView3.text=currentitem.value
        holder.textView.text=currentitem.key
    }

    override fun getItemCount(): Int {
        return examplelist.size
    }
}