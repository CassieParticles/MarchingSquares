package main.generation;

import java.util.concurrent.ThreadLocalRandom;

public class Values {
    private float[][] values;
    private int size;

    public Values(int size){
        this.size=size;
        this.values=new float[size+1][size+1];
    }

    public void init(){
        for(int x=0;x<size+1;x++){
            for(int y=0;y<size+1;y++){
                float val= ThreadLocalRandom.current().nextFloat()-0.5f;
                values[x][y]=val;
            }
        }
    }

    public float[][] getArray(){
        return values;
    }

    public void setValue(int x, int y, float val){
        if(x>0&&x<size&&y>0&&y<=size){
            values[x][y]=val;
        }
    }

    public void incrementValue(int x, int y, float val){
        if(x>0&&x<size&&y>0&&y<=size){
            values[x][y]+=val;
        }
    }

    public float getVal(int x, int y){
        if(x>0&&x<size&&y>0&&y<=size){
            return values[x][y];
        }
        return 0;
    }
}
