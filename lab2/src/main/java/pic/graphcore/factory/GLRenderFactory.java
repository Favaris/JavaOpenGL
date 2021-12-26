package pic.graphcore.factory;

import pic.graphcore.GLRender;
import pic.graphcore.GLRenderSystem;
import pic.graphcore.GLRendererOld2_1;

public abstract class GLRenderFactory {
    private GLRenderFactory() {}
    private static final GLRenderSystem rendererInstance = new GLRender();

    public static GLRenderSystem getGLRenderer() {
        return rendererInstance;
    }
}
