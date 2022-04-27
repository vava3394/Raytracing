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
                createScene2();
                break;
            default:
                createScene3();
        }
    }
    
    private void createScene0(){
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, -1.0D), 6.0D, Color.RED,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, 1.0D), 6.0D, Color.GREEN,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.WHITE,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA,Color.WHITE, 20.0, 0.1,0,1));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -4.0D), 1D, Color.CYAN,Color.WHITE, 100.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -4.0D), 0.5D, Color.RED,Color.WHITE, 100.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(-2.0D, 0.0D, -4.0D), 0.5D, Color.BLUE,Color.WHITE, 100.0D, 0.35D, 0.15D, 1.0D));
        
        lights.add(new Light(new Vec3d(1,1,0), Color.WHITE,Color.WHITE));
    }
    private void createScene1(){

        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.YELLOW,Color.WHITE, 20.0, 0.1D,0,1));

        objs.add(new Sphere(new Vec3d(0.0D, 0.0D, -8.0D), 1D, Color.CYAN,Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(2.0D, 0.0D, -5.0D), 0.5D, Color.RED,Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        objs.add(new Sphere(new Vec3d(-1.5D, 0.0D, -6.0D), 0.2D, Color.BLUE,Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
        
        lights.add(new Light(new Vec3d(-3,0,-4), Color.LIGHT_GREEN,Color.LIGHT_GREEN));
        lights.add(new Light(new Vec3d(3,2,-4), Color.LIGHT_GREEN,Color.LIGHT_GREEN));
    }
    private void createScene2(){
        Plan gauche = new Plan(new Vec3d(1,0,0),10,Color.RED,Color.WHITE,20,0,0,1);
        
        Plan droite = new Plan(new Vec3d(-1,0,0),10,Color.BLUE,Color.WHITE,20,0,0,1);
        
        Plan bas = new Plan(new Vec3d(0,-1,0),2d,Color.WHITE,Color.WHITE,20,0,0,1);
        
        Plan haut = new Plan(new Vec3d(0,1,0),2d,Color.GREEN,Color.WHITE,20,0,0,1);
        
        Plan avant = new Plan(new Vec3d(0,0,1),15,Color.GRAY,Color.WHITE,20,0,0,1);
        
        Plan arriere = new Plan(new Vec3d(0,0,-1),10,Color.YELLOW,Color.WHITE,20,0,0,1);
        
        Sphere s = (new Sphere(new Vec3d(-4.0D,0.0D, 8.0D), 2D, Color.CYAN,Color.WHITE, 10.0D, 0.75D, 0.0, 1.0D));
        Sphere s1 = (new Sphere(new Vec3d(4.0D,0.0D, 8.0D), 2D, Color.MAGENTA,Color.WHITE, 10.0D, 0.75D, 0.0, 1.0D));
        Sphere s2 = (new Sphere(new Vec3d(0,0, -3.0D), 1D, Color.RED,Color.WHITE, 10.0D, 0.75D, 0.0, 1.0D));
        
        lights.add(new Light(new Vec3d(1,0,1), Color.LIGHT_GRAY,Color.LIGHT_GRAY));
        
        objs.add(droite);
        objs.add(gauche);
        objs.add(haut);
        objs.add(bas);
        objs.add(avant);
        objs.add(arriere);
        objs.add(s);
        objs.add(s1);
        objs.add(s2);
    }
    private void createScene3(){
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, -1.0D), 16.0D, Color.BLACK,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 0.0D, 1.0D), 16.0D, Color.BLACK,Color.WHITE, 20.0, 0.1,0,1));
        objs.add(new Plan(new Vec3d(1.0D, 0.0D, 0.0D), 6.0D, Color.BLACK,Color.WHITE, 20.0, 0,0,1));
        objs.add(new Plan(new Vec3d(-1.0D, 0.0D, 0.0D), 6.0D, Color.BLACK,Color.WHITE, 20.0, 0,0,1));
        objs.add(new Plan(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.BLACK,Color.WHITE, 20.0, 0,0.1,1));
        objs.add(new Plan(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.BLACK,Color.WHITE, 20.0, 0,0.1,1));
        
        
        lights.add(new Light(new Vec3d(-5,0,-15), Color.LIGHT_GREEN,Color.LIGHT_GREEN));
        lights.add(new Light(new Vec3d(5,0,-15), Color.LIGHT_BLUE,Color.LIGHT_BLUE));
        lights.add(new Light(new Vec3d(0,0,-10), Color.LIGHT_RED,Color.LIGHT_RED));
        
    }
    
}
