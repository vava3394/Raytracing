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
public class Plan extends InterfaceRay{
        
    protected Vec3d normal;
    protected double dist;
        
    public Plan(Vec3d normal, double distance, Color color, Color colorSpecular, double shininess,double coeffReflection, double coeffTransmission,double indexRefraction){
        this.normal = normal;
        this.dist = distance;
        this.color = color;
        this.colorSpecular = colorSpecular;
        this.shininess = shininess;
        this.coeffReflection = coeffReflection;
        this.coeffTransmission = coeffTransmission;
        this.indexRefraction = indexRefraction;
    }
    
    @Override
    public double getIntersection(Vec3d p, Vec3d v) { 
        
        double div = normal.dotProduct(v);
        if(div != 0.0d){
            double lambda = -(normal.dotProduct(p) + dist) /div;
            if (lambda > 0.0001D)
                return lambda;
        }
        return -1;

    }

    @Override
    public Vec3d getNormal(Vec3d I) {
        return new Vec3d(normal.x, normal.y, normal.z);
    }
        
}
