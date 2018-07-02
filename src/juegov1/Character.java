/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import org.newdawn.slick.Animation;

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

    public void IniAnimations(Animation animations[]) {
        this.animations = animations;

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
            float dify = this.position.y - y0;
            this.vey0 = this.vey0 + (this.gravity * tiempo);
        }

    }

}
