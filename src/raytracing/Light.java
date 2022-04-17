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
public class Light {
    Vec3f pos;
    float[] colorLight;
    
    public Light(Vec3f pos,float[] colorLight){
        this.pos = pos;
        this.colorLight = colorLight;
    }
}
