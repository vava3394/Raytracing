package raytracing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class JavaTga
{
    private static Scene scene;
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
        
        
        scene = new Scene(Integer.parseInt(args[0]));
        
        
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];
        
        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image
      
                double x = (col - w / 2.0D) / h;
                double y = (row - h / 2.0D) / h;
                double z = 0.8;
                
                Color color = findColor(new Vec3d(0,0,0),new Vec3d(x,y,z).mult(-1),2);
                
                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer
                               
                // Depending on the x position, select a color... 
                buffer[index]=(byte)(color.getBlue()); // Blue in the left part of the image
                buffer[index+1]=(byte)(color.getGreen()); // Green in the middle
                buffer[index+2]=(byte)(color.getRed()); // Red in the right part
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
    
    
    
    public static void createObjs(){ 

        
        /*
        Plan gauche = new Plan(new Vec3d(1,0,0),10,Color.RED,Color.WHITE,20,0.1,0,1);
        
        Plan droite = new Plan(new Vec3d(-1,0,0),10,Color.BLUE,Color.WHITE,20,0.1,0,1);
        
        Plan bas = new Plan(new Vec3d(0,-1,0),2d,Color.WHITE,Color.WHITE,20,0,0.1,1);
        
        Plan haut = new Plan(new Vec3d(0,1,0),2d,Color.GREEN,Color.WHITE,20,0,0.1,1);
        
        Plan millieu = new Plan(new Vec3d(0,0,1),10,Color.YELLOW,Color.WHITE,20,0.1,0,1);
        
        Sphere s = new Sphere(new Vec3d(-2.0D,0.0D, -4.0D), 0.5D, Color.CYAN, Color.WHITE,10,0.75,0);
        Sphere s1 = new Sphere(new Vec3d(2.0D,0.0D, -4.0D), 0.5D, Color.MAGENTA, Color.WHITE,100,0.75,0);
        
        objs.add(droite);
        objs.add(gauche);
        objs.add(haut);
        objs.add(bas);
        objs.add(millieu);
        objs.add(s);
        objs.add(s1);
        */
    }
    
    public static Color findColor(Vec3d p,Vec3d v,int nbRay){
        if (nbRay <= 0)
            return Color.AMBIENT_LIGHT;
        
        double lambdaMin = Double.MAX_VALUE;
        InterfaceRay objMin = null;

        for(InterfaceRay f : scene.objs){
            double lambda = f.getIntersection(p, v);

            if(lambda>0.0d && lambda < lambdaMin){
                lambdaMin = lambda;
                objMin = f;
            }
        }
        
        if(objMin==null)
            return Color.AMBIENT_LIGHT;

        
        //I = p + lambda * v
        Vec3d I = objMin.getIntersectionPoint(p, v, lambdaMin);
        Vec3d n = objMin.getNormal(I);
        
        boolean inside = n.dotProduct(v) > 0.0D;
        if (inside)
            n = n.mult(-1);
        
        Color color = objMin.getColor(I).mult(Color.AMBIENT_LIGHT);

        for(Light l : scene.lights){
            Vec3d IS = l.getPosition().sub(I);
            
            boolean vis = true;
            
            for(InterfaceRay f : scene.objs){
                double lambdaObj = f.getIntersection(I, IS);
                if(0.0D < lambdaObj && lambdaObj < 1.0D){
                    //System.out.println("NON LIGHT");
                    vis = false;
                }
            }
            if(vis){
                n.normalize();
                IS.normalize();
                v.normalize();

                double weight = Math.max(n.dotProduct(IS), 0.0f);

                Color diffuse = l.getDiffuse().mult(objMin.color).mult(weight);

                //Vec3d halfwayDir = IS.sub(n); // direction
                
                Vec3d halfwayDir = IS.sub(n.mult(2.0D * weight)); // direction
                Color specular = l.getSpecular().mult(objMin.getSpecularColor())
                        .mult(Math.pow(Math.max(halfwayDir.dotProduct(v), 0.0d), objMin.getShininess())); 
                  
                  
                color = color.add(diffuse).add(specular);
                
                if (objMin.getReflectionCoeff() > 0.0D) {
                    Vec3d r = v.sub(n.mult(2.0D * n.dotProduct(v))); // r = v - 2 * nIDotV * nI
                    r.normalize(); // r / ||r||
                    color = color.add(findColor(I, r,nbRay - 1).mult(objMin.getReflectionCoeff()));
                }

                if (objMin.getTransmissionCoeff() > 0.0D) {
                    double eta = inside ? objMin.getRefractionIndex() : 1.0D / objMin.getRefractionIndex();
                    double c1 = -n.dotProduct(v); // c1 = nI . v
                    double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
                    Vec3d t = v.mult(eta).add(n.mult(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
                    t.normalize(); // t / ||t||
                    color = color.add(findColor(I, t, nbRay - 1).mult(objMin.getTransmissionCoeff()));
                }
            }
        }
        
        return color;
    }

}

