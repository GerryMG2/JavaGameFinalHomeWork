package elements.gameactor;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author yury_
 */
public class Player {

    private final Image playerImg;
    private final SpriteSheet subImage;
    private final Animation run;
    private int x = 0, y = 450;
    private int oriX, oriY, finX, finY;
    public boolean reachXf = false;
    public boolean reachXi = false;

    public Player(String path) throws SlickException {
        playerImg = new Image(path);
        subImage = new SpriteSheet(playerImg.getSubImage(0, 150, 368, 50), 46, 50);
        run = new Animation(subImage, 80);

    }

    public void update(GameContainer cnt, int delta) {

        //switch(Input.){}
        if (cnt.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += (0.25 * delta);
            if (x > finX) {
                x = finX;
            }
            checkBoundri();
        } else if (cnt.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= (0.25 * delta);
            if (x < oriX) {
                x = oriX;
            }
            checkBoundri();
        } else if (cnt.getInput().isKeyDown(Input.KEY_UP)) {
            y -= (0.25 * delta);
            if (y < oriY) {
                y = oriY;
            }
            checkBoundri();
        } else if (cnt.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += (0.25 * delta);
            if (y > finY) {
                y = finY;
            }
            checkBoundri();
        }
    }

    public void render() {
        run.draw(x, y, 92, 100);
    }

    public void setBoundries(Rectangle bound) {
        oriX = (int) (bound.getX());
        finX = (int) (bound.getX() + bound.getWidth() - run.getWidth());
        oriY = (int) bound.getY();
        finY = (int) (bound.getY() + bound.getHeight());
        x = oriX;
    }

    public void setBoundries(int x0, int y0, int x1, int y1) {
        oriX = x0;
        oriY = y0;
        finX = x1;
        finY = y1;
    }

    private void checkBoundri() {
        if (x == finX) {
            reachXf = true;
        } else {
            reachXf = false;
        }
        if (x == oriX) {
            reachXi = true;
        } else {
            reachXi = false;
        }

    }

}
