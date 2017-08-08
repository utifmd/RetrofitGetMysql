package com.doricovix.utif.retrofitgetmysql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by utif on 8/8/2017.
 */

public class SccAdapter extends RecyclerView.Adapter<SccAdapter.ViewHolder> {
    private Context context;
    private List<ItemScc> itemScc;
    private View view;
    private ViewHolder holder;
    private ClickInterface clickInterface;

    public SccAdapter(Context context, List<ItemScc> itemScc) {
        this.context = context;
        this.itemScc = itemScc;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_tittle, tv_desc;
        ImageView img_icon;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_tittle = (TextView) itemView.findViewById(R.id.title_scc);
            tv_desc = (TextView) itemView.findViewById(R.id.desc_scc);
            img_icon = (ImageView) itemView.findViewById(R.id.icon_scc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickInterface != null){ clickInterface.onItemClick(view, getAdapterPosition()); }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_layout_scc, parent, false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_tittle.setText(itemScc.get(position).getIdentity());
        holder.tv_desc.setText(itemScc.get(position).getLevel()+", "+itemScc.get(position).getJbt());
    }

    @Override
    public int getItemCount() {
        return itemScc.size();
    }

    public void removeAt(int position) {
        itemScc.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemScc.size());
    }

    public String getItemTitle(int id){
        return itemScc.get(id).getIdentity();
    }

    public void setClickListener(ClickInterface clickListener){
        this.clickInterface = clickListener;
    }
    public interface ClickInterface{
        void onItemClick(View view, int position);
    }
}
