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
        int nbRec = 1;
        
        if(args.length == 2){
            nbRec = Integer.parseInt(args[1]);
            scene = new Scene(Integer.parseInt(args[0]));
        }else{
            System.err.println("error arguments\n" + "java raytracing.JavaTga <number scene> <number of recurrent>");;
            return;
        }
        
        
        
        
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];
        
        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image
                double x = (col - w / 2.0d) / h;
                double y = (row - h / 2.0d) / h;
                double z = -0.8;
                
                Color color = findColor(new Vec3d(0,0,0),new Vec3d(x,y,z),nbRec);
                
                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer
                               
                // Depending on the x position, select a color... 
                buffer[index]=(byte)(color.getBlue()); // Blue in the left part of the image
                buffer[index+1]=(byte)(color.getGreen()); // Green in the middle
                buffer[index+2]=(byte)(color.getRed()); // Red in the right part
            }
        }
        try {
            saveTGA("image.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }
    
    public static Color findColor(Vec3d p,Vec3d v,int nbRec){
        
        //si le nombre de recurence est 0 cela signifie qu'on lance plus de rayon
        if (nbRec <= 0)
            return scene.ambiantLight;
        
        //on cherche le plus proche objet
        double lambdaMin = Double.MAX_VALUE;
        InterfaceRay objMin = null;

        //par rapport à tous les objets de la scene
        for(InterfaceRay f : scene.objs){
            double lambdaObj = f.getIntersection(p, v);

            if(lambdaObj>0.0d && lambdaObj < lambdaMin){
                lambdaMin = lambdaObj;
                objMin = f;
            }
        }
        
        //si aucun objet trouvé
        if(objMin==null)
            return scene.ambiantLight;

        
        //I = p + lambda * v
        Vec3d I = objMin.getIntersectionPoint(p, v, lambdaMin);
        Vec3d n = objMin.getNormal(I);
        
        Color color = objMin.getColor(I).mult(scene.ambiantLight);
        
        //si on est dans l'objet
        boolean inside = n.dotProduct(v) > 0.0d;
        if (inside)
            n = n.mult(-1);

        //une fois l'objet trouvé, on verifie pour tous les source lumineuses, si entre l'objet et la source, il n'y a aucun obstacle
        for(Light l : scene.lights){
            Vec3d IS = l.getPosition().sub(I);
            
            boolean vis = true;
            
            for(InterfaceRay f : scene.objs){
                double lambdaObj = f.getIntersection(I, IS);
                if(0.0d < lambdaObj && lambdaObj < 1.0d){
                    vis = false;
                }
            }
            if(vis){
                n.normalize();
                IS.normalize();
                v.normalize();

                double weight = Math.max(n.dotProduct(IS), 0.0f);

                Color diffuse = l.getDiffuse().mult(objMin.color).mult(weight);
                
                Vec3d halfwayDir = IS.sub(n.mult(2.0d * weight));
                Color specular = l.getSpecular()
                        .mult(Math.pow(Math.max(halfwayDir.dotProduct(v), 0.0d), objMin.getShininess())).mult(objMin.colorSpecular);          

                color = color.add(diffuse).add(specular);
                
                
                if (objMin.getReflectionCoeff() > 0.0d) {
                    Vec3d r = v.sub(n.mult(2.0d * n.dotProduct(v)));
                    r.normalize();

                    color = color.mult(1-objMin.getReflectionCoeff()).add(findColor(I, r,nbRec - 1).mult(objMin.getReflectionCoeff()));
                }

                if (objMin.getTransmissionCoeff() > 0.0d) {
                    double eta = inside ? objMin.getRefractionIndex() : 1.0d / objMin.getRefractionIndex();
                    double c1 = -n.dotProduct(v);
                    double c2 = Math.sqrt(1.0d - eta * eta * (1.0d - c1 * c1));// c2 = sqrt(1 - eta^2 * (1 - c1^2))
                    
                    Vec3d t = v.mult(eta).add(n.mult(eta * c1 - c2));// t = eta * v + (eta * c1 - c2) * nI
                    t.normalize();

                    color = color.add(findColor(I, t, nbRec - 1).mult(objMin.getTransmissionCoeff()));
                }
            }      
        }
        return color;
    }

}

