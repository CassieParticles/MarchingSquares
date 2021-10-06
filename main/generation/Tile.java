package main.generation;

import org.joml.Vector2f;

import rendering.Program;
import rendering.mesh.Mesh;


public class Tile {
    private Vector2f position;
    private int size;

    private Mesh mesh;

    public Tile(Vector2f position, float[] corners, MeshGenerator generator, int size){
        this.position=new Vector2f(position);
        this.size=size;

        this.mesh=generator.genSquare(corners[0],corners[1],corners[2],corners[3]);
    }

    public Tile(float[] corners, MeshGenerator generator, int size){
        this(new Vector2f(0,0),corners,generator,size);
    }

    public void updateMesh(float[] corners, MeshGenerator generator){
        mesh.cleanup();
        mesh=generator.genSquare(corners[0],corners[1],corners[2],corners[3]);
    }

    public void render(Program program){
        program.useProgram();

        program.setUniform("meshPos",position);
        program.setUniform("size",size);

        mesh.render(program);
    }

    public void cleanup(){
        mesh.cleanup();
    }

    public void setPosition(Vector2f position){
        this.position=new Vector2f(position);
    }

    public Vector2f getPosition(){
        return new Vector2f(position);
    }

    public void setSize(int size){
        this.size=size;
    }

    public int getSize(){
        return size;
    }

    public Mesh getMesh(){
        return mesh;
    }
}
