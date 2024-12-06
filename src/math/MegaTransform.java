package math;

import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.List;

public class MegaTransform implements IAffine{//composite
    // point transformations
    private final List<IAffine> affine = new ArrayList<>();

    public MegaTransform() {}

    @Override
    public Matrix4f vertexTransform() {
        Matrix4f modelMatrix = new Matrix4f();
        for (int i = 0; i < 3; i++) {           //ToDo: единичная матрица в библиотеке
            modelMatrix.setElement(i, i, 1);
        }
        for (IAffine a : affine) {
            modelMatrix.mul(a.vertexTransform());
        }
        return modelMatrix;
    }

    public void add(IAffine a) {
        affine.add(a);
    }

    public void clear() {
        this.affine.clear();
    }
}
