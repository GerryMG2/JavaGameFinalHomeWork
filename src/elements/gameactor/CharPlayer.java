package elements.gameactor;

import TiposGenerales.ContainerS;
import TiposGenerales.UtilEnum;
import elements.levelcomponents.Bullet;
import elements.levelcomponents.Platform;
import juegov1.Punto;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import subsystem.SpriteSheetCutter;

/**
 *
 * @author gerar
 */
public class CharPlayer {

    public Punto LastPosition;
    private int vida;
    Animation animations[];
    Animation PrincipalAnimation;
    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = 9.8f;
    private float escala;
    private int desfase;
    private float velocidadx;
    private SpriteSheetCutter tijeras;
    private float velocidadsalto;
    public Shape shape;
    public boolean puedoSaltar = false;
    private Rectangle boundry;
    protected Bullet[] bullets;
    protected boolean alive = true;

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
        shape = new Rectangle(position.x, position.y, position.x + (PrincipalAnimation.getWidth() * escala) - (desfase * escala), position.y + (PrincipalAnimation.getHeight() * escala) - (desfase * escala));

    }

    public CharPlayer(float x, float y, float escala, int desfase, float velocidad, float velocidadsalto) {
        try {
            position = new Punto();
            LastPosition = new Punto();
            position.setX(x);
            position.setY(y);
            this.escala = escala;
            this.desfase = desfase;
            this.velocidadx = velocidad;
            this.velocidadsalto = velocidadsalto;

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void setBoundry(int x, int y, int wid, int heig) {
        boundry = new Rectangle(x, y, wid, heig);
    }

    public int getAncho() {
        return (int) (this.PrincipalAnimation.getWidth() - desfase) * (int) escala;
    }

    public int getAlto() {
        return (this.PrincipalAnimation.getHeight() - desfase) * (int) escala;
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

    public void RenderDraw() {
        this.PrincipalAnimation.draw(position.x, position.y, this.getAncho(), this.getAlto());
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float y0 = position.y;
        if (y0 + (PrincipalAnimation.getHeight() * escala) - (desfase * escala) >= boundry.getHeight()) {
            position.y = boundry.getHeight() - (PrincipalAnimation.getHeight() * escala) + (desfase * escala);
            shape.setY(position.y);
            position.y = (float) (position.y - (vey0 * tiempo) + (0.5f * (gravity) * (Math.pow(tiempo, 2))));
            if (vey0 == velocidadsalto) {
                vey0 = vey0 + (gravity * tiempo);
                shape.setY(position.y);
            }
        } else {
            vey0 = vey0 + (gravity * tiempo);
            shape.setY(position.y);
        }
        ActionMove(tiempo);

    }

    public void actionClick(int key) {
        switch (key) {
            case Input.KEY_D:
                vx = velocidadx;
                break;
            case Input.KEY_A:
                vx = -velocidadx;
                break;

            case Input.KEY_X:
                if (position.y == boundry.getHeight() - (PrincipalAnimation.getHeight() * escala) + (desfase * escala)) {
                    vey0 = velocidadsalto;
                }
            default:
                vx = 0;
                break;
        }
    }

    public void ActionMove(float tiempo) {
        if (boundry.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * escala) + (desfase * escala)) {
            this.position.x = boundry.getWidth() - (this.PrincipalAnimation.getWidth() * escala) - (desfase * escala);
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

    public void update(int delta, ContainerS con) {
        float tiempo = (float) (delta / 1000);
        this.LastPosition = this.position;
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * this.getEscala()) - (this.getDesfase() * this.getEscala()) >= boundry.getHeight()) {
            this.position.y = boundry.getHeight() - (this.PrincipalAnimation.getHeight() * this.getEscala()) + (this.getDesfase() * this.getEscala());
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
            if (this.Choca(con.lista) == UtilEnum.YD) {
                this.position = this.LastPosition;
                this.puedoSaltar = true;
                this.shape.setY(this.position.y);
            }

        }
        ActionMove(tiempo, con);
    }

    private void ActionMove(float tiempo, ContainerS con) {
        if (juegov1.JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * this.getEscala()) - (this.getDesfase() * this.getEscala())) {
            this.setVx(0);
            this.position.x = juegov1.JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * this.getEscala()) + (this.getDesfase() * this.getEscala());
            this.shape.setX(this.position.x);

        } else {
            if (this.position.x <= 0) {
                this.setVx(0);
                this.position.x = 0;
                this.shape.setX(this.position.x);

            }
        }
//        this.position.x = this.position.x + (this.setVx(0); * (tiempo));
        if (this.Choca(con.lista) == UtilEnum.XR) {
            this.position = this.LastPosition;
        }
        this.shape.setX(this.position.x);
    }

    public UtilEnum Choca(Platform[] recList) {
        UtilEnum response = UtilEnum.N;
        for (Platform r : recList) {
            if (this.shape.intersects(r.shape)) {
                if (this.position.y == this.LastPosition.y) {
                    //response = UtilEnum.X;
                } else {
                    // response = UtilEnum.Y;
                }

            }
        }

        return response;
    }

    public void checkBulletCollision(Bullet[] otherBullets) {
        for (Bullet b : otherBullets) {
            if (b.getActive() && b.collideWith(new Vector2f(this.getVx(), this.getVey0()), this.getAncho() / 2)) {
                // Delete the bullet if it hits something
                b.setActive(false);
                vida -= b.getDamage();
                if (vida < 0 && alive) {
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
