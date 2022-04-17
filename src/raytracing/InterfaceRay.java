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
    
    protected Vec3f normal;
    protected float dist;
    protected float[] color;
    
    public abstract float getIntersection(Vec3f p, Vec3f v);

    public abstract Vec3f getNormal(Vec3f pos);
    
}
