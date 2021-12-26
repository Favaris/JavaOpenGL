package pic.constant;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public abstract class Squares {
    private Squares() {}

    public static final float[] fVertices = {
            0, 0, 0,
            1, 0, 0,
            1, 1, 0,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            -1, 1, 0,
            -1, 0, 0
    };

    public static final float[] fColors = {
            255, 0, 0,
            255, 0, 0,
            255, 0, 0,
            255, 0, 0,

            0, 0, 255,
            0, 0, 255,
            0, 0, 255,
            0, 0, 255
    };

    public static final FloatBuffer vertices;
    public static final FloatBuffer colors;

    static {
        vertices = BufferUtils.createFloatBuffer(fVertices.length);
        vertices.put(fVertices);
        vertices.flip();

        colors = BufferUtils.createFloatBuffer(fColors.length);
        colors.put(fVertices);
        colors.flip();
    }
}
