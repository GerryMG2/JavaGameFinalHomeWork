/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import juegov1.Punto;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gerar
 */
public abstract class DinamicObject extends ObjectS{
    
    Animation animations[];
    Animation PrincipalAnimation;
    private float vx;
    private float vey0;
    public float gravity;
    private float escala;
    private int desfase;
    private float velocidadx;
    private Image playerImg;
    private SpriteSheet subImage;
    private float ancho;
    private float alto;
    

    public DinamicObject(float x,float y, float gravity, float escala, int desfase) {
        position = new Punto();
        this.position.x = x;
        this.position.y = y;
        this.gravity = gravity;
        this.escala = escala;
        this.desfase = desfase;
    }
    
    public void IniAnimations(Image sprite) {
        playerImg = sprite;
        subImage = new SpriteSheet(playerImg.getSubImage(0, 150, 368, 50), 46, 50);
        PrincipalAnimation = new Animation(subImage, 80);
        shape = new Rectangle(this.position.x,this.position.y,this.position.x + (this.PrincipalAnimation.getWidth() * escala) - (desfase * escala),this.position.y + (this.PrincipalAnimation.getHeight() * escala) - (desfase * escala));
        this.ancho = (this.PrincipalAnimation.getWidth() - desfase ) *escala;
        this.alto = (this.PrincipalAnimation.getHeight() - desfase) * escala;
    }
    
    
    public void render(){
        this.PrincipalAnimation.draw(this.position.x, this.position.y, ancho, alto);
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

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }
    
    public abstract void update(int delta);
    
}
