package com.example.midtermexam;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CustomAdapter(Context aContext, List<Item> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);

    }
    @Override
    public int getCount() {

        return listData.size();
    }
    @Override
    public Object getItem(int position) {

        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {

        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_info, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            holder.diachi = (TextView) convertView.findViewById(R.id.diachi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = this.listData.get(position);
        holder.name.setText(item.getTen());
        holder.age.setText("Tuổi: "+item.getTuoi());
        holder.diachi.setText("Địa chỉ: "+item.getDiachi());
        return convertView;
    }
    static class ViewHolder {
        TextView name;
        TextView age;
        TextView diachi;

    }
}
