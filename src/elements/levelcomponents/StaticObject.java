package elements.levelcomponents;

/**
 *
 * @author yury_
 */
import core.GameObject;
import org.newdawn.slick.Image;

public class StaticObject extends GameObject {
    
    private int recalX, recalY;

    public StaticObject(Image faceText,int CoorX, int CoorY) {
        super(faceText, CoorX, CoorY);
        
    }

    @Override
    public void render() {
    }

    @Override
    public void update() {
        
    }
    
    public void update(int globalX, int globalY) {
        
    }
    
    
}
