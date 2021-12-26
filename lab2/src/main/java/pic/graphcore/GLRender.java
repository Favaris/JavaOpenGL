package pic.graphcore;

import pic.constant.Cube;

import java.nio.FloatBuffer;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLRender implements GLRenderSystem{
    private double colorRGB = 0.;

    @Override
    public void init() {
        if (!glfwInit()) {
            System.err.println("Ошибка при инициализации GLFW\n");
            return;
        }

        glfwWindowHint(GLFW_SAMPLES, 4); // 4x Сглаживание
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
    }

    @Override
    public void render(long window) {
        glClearColor((float) sin(colorRGB * PI / 180), (float) abs(cos(colorRGB * PI / 180)), (float) abs(sin(colorRGB * PI / 180) + cos(colorRGB * PI / 180)), 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // also clear the depth buffer now!
        if (colorRGB <= 180.) {
            colorRGB += 0.1;
        } else {
            colorRGB = 0;
        }
        glMatrixMode(GL_MODELVIEW); //set the matrix to model view mode

        glPushMatrix(); // push the matrix
//        glRotatef((float) glfwGetTime() * 50.0f, 1.f, 1.f, 0.f); //apply transformation

        int id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, Cube.vertices, GL_STATIC_DRAW);
        glVertexPointer(3, GL_FLOAT, 0, NULL);
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * 4, NULL);
        glEnableVertexAttribArray(0);

        glEnableClientState(GL_VERTEX_ARRAY);
        glDrawArrays(GL_TRIANGLES, 0, Cube.fVertices.length / 5);
        glPopMatrix(); //pop the matrix
        glDisableClientState(GL_VERTEX_ARRAY);
    }

    @Override
    public void renderTriangleArray(float[] vertices, float[] colors) {

    }

    @Override
    public void renderVBO() {

    }
}
