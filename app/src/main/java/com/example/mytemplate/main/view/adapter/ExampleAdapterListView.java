package com.example.mytemplate.main.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mytemplate.R;
import com.example.mytemplate.main.model.local.DefaultItemList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExampleAdapterListView extends BaseAdapter {

    private Context context;
    private List<DefaultItemList> mItems;

    public ExampleAdapterListView(Context context, List<DefaultItemList> mItems){
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view != null){
            holder = (ViewHolder) view.getTag();
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.list_item_example, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        // set view in here...
        holder.tvId.setText(Integer.toString(mItems.get(i).getId()));
        holder.tvText.setText(mItems.get(i).getText());

        return view;
    }

    static class ViewHolder{

        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
