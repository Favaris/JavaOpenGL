import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    public static void argsEcho(String[] args) {
        System.out.println("____ARGS____");
        if (args == null || args.length == 0) {
            System.out.println("NO ARGS");
        } else {
            for (String s : args) {
                System.out.println(s);
            }
        }
        System.out.println("____________\n");
    }

    public static void main(String[] args) {
        argsEcho(args);
        System.out.println("Hello OpenGL");
        System.out.println("Author: Igor Prusan");

        if (!glfwInit()) {
            System.err.println("Ошибка при инициализации GLWF");
            return;
        }
        glfwWindowHint(GLFW_SAMPLES, 4); // 4x Сглаживание
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // М
        // ы хотим использовать OpenGL 3.3
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        // glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // To make MacOS happy; should not be needed
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); // Мы не хотим старый OpenGL


        long monitor = glfwGetPrimaryMonitor();
        final GLFWVidMode mode = glfwGetVideoMode(monitor);
        glfwWindowHint(GLFW_RED_BITS, mode.redBits());
        glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits());
        glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits());
        glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());


        long window = glfwCreateWindow(640, 480, "Lesson 01 - RAINBOW - Igor Prusan PA-19-1", NULL, NULL);
        if (window == NULL) {
            System.err.println("Невозможно открыть окно GLFW. Если у вас Intel GPU, то он не поддерживает версию 3.3. Попробуйте версию уроков для OpenGL 2.1.\n");
            glfwTerminate();
            return;
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);

        float colorRGB = 0.0f;
        do {
            // Пока что ничего не выводим. Это будет в уроке 2.
            glClearColor((float) sin(colorRGB * PI / 180), (float) abs(cos(colorRGB * PI / 180)), (float) abs(sin(colorRGB * PI / 180) + cos(colorRGB * PI / 180)), 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            // Сбрасываем буферы
            glfwSwapBuffers(window);
            glfwPollEvents();

            {
                //colorRGB<=180?colorRGB+=0.1:colorRGB=0;
                if (colorRGB <= 180) colorRGB += 0.1;
                else colorRGB = 0;
            }
        } while (glfwGetKey(window, GLFW_KEY_ESCAPE) != GLFW_PRESS && !glfwWindowShouldClose(window));

        glfwTerminate();
    }

}