package pic.graphcore;

import glm.vec._3.Vec3;
import glm.mat._4.Mat4;

import static org.lwjgl.opengl.GL11.*;

public class GLCameraFree extends Camera {
    @Override
    public void setPerspective(float fov, float aspect, float near, float far) {
        glMatrixMode(GL_PROJECTION);
        modelProj = modelProj.perspective(fov, aspect, near, far);
        glLoadMatrixf(modelProj.toFa_());
    }

    @Override
    public void setPos(Vec3 pos) {
        Vec3 target = new Vec3(0.f);
        Vec3 direction = pos.normalize(pos.sub(target));
        modelView = modelView.lookAt(pos, direction, new Vec3(0, 1, 0));

        glMatrixMode(GL_MODELVIEW);
        glLoadMatrixf(modelView.toFa_());
    }

    @Override
    public void setTarget(Vec3 pos) {

    }

    @Override
    public void start() {
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadMatrixf(modelView.toFa_());
    }

    @Override
    public void end() {
        glPopMatrix();
    }

    @Override
    public Vec3 getPos() {
        return pos;
    }
}
