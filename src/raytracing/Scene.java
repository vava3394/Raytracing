/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing;

import java.util.ArrayList;

/**
 *
 * @author vava3
 */
public class Scene {
    
    ArrayList<InterfaceRay> objs;
    ArrayList<Light> lights;
    Color ambiantLight;
    
    public Scene(int numScene){
        objs = new ArrayList<>();
        lights = new ArrayList<>();
        ambiantLight = Color.AMBIENT_LIGHT;
        switch (numScene) {
            case 0:
                createScene0();
                break;
            case 1:
                createScene1();
                break;
            case 2:
                createScene2();
                break;
            default:
                createScene3();
        }
    }
    
    private void createScene0(){

        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.WHITE,Color.WHITE, 200.0D, 0.0D,0.0D,0.0D));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -8.0D), 1D, Color.CYAN,Color.WHITE, 100.0D, 0.0D, 0.0D, 0.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -5.0D), 0.5D, Color.RED,Color.WHITE, 100.0D, 0.0D, 0.0D, 0.0D));
        objs.add(new Sphere(new Vec3d(-1.5D, 0.0D, -6.0D), 0.2D, Color.BLUE,Color.WHITE, 100.0D, 0.0D, 0.0D, 0.0D));
        
        lights.add(new Light(new Vec3d(-3,0,-4), Color.WHITE,Color.LIGHT_GRAY));
        lights.add(new Light(new Vec3d(3,2,-4), Color.LIGHT_BLUE,Color.LIGHT_BLUE));        
    }
    
    private void createScene1(){
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, -1.0D), 6.0D, Color.RED,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, 1.0D), 6.0D, Color.GREEN,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));
        objs.add(new Plan(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.WHITE,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));
        objs.add(new Plan(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));
        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));
        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA,Color.WHITE, 20.0D, 0.1D,0.1D,1.0D));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -4.0D), 1D, Color.CYAN,Color.WHITE, 100.0D, 0.35D, 0.25D, 1.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -4.0D), 0.5D, Color.RED,Color.WHITE, 100.0D, 0.35D, 0.25D, 1.0D));
        objs.add(new Sphere(new Vec3d(-2.0D, 0.0D, -4.0D), 0.5D, Color.BLUE,Color.WHITE, 100.0D, 0.35D, 0.25D, 1.0D));
        
        lights.add(new Light(new Vec3d(1,1,0), Color.WHITE,Color.WHITE)); 
    }
    
    private void createScene2(){
        lights.add(new Light(new Vec3d(2,0,1), Color.WHITE,Color.WHITE));
        
        objs.add(new Plan(new Vec3d(-1,0,0),10,Color.BLUE,Color.WHITE,200,0,0,0));
        objs.add(new Plan(new Vec3d(1,0,0),10,Color.RED,Color.WHITE,200,0,0,0));
        objs.add(new Plan(new Vec3d(0,-1,0),2d,Color.WHITE,Color.WHITE,200,0,0,0));
        objs.add(new Plan(new Vec3d(0,1,0),2d,Color.GREEN,Color.WHITE,200,0,0,0));
        objs.add(new Plan(new Vec3d(0,0,1),15,Color.BLACK,Color.WHITE,200,0,0,0));
        objs.add(new Plan(new Vec3d(0,0,-1),10,Color.YELLOW,Color.WHITE,200,0,0,0));
        
        objs.add(new Sphere(new Vec3d(-4.0D,0.0D, 8.0D), 2D, Color.CYAN,Color.WHITE, 10.0D, 0.5D, 0.0, 0D));
        objs.add(new Sphere(new Vec3d(4.0D,0.0D, 8.0D), 2D, Color.MAGENTA,Color.WHITE, 10.0D, 0.5D, 0.0, 0D));
        objs.add(new Sphere(new Vec3d(0,0, -3.0D), 1D, Color.RED,Color.WHITE, 1000.0D, 0.99D, 0, 0D));
    }
    private void createScene3(){
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, -1.0D), 16.0D, Color.RED,Color.WHITE, 200.0, 0.99,0,0));
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, 1.0D), 16.0D, Color.GREEN,Color.WHITE, 200.0, 0.99,0,0));
        objs.add(new Plan(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.WHITE,Color.WHITE, 200.0, 0.99,0,0));
        objs.add(new Plan(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW,Color.WHITE, 200.0, 0.99,0,0));
        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN,Color.WHITE, 200.0, 0.99,0,0));
        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA,Color.WHITE, 200.0, 0.99,0,0));
        
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -15.0D), 0.2D, Color.CYAN,Color.WHITE, 100.0D, 0, 0, 0));
        
        lights.add(new Light(new Vec3d(0,0,0), Color.WHITE,Color.WHITE));
    }    
}
