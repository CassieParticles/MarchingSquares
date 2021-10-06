package main.generation;

import org.joml.Vector2f;

import rendering.mesh.Mesh;


public class MeshGenerator {

    private float normalise(float a, float b, float r){    //value a should be the "0" value
        if(b-a==0){
            return 0;
        }if(a*b<0) {
        	System.out.println("uh oh");
        }
        return (r-a)/(b-a);
    }

    /*
  x:0  1
            y:
    0--1    1
    |  |
    3--2    0
     */

    public Mesh genSquare(float c0, float c1, float c2, float c3){
        int index=0;
        
        if(c0>=0){index+=1;}
        if(c1>=0){index+=2;}
        if(c2>=0){index+=4;}
        if(c3>=0){index+=8;}

        int[] indices=indexArray.getIndices(index);
        int edges=indexArray.getEdges(index);
        Vector2f[] verticesPositions=new Vector2f[8];
        
        if((edges&1)==1){
        	verticesPositions[0]=new Vector2f(0,1);
        }if((edges&4)==4){
        	verticesPositions[2]=new Vector2f(1,1);
        }if((edges&16)==16){
        	verticesPositions[4]=new Vector2f(1,0);
        }if((edges&64)==64){
        	verticesPositions[6]=new Vector2f(0,0);
        }if((edges&2)==2){
        	verticesPositions[1]=new Vector2f(normalise(c0,c1,0),1);
        }if((edges&8)==8){
        	verticesPositions[3]=new Vector2f(1,normalise(c2,c1,0));
        }if((edges&32)==32){
        	verticesPositions[5]=new Vector2f(normalise(c2,c3,0),0);
        }if((edges&128)==128){
        	verticesPositions[7]=new Vector2f(1,normalise(c3,c0,0));
        }

        float[] vertices=new float[16];
        for(int i=0;i<8;i++) {
        	if(verticesPositions[i]!=null) {
        		vertices[(2*i)+0]=verticesPositions[i].x;
        		vertices[(2*i)+1]=verticesPositions[i].y;
        	}
        }
        
        return new Mesh(vertices,indices);

//        if(activeCorners[0]){
//            vertices.add(0f);
//            vertices.add(1f);
//        }if(activeCorners[1]){
//            vertices.add(1f);
//            vertices.add(1f);
//        }if(activeCorners[2]){
//            vertices.add(1f);
//            vertices.add(0f);
//        }if(activeCorners[3]){
//            vertices.add(0f);
//            vertices.add(0f);
//        }

//        if(activeCorners[0]!=activeCorners[1]){
//            vertices.add(normalise(c0,c1,threshold));
//            vertices.add(1f);
//        }if(activeCorners[1]!=activeCorners[2]){
//            vertices.add(1f);
//            vertices.add(normalise(c2,c1,threshold));
//        }if(activeCorners[2]!=activeCorners[3]){
//            vertices.add(normalise(c3,c2,threshold));
//            vertices.add(0f);
//        }if(activeCorners[3]!=activeCorners[0]){
//            vertices.add(0f);
//            vertices.add(normalise(c3,c0,threshold));
//        }

//        float[] verticesNew=new float[vertices.size()]; //toArray didn't work
//
//        for(int i=0;i<vertices.size();i++){
//            verticesNew[i]=vertices.get(i);
//        }
//
//        int[] indices= indexArray.getIndices(index);

//        return new Mesh(verticesNew,indices);
    }
}
