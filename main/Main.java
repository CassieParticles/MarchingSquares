package MarchingSquares.main;

import MarchingSquares.utils.Timer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL46;

public class Main {
    private Window window;
    private Timer timer;
    private Input input;

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

    public void init(){
        window=new Window(600,600,"Marching squares");
        timer=new Timer(60,60); //UPS,FPS
        input=new Input();

        window.init();
        input.init(window);

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
    }

    public void cleanup(){
        System.out.println("Cleaning up");
        window.cleanup();
    }

}
