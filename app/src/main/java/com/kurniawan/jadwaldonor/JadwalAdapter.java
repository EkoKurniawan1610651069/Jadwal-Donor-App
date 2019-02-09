package com.kurniawan.jadwaldonor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JadwalAdapter extends BaseAdapter {

    private ArrayList<JadwalItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public JadwalAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<JadwalItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final JadwalItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.jadwal_items, null);
            holder.textViewIntansi = (TextView) convertView.findViewById(R.id.textViewInstansi);
            holder.textViewAlamat = (TextView) convertView.findViewById(R.id.textViewAlamat);
            holder.textViewPukul = (TextView) convertView.findViewById(R.id.textViewPukul);
            holder.textViewRencanaDonor = (TextView) convertView.findViewById(R.id.textViewRencanaDonor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewIntansi.setText(mData.get(position).getNamaIntansi());
        holder.textViewAlamat.setText(mData.get(position).getAlamat());
        holder.textViewPukul.setText(mData.get(position).getJam());
        holder.textViewRencanaDonor.setText(String.valueOf(mData.get(position).getJumlahRencanaDonor()));


        return convertView;
    }

    private static class ViewHolder {
        TextView textViewIntansi;
        TextView textViewAlamat;
       TextView textViewPukul;
        TextView textViewRencanaDonor;
    }

    public void updateList(ArrayList<JadwalItems> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
