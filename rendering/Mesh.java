package MarchingSquares.rendering;

import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
    private int vaoId;

    private int vertexVboId;
    private int indexVboId;

    private int vertexCount;

    public Mesh(float[] vertices, int[] indices){
        vertexCount=indices.length;

        FloatBuffer vertexBuffer=null;
        IntBuffer indexBuffer=null;

        try{
            vaoId= GL46.glGenVertexArrays();
            GL46.glBindVertexArray(vaoId);

            vertexVboId=GL46.glGenBuffers();
            vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
            vertexBuffer.put(vertices).flip();
            GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, vertexVboId);
            GL46.glBufferData(GL46.GL_ARRAY_BUFFER, vertexBuffer, GL46.GL_STATIC_DRAW);
            GL46.glEnableVertexAttribArray(0);
            GL46.glVertexAttribPointer(0, 2, GL46.GL_FLOAT, false, 0, 0);

            indexVboId=GL46.glGenBuffers();
            indexBuffer = MemoryUtil.memAllocInt(indices.length);
            indexBuffer.put(indices).flip();
            GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, indexVboId);
            GL46.glBufferData(GL46.GL_ELEMENT_ARRAY_BUFFER, indexVboId, GL46.GL_STATIC_DRAW);
        }finally{
            if(vertexBuffer!=null){
                MemoryUtil.memFree(vertexBuffer);
            }if(indexBuffer!=null){
                MemoryUtil.memFree(indexBuffer);
            }
        }
    }

    public void cleanup(){
        GL46.glDisableVertexAttribArray(0);

        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);
        GL46.glDeleteBuffers(vertexVboId);
        GL46.glDeleteBuffers(indexVboId);

        // Delete the VAO
        GL46.glBindVertexArray(0);
        GL46.glDeleteVertexArrays(vaoId);
    }
}
