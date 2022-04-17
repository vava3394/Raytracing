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
        
    public Plan(){
        
    }
        
    public Plan(Vec3f normal, float distance, float[] color){
        this.normal = normal;
        this.dist = distance;
        this.color = color;
        this.dist = distance;
    }
    
    @Override
    public float getIntersection(Vec3f p, Vec3f v) { 
        
        float div = (normal.x*v.x+normal.y*v.y+normal.z*v.z);
        if(div == 0){
            return -1;
        }
        float lambda = -(normal.x*p.x+normal.y*p.y+normal.z*p.z+dist)/div;
        return lambda;
    }

    @Override
    public Vec3f getNormal(Vec3f pos) {
        return this.normal;
    }

    
}
