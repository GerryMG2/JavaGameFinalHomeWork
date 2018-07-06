package elements.levelcomponents;

/**
 *
 * @author yury_
 */
import org.newdawn.slick.geom.Rectangle;
import TiposGenerales.StaticObject;
import org.newdawn.slick.Image;

public class Platform extends StaticObject {

    private Image texture;

    public Platform(Rectangle contendor) {
        super(contendor);
    }

    public void render() {
        texture.draw(position.x, position.y,shape.getWidth(), shape.getHeight());
    }

    @Override
    public void update() {

    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

}
