package MarchingSquares.main.generation;

import MarchingSquares.rendering.Program;
import MarchingSquares.rendering.mesh.Mesh;
import org.joml.Vector2f;
import org.lwjgl.system.CallbackI;

public class Tile {
    private Vector2f position;
    private int size;

    private final float[] corners;

    private final Mesh mesh;

    public Tile(Vector2f position, float[] corners, float threshold, MeshGenerator generator, int size){
        this.position=new Vector2f(position);
        this.corners=corners;
        this.size=size;

        this.mesh=generator.genSquare(corners[0],corners[1],corners[2],corners[3],threshold);
    }

    public Tile(float[] corners, float threshold, MeshGenerator generator, int size){
        this(new Vector2f(0,0),corners,threshold,generator,size);
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

    public float[] getCorners(){
        return corners;
    }
}
