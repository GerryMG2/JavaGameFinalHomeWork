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

    public ImagenP() {
        position.x = 0f;
        position.y = 0f;
        vey0 = 0f;
        vx = 0f;
    }

    public ImagenP(float x, float y, String ref) throws SlickException {
        super(ref);
        try {
            position = new Punto();
            position.setX(x);
            position.setY(y);
        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    public void updatePosition(float delta) {
        float tiempo = (float) delta / 1000;
        float x0 = (float) this.position.x + this.width;
        float y0 = (float) this.position.y + this.height;
        if (y0 >= JuegoV1.contenedor.getWidth()) {
            this.position.y = JuegoV1.contenedor.getWidth() - this.width;
        } else {

        }

    }

}
