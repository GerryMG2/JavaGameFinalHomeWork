package elements.levelcomponents;

import org.newdawn.slick.Image;

/**
 *
 * @author yury_
 */
public class Frame {

    public final int id;
    private Image face;

    public Frame(int id, int xc, int yc) {
        //super(xc, yc);
        this.id = id;
    }

    public void setImg(Image texture) {
        face = texture;
    }
    


}
