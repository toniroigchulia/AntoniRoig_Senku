package com.example.antoniroig_senku;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.antoniroig_senku.R;

public class Adaptador extends ArrayAdapter<Object> {
    private Activity context;
    private SenkuTile[] senkuTiles;
    public Adaptador (Activity context, SenkuTile[] senkuTiles){
        super(context, R.layout.senkutile, senkuTiles);
        this.senkuTiles = senkuTiles;
        this.context = context;
    }

    static class ViewHolder {
        TextView senkuTiles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        ViewHolder holder = new ViewHolder();
        if(item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.senkutile, null);
            holder.senkuTiles = item.findViewById(R.id.tile);
            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(50);
        if(senkuTiles[position].isCorner()) {

            holder.senkuTiles.setVisibility(View.INVISIBLE);
        } else if(senkuTiles[position].isEmpty()) {

            gd.setColor(Color.GRAY);
            holder.senkuTiles.setBackground(gd);
        } else {

            gd.setColor(Color.BLUE);
            holder.senkuTiles.setBackground(gd);
        }

        return (item);
    }

    public SenkuTile[] getSenkuTiles() {
        return senkuTiles;
    }

    public void setSenkuTiles(SenkuTile[] senkuTiles) {
        this.senkuTiles = senkuTiles;
    }
}
