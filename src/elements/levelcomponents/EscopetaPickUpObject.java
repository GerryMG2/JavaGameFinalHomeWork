/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements.levelcomponents;

import TiposGenerales.DinamicObject;
import TiposGenerales.personaje;
import elements.gameactor.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author mcdre
 */
public class EscopetaPickUpObject extends DinamicObject {

    float vx;
    float vey0;
    private final int alturaDeLevitacion;
    private final int vxdeltaD;
    private int changeD = 0;
    private boolean active = true;
    private ArrayList<DinamicObject> objects;
    private personaje p;

    public EscopetaPickUpObject(float x, float y, float gravity, float escala, int desfase, int alturaDeLevitacion, int vxdeltaD, ArrayList<DinamicObject> objects, personaje p) {
        super(x, y, gravity, escala, desfase);
        this.alturaDeLevitacion = alturaDeLevitacion;
        this.vxdeltaD = vxdeltaD;
        this.objects = objects;
        this.p=p;
    }

    public boolean colisionsuelo(DinamicObject object) {
        int disy = (int) object.position.y;

        if (disy + alturaDeLevitacion < object.position.y && object.position.x <= this.position.x && this.position.x <= object.position.x + object.getAncho()) {
            return true;
        }
        return false;
    }

    public void pickedup() {
        if (active) {
            if (p.position.x <= this.position.x && this.position.x <= p.position.x + p.getAncho() && p.position.y <= this.position.y && this.position.y <= p.position.y + p.getAncho()) {
                p.SetWeapon(1);
                this.active = false;
            }
        }
    }

    @Override
    public void update(int delta) {
        pickedup();
        for (DinamicObject object : objects) {
            if (active && changeD == 0 && colisionsuelo(object)) {
                Vector2f Velocidad = new Vector2f(vxdeltaD, gravity);
                //Velocidad se pasa a una constante de movimiento
                Velocidad.scale((delta / 1000.0f));
                this.vx = vx + 2 * Velocidad.x;
                this.vey0 = vey0 + Velocidad.y;
                changeD = 1;
            }
            if (active && changeD == 1 && colisionsuelo(object)) {
                Vector2f Velocidad = new Vector2f(vxdeltaD, gravity);
                Velocidad.scale((delta / 1000.0f));
                this.vx = vx - 2 * Velocidad.x;
                this.vey0 = vey0 + Velocidad.y;
                changeD = 0;
            }
            if (active && !colisionsuelo(object)) {
                Vector2f Velocidad = new Vector2f(vxdeltaD, gravity);
                switch (changeD) {
                    case 1:
                        Velocidad.scale((delta / 1000.0f));
                        this.vx = vx - 2 * Velocidad.x;
                        changeD = 0;
                        break;
                    case 0:
                        Velocidad.scale((delta / 1000.0f));
                        this.vx = vx + 2 * Velocidad.x;
                        changeD = 1;
                        break;
                }
            }

        }

    }

}
