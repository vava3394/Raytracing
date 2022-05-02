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
public class Color {
    
    
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static final Color GRAY = new Color(0.2f, 0.2f, 0.2f);
    public static final Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f);
    public static final Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f);
    public static final Color LIGHT_RED = new Color(1.0f, 0.5f, 0.5f);
    public static final Color LIGHT_GREEN = new Color(0.5f, 1.0f, 0.5f);
    public static final Color LIGHT_BLUE = new Color(0.5f, 0.5f, 1.0f);
    public static final Color AMBIENT_LIGHT = new Color(0f, 0f, 0f);

    private float r;
    private float g;
    private float b;
    
    
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public float getR() {
        return (this.r);
    }
    
    public float getG() {
        return (this.g);
    }
    
    public float getB() {
        return (this.b);
    }
    
    public float getRed() {
        return (this.r * 255.0f);
    }

    public float getGreen() {
        return (this.g * 255.0f);
    }

    public float getBlue() {
        return (this.b * 255.0f);
    }
    
    public Color add(Color c) {
        return new Color(this.r + c.r, this.g + c.g, this.b + c.b).normalize();
    }
    
    public Color mult(Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b).normalize();
    }
    
    public Color mult(double s) {
        return new Color(this.r * (float) s, this.g * (float) s, this.b * (float) s).normalize();
    }


    public Color normalize()
    {
        this.r = Math.max(Math.min(this.r, 1.0f),0.0f);
        this.g = Math.max(Math.min(this.g, 1.0f),0.0f);
        this.b = Math.max(Math.min(this.b, 1.0f),0.0f);
        return this;
    }
}
