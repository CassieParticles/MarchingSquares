package main.generation;

public class Point {
    private float value;
    private Tile[] surroundingTiles;

    public Point(float value){
        this.value=value;
        this.surroundingTiles=new Tile[4];
    }

    public void attachTile(Tile tile, int corner){
        surroundingTiles[corner]=tile;
    }

    public Tile getTile(int corner){
        return surroundingTiles[corner];
    }

    public Tile[] getAllTiles(){
        return surroundingTiles;
    }

    public void setValue(float value){
        this.value=value;
    }

    public float getValue(){
        return value;
    }
}
