package elements.gameactor;

import juegov1.JuegoV1;
import juegov1.Punto;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author yury_
 */
public class CharPlayer {

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
    private Image playerImg;
    private SpriteSheet subImage;
    private float velocidadsalto;
    public Shape shape;

    public void IniAnimations(Image sprite) {
        playerImg = sprite;
        subImage = new SpriteSheet(playerImg.getSubImage(0, 150, 368, 50), 46, 50);
        PrincipalAnimation = new Animation(subImage, 80);
        shape = new Rectangle(this.position.x, this.position.y, this.position.x + (this.PrincipalAnimation.getWidth() * escala) - (desfase * escala), this.position.y + (this.PrincipalAnimation.getHeight() * escala) - (desfase * escala));

    }

    public CharPlayer(float x, float y, float escala, int desfase, float velocidad, float velocidadsalto) {
        try {
            position = new Punto();
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
        this.PrincipalAnimation.draw(position.x, position.y, this.PrincipalAnimation.getWidth() * escala, this.PrincipalAnimation.getHeight() * escala);
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight() * escala) - (desfase * escala) >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala);
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

        if (key == Input.KEY_X && this.position.y == JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala)) {

            this.vey0 = this.velocidadsalto;
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

}