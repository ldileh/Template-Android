package com.example.mytemplate.main.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mytemplate.R
import com.example.mytemplate.main.model.pojo.DefaultItemList

class ExampleAdapterListView(private val context: Context, private val mItems: List<DefaultItemList>) : BaseAdapter() {
    override fun getCount(): Int {
        return mItems.size
    }

    override fun getItem(i: Int): Any {
        return mItems[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        val holder: ViewHolder = view.tag as ViewHolder
        // set view in here...
        holder.tvId.text = mItems[i].id.toString()
        holder.tvText.text = mItems[i].text
        return view
    }

    internal class ViewHolder(view: View) {
        val tvId: TextView = view.findViewById(R.id.tv_id)
        val tvText: TextView = view.findViewById(R.id.tv_text)
    }
}