package com.example.mytemplate.main.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mytemplate.R
import com.example.mytemplate.main.model.pojo.DefaultItemList

class ExampleAdapterListView(private val mItems: List<DefaultItemList>) : BaseAdapter() {

    override fun getCount(): Int = mItems.size

    override fun getItem(i: Int): Any = mItems[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewHolder
        val currentView: View
        if(view == null){
            currentView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_example, viewGroup, false)
            holder = ViewHolder(currentView)
            currentView.tag = holder
        }else{
            currentView = view
            holder = view.tag as ViewHolder
        }

        holder.bind(mItems[i])

        return currentView
    }

    class ViewHolder(view: View) {
        private val tvId: TextView = view.findViewById(R.id.tv_id)
        private val tvText: TextView = view.findViewById(R.id.tv_text)

        fun bind(data: DefaultItemList) {
            tvId.text = data.id.toString()
            tvText.text = data.text
        }
    }
}