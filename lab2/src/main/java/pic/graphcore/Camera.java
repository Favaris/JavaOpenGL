package pic.graphcore;

import glm.mat._4.Mat4;
import glm.vec._3.Vec3;

public abstract class Camera {
    public abstract void setPerspective(float fov, float aspect, float near, float far);
    public abstract void setPos(Vec3 pos);
    public abstract void setTarget(Vec3 pos);
    public abstract void start() ;
    public abstract void end();

    public abstract Vec3 getPos();

    Mat4 modelProj = new Mat4(0.0f);
    Vec3 pos = new Vec3(0.f);
    Vec3 direction = new Vec3(0.f);
    Mat4 modelView = new Mat4(0.0f);
}
