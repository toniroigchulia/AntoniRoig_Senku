package com.example.antoniroig_senku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private SenkuTile[] senkuTiles = new SenkuTile[49];
    private GridView tileList;
    private Adaptador tileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileList = findViewById(R.id.senkuTileList);

        for (int i = 0; i < senkuTiles.length; i++){

            if (checkCorner(i)){
                senkuTiles[i] = new SenkuTile(false, true);
            } else {
                senkuTiles[i] = new SenkuTile();
            }
        }

        tileAdapter = new Adaptador(this, senkuTiles);
        tileList.setAdapter(tileAdapter);

    }

    private boolean checkCorner(int j){
        int[] checkPosition = {0,1,5,6,7,8,12,13,35,36,40,41,42,43,47,48};
        for (int i = 0; i < checkPosition.length; i++){
            if (j == checkPosition[i]){
                return true;
            }
        }
        return false;
    }
}