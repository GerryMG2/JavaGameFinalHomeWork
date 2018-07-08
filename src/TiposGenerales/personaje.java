/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import elements.levelcomponents.Platform;
import juegov1.Punto;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author gerar
 */
public abstract class personaje extends DinamicObject {
    
    private float vx;
    private float vey0;
    private float velocidadx;
    private float velocidadsalto;
    public boolean puedoSaltar = false;
    public int arma;
    
    public personaje(float x, float y, float gravity, float escala, int desfase,float velocidadSalto,float velocidad) {
        super(x, y, gravity, escala, desfase);
        this.velocidadsalto = velocidadSalto;
        this.velocidadx = velocidad;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vxn) {
        this.vx = vxn;
    }

    public float getVey0() {
        return vey0;    
    }

    public void setVey0(float vey0n) {
        this.vey0 = vey0n;
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
    
    public UtilEnum Choca(Platform[] recList){
        UtilEnum response = UtilEnum.N;
        for(Platform r: recList){
            if(this.shape.intersects(r.shape))
            {
                if(this.position.y == this.lastPosition.y){
                    response = UtilEnum.XR;
                }
                else
                {
                    response = UtilEnum.YD;
                }
                
            }
        }
        
        
        
        
        return response;
    }

    public abstract void ActionClick(int key);
    
    public void SetWeapon(int arma){
        this.arma=arma;
    } 
    
}
