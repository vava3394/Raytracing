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
    public Color ambientLight;
    
    public Light(Vec3d pos,Color colorLight,Color colorSpecular,Color ambientLight){
        this.pos = pos;
        this.colorLight = colorLight;
        this.colorSpecular = colorSpecular;
        this.ambientLight = ambientLight;
    }
    
    public Light(Vec3d pos,Color colorLight,Color colorSpecular){
        this.pos = pos;
        this.colorLight = colorLight;
        this.colorSpecular = colorSpecular;
        this.ambientLight = new Color(0.1f, 0.1f, 0.1f);
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
