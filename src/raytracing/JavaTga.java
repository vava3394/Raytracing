package raytracing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class JavaTga
{
    
    /**
     * 
     * @param fout : output file stream
     * @param s : short to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fout,int n) throws IOException
    {
        fout.write(n&255);
        fout.write((n>>8)&255);
    }

    /**
     * 
     * @param filename name of final TGA file
     * @param buffer buffer that contains the image. 3 bytes per pixel ordered this way : Blue, Green, Red
     * @param width Width of the image
     * @param height Height of the image
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    private static void saveTGA(String filename, byte buffer[], int width, int height) throws IOException, UnsupportedEncodingException {

        FileOutputStream fout = new FileOutputStream(new File(filename));

        fout.write(0); // Comment size, no comment
        fout.write(0); // Colormap type: No colormap
        fout.write(2); // Image type
        writeShort(fout,0); // Origin
        writeShort(fout,0); // Length
        fout.write(0); // Depth
        writeShort(fout,0); // X origin
        writeShort(fout,0); // Y origin
        writeShort(fout,width); // Width of the image
        writeShort(fout,height); // Height of the image
        fout.write(24); // Pixel size in bits (24bpp)
        fout.write(0); // Descriptor

        /* Write the buffer */
        fout.write(buffer);

        fout.close();
    }

    
    
    
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        ArrayList<InterfaceRay> objs = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();
        
        objs = createObjs();
        lights = createLight();
        
        
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];
        
        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image
      
                float x = (col-w/2)*2;
                float y = (row-h/2)*2;
                float z = -1;
                
                float[] color = findColor(new Vec3f(0,0,0),new Vec3f(x,y,z),objs,lights);
                
                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer
                               
                // Depending on the x position, select a color... 
                buffer[index]=(byte)color[2]; // Blue in the left part of the image
                buffer[index+1]=(byte)color[1]; // Green in the middle
                buffer[index+2]=(byte)color[0]; // Red in the right part
            }
        }
        try {
            saveTGA("imagetest.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }
    
    public static ArrayList<Light> createLight(){
        ArrayList<Light> lights = new ArrayList<>(); 
        
        lights.add(new Light(new Vec3f(0,0,0), new float[]{0.5f,1f,0.5f}));
        
        return lights;
    }
    
    public static ArrayList<InterfaceRay> createObjs(){
        ArrayList<InterfaceRay> objs = new ArrayList<>(); 
        
        Plan gauche = new Plan(new Vec3f(1,0,0),400,new float[]{255,0,0});
        
        Plan droite = new Plan(new Vec3f(-1,0,0),400,new float[]{0,0,255});
        
        Plan haut = new Plan(new Vec3f(0,-1,0),400,new float[]{255,255,255});
        
        Plan bas = new Plan(new Vec3f(0,1,0),400,new float[]{0,255,0});
        
        Plan millieu = new Plan(new Vec3f(0,0,1),2,new float[]{120,50,10});
        
        objs.add(droite);
        objs.add(gauche);
        objs.add(haut);
        objs.add(bas);
        objs.add(millieu);
        
        return objs;
    }
    
    public static float[] findColor(Vec3f posView,Vec3f posPixel,ArrayList<InterfaceRay> objs,ArrayList<Light> lights){
        
        float[] color = new float[]{0,0,0};
        
        float lambdaMin = Float.MAX_VALUE;
        InterfaceRay objMin = null;

        for(InterfaceRay f : objs){
            float lambda = f.getIntersection(posView, posPixel);

            if(lambda < lambdaMin && lambda>0){
                lambdaMin = lambda;
                objMin = f;
            }
        }
        
        if(objMin!=null){
            //posView + lambdaMin*posPixel
            Vec3f I = new Vec3f(posView).add(new Vec3f(posPixel).scale(lambdaMin));
            Vec3f n = objMin.getNormal(I);
            //ambiant Light
            color[0] += objMin.color[0]*0.1f;
            color[1] += objMin.color[1]*0.1f;
            color[2] += objMin.color[2]*0.1f;
            
            for(Light l : lights){
                boolean vis = true;
                float lambdaObj;
                Vec3f IS = new Vec3f(l.pos).sub(I);
                for(InterfaceRay f : objs){
                    lambdaObj = f.getIntersection(I, IS);
                    if(lambdaObj>0 && lambdaObj<1){
                        vis = false;
                    }
                }
                if(vis){
                    float weight = Math.max(n.dotProduct(IS), 0.0f);
                    float[] diffuse = new float[]{weight*l.colorLight[0],
                                                  weight*l.colorLight[1],
                                                  weight*l.colorLight[2]
                                                 };
                    color[0] += diffuse[0];
                    color[1] += diffuse[1];
                    color[2] += diffuse[2];
                }
            }
        }
        
        return color;
    }
}

