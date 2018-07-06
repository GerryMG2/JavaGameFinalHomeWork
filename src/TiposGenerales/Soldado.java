/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import juegov1.JuegoV1;
import org.newdawn.slick.Input;

/**
 *
 * @author gerar
 */
public class Soldado extends personaje {

    public Soldado(float x, float y, float gravity, float escala, int desfase, float velocidadSalto, float velocidad) {
        super(x, y, gravity, escala, desfase, velocidadSalto, velocidad);
    }

    
    
    

    @Override
    public void update(int delta) {
        float tiempo = (float) (delta / 1000);
        this.lastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * this.getEscala()) - (this.getDesfase() * this.getEscala()) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala());
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.getVey0() == this.getVelocidadsalto()) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.getVey0() * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.setVey0(this.getVey0() + (this.gravity * tiempo));
                this.shape.setY(this.position.y);
            }
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.getVey0() * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.setVey0(this.getVey0() + (this.gravity * tiempo));
            this.shape.setY(this.position.y);
        }
        ActionMove(tiempo);
    }

    public void ActionMove(float tiempo) {
        if (juegov1.JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * this.getEscala()) + (this.getDesfase() * this.getEscala())) {
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * this.getEscala()) - (this.getDesfase() * this.getEscala());
            this.shape.setX(this.position.x);
            this.vx = 0;
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.vx = 0;
            }
        }
        this.position.x = this.position.x + (this.vx * (tiempo));
        this.shape.setX(this.position.x);
    }
    
    

    @Override
    public void ActionClick(int key) {
        if (key == Input.KEY_D) {
            this.vx = this.getVelocidadx();
        } else {
            //this.vx = 0;
        }
        if (key == Input.KEY_A) {
            this.vx = -this.getVelocidadx();
        } else {
            //this.vx = 0;
        }
        if (key == 666) {
            //System.out.println("");
            this.vx = 0;
        }

        if (key == Input.KEY_X && this.position.y == juegov1.JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala())) {

            this.setVey0(this.getVelocidadsalto());
        }

    }

    @Override
    public void update(int delta, ContainerS con) {
        float tiempo = (float) (delta / 1000);
        this.lastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * this.getEscala()) - (this.getDesfase() * this.getEscala()) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala());
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.getVey0() == this.getVelocidadsalto()) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.getVey0() * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.setVey0(this.getVey0() + (this.gravity * tiempo));
                this.shape.setY(this.position.y);
            }
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.getVey0() * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.setVey0(this.getVey0() + (this.gravity * tiempo));
            this.shape.setY(this.position.y);
            if(this.Choca(con.lista) == UtilEnum.Y){
                this.position = this.lastPosition;
            }
            if (this.getVey0() == this.getVelocidadsalto()) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.getVey0() * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.setVey0(this.getVey0() + (this.gravity * tiempo));
                this.shape.setY(this.position.y);
            }
        }
        ActionMove(tiempo,con);
    }

    private void ActionMove(float tiempo, ContainerS con) {
       if (juegov1.JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * this.getEscala()) + (this.getDesfase() * this.getEscala())) {
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * this.getEscala()) - (this.getDesfase() * this.getEscala());
            this.shape.setX(this.position.x);
            this.vx = 0;
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.vx = 0;
            }
        }
        this.position.x = this.position.x + (this.vx * (tiempo));
         if(this.Choca(con.lista) == UtilEnum.X){
                this.position = this.lastPosition;
            }
        this.shape.setX(this.position.x);
    }
    
    

}
