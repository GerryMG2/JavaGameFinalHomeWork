package TiposGenerales;

import org.newdawn.slick.geom.Rectangle;
import juegov1.Punto;

/**
 *
 * @author yury_
 */
public abstract class StaticObject  extends ObjectS{

    private final int x, y;
    private final Rectangle contenedor;

    public StaticObject(Rectangle placeholder) {
        x = (int) placeholder.getX();
        y = (int) placeholder.getY();
        super.position = new Punto();
        contenedor = placeholder;
        super.shape = contenedor;
    }

    /**
     * cuado necesiten que sus objetos hijos se actualicen no llamen al metodo update directamente, eso
     * llevarqa a errores, tienen que llamar a este metodo relocalizar, este metodo automaticamente le llamara al
     * metodo update de sus hijos. Asi se garantiza que la picicon ha sido actualizada y correjida antes de que el objeto 
     * comience cualquier animacion o se renderice
     * @param Gx Este paremetro representa la pocion global horizontal reclaculada en la cual el objeto debe desplazaerce
     * @param Gy Este paremetro representa la pocion global vertical reclaculada en la cual el objeto debe desplazaerce
     */
    public void relocalizar(int Gx, int Gy) {
        contenedor.setLocation(x - Gx, y - Gy);
        super.position.setX(contenedor.getX());
        super.position.setY(contenedor.getY());
        update();
    }

    public abstract void update();

    public Rectangle getContenedor() {
        return contenedor;
    }

}
