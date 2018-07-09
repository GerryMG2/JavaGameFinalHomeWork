/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import subsystem.LeverLoader;
import TiposGenerales.ContainerS;
import TiposGenerales.UtilEnum;
import elements.levelcomponents.Bullet;
import elements.levelcomponents.Platform;
import elements.leveltypes.StaticLevel;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import subsystem.SpriteSheetCutter;

/**
 *
 * @author gerar
 */
public class Character {

    public final int IDLE = 0;
    public final int SALTAR = 6;
    public final int MORIR = 8;
    public final int CAMINAR = 1;
    public final int APUNTAR = 3;

    public boolean isenemy;
    public LeverLoader cargar;
    public Punto LastPosition;
    public int vida;
    public int municion = 20;
    public int disparadasB = 0;
    public int vidatotal;
    public Animation animations[];
    public Animation animations2[];
    public Animation PrincipalAnimation;
    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = -2300f;
    private float escala;
    private int desfase;
    private float velocidadx;
    private float velocidadsalto;
    public Shape shape;
    public Shape upshape;
    public Shape downshape;
    public Shape leftshape;
    public Shape rigthshape;
    protected Bullet[] bullets;

    protected boolean alive = true;

    protected int tiempoEsperaBala = 0;
    protected int DELAYBALA = 200;
    protected int current = 0;

    public boolean puedoSaltar = false;
    private final int desfasey;
    private final int desfaseextra;
    private float aceleracionx;
    private boolean Derecha = true;

    public float getVey0() {
        return vey0;
    }

    public void setVey0(float vey0) {
        this.vey0 = vey0;
    }

    public void IniAnimations(String filepath) throws SlickException {
        cargar = new LeverLoader();
        cargar.prepareLevel(2);
        animations = cargar.getPlayerAnimations(false, filepath);
        animations2 = cargar.getPlayerAnimations(true, filepath);
        PrincipalAnimation = animations[0];
        shape = new Rectangle(this.position.x, this.position.y, this.getAncho(), this.getAlto());
        upshape = new Rectangle(this.position.x, this.position.y - 10, this.getAncho(), 10);
        downshape = new Rectangle(this.position.x, this.position.y + this.getAlto(), this.getAncho(), 10);
        rigthshape = new Rectangle(this.position.x + this.getAncho(), this.position.y, 10, this.getAlto());
        leftshape = new Rectangle(this.position.x - 10, this.position.y, 10, this.getAlto());

        bullets = new Bullet[35];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }

    }

    public void setVidaAmmo(int vida, int ammo) {
        this.vida = vida;
        this.vidatotal = vida;
        this.municion = ammo;
    }

    public void setbalas(int ammos) {
        this.municion += ammos;
    }

    public void disparar() {
        this.municion -= 1;
        if (this.municion <= 0) {
            this.municion = 0;
        }
    }

    public void mehicierondannio(int dannio) {
        // ay no le hicieron el dannio
        this.vida -= dannio;
    }

    public boolean estoymuerto() {
        return this.vida <= 0;
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
    public Character(float x, float y, float escala, int desfasex, float velocidad, float velocidadsalto, int desfasey, int desfaseextra, int enemy) {
        try {
            if (enemy == 1) {
                this.isenemy = true;
            } else {
                this.isenemy = false;
            }
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

    public void fireBullet(Vector2f vec, Bullet b) {
        this.PrincipalAnimation = !this.Derecha ? animations[5] : animations2[5];

        if (Derecha) {
            if (municion >= disparadasB) {
                disparadasB++;
                tiempoEsperaBala = 0;
                vec.sub(new Vector2f(position.x, position.y));
                vec.normalise();
                Vector2f aux = new Vector2f(position.x, position.y).copy();
                Vector2f aux2 = new Vector2f(aux.x + 140, aux.y + 65);
                bullets[current] = b.init(aux2, vec);
                current++;
                if (current >= bullets.length) {
                    current = 0;
                }
            }
        } else {
            if (municion >= disparadasB) {
                disparadasB++;
                tiempoEsperaBala = 0;
                vec.sub(new Vector2f(position.x, position.y));
                vec.normalise();
                Vector2f aux = new Vector2f(position.x, position.y).copy();
                Vector2f aux2 = new Vector2f(aux.x, aux.y + 65);
                bullets[current] = b.init(aux2, vec);
                current++;
                if (current >= bullets.length) {
                    current = 0;
                }
            }
        }

    }

    public int getAncho() {

        return (int) ((float) (this.PrincipalAnimation.getWidth() - desfase) * (float) escala);
    }

    public int getAlto() {
        return (int) (((float) this.PrincipalAnimation.getHeight() - desfasey) * (float) escala);
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

    public void RenderDraw(GameContainer gc, Graphics g) throws SlickException {
        //System.out.println(this.getAncho());
        //System.out.println(this.PrincipalAnimation.getWidth());
        if (this.isAlive()) {

            this.PrincipalAnimation.draw(this.position.x, this.position.y, this.getAncho(), this.getAlto());
        } else {
            this.PrincipalAnimation = !this.Derecha ? animations[8] : animations2[8];
            this.PrincipalAnimation.setLooping(false);
            this.PrincipalAnimation.draw(this.position.x, this.position.y, this.getAncho(), this.getAlto());

        }
        g.draw(this.shape);
        System.out.println(this.position.y);
        g.draw(this.downshape);
        g.draw(this.leftshape);
        g.draw(this.rigthshape);
        g.draw(this.upshape);
        for (Bullet b : bullets) {
            b.render(gc, g);
        }
    }

    public void actionClick(int key) {
        if (key == Input.KEY_D) {
            this.vx = this.velocidadx;
            this.Derecha = true;
            this.PrincipalAnimation = !this.Derecha ? animations[2] : animations2[2];

        } else {
            //this.vx = 0;
        }
        if (key == Input.KEY_A) {
            this.vx = -this.velocidadx;
            this.Derecha = false;
            this.PrincipalAnimation = !this.Derecha ? animations[2] : animations2[2];

        } else {
            //this.vx = 0;
        }
        if (key == 666) {
            //System.out.println("");
            this.vx = 0;
            this.PrincipalAnimation = !this.Derecha ? animations[0] : animations2[0];
        }
        if (key == Input.KEY_X && this.puedoSaltar == true) {
            this.PrincipalAnimation = !this.Derecha ? animations[6] : animations2[6];

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
        tiempoEsperaBala += delta;
        float tiempo = (float) (delta / 1000);
        this.LastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + this.getAlto() >= JuegoV1.contenedor.getHeight() && this.isenemy == false) {

            this.position.y = JuegoV1.contenedor.getHeight() - this.getAlto();
            //System.out.println(this.position.y);
            this.shape.setY(this.position.y);
            this.downshape.setY(this.position.y + this.getAlto());
            this.leftshape.setY(this.position.y);
            this.rigthshape.setY(this.position.y);
            this.upshape.setY(this.position.y - 10);
            this.puedoSaltar = true;
            //System.out.println(JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala));
            if (this.vey0 == this.velocidadsalto) {
                //System.out.println("salto");
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.vey0 = this.vey0 + (this.gravity * tiempo);
                this.shape.setY(this.position.y);
                this.downshape.setY(this.position.y + this.getAlto());
                this.leftshape.setY(this.position.y);
                this.rigthshape.setY(this.position.y);
                this.upshape.setY(this.position.y - 10);
            }
        } else {
            this.PrincipalAnimation = !this.Derecha ? animations[6] : animations2[6];

            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);
            this.shape.setY(this.position.y);

            this.downshape.setY(this.position.y + this.getAlto());
            this.leftshape.setY(this.position.y);
            this.rigthshape.setY(this.position.y);
            this.upshape.setY(this.position.y - 10);
            System.out.println(this.Choca(con.lista));
            if (this.Choca(con.lista) == UtilEnum.YU) {
                this.PrincipalAnimation = !this.Derecha ? animations[0] : animations2[0];

                this.vey0 = -this.vey0 * 0.5f;
                this.aceleracionx = 0;
                this.position = this.LastPosition;
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.vey0 = this.vey0 + (this.gravity * tiempo);
                this.shape.setY(this.position.y);

                this.downshape.setY(this.position.y + this.getAlto());
                this.leftshape.setY(this.position.y);
                this.rigthshape.setY(this.position.y);
                this.upshape.setY(this.position.y - 10);
                this.puedoSaltar = true;
                this.shape.setY(this.position.y);
                this.downshape.setY(this.position.y + this.getAlto());
                this.leftshape.setY(this.position.y);
                this.rigthshape.setY(this.position.y);
                this.upshape.setY(this.position.y - 10);
            } else {
                if (this.Choca(con.lista) == UtilEnum.YD) {
                    this.PrincipalAnimation = !this.Derecha ? animations[6] : animations2[6];

                    this.position = this.LastPosition;
                    this.downshape.setY(this.position.y + this.getAlto());
                    this.leftshape.setY(this.position.y);
                    this.rigthshape.setY(this.position.y);
                    this.upshape.setY(this.position.y - 10);
                    this.vey0 = -500f;
                    this.position.y = (float) this.position.y
                            - (float) (this.vey0 * tiempo)
                            + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                    this.vey0 = this.vey0 + (this.gravity * tiempo);
                    this.shape.setY(this.position.y);

                    this.downshape.setY(this.position.y + this.getAlto());
                    this.leftshape.setY(this.position.y);
                    this.rigthshape.setY(this.position.y);
                    this.upshape.setY(this.position.y - 10);
                }
            }
        }
        for (Bullet b : bullets) {
            b.update((int) delta);
        }
        ActionMove(tiempo, con);

    }

    public void updatePosition(float delta, ContainerS con, boolean enemy, StaticLevel level) {
        float tiempo = (float) delta / 1000;
        /*if (this.position.y < 0) {
            System.out.println("menor a cero");
            this.shape.setY(this.position.y);
            this.vey0 = 10f;

            this.downshape.setY(this.position.y + this.getAlto());
            this.leftshape.setY(this.position.y);
            this.rigthshape.setY(this.position.y);
            this.upshape.setY(this.position.y - 10);
        } 
        else {*/
        if (this.position.y > level.getDown() + juegov1.JuegoV1.contenedor.getHeight()) {
            System.out.println("leveldown");
            System.out.println(level.getDown());
            this.position.y = juegov1.JuegoV1.contenedor.getHeight() + level.getDown() - this.getAlto();
            this.shape.setY(this.position.y);
            this.PrincipalAnimation = !this.Derecha ? animations[0] : animations2[0];

            this.downshape.setY(this.position.y + this.getAlto());
            this.leftshape.setY(this.position.y);
            this.rigthshape.setY(this.position.y);
            this.upshape.setY(this.position.y - 10);
            this.vey0 = 0;
        } else {
            if (this.position.y == juegov1.JuegoV1.contenedor.getHeight() + level.getDown() - this.getAlto()) {

            } else {
                this.PrincipalAnimation = !this.Derecha ? animations[6] : animations2[6];

                System.out.println("Donde debe estar");
                System.out.println(this.position.y);
                System.out.println(
                        (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2))));
                this.position.y = (float) this.position.y
                        - (float) (this.vey0 * tiempo)
                        + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                this.vey0 = this.vey0 + (this.gravity * tiempo);
                this.shape.setY(this.position.y);

                this.downshape.setY(this.position.y + this.getAlto());
                this.leftshape.setY(this.position.y);
                this.rigthshape.setY(this.position.y);
                this.upshape.setY(this.position.y - 10);
                System.out.println(this.Choca(con.lista));
                if (this.Choca(con.lista) == UtilEnum.YU) {
                    this.PrincipalAnimation = this.Derecha ? animations[0] : animations2[0];

                    this.vey0 = -this.vey0 * 0.5f;
                    this.aceleracionx = 0;
                    this.position = this.LastPosition;
                    this.position.y = (float) this.position.y
                            - (float) (this.vey0 * tiempo)
                            + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                    this.vey0 = this.vey0 + (this.gravity * tiempo);
                    this.shape.setY(this.position.y);

                    this.downshape.setY(this.position.y + this.getAlto());
                    this.leftshape.setY(this.position.y);
                    this.rigthshape.setY(this.position.y);
                    this.upshape.setY(this.position.y - 10);
                    this.puedoSaltar = true;
                    this.shape.setY(this.position.y);
                    this.downshape.setY(this.position.y + this.getAlto());
                    this.leftshape.setY(this.position.y);
                    this.rigthshape.setY(this.position.y);
                    this.upshape.setY(this.position.y - 10);
                } else {
                    if (this.Choca(con.lista) == UtilEnum.YD) {
                        this.position = this.LastPosition;
                        this.downshape.setY(this.position.y + this.getAlto());
                        this.leftshape.setY(this.position.y);
                        this.rigthshape.setY(this.position.y);
                        this.upshape.setY(this.position.y - 10);
                        this.vey0 = -500f;
                        this.position.y = (float) this.position.y
                                - (float) (this.vey0 * tiempo)
                                + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
                        this.vey0 = this.vey0 + (this.gravity * tiempo);
                        this.shape.setY(this.position.y);

                        this.downshape.setY(this.position.y + this.getAlto());
                        this.leftshape.setY(this.position.y);
                        this.rigthshape.setY(this.position.y);
                        this.upshape.setY(this.position.y - 10);
                    }
                }
            }

        }
        for (Bullet b : bullets) {
            b.update((int) delta);
        }

        this.ActionMove(tiempo, con, level);/*
        }*/

    }

    private void ActionMove(float tiempo, ContainerS con) {
        if (JuegoV1.contenedor.getWidth() < this.position.x + this.getAncho()) {
            this.position.x = JuegoV1.contenedor.getWidth() - this.getAncho();
            this.shape.setX(this.position.x);
            this.shape.setX(this.position.x);
            this.downshape.setX(this.position.x);
            this.leftshape.setX(this.position.x - 10);
            this.rigthshape.setX(this.position.x + this.getAncho());
            this.upshape.setX(this.position.x);

            this.vx = 0;
        } else {
            if (this.position.x < 0) {
                this.position.x = 0;
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);
                this.vx = 0;
                this.puedoSaltar = false;

            } else {
                this.position.x = this.position.x + ((float) this.vx * (float) (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);

                System.out.println(this.Choca(con.lista));
                if (this.Choca(con.lista) == UtilEnum.XR) {
                    this.position = this.LastPosition;
                    this.vx = 300;
                    this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));

                    /*if (this.vx > 0) {
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
                    }*/
                } else {
                    if (this.Choca(con.lista) == UtilEnum.XL) {
                        this.position = this.LastPosition;
                        this.vx = -300;
                        this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                    }
                }
                /*this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                //this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                // this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));*/
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);
            }
        }

        //aca
    }

    public UtilEnum Choca(Platform[] recList) {
        UtilEnum response = UtilEnum.N;
        for (Platform r : recList) {
            if (this.downshape.intersects(r.getContenedor())) {

                // this.position.y = r.getContenedor().getMaxY() - this.getAlto();
                /*
                if (this.position.y == this.LastPosition.y) {
                    response = UtilEnum.X;
                }else {
                    response = UtilEnum.Y;
                }*/
                response = UtilEnum.YU;

            } else {
                if (this.upshape.intersects(r.getContenedor())) {
                    this.vey0 = 0;
                    //this.position.y = r.getContenedor().getMinY();
                    response = UtilEnum.YD;
                } else {
                    if (this.rigthshape.intersects(r.getContenedor())) {
                        // this.position.x = r.getContenedor().getMinX() - this.getAncho() - 1;
                        response = UtilEnum.XL;
                    } else {
                        if (this.leftshape.intersects(r.getContenedor())) {
                            //this.position.x = r.getContenedor().getMaxX();
                            response = UtilEnum.XR;
                        }
                    }

                }
            }

        }

        return response;
    }

    private void ActionMove(float tiempo, ContainerS con, StaticLevel level) {
        if (this.position.x > juegov1.JuegoV1.contenedor.getWidth() + level.getRight() - this.getAncho()) {
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() + level.getRight() - this.getAncho();
            this.shape.setX(this.position.x);
            this.downshape.setX(this.position.x);
            this.leftshape.setX(this.position.x - 10);
            this.rigthshape.setX(this.position.x + this.getAncho());
            this.upshape.setX(this.position.x);
        } else {
            if (this.position.x < level.getLeft()) {
                this.position.x = level.getLeft();
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);
            } else {

                this.position.x = this.position.x + ((float) this.vx * (float) (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);

                System.out.println(this.Choca(con.lista));
                if (this.Choca(con.lista) == UtilEnum.XR) {
                    this.position = this.LastPosition;
                    this.vx = 300;
                    this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));

                    /*if (this.vx > 0) {
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
                    }*/
                } else {
                    if (this.Choca(con.lista) == UtilEnum.XL) {
                        this.position = this.LastPosition;
                        this.vx = -300;
                        this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                    }
                }
                /*this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                //this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));
                // this.position.x = this.position.x + (this.vx * (tiempo)) + (0.5f * aceleracionx * (float) (Math.pow(tiempo, 2)));*/
                this.shape.setX(this.position.x);
                this.downshape.setX(this.position.x);
                this.leftshape.setX(this.position.x - 10);
                this.rigthshape.setX(this.position.x + this.getAncho());
                this.upshape.setX(this.position.x);
            }

        }
    }

    public int getDelay() {
        return DELAYBALA;
    }

    public int getTiempoBa() {
        return tiempoEsperaBala;
    }

    public void checkBulletCollision(Bullet[] otherBullets) {
        for (Bullet b : otherBullets) {
            if (b.getActive() && b.collideWith(new Vector2f(position.x, position.y), this.getAncho() * this.getAlto())) {
                b.setActive(false);
                vida -= b.getDamage();
                if (vida <= 0 && alive) {
                    die();
                }
            }
        }
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

}
