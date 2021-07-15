package MarchingSquares.main.generation;

import MarchingSquares.rendering.Mesh;

import java.util.ArrayList;

public class MeshGenerator {

    private float unlerp(float a, float b, float r){    //value a should be the "0" value
        return (r-a)/(b-a);
    }

    /*
  x:0  1
            y:
    0--1    1
    |  |
    3--2    0
     */

    private Mesh genSquare(float c0, float c1, float c2, float c3, float threshold){
        int index=0;

        boolean[] activeCorners=new boolean[]{
                c0>threshold,
                c1>threshold,
                c2>threshold,
                c3>threshold
        };

        index+=(activeCorners[0]?1:0);
        index+=(activeCorners[1]?2:0);
        index+=(activeCorners[2]?4:0);
        index+=(activeCorners[3]?8:0);

        ArrayList<Float> vertices=new ArrayList<>();

        if(activeCorners[0]){   //4 corners
            vertices.add(0f);
            vertices.add(1f);
        }if(activeCorners[1]){
            vertices.add(1f);
            vertices.add(1f);
        }if(activeCorners[2]){
            vertices.add(1f);
            vertices.add(0f);
        }if(activeCorners[3]){
            vertices.add(0f);
            vertices.add(0f);
        }
        if(activeCorners[0]!=activeCorners[1]){ //4 sides
            vertices.add(unlerp(c0,c1,threshold));
            vertices.add(1f);
        }if(activeCorners[1]!=activeCorners[2]){
            vertices.add(1f);
            vertices.add(unlerp(c2,c1,threshold));
        }if(activeCorners[2]!=activeCorners[3]){
            vertices.add(unlerp(c3,c2,threshold));
            vertices.add(0f);
        }if(activeCorners[3]!=activeCorners[0]){
            vertices.add(0f);
            vertices.add(unlerp(c3,c0,threshold));
        }

        float[] verticesNew=new float[vertices.size()]; //toArray didn't work

        for(int i=0;i<vertices.size();i++){
            verticesNew[i]=vertices.get(i);
        }

        Mesh mesh=new Mesh(verticesNew,indexArray.getIndices(index));

        return mesh;
    }
}
