package elements.levelcomponents;

/**
 *
 * @author yury_
 */
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import TiposGenerales.StaticObject;

public class Platform extends StaticObject{
    
    private Rectangle contenedor;

    public Platform(Rectangle contendor) {
        super(contendor);
    }
    
    public void render(Graphics g) {
        g.fill(this.getContenedor());
        g.draw(this.getContenedor());   
    }

    @Override
    public void update() {
        
    }
    
}
