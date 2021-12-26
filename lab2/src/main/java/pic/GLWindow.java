package pic;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLWindow {
    private int width;
    private int height;
    private long glfwHandle;
    private final int[] xpos = { 0 };
    private final int[] ypos = { 0 };

    public GLWindow(final String title, int width, int height) {
        this(title, width, height, NULL);
    }

    public GLWindow(final String title, int width, int height, long share) {
        glfwHandle = glfwCreateWindow(width, height, title, NULL, share);
        if (glfwHandle == NULL) {
            System.err.println("Невозможно открыть окно GLFW. Если у вас Intel GPU, то он не поддерживает версию 3.3. Попробуйте версию уроков для OpenGL 2.1.\n");
            glfwTerminate();
            throw new RuntimeException("failed to open glfw window");
        }
        glfwMakeContextCurrent(glfwHandle);
        setWidth(width);
        setHeight(height);
        setGLFWHandle(glfwHandle);
        glfwGetWindowPos(glfwHandle, xpos, ypos);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGLFWHandle(long glfwHandle) {
        this.glfwHandle = glfwHandle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getGLFWHandle() {
        return glfwHandle;
    }
}
