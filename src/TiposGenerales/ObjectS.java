/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import juegov1.Punto;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gerar
 */
public abstract class ObjectS {
    public Punto position;
    public Shape shape;
    public boolean transparente = false;
    public boolean traspasable = false;
}
