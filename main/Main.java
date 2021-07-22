package MarchingSquares.main;

import MarchingSquares.main.generation.MeshGenerator;
import MarchingSquares.main.generation.Tile;
import MarchingSquares.rendering.Program;
import MarchingSquares.rendering.Shader;
import MarchingSquares.rendering.mesh.Mesh;
import MarchingSquares.utils.FileHandling;
import MarchingSquares.utils.Timer;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private Window window;
    private Timer timer;
    private Input input;
    private MeshGenerator gen;

    private Program program;

    private Random rand;

    private float[][] values;
    private int size=100;

    private Tile[] tiles;

    private static float unlerp(float a, float b, float r){
        return (r-a)/(b-a);
    }

    public static void main(String[] args){
        new Main().gameLoop();
    }

    public void gameLoop(){
        try{
            init();
            loop();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cleanup();
        }
    }

    public void init() throws Exception {
        window=new Window(600,600,"Marching squares");
        timer=new Timer(60,60); //UPS,FPS
        input=new Input();
        gen=new MeshGenerator();
        rand=new Random();

        float threshold=0.83f;

        window.init();
        input.init(window);
        values=new float[size+1][size+1];
        tiles=new Tile[size*size];

        for(int x=0;x<size+1;x++){
            for(int y=0;y<size+1;y++){
                float val= rand.nextFloat();
                values[x][y]=val;
            }
        }

        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                tiles[x*size+y]=new Tile(new Vector2f(x,y),new float[]{
                        values[x][y+1],
                        values[x+1][y+1],
                        values[x+1][y],
                        values[x][y]},
                        threshold,gen,size);
            }
        }

        program=new Program();

        program.attachShaders(new Shader[]{
                new Shader(FileHandling.loadResource("src/MarchingSquares/rendering/glsl/meshes/vertex.glsl"),GL46.GL_VERTEX_SHADER),
                new Shader(FileHandling.loadResource("src/MarchingSquares/rendering/glsl/meshes/fragment.glsl"),GL46.GL_FRAGMENT_SHADER),
        });

        program.link();

        program.createUniform("meshPos");
        program.createUniform("size");

        GL46.glClearColor(0.1f, 0.1f, 0.2f, 1.0f);

        window.loop();  //This bit sets the window up to display something, if an issue occurs with the timer, the window should still be openable
    }

    public void loop(){
        while(!window.shouldClose()){
            timer.update();
            if(timer.getUpdate()){
                update();
            }if(timer.getFrame()){
                render();
            }
        }
    }

    public void update(){
        if(input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            window.close();
        }
    }

    public void render(){
        window.loop();
        GL46.glClear(GL46.GL_COLOR_BUFFER_BIT | GL46.GL_DEPTH_BUFFER_BIT);

        for(Tile tile:tiles){
            tile.render(program);
        }
    }

    public void cleanup(){
        System.out.println("Cleaning up");
        window.cleanup();

        program.cleanup();
        for(Tile tile:tiles){
            tile.cleanup();
        }
    }

}
