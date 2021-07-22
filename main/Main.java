package MarchingSquares.main;

import MarchingSquares.main.generation.MeshGenerator;
import MarchingSquares.main.generation.Tile;
import MarchingSquares.main.generation.Values;
import MarchingSquares.rendering.Program;
import MarchingSquares.rendering.Shader;
import MarchingSquares.utils.FileHandling;
import MarchingSquares.utils.Timer;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;

import java.util.Random;

public class Main {
    private Window window;
    private Timer timer;
    private Input input;
    private MeshGenerator gen;

    private Program program;

    private Random rand;

    private Values values;
    private int size=30;
    private float threshold=0.45f;

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

        window.init();
        input.init(window);
        values=new Values(size);
        tiles=new Tile[size*size];

        values.init();

        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                Tile tile=new Tile(new Vector2f(x,y),new float[]{
                        values.getVal(x,y+1),
                        values.getVal(x+1,y+1),
                        values.getVal(x+1,y),
                        values.getVal(x,y)},
                        threshold,gen,size);
                tiles[x*size+y]=tile;
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

    private void updateAllMeshes(){
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                tiles[x*size+y].updateMesh(new float[]{
                                values.getVal(x,y+1),
                                values.getVal(x+1,y+1),
                                values.getVal(x+1,y),
                                values.getVal(x,y)},
                        threshold,gen);
            }
        }
    }

    private void updateMesh(int x, int y){
        if(x*size+y<size*size){
            tiles[x*size+y].updateMesh(new float[]{
                            values.getVal(x,y+1),
                            values.getVal(x+1,y+1),
                            values.getVal(x+1,y),
                            values.getVal(x,y)},
                    threshold,gen);
        }else{
            System.out.println("Gameing");
        }
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
        }if(input.isKeyDown(GLFW.GLFW_KEY_UP)){
            threshold+=0.01f;
            updateAllMeshes();
        }
        if(input.isKeyDown(GLFW.GLFW_KEY_DOWN)){
            threshold-=0.01f;
            updateAllMeshes();
        }if(input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            float[] mousePosNormalised=new float[]{input.getMousePos()[0]/ (float)window.getWidth(),1-input.getMousePos()[1]/ (float)window.getHeight()};
            int x= (int) Math.floor(mousePosNormalised[0]*size);
            int y= (int) Math.floor(mousePosNormalised[1]*size);
            System.out.println("X: "+x);
            System.out.println("Y: "+y);
            if(x>0&&x<size&&y>0&&y<size){
                values.incrementValue(x,y,0.01f);
                updateMesh(x-1,y-1);
                updateMesh(x,y-1);
                updateMesh(x-1,y);
                updateMesh(x,y);
            }
        }if(input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)){
            float[] mousePosNormalised=new float[]{input.getMousePos()[0]/(float)window.getWidth(),1-input.getMousePos()[1]/ (float)window.getHeight()};
            int x= (int) Math.floor(mousePosNormalised[0]*(size+1));
            int y= (int) Math.floor(mousePosNormalised[1]*(size+1));
            if(x>0&&x<size&&y>0&&y<size){
                values.incrementValue(x,y,-0.01f);
                updateMesh(x,y);
                updateMesh(x,y-1);
                updateMesh(x-1,y);
                updateMesh(x-1,y-1);
            }
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
