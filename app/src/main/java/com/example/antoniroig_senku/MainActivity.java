package com.example.antoniroig_senku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        tileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                SenkuTile[] senkuTilesArrayClick = senkuTilesArray;
                if (!senkuTilesArray[position].isCorner() || senkuTilesArray[position].isEmpty()){

                    if (senkuTilesArray[position].isPossible()){
                        senkuTilesArrayClick = realizateMove(senkuTilesArray, position);
                        senkuTilesArrayClick[position].setEmpty(false);
                    }

                    senkuTilesArrayClick = deselectAll(senkuTilesArrayClick);

                    senkuTilesArrayClick[position].setSelected(true);
                    senkuTilesArrayClick = possibleMove(senkuTilesArray);
                    adapterUpdate(senkuTilesArrayClick);
                }
            }
        });
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

    private SenkuTile[][] arrayToMatriz (SenkuTile[] senkuTiles){
        SenkuTile[][] matrizSenku = new SenkuTile[7][7];
        int arrayIndex = 0;
        for (int i = 0; i < matrizSenku.length; i++){
            for (int j = 0; j < matrizSenku[i].length; j ++){
                matrizSenku[i][j] = senkuTiles[arrayIndex];
                arrayIndex = arrayIndex + 1;
            }
        }

        return matrizSenku;
    }

    private int[] arrayPositionToMatrizPosition (int position, SenkuTile[][] matrizSenku) {
        int[] matrizArrayPosition = new int[2];
        int arrayIndex = 0;
        for (int i = 0; i < matrizSenku.length; i++){
            for (int j = 0; j < matrizSenku[i].length; j ++){
                if (arrayIndex == position){
                    matrizArrayPosition[0] = i;
                    matrizArrayPosition[1] = j;
                }
                arrayIndex = arrayIndex + 1;
            }
        }

        return  matrizArrayPosition;
    }

    private SenkuTile[] realizateMove (SenkuTile[] senkuTiles, int movePosition){
        SenkuTile[][] matrizSenku = arrayToMatriz(senkuTiles);
        int[] matrizArrayPosition = arrayPositionToMatrizPosition(movePosition, matrizSenku);

        for (int i = 0; i < matrizSenku.length; i++){
            for (int j = 0; j < matrizSenku[i].length; j ++){
                if (matrizSenku[i][j].isSelected()){
                    try {
                        if(i == matrizArrayPosition[0]){
                            if (j < matrizArrayPosition[1]){
                                matrizSenku[i][j+1].setEmpty(true);
                            } else {
                                matrizSenku[i][j-1].setEmpty(true);
                            }
                        } else {
                            if (i < matrizArrayPosition[0]){
                                matrizSenku[i+1][j].setEmpty(true);
                            } else {
                                matrizSenku[i-1][j].setEmpty(true);
                            }
                        }
                    } catch (Exception e){

                    }
                    matrizSenku[i][j].setEmpty(true);
                }
            }
        }

        return matrizToArray(matrizSenku);
    }

    private SenkuTile[] possibleMove (SenkuTile[] senkuTiles){
        SenkuTile[][] matrizSenku = arrayToMatriz(senkuTiles);
        int[] direcciones = new int[]{1, -1, 1, -1};

        for (int i = 0; i < matrizSenku.length; i++){
            for (int j = 0; j < matrizSenku[i].length; j ++){
                if(matrizSenku[i][j].isSelected()){
                    for (int k = 0; k < direcciones.length; k++){
                        try {
                            if(k == 0 || k == 1){
                                if(!matrizSenku[i][j+(direcciones[k])].isEmpty() &&
                                        matrizSenku[i][j+(direcciones[k]*2)].isEmpty()) {

                                    matrizSenku[i][j+(direcciones[k]*2)].setPossible(true);
                                }
                            } else {
                                if(!matrizSenku[i+(direcciones[k])][j].isEmpty() &&
                                        matrizSenku[i+(direcciones[k]*2)][j].isEmpty()) {

                                    matrizSenku[i+(direcciones[k]*2)][j].setPossible(true);
                                }
                            }
                        } catch (Exception e){

                        }
                    }
                }
            }
        }

        return matrizToArray(matrizSenku);
    }

    private SenkuTile[] deselectAll(SenkuTile[] senkuTilesArray){
        for (int i = 0; i < senkuTilesArray.length; i++){
            if(senkuTilesArray[i].isSelected()){
                senkuTilesArray[i].setSelected(false);
            } else if(senkuTilesArray[i].isPossible()){
                senkuTilesArray[i].setPossible(false);
            }
        }

        return senkuTilesArray;
    }

    public SenkuTile[][] getSenkuTiles() {
        return senkuTiles;
    }
}