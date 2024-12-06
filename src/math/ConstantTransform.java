package math;

import javax.vecmath.Matrix4f;

public class ConstantTransform implements IAffine {
    private final Matrix4f modelMatrix;

    public ConstantTransform(Matrix4f modelMatrix) {
        this.modelMatrix = modelMatrix;
    }

    @Override
    public Matrix4f vertexTransform() {
        return modelMatrix;
    }

}
