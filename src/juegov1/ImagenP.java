/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author gerar
 */
public class ImagenP extends Image{
    public Punto position;
    private float vx;
    private float vey0;
    public ImagenP (){
        position.x = 0;
        position.y = 0;
        vey0 = 0f;
        vx = 0f;
    }
    
    

    public ImagenP(int x,int y,String ref) throws SlickException {
        super(ref);
        position.x = (float)x;
        position.y = (float)y;
    }
    
    public void updatePosition(float delta){
        float tiempo = delta / 1000;
        float x0 = (float) this.position.x + this.width;
        float y0 = (float) this.position.y + this.height;
        if(y0 >= JuegoV1.contenedor.getWidth())
        {
            this.position.y = JuegoV1.contenedor.getWidth() - this.width;
        }else{
            
        }
        
    }

    
    

}
