/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author gerar
 */
public class Character {

    private int vida;
    Animation animations[];
    Animation PrincipalAnimation;
    public Punto position;
    private float vx;
    private float vey0;
    public float gravity = -100f;
    private float escala;
    private int desfase;
    private float velocidadx;
    private Image playerImg;
    private SpriteSheet subImage;

    public void IniAnimations(Image sprite) {
        playerImg = sprite;
        subImage = new SpriteSheet(playerImg.getSubImage(0, 150, 368, 50), 46, 50);
        PrincipalAnimation = new Animation(subImage, 80);
        
    }

    public Character(float x, float y, float escala, int desfase,float velocidad) {
        try {
            position = new Punto();
            position.setX(x);
            position.setY(y);
            this.escala = escala;
            this.desfase = desfase;
            this.velocidadx = velocidad;
            
        } catch (Exception error) {
            error.printStackTrace();
        }
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
        this.PrincipalAnimation.draw(position.x,position.y);
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float y0 = this.position.y;
        if (y0 + (this.PrincipalAnimation.getHeight()*escala) - desfase >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala) + (desfase * escala);
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (-gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);
        }
        ActionMove(tiempo);

    }
    public void actionClick(Input in){
        if(in.isKeyDown(Input.KEY_D))
        {
            this.vx = this.velocidadx;
        }
        else
        {
            if(in.isKeyDown(Input.KEY_A))
            {
                this.vx = - this.velocidadx;
            }
            else{
                this.vx = 0;
            }
        }
        
    }
    
    public void ActionMove(float tiempo){
        if(JuegoV1.contenedor.getWidth() < this.position.x + (this.PrincipalAnimation.getWidth() * escala) - (desfase *escala)){
            this.position.x = JuegoV1.contenedor.getWidth() - (this.PrincipalAnimation.getWidth() * escala) -(desfase * escala);
            this.vx = 0;
        }
        else
        {
            if(this.position.x < 0){
                this.position.x = 0;
                this.vx = 0;
            }
        }
        this.position.x = this.position.x + this.vx * (tiempo/10);
    }

}
