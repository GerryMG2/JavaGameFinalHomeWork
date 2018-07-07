/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import juegov1.Punto;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author gerar
 */
public abstract class ObjectS {
    public Punto position;
    public Punto lastPosition;
    public Shape shape;
    public boolean transparente = false;
    public boolean traspasable = false;

    public ObjectS() {
        this.position = new Punto();
        this.lastPosition = new Punto();
        this.shape = new Shape() {
            @Override
            public Shape transform(Transform t) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void createPoints() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
}
