package MarchingSquares.main;

import MarchingSquares.main.generation.MeshGenerator;
import MarchingSquares.rendering.Program;
import MarchingSquares.rendering.Shader;
import MarchingSquares.rendering.mesh.Mesh;
import MarchingSquares.utils.FileHandling;
import MarchingSquares.utils.Timer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;

public class Main {
    private Window window;
    private Timer timer;
    private Input input;
    private MeshGenerator gen;

    private Program program;

    private Mesh mesh;

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

        float threshold=0.4f;

        window.init();
        input.init(window);

        float[] vertices=new float[]{
                0,1,
                1,1,
                1,0,
                0,0
        };

        int[] indices=new int[]{
                0,1,2,
                0,2,3
        };

        mesh=gen.genSquare(0.75f,0.2f,0.6f,0.3f,threshold);

        program=new Program();

        program.attachShaders(new Shader[]{
                new Shader(FileHandling.loadResource("src/MarchingSquares/rendering/glsl/meshes/vertex.glsl"),GL46.GL_VERTEX_SHADER),
                new Shader(FileHandling.loadResource("src/MarchingSquares/rendering/glsl/meshes/fragment.glsl"),GL46.GL_FRAGMENT_SHADER),
        });

        program.link();

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

        mesh.render(program);
    }

    public void cleanup(){
        System.out.println("Cleaning up");
        window.cleanup();

        program.cleanup();
        mesh.cleanup();
    }

}
