/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import TiposGenerales.ContainerS;
import TiposGenerales.UtilEnum;
import elements.levelcomponents.Platform;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import subsystem.SpriteSheetCutter;

/**
 *
 * @author gerar
 */
public class Character {

    public Punto LastPosition;
    private int vida;
    Animation animations[];
    Animation PrincipalAnimation;
    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = -2300f;
    private float escala;
    private int desfase;
    private float velocidadx;
    private SpriteSheetCutter tijeras;
    private SpriteSheet subImage;
    private float velocidadsalto;
    public Shape shape;
    public boolean puedoSaltar = false;
    private final int desfasey;
    private final int desfaseextra;
    private float aceleracionx;

    public float getVey0() {
        return vey0;
    }

    public void setVey0(float vey0) {
        this.vey0 = vey0;
    }

    public void IniAnimations(Image sprite) {
        tijeras = new SpriteSheetCutter();
        PrincipalAnimation = tijeras.makeAnimation(sprite, 0, 160, 368, 40, 8, 1);
        animations = new Animation[3];
        animations[0] = tijeras.makeAnimation(sprite, 0, 50, 10, 270, 6, 1);
        shape = new Rectangle(this.position.x, this.position.y, this.getAncho(), this.getAlto() - desfaseextra);

    }

    /**
     *
     * @param x
     * @param y
     * @param escala
     * @param desfasex
     * @param velocidad
     * @param velocidadsalto
     * @param desfasey
     * @param desfaseextra
     */
    public Character(float x, float y, float escala, int desfasex, float velocidad, float velocidadsalto, int desfasey, int desfaseextra) {
        try {
            position = new Punto();
            LastPosition = new Punto();
            position.setX(x);
            position.setY(y);
            this.escala = escala;
            this.desfase = desfasex;
            this.velocidadx = velocidad;
            this.velocidadsalto = velocidadsalto;
            this.aceleracionx = 0;

        } catch (Exception error) {
            error.printStackTrace();
        }
        this.desfasey = desfasey;
        this.desfaseextra = desfaseextra;
        //this.aceleracionx = aceleracion;
    }

    public int getAncho() {
        return (int) (this.PrincipalAnimation.getWidth() - desfase) * (int) escala;
    }

    public int getAlto() {
        return (this.PrincipalAnimation.getHeight() - desfasey) * (int) escala;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    public int getDesfase() {
        return desfase;
    }

    public void setDesfase(int desfase) {
        this.desfase = desfase;
    }

    public void RenderDraw(Graphics g) {
        this.PrincipalAnimation.draw(position.x, position.y, this.getAncho(), this.getAlto());
        g.draw(this.shape);
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float y0 = this.position.y;
        if (y0 + this.getAlto() >= JuegoV1.contenedor.getHeight()) {

            this.position.y = JuegoV1.contenedor.getHeight() - this.getAlto();
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.vey0 == this.velocidadsalto) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.vey0 = this.vey0 + (this.gravity * tiempo);
                this.shape.setY(this.position.y);
            }
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);
            this.shape.setY(this.position.y);
        }
        ActionMove(tiempo);

    }

    public void actionClick(int key) {
        if (key == Input.KEY_D) {
            this.vx = this.velocidadx;
        } else {
            //this.vx = 0;
        }
        if (key == Input.KEY_A) {
            this.vx = -this.velocidadx;
        } else {
            //this.vx = 0;
        }
        if (key == 666) {
            //System.out.println("");
            this.vx = 0;
        }

        if (key == Input.KEY_X && this.puedoSaltar == true) {

            this.vey0 = this.velocidadsalto;
            this.puedoSaltar = false;
        }

    }

    public void ActionMove(float tiempo) {
        if (JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * escala) + (desfase * escala)) {
            this.position.x = JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * escala) - (desfase * escala);
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

    public void updatePosition(float delta, ContainerS con) {
        float tiempo = (float) (delta / 1000);
        this.LastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + this.getAlto() >= JuegoV1.contenedor.getHeight()) {

            this.position.y = JuegoV1.contenedor.getHeight() - this.getAlto();
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            this.puedoSaltar = true;
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.vey0 == this.velocidadsalto) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.vey0 = this.vey0 + (this.gravity * tiempo);
                this.shape.setY(this.position.y);
            }
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);
            this.shape.setY(this.position.y);
            System.out.println(this.Choca(con.lista));
            if (this.Choca(con.lista) == UtilEnum.Y) {
                if (this.vey0 > 0) {
                    this.vey0 = -(this.vey0 * 2);
                } else {
                    this.vey0 = 1;
                }
                this.aceleracionx = 0;
                this.position = this.LastPosition;
                this.puedoSaltar = true;
                this.shape.setY(this.position.y);
            }
        }
        ActionMove(tiempo, con);

    }

    public void update(int delta, ContainerS con) {
        float tiempo = (float) (delta / 1000);
        this.LastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * this.getEscala()) - (this.getDesfase() * this.getEscala()) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala());
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.getVey0() == this.velocidadsalto) {
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
            if (this.Choca(con.lista) == UtilEnum.Y) {
                //this.position = this.LastPosition;
                this.puedoSaltar = true;
                this.shape.setY(this.position.y);
            }

        }
        ActionMove(tiempo, con);
    }

    private void ActionMove(float tiempo, ContainerS con) {
        if (JuegoV1.contenedor.getWidth() < this.position.x + this.getAncho()) {
            this.position.x = JuegoV1.contenedor.getWidth() - this.getAncho();
            this.shape.setX(this.position.x);
            this.vx = 0;
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.vx = 0;
                this.puedoSaltar = false;

            }
        }

        this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
        this.shape.setX(this.position.x);
        System.out.println(this.Choca(con.lista));
        if (this.Choca(con.lista) == UtilEnum.Y) {
            this.position = this.LastPosition;
            if (this.vx > 0) {
                this.vx = -250;
                //this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
               // this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                // this.aceleracionx = -500;
            } else {
                if (this.vx < 0) {
                    this.vx = 250;
                   // this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                    //this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                    //this.aceleracionx = 500;
                }
            }
        }
        this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
        //this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
        // this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
        this.shape.setX(this.position.x);
        //aca

    }

    public UtilEnum Choca(Platform[] recList) {
        UtilEnum response = UtilEnum.N;
        for (Platform r : recList) {
            if (this.shape.intersects(r.getContenedor())) {
                /*
                if (this.position.y == this.LastPosition.y) {
                    response = UtilEnum.X;
                }else {
                    response = UtilEnum.Y;
                }*/
                
                response = UtilEnum.Y;

            }
        }

        return response;
    }
}
