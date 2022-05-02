/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing;

/**
 *
 * @author vava3
 */
public abstract class InterfaceRay {
    
    protected Color color;
    protected Color colorSpecular;
    
    protected double shininess;
    
    protected double coeffReflection;
    protected double coeffTransmission;
    protected double indexRefraction;
    
    public abstract double getIntersection(Vec3d p, Vec3d v);

    public abstract Vec3d getNormal(Vec3d I);
    
    public Vec3d getIntersectionPoint(Vec3d P, Vec3d v, double lambdaI) {
        return P.add(v.mult(lambdaI));
    }
    
    public abstract Color getColor(Vec3d I);

    public Color getSpecularColor() {
        return colorSpecular;
    }

    public double getShininess() {
        return shininess;
    }
    
    public double getReflectionCoeff() {
        return coeffReflection;
    }

    public double getTransmissionCoeff() {
        return coeffTransmission;
    }
    
    public double getRefractionIndex() {
        return indexRefraction;
    }
    
}
