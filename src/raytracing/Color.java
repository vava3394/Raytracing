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
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f);
    public static final Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f);
    public static final Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f);
    public static final Color LIGHT_RED = new Color(1.0f, 0.5f, 0.5f);
    public static final Color LIGHT_GREEN = new Color(0.5f, 1.0f, 0.5f);
    public static final Color LIGHT_BLUE = new Color(0.5f, 0.5f, 1.0f);
    public static final Color AMBIENT_LIGHT = new Color(0.1f, 0.1f, 0.1f);

    private float r;
    private float g;
    private float b;
    
    
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
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
        return new Color(Math.min(this.r + c.r, 1.0f), Math.min(this.g + c.g, 1.0f), Math.min(this.b + c.b, 1.0f));
    }
    
    public Color mult(Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
    }
    
    public Color mult(double s) {
        return new Color(this.r * (float) s, this.g * (float) s, this.b * (float) s);
    }
}
