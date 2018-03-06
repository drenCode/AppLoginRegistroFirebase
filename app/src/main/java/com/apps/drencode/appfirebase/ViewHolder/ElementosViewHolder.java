package com.apps.drencode.appfirebase.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.drencode.appfirebase.Interfaz.ItemClickListener;
import com.apps.drencode.appfirebase.R;

/**
 * Created by drenKoCode on 24/02/2018.
 */

public class ElementosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nombre_elemento;
    public ImageView elemento_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public ElementosViewHolder(View itemView) {
        super(itemView);

        nombre_elemento = (TextView)itemView.findViewById(R.id.nombre_elemento);
        elemento_image = (ImageView)itemView.findViewById(R.id.elemento_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
