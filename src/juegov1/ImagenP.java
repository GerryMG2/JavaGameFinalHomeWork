/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author gerar
 */
public class ImagenP extends Image {

    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = -2300f;
    private float escala;
    private int desfase;
    private float velocidadX;
    private float velocidadSalto;
    private int anchoImagen;
    

    public ImagenP() {
        position.x = 0f;
        position.y = 0f;
        vey0 = 0f;
        vx = 0f;
    }

    public ImagenP(float x, float y, float escala, int desfase, float velocidad, float salto, String ref) throws SlickException {
        super(ref);
        try {
            position = new Punto();
            position.setX(x);
            position.setY(y);
            this.escala = escala;
            this.desfase = desfase;
            this.velocidadX = velocidad;
            this.velocidadSalto = salto;
            anchoImagen = (int) this.width * (int) this.escala;

        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    public float getEscala() {
        return escala;
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);

        float y0 = this.position.y;
        if (y0 + (this.height * escala) - (desfase * escala) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.height * escala) + (desfase * escala);
            if (this.vey0 == this.velocidadSalto) {
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));

                this.vey0 = this.vey0 + (this.gravity * tiempo);
            }
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));

            this.vey0 = this.vey0 + (this.gravity * tiempo);
        }

        ActionMove(tiempo);

    }

    public void actionClick(int key) {
        if (key == Input.KEY_D) {
            this.vx = velocidadX;
        } else {
            //this.vx = 0;
        }
        if (key == Input.KEY_A) {
            this.vx = -velocidadX;
        } else {
            //this.vx = 0;
        }
        
        if(key == 666)
        {
            this.vx = 0;
        }
        if (key == Input.KEY_X && this.position.y == JuegoV1.contenedor.getHeight() - (this.height * escala) + (desfase * escala)) {
            this.vey0 = this.velocidadSalto;
        }

    }

    public void ActionMove(float tiempo) {
        if (JuegoV1.contenedor.getWidth() < this.position.x + (this.width * escala) - (desfase * escala)) {
            this.position.x = JuegoV1.contenedor.getWidth() - (this.width * escala) + (desfase * escala);
            this.vx = 0;
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.vx = 0;
            }
        }
        this.position.x = this.position.x + (this.vx * tiempo);
    }

}
