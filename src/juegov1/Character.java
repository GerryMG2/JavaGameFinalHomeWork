/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;

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

    public void IniAnimations() {
        

    }

    public Character(float x, float y, float escala, int desfase) {
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
        this.PrincipalAnimation.draw();
    }

    public void updatePosition(float delta) {
        float tiempo = (float) (delta / 1000);
        float x0 = (float) this.position.x + (this.PrincipalAnimation.getWidth() * escala);
        float y0 = this.position.y;
        if (y0 + this.PrincipalAnimation.getHeight() >= JuegoV1.contenedor.getHeight()) {
            this.position.y = JuegoV1.contenedor.getHeight() - (this.PrincipalAnimation.getHeight() * escala);
        } else {
            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (-gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);
        }

    }
    public void actionClick(Input in){
        if(in.isKeyDown(Input.KEY_D))
        {
            this.vx = 5;
        }
        else
        {
            if(in.isKeyDown(Input.KEY_A))
            {
                this.vx = - 5;
            }
        }
    }
    
    public void ActionDerecha(float tiempo){
        if(JuegoV1.contenedor.getWidth() <= this.position.x + this.PrincipalAnimation.getWidth()){
            this.position.x = JuegoV1.contenedor.getWidth() - this.PrincipalAnimation.getWidth();
            this.vx = 0;
        }
        else
        {
            if(this.position.x  - this.PrincipalAnimation.getWidth() == 0){
                this.position.x = this.PrincipalAnimation.getWidth();
            }
        }
        this.position.x = this.position.x +  this.vx * tiempo;
    }

}
