package math;


import math.vector.Vector3f;

public class AffineConverter {
    private IAffine modelTransform;
    public void set(IAffine mt){//ToDo: modelMatrix in set
        modelTransform = mt;
    }
    public void apply(Model model){
        for(Vector3f vect: model.vertices){
            //vect = modelTransform.vertexTransform().mul(vect);//ToDO: умножение матрицы на вектор
        }
    }


}
