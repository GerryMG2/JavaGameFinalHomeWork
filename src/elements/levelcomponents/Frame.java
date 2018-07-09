package elements.levelcomponents;

import TiposGenerales.StaticObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author yury_
 */
public class Frame extends StaticObject {
    
    public Frame(int x, int y, int xw, int yhi) {
        super(new Rectangle(x, y, xw, yhi));
        
    }
    
    public void render(Graphics g) {
        g.fill(shape);
        g.draw(shape);
    }
    
    @Override
    public void update() {
    }
}
