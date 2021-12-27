package pic;

import glm.vec._3.Vec3;
import org.lwjgl.opengl.GL;
import pic.constant.Cube;
import pic.graphcore.Camera;
import pic.graphcore.GLCameraFree;
import pic.graphcore.GLRenderSystem;
import pic.graphcore.factory.GLRenderFactory;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.system.MemoryUtil.NULL;

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
        int width = 640;
        int height = 480;

        System.out.println("Task 3\n" +
                "Author: Ihor Prusan");

        GLRenderSystem renderer = GLRenderFactory.getGLRenderer();

        renderer.init();
        Cube.init();

        long window = glfwCreateWindow(width, height, "Ihor Prusan LAB3", NULL, NULL);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);

        Camera Cam = new GLCameraFree();
        Cam.setPerspective((float) Math.toRadians(45.0), (float)width / height, 0.01f, 1000.0f);

        while (!glfwWindowShouldClose(window)) {
            glfwMakeContextCurrent(window);
            double angle = glfwGetTime() * 50.0f;
            Cam.setPos(new Vec3(2 * cos(angle * PI / 180), 2, 2 * sin(angle * PI / 180)));
//            Cam.setPos(new Vec3(0.f, 2.f, 0.f));
            Cam.start();
            renderer.render(window);
            Cam.end();
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

}