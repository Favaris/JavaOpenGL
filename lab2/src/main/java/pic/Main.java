package pic;

import org.lwjgl.opengl.GL;
import pic.constant.Cube;
import pic.constant.Squares;
import pic.graphcore.GLRender;
import pic.graphcore.GLRenderSystem;
import pic.graphcore.factory.GLRenderFactory;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    static void resize(long window, int width, int height) {
        System.out.println("Width:" + width + "-Height:" + height);

        float ratio = width / (float) height;
        glViewport(0, 0, width, height);
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-ratio, ratio, -1.f, 1.f, 1.f, -1.f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public static void main(String[] args) {
        int width = 320;
        int height = 320;

        System.out.println("Task 2\n" +
                "Author: Ihor Prusan");

        GLRenderSystem renderer = GLRenderFactory.getGLRenderer();

        renderer.init();

        GLWindow win1 = new GLWindow("Ihor Prusan LAB2 win#1", width, height);
        GLWindow win2 = new GLWindow("Ihor Prusan LAB2 win#2", width, height);
        GLWindow win3 = new GLWindow("Ihor Prusan LAB2 win#3", width, height);

        Cube.init();

        while (!glfwWindowShouldClose(win1.getGLFWHandle())  ||
                !glfwWindowShouldClose(win2.getGLFWHandle()) ||
                !glfwWindowShouldClose(win3.getGLFWHandle())
                ) {
            processRendering(width, height, renderer, win1);
            processRendering(width, height, renderer, win2);
            processRendering(width, height, renderer, win3);

            glfwPollEvents();
        }
    }

    private static void processRendering(int width, int height, GLRenderSystem renderer, GLWindow win) {
        int[] w = new int[1];
        w[0] = width;
        int[] h = new int[1];
        h[0] = height;

        glfwMakeContextCurrent(win.getGLFWHandle());
        GL.createCapabilities();
        glfwGetFramebufferSize(win.getGLFWHandle(), w, h);
        glViewport(0, 0, width, height);
        if (renderer instanceof GLRender) {
            renderer.render(win.getGLFWHandle());
        }
        else {
            renderer.renderTriangleArray(Squares.fVertices, Squares.fColors);
        }
        glfwSwapBuffers(win.getGLFWHandle());
    }
}