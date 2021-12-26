package pic.graphcore;

import java.nio.FloatBuffer;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GLRendererOld2_1 implements GLRenderSystem {
    private double colorRGB;

    @Override
    public void init() {
        if (!glfwInit()) {
            System.err.println("Ошибка при инициализации GLFW");
        }
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
    }

    @Override
    public void render(long window) {
        clearAndFillBackground();

        glLoadIdentity();
        glRotatef((float) glfwGetTime() * 50.f, 0.f, 0.f, 1.f);

        glBegin(GL_TRIANGLES);

        glColor3f(1.f, 0.f, 0.f);
        glVertex3f(-0.6f, -0.4f, 0.f);
        glColor3f(0.f, 1.f, 0.f);
        glVertex3f(0.6f, -0.4f, 0.f);
        glColor3f(0.f, 0.f, 1.f);
        glVertex3f(0.f, 0.6f, 0.f);

        glEnd();
    }

    @Override
    public void renderTriangleArray(float[] vertices, float[] colors) {
        clearAndFillBackground();

        glBegin(GL_TRIANGLES);

        if (vertices.length % 3 != 0) {
            System.err.println("Triangle error");
            return;
        }

        for (int i = 0; i < vertices.length; i += 3) {
            glColor3f(colors[i], colors[i + 1], colors[i + 2]);
            glVertex3f(vertices[i], vertices[i + 1], vertices[i + 2]);
        }

        glEnd();
    }

    private void clearAndFillBackground() {
        glClearColor((float) sin(colorRGB * PI / 180), (float) abs(cos(colorRGB * PI / 180)), (float) abs(sin(colorRGB * PI / 180) + cos(colorRGB * PI / 180)), 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glEnable(GL_DEPTH_TEST);
        if (colorRGB <= 180.) {
            colorRGB += 0.1;
        } else {
            colorRGB = 0;
        }
    }

    @Override
    public void renderVBO() {
        // . . .
    }
}
