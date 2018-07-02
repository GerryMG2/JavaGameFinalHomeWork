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
public class ImagenP extends Image {

    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = -1300f;
    private float escala;
    private int desfase;

    public ImagenP() {
        position.x = 0f;
        position.y = 0f;
        vey0 = 0f;
        vx = 0f;
    }

    public ImagenP(float x, float y,float escala,int desfase, String ref) throws SlickException {
        super(ref);
        try {
            position = new Punto();
            position.setX(x);
            position.setY(y);
            this.escala = escala;
            this.desfase = desfase;
        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    public float getEscala() {
        return escala;
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float x0 = (float) this.position.x + (this.width * escala);
        float y0 = this.position.y;
        if (y0 + (this.height * escala) - (desfase * escala) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight()- (this.height * escala) + (desfase*escala);
        } else {
            this.position.y =  (float)this.position.y -  
                    (float)(this.vey0 * tiempo) + 
                    (float)(0.5f * (-gravity) * (float)(Math.pow(tiempo, 2))) ;
            
            this.vey0 = this.vey0 + (this.gravity * tiempo);
        }

    }

}
