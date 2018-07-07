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
        //System.out.println("Entro");
        float tiempo = (float) (delta / 1000);
        this.lastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * this.getEscala()) - (this.getDesfase() * this.getEscala()) >= juegov1.JuegoV1.contenedor.getHeight()) {
            this.position.y = juegov1.JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala());
            
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
            System.out.println(this.position.y);
            this.shape.setY(this.position.y);
        }
        ActionMove(tiempo);
    }

    public void ActionMove(float tiempo) {
       System.out.println(juegov1.JuegoV1.contenedor.getWidth());
        if (juegov1.JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * this.getEscala()) + (this.getDesfase() * this.getEscala())) {
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * this.getEscala()) - (this.getDesfase() * this.getEscala());
            this.shape.setX(this.position.x);
            this.setVx(0);
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.setVx(0);
            }
        }
        this.position.x = this.position.x + (this.getVx() * (tiempo));
        this.shape.setX(this.position.x);
    }
    
    

    @Override
    public void ActionClick(int key) {
        if (key == Input.KEY_D) {
            this.setVx(this.getVelocidadx());
        } else {
            //this.vx = 0;
        }
        if (key == Input.KEY_A) {
            this.setVx(-this.getVelocidadx());
        } else {
            //this.vx = 0;
        }
        if (key == 666) {
            //System.out.println("");
            this.setVx(0);
        }

        if (key == Input.KEY_X && this.puedoSaltar ) {

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
                this.puedoSaltar = true;
                this.shape.setY(this.position.y);
            }
            
        }
        ActionMove(tiempo,con);
    }

    private void ActionMove(float tiempo, ContainerS con) {
       if (juegov1.JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * this.getEscala()) + (this.getDesfase() * this.getEscala())) {
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * this.getEscala()) - (this.getDesfase() * this.getEscala());
            this.shape.setX(this.position.x);
            this.setVx(0);
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.setVx(0);
            }
        }
//        this.position.x = this.position.x + (this.setVx(0); * (tiempo));
         if(this.Choca(con.lista) == UtilEnum.X){
                this.position = this.lastPosition;
            }
        this.shape.setX(this.position.x);
    }
    
    public void debug(){
        System.out.println("velocidad x = " + this.getVx());
        System.out.println("Velocidad y = " + this.getVey0());
        System.out.println("Position x = " + this.position.x);
        System.out.println("Position y = " + this.position.y);
        System.out.println("Gravedad = " + this.gravity);
        System.out.println("Puede saltar = " + this.puedoSaltar);
        System.out.println("Transparente =" + this.transparente);
        System.out.println("Velocidad correr" + this.getVelocidadx());
    }

}
