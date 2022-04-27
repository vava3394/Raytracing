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
public class Sphere extends InterfaceRay{

    private Vec3d center;
    private double rayon;
    
    public Sphere(Vec3d center, double rayon, Color color, Color colorSpecular, double shininess,double coeffReflection, double coeffTransmission,double indexRefraction){
        this.center = center;
        this.rayon = rayon;
        this.color = color;
        this.colorSpecular = colorSpecular;
        this.shininess = shininess;
        this.coeffReflection = coeffReflection;
        this.coeffTransmission = coeffTransmission;
        this.indexRefraction = indexRefraction;
    }
    
    @Override
    public double getIntersection(Vec3d p, Vec3d v) {
        Vec3d CP = p.sub(center);
        
        double a = v.dotProduct(v);
        double b = v.mult(2.0D).dotProduct(CP);
        double c = CP.dotProduct(CP) - rayon * rayon;
        
        double delta = (b*b)-(4*a*c);
        
        if(delta == 0){
            double lambda = -b/(2*a);
            if (lambda > 0.0001D)
                return lambda;
        }else if(delta>0){
            double lambda1 = (-b-Math.sqrt(delta))/(2*a);
            double lambda2 = (-b+Math.sqrt(delta))/(2*a);
            if (lambda1 < 0.0001D && 0.0001D < lambda2) {
                return lambda2;
            } else if (0.0001D < lambda1 && lambda1 < lambda2) {
                return lambda1;
            }
        }
        return -1; 
        
    } 

    @Override
    public Vec3d getNormal(Vec3d I) {
        Vec3d CI = I.sub(center);
        CI.normalize();

        return CI;
    }
    
}
