package pic.graphcore;

import java.nio.FloatBuffer;

public interface GLRenderSystem {
    void init();

     void render(long window);

     void renderTriangleArray(float[] vertices, float[] colors);

     void renderVBO();
}
