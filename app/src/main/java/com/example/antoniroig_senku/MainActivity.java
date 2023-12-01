package com.example.antoniroig_senku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private SenkuTile[][] senkuTiles = new SenkuTile[7][7];
    private GridView tileList;
    private Adaptador tileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileList = findViewById(R.id.senkuTileList);

        SenkuTile[] senkuTilesArray = matrizToArray(senkuTiles);
        for (int i = 0; i < senkuTilesArray.length; i++){

            if (checkCorner(i)){
                senkuTilesArray[i] = new SenkuTile(false, true);
            } else {
                if (i == 24){
                    senkuTilesArray[i] = new SenkuTile(true, false);
                } else {
                    senkuTilesArray[i] = new SenkuTile();
                }
            }
        }

        tileAdapter = new Adaptador(this, senkuTilesArray);
        tileList.setAdapter(tileAdapter);
    }

    private void adapterUpdate(SenkuTile[] tilesArray) {
        this.tileAdapter.setSenkuTiles(tilesArray);
        this.tileAdapter.notifyDataSetChanged();
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

    private SenkuTile[] matrizToArray (SenkuTile[][] senkuTiles) {
        SenkuTile[] tilesArray = new SenkuTile[49];
        int arrayIndex = 0;

        for (int i = 0; i < senkuTiles.length; i++){
            for (int j = 0; j < senkuTiles[i].length; j ++){
                tilesArray[arrayIndex] = senkuTiles[i][j];
                arrayIndex = arrayIndex + 1;
            }
        }

        return tilesArray;
    }
}