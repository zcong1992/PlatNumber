package com.andrzc.platnumber;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZC on 2017/12/11.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyHolder> {

    List<String> strList;
    Context mContext;

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CityAdapter(Context context, List<String> clist) {
        mContext = context;
        strList = clist;
    }


    @Override
    public int getItemCount() {
        return strList != null && strList.size() > 0 ? strList.size() : 0;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        String strCity = strList.get(position);
        holder.tv_name.setText(strCity);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, (int) view.getTag());
            }
        });
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false));
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        public MyHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_city);
        }
    }
}
