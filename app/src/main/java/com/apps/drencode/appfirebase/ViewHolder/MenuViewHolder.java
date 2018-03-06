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

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNombreMenu;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtNombreMenu = (TextView)itemView.findViewById(R.id.nombre_menu);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
   }
}
