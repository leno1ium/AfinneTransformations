package tests.com.cgvsu;

import math.AXIS;
import math.Rotate;
import math.Scale;
import math.Translate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;

import static math.AffineTransformations.*;
import static java.lang.Math.PI;


public class AffineTransformationsTest {
    @Test
    public void testScaleMatrix() {
        float[] floats = new float[]{1, 0, 0, 0, 2, 0, 0, 0, 3};
        Matrix3f expectedResult = new Matrix3f(floats);
        Matrix3f result = scaleMatrix(1, 2, 3);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testRotationAroundAxisZMatrix() {
        float[] floats = new float[]{(float) Math.cos((PI / 2)), (float) Math.sin(PI / 2), 0,
                (float) -Math.sin(PI / 2), (float) Math.cos(PI / 2), 0, 0, 0, 1};
        Matrix3f expectedResult = new Matrix3f(floats);
        Matrix3f result = rotationAroundAxisMatrix(PI / 2, AXIS.Z);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testRotationAroundAxisYMatrix() {
        float[] floats = new float[]{(float) Math.cos((PI * 5 / 3)), 0, (float) Math.sin(PI * 5 / 3), 0, 1, 0,
                (float) -Math.sin(PI * 5 / 3), 0, (float) Math.cos(PI * 5 / 3)};
        Matrix3f expectedResult = new Matrix3f(floats);
        Matrix3f result = rotationAroundAxisMatrix(PI * 5 / 3, AXIS.Y);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testRotationAroundAxisXMatrix() {
        float[] floats = new float[]{1, 0, 0, 0, (float) Math.cos((PI)), (float) Math.sin(PI), 0,
                (float) -Math.sin(PI), (float) Math.cos(PI)};
        Matrix3f expectedResult = new Matrix3f(floats);
        Matrix3f result = rotationAroundAxisMatrix(PI, AXIS.X);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testRotationMatrix() {
        double x = PI / 3;
        double y = PI / 6;
        double z = PI / 9;
        Matrix3f Rx = rotationAroundAxisMatrix(x, AXIS.X);
        Matrix3f Ry = rotationAroundAxisMatrix(y, AXIS.Y);
        Matrix3f Rz = rotationAroundAxisMatrix(z, AXIS.Z);
        Matrix3f expectedResult = new Matrix3f();
        expectedResult.mul(Rz, Ry);
        expectedResult.mul(Rx);
        Matrix3f result = rotationMatrix(x, y, z);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testTranslationMatrix() {
        float[] floats = new float[]{1, 0, 0, 30, 0, 1, 0, 60, 0, 0, 1, 80, 0, 0, 0, 1};
        Matrix4f expectedResult = new Matrix4f(floats);
        Matrix4f result = translationMatrix(30, 60, 80);
        Assertions.assertTrue(result.equals(expectedResult));
    }
    @Test
    public void testMakeMatrix4f() {
        float[] floats = new float[]{1,2,3,0,4,5,6,0,7,8,9,0,0,0,0,1};
        Matrix4f expectedResult = new Matrix4f(floats);
        float[] floatsTest = new float[]{1,2,3,4,5,6,7,8,9};
        Matrix4f result = makeMatrix4f(new Matrix3f(floatsTest));
        Assertions.assertTrue(result.equals(expectedResult));
    }
    @Test
    public void testTranslationMatrixInit() {
        float[] floats = new float[]{1, 0, 0, 30, 0, 1, 0, 60, 0, 0, 1, 80, 0, 0, 0, 1};
        Matrix4f expectedResult = new Matrix4f(floats);
        Translate trans = new Translate(30, 60, 80);
        Matrix4f result = trans.vertexTransform();
        Assertions.assertTrue(result.equals(expectedResult));
    }
    @Test
    public void testRotationMatrixInit() {
        float[] floats = new float[]{1, 0, 0, 0, (float) Math.cos((PI)), (float) Math.sin(PI), 0,
                (float) -Math.sin(PI), (float) Math.cos(PI)};
        Matrix4f expectedResult = makeMatrix4f(new Matrix3f(floats));
        Matrix4f result = new Rotate(PI, AXIS.X).vertexTransform();
        Assertions.assertTrue(result.equals(expectedResult));
    }
    @Test
    public void testScaleMatrixInit() {
        float[] floats = new float[]{1, 0, 0, 0, 2, 0, 0, 0, 3};
        Matrix4f expectedResult = makeMatrix4f(new Matrix3f(floats));
        Matrix4f result = new Scale(1, 2, 3).vertexTransform();
        Assertions.assertTrue(result.equals(expectedResult));
    }

}
