package math;

import javax.vecmath.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class AffineTransformations { //основная логика вычислений (дубль)
    public void xxx() { //пример применения методов
        Model model = new Model();
        MegaTransform mt = new MegaTransform();
        mt.add(new Translate(1, 3, 0));
        mt.add(new Scale(1, 0, 0));
        mt.add(new Translate(-1, 0, 0));

        Rotate r = new Rotate(0, AXIS.X);

        AffineConverter ac = new AffineConverter();
        ac.set(mt);
        //ac.set(r);
        ac.apply(model);
        //r.addAngle(1);
        mt.add(new Rotate(1, AXIS.X));
        ac.apply(model);

        BuilderAffine x = new BuilderAffine();
        IAffine zzz = x.translate(1, 2, 4).rotateX(30).translateY(10).rotate(10, AXIS.Y).apply(mt).build();

    }

    public static Matrix4f modelMatrix(int tx, int ty, int tz,
                                       double alpha, double beta, double gamma,
                                       int sx, int sy, int sz) {
        Matrix4f modelMatrix = new Matrix4f();
        Matrix4f transitionMatrix = translationMatrix(tx, ty, tz);
        Matrix4f rotationMatrix = makeMatrix4f(rotationMatrix(alpha, beta, gamma));
        Matrix4f scaleMatrix = makeMatrix4f(scaleMatrix(sx, sy, sz));
        modelMatrix.mul(transitionMatrix, rotationMatrix);
        modelMatrix.mul(scaleMatrix);
        return modelMatrix;
    }

    public static Matrix3f scaleMatrix(float sx, float sy, float sz) {
        Matrix3f S3 = new Matrix3f();
        S3.setElement(0, 0, sx);
        S3.setElement(1, 1, sy);
        S3.setElement(2, 2, sz);
        return S3;
    }

    public static Matrix3f rotationAroundAxisMatrix(double alpha, AXIS axis) {
        Matrix3f R3 = new Matrix3f();
        float[] Ra = new float[]{(float) Math.cos(alpha), (float) Math.sin(alpha), (float) -Math.sin(alpha), (float) Math.cos(alpha)};
        int ind;
        if (axis == AXIS.X) ind = 0;
        else {
            if (axis == AXIS.Y) ind = 1;
            else ind = 2;
        }

        int raInd = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == ind || j == ind) {
                    if (j == i) R3.setElement(i, j, 1);
                    else R3.setElement(i, j, 0);
                } else {
                    R3.setElement(i, j, Ra[raInd]);
                    raInd++;
                }
            }
        }
        return R3;
    }

    public static Matrix3f rotationMatrix(double alpha, double beta, double gamma) {
        /*Matrix3f Rx = rotationAroundAxisMatrix(alpha, AXIS.x);
        Matrix3f Ry = rotationAroundAxisMatrix(beta, AXIS.y);
        Matrix3f Rz = rotationAroundAxisMatrix(gamma, AXIS.z);
        Matrix3f R = new Matrix3f();
        R.mul(Rz, Ry);
        R.mul(Rx);*/
        float y1 = (float) cos(beta), y2 = (float) sin(beta), y3 = (float) -sin(beta), y4 = (float) cos(beta);
        float x1 = (float) cos(alpha), x2 = (float) sin(alpha), x3 = (float) -sin(alpha), x4 = (float) cos(alpha);
        float z1 = (float) cos(gamma), z2 = (float) sin(gamma), z3 = (float) -sin(gamma), z4 = (float) cos(gamma);

        float[] floats = new float[]
                {(y1 * z1), (x1 * z2 + y2 * x3 * z1), (z2 * x2 + z1 * y2 * x4),
                        (y1 * z3), (x1 * z4 + y2 * z3 * x3), (z4 * x2 + x4 * y2 * z3),
                        (y3), (y4 * x3), (x4 * y4)};
        return new Matrix3f(floats);
    }

    public static Matrix4f translationMatrix(float tx, float ty, float tz) {
        Matrix4f T4 = new Matrix4f();
        for (int i = 0; i < 3; i++) {
            T4.setElement(i, i, 1);
        }
        T4.setColumn(3, new float[]{tx, ty, tz, 1});
        return T4;
    }

    public static Matrix4f makeMatrix4f(Matrix3f matrix3f) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.set(matrix3f);
        return matrix4f;
    }
}
