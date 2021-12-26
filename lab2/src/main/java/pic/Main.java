package pic;

import glm.vec._3.Vec3;
import org.lwjgl.opengl.GL;
import pic.constant.Cube;
import pic.graphcore.Camera;
import pic.graphcore.GLCameraFree;
import pic.graphcore.GLRenderSystem;
import pic.graphcore.GLShader;
import pic.graphcore.factory.GLRenderFactory;

import static glm.Glm.linearRand;
import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {
    private static Vec3 RGB = new Vec3();

    static void keyCallback(long window, int key, int scancode, int action, int mode) {
        System.out.println("key:" + key + "-scancode:" + scancode + "-action:" + action + "-mode:" + mode);

        if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
            System.out.println("SPACE");
            RGB = new Vec3(linearRand(), linearRand(), linearRand());
        }
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
        glfwSetKeyCallback(window, Main::keyCallback);

        Camera Cam = new GLCameraFree();
//        Cam.setPerspective((float) Math.toRadians(45.0), (float)width / height, 0.01f, 1000.0f);

        GLShader shaderBrightDim = new GLShader("BrightAndDim_VertexShader.vs", "BrightAndDim_FragmentShader.fs", null);

        while (!glfwWindowShouldClose(window)) {
            glfwMakeContextCurrent(window);
            double angle = glfwGetTime() * 50.0f;
//            Cam.setPos(new Vec3(2 * cos(angle * PI / 180), 2, 2 * sin(angle * PI / 180)));

            shaderBrightDim.use();
            shaderBrightDim.setVec3("rgb", RGB);
            shaderBrightDim.setMat4("modelView", Cam.getModelView());
            shaderBrightDim.setMat4("modelProj", Cam.getModelProj());
            shaderBrightDim.setFloat("time", (float) glfwGetTime());

            renderer.render(window);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

}