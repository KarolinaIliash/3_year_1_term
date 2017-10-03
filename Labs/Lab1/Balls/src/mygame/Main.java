package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.light.DirectionalLight;
import com.jme3.util.TangentBinormalGenerator;
import com.jme3.scene.shape.Sphere;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.Random;


public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    protected ArrayList<Geometry> spheres = new ArrayList();
    //Variable for generating balls not in every frame but
    //every hand-picked period of time
    protected float timer = 0;
    //Auxiliary variable for generating balls from different sides of Oz
    protected boolean isPositivePosition = true;
    
    @Override
    public void simpleInitApp() {
        ColorRGBA value = ColorRGBA.Yellow;
        //Initializing four spheres
        spheres.add(makeGeom(0.3f, new Vector3f(0, 0 ,0), new Vector3f(0,0,0), value));
        rootNode.attachChild(spheres.get(0));
        spheres.add(makeGeom(0.3f, new Vector3f(0, -1, -10), new Vector3f(0,0.5f, 5f), value));
        rootNode.attachChild(spheres.get(1));
        spheres.add(makeGeom(0.3f, new Vector3f(0, -1, 20), new Vector3f(0,0.1f,-2f), value));
        rootNode.attachChild(spheres.get(2));
        spheres.add(makeGeom(0.3f, new Vector3f(3, 0, -10), new Vector3f(-0.15f,0,0.5f), value));
        rootNode.attachChild(spheres.get(3));
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1,0,0).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        cam.lookAtDirection(new Vector3f(-1, 0, 0), new Vector3f(0, 1, 0));
        cam.setLocation(new Vector3f(10f, 0, 0));
    }

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f velocity;
        for(int i = 0; i < spheres.size(); i++){
            float z = spheres.get(i).getLocalTranslation().z;
            float y = spheres.get(i).getLocalTranslation().y;
            if(z < 30 && z > -30 && y < 30 && y > -30){
                //Move ball on v*dt
                velocity = spheres.get(i).getUserData("speed");
                spheres.get(i).setLocalTranslation(spheres.get(i).getLocalTranslation().add(velocity.mult(tpf)));
            }
            else{
                //Deleting balls which are definitely invisible from user
                rootNode.detachChild(spheres.get(i));
                spheres.remove(i);
            }
        }
        //Checking whether there are collisions between balls
        for(int i = 0; i < spheres.size(); i++){
            for(int j = i + 1; j < spheres.size(); j++){
                Vector3f centre1 = spheres.get(i).getLocalTranslation();
                Vector3f centre2 = spheres.get(j).getLocalTranslation();
                float radius1 = spheres.get(i).getUserData("radius");
                float radius2 = spheres.get(j).getUserData("radius");
                if(centre1.distance(centre2) <= radius1 + radius2){
                    boom(spheres.get(i), spheres.get(j));
                }
            }
        }
        timer += tpf;
        //Generating new balls
        if(timer > 0.3f && spheres.size() < 80){
            timer = 0;
            float minCoord = -15f;
            float maxCoord = 15f;
            float minSpeed = -3f;
            float maxSpeed = 3f;
            float x = getRandFloat(minCoord, maxCoord);
            float y = getRandFloat(minCoord, maxCoord);
            float sX;
            float sY;
            float z;
            sX = -x*0.1f;
            sY = -y*0.1f;
            float sZ;
            if(isPositivePosition){
                z = 20;
                isPositivePosition = false;
            }
            else{
                z = -20;
                isPositivePosition = true;
            }
            sZ = -z*0.1f;
            float radius = getRandFloat(0f, 1f);
            ColorRGBA value = ColorRGBA.Blue;
            if(radius < 0.1f){
                value = ColorRGBA.Green;
            }
            else if(radius < 0.3f){
                value = ColorRGBA.Red;
            }
            else if(radius < 0.5f){
                value = ColorRGBA.Yellow;
            }
            else if(radius < 0.7f){
                value = ColorRGBA.Magenta;
            }
            else if(radius < 0.9f){
                value = ColorRGBA.Orange;
            }
            spheres.add(makeGeom(radius, new Vector3f(x, y, z), new Vector3f(sX, sY, sZ), value));
            rootNode.attachChild(spheres.get(spheres.size()-1));
        }
    }

    //Auxiliary function which generate random float number in preset interval
    protected float getRandFloat(float min, float max){
        Random random = new Random();
        return random.nextFloat()*(max - min) + min;
    }
    
    //Function which calculate and set new velocity vectors for balls
    protected void boom(Geometry first, Geometry second){
        Vector3f O1 = first.getLocalTranslation();
        Vector3f O2 = second.getLocalTranslation();
        Vector3f x = O1.subtract(O2);
        Vector3f v1 = first.getUserData("speed");
        Vector3f v2 = second.getUserData("speed");
        //Checking whether at least one of balls is
        //moving in the direction of other
        //if(v1.dot(x) >0 && v2.dot(x) < 0) return;
        if(!(v1.dot(x) < 0 || v2.dot(x) > 0)) return;
        Vector3f v1P = v1.project(x);
        Vector3f v2P= v2.project(x);
        //Constant parts of velocity vectors
        Vector3f v1Const = v1.subtract(v1P);
        Vector3f v2Const = v2.subtract(v2P);
        float r1 = first.getUserData("radius");
        float r2 = second.getUserData("radius");
        //Calculate masses of balls. We consider that density = 1
        float m1 = (float) ((4f/3f)*Math.PI*r1*r1*r1);
        float m2 = (float) ((4f/3f)*Math.PI*r2*r2*r2);
        //On the axe OO1 it's central collision 
        //v1New = -v1 + (v1*m1+v2*m2)/(m1+m2)
        //v2New = -v2 + (v1*m1+v2*m2)/(m1+m2)
        Vector3f v1PNew = (((v1P.mult(m1).add(v2P.mult(m2))).divide(m1+m2)).mult(2f)).subtract(v1P);
        Vector3f v2PNew = (((v1P.mult(m1).add(v2P.mult(m2))).divide(m1+m2)).mult(2f)).subtract(v2P);
        first.setUserData("speed", v1Const.add(v1PNew));
        second.setUserData("speed", v2Const.add(v2PNew));
    }
    
    //Creating Geometry with sphere mesh
    protected Geometry makeGeom(float radius, Vector3f transl, Vector3f speed, ColorRGBA value){
        Sphere sphere = new Sphere(32, 32, radius);
        Geometry geom = new Geometry("Sphere", sphere);
        sphere.setTextureMode(Sphere.TextureMode.Projected);
        TangentBinormalGenerator.generate(sphere);
        Material sphereMat = new Material(assetManager,
        "Common/MatDefs/Light/Lighting.j3md");
        sphereMat.setBoolean("UseMaterialColors",true);
        sphereMat.setColor("Diffuse",value);
        geom.setMaterial(sphereMat);
        geom.setLocalTranslation(transl);
        geom.setUserData("speed", speed);
        geom.setUserData("radius", radius);
        return geom;
    }
}
