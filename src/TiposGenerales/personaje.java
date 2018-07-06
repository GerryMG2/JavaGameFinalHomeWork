/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

/**
 *
 * @author gerar
 */
public abstract class personaje extends DinamicObject {
    
    float vx;
    private float vey0;
    private float velocidadx;
    private float velocidadsalto;
    public boolean puedoSaltar = false;
    public personaje(float x, float y, float gravity, float escala, int desfase,float velocidadSalto,float velocidad) {
        super(x, y, gravity, escala, desfase);
        this.velocidadsalto = velocidadSalto;
        this.velocidadx = velocidad;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVey0() {
        return vey0;    
    }

    public void setVey0(float vey0) {
        this.vey0 = vey0;
    }

    public float getVelocidadx() {
        return velocidadx;
    }

    public void setVelocidadx(float velocidadx) {
        this.velocidadx = velocidadx;
    }

    public float getVelocidadsalto() {
        return velocidadsalto;
    }

    public void setVelocidadsalto(float velocidadsalto) {
        this.velocidadsalto = velocidadsalto;
    }
    
    /**
     *
     * @param key
     */

    public abstract void ActionClick(int key);
    
    
    
    
    
}
