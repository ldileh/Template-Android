package com.example.mytemplate.main.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mytemplate.databinding.ListItemExampleBinding
import com.example.mytemplate.main.model.pojo.DefaultItemList

class ExampleAdapterListView(private val mItems: List<DefaultItemList>) : BaseAdapter() {

    override fun getCount(): Int = mItems.size

    override fun getItem(i: Int): Any = mItems[i]

    override fun getItemId(i: Int): Long = i.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val binding = ListItemExampleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false).also { binding ->
            bind(binding, mItems[i])
        }

        return binding.root
    }

    private fun bind(binding: ListItemExampleBinding, data: DefaultItemList) {
        binding.tvId.text = data.id.toString()
        binding.tvText.text = data.text
    }
}