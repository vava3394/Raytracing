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
    private Vec3d pos;
    private final Color colorLight;
    private final Color colorSpecular;
    
    public Light(Vec3d pos,Color colorLight,Color colorSpecular){
        this.pos = pos;
        this.colorLight = colorLight;
        this.colorSpecular = colorSpecular;
    }

    public Vec3d getPosition() {
        return pos;
    }

    public Color getDiffuse() {
        return colorLight;
    }

    public Color getSpecular() {
        return colorSpecular;
    }
}
