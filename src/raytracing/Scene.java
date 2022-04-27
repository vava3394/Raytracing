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
    
    public Scene(int numScene){
        objs = new ArrayList<>();
        lights = new ArrayList<>();
        switch (numScene) {
            case 0:
                createScene0();
                break;
            case 1:
                createScene1();
                break;
            case 2:
                
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void createScene0(){
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, -1.0D), 6.0D, Color.RED, Color.LIGHT_GRAY, 20.0D, 0.1,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, 1.0D), 6.0D, Color.GREEN, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));
        objs.add(new Plan(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.WHITE, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));
        objs.add(new Plan(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));
        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -4.0D), 1D, Color.CYAN, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -4.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(-2.0D, 0.0D, -4.0D), 0.5D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        
        lights.add(new Light(new Vec3d(-1,-1,0), Color.WHITE,Color.WHITE));
    }
    private void createScene1(){

        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.GREEN, Color.LIGHT_GRAY, 20.0D, 0.1D,0,1));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -8.0D), 1D, Color.CYAN, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -5.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(-1.5D, 0.0D, -6.0D), 0.2D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        
        lights.add(new Light(new Vec3d(-3,0,-4), Color.LIGHT_RED,Color.LIGHT_RED));
    }
    private void createScene2(){
        
    }
    
}
