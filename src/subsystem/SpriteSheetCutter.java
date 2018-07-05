package subsystem;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;

/**
 *
 * @author yury_
 */
public class SpriteSheetCutter {

    private Image sheet;
    private SpriteSheet cuttedSheet;
    private Animation anime;
    

    public SpriteSheetCutter() {
    }

    public SpriteSheet cutimg(Image imagen, int htiles, int vtiles) {
        return new SpriteSheet(imagen, htiles, vtiles);
    }
    
    public Animation makeAnimation(Image imagen, int htiles, int vtiles){
    return new Animation(new SpriteSheet(imagen, htiles, vtiles), 80);
    
    }

    private void clear() {
        sheet = null;
        cuttedSheet = null;
    }

}
