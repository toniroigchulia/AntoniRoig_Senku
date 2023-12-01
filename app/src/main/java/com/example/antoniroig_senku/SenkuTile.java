package com.example.antoniroig_senku;

public class SenkuTile {

    private boolean empty = false;
    private boolean corner = false;
    private boolean possible = false;
    private boolean selected = false;

    public SenkuTile(){

    }

    public SenkuTile(boolean isEmpty, boolean isCorner){
        this.empty = isEmpty;
        this.corner = isCorner;
    }


    // Getters And Setters
    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isCorner() {
        return corner;
    }

    public void setCorner(boolean corner) {
        this.corner = corner;
    }

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
