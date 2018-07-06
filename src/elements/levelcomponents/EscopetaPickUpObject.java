/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements.levelcomponents;

import TiposGenerales.DinamicObject;
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
    private int alturaDeLevitacion;
    private int vxdeltaD;
    private int changeD = 0;
    private boolean active = true;
    private ArrayList<DinamicObject> objects;

    public EscopetaPickUpObject(float x, float y, float gravity, float escala, int desfase, int alturaDeLevitacion, int vxdeltaD, ArrayList<DinamicObject> objects) {
        super(x, y, gravity, escala, desfase);
        this.alturaDeLevitacion = alturaDeLevitacion;
        this.vxdeltaD = vxdeltaD;
        this.objects = objects;
    }

    public boolean colisionsuelo(DinamicObject object) {
        int disy = (int) object.position.y;

        if (disy + alturaDeLevitacion < object.position.y && object.position.x <= this.position.x && this.position.x <= object.position.x + object.getAncho()) {
            return true;
        }
        return false;
    }

    @Override
    public void update(int delta) {
        for (DinamicObject object : objects) {
            if (active && changeD == 0 && colisionsuelo(object)) {
                Vector2f Velocidad = new Vector2f(delta, gravity);
                //Velocidad se pasa a una constante de movimiento
                Velocidad.scale((delta / 1000.0f));
                this.vx = vx + 2 * Velocidad.x;
                this.vey0 = vey0 + Velocidad.y;
                changeD = 1;
            }
            if (active && changeD == 1 && colisionsuelo(object)) {
                Vector2f Velocidad = new Vector2f(delta, gravity);
                Velocidad.scale((delta / 1000.0f));
                this.vx = vx - 2 * Velocidad.x;
                this.vey0 = vey0 + Velocidad.y;
                changeD=0;
            }

        }

    }

}
