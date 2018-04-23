package com.programer.caio.pgp;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PedidoViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView bodyView;

    public PedidoViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.post_title);
        bodyView = itemView.findViewById(R.id.post_body);
    }

}