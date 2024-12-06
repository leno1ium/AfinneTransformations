package math;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;

public class Scale implements IAffine{
    private float sx, sy, sz;

    public Scale(float sx, float sy, float sz) {
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        scaleMatrixInit();
    }

    private Matrix4f scaleMatrixInit() {
        Matrix3f scaleMatrix = new Matrix3f();
        scaleMatrix.setElement(0, 0, sx);
        scaleMatrix.setElement(1, 1, sy);
        scaleMatrix.setElement(2, 2, sz);
        return AffineTransformations.makeMatrix4f(scaleMatrix); //ToDO: library method to convert into Matrix4f
    }

    @Override
    public Matrix4f vertexTransform() {
        return scaleMatrixInit();
    }
}
