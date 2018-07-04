package core;

import org.newdawn.slick.Image;

/**
 *
 * @author yury_
 */
public abstract class GameObject {

    private int xcoor = 0, ycoor = 0;
    private int wival = 0, heival = 0;
    private Image textture;

    public GameObject(int xpos, int ypos, int width, int height) {
        xcoor = xpos;
        ycoor = ypos;
        wival = width;
        heival = height;
    }

    public GameObject(Image textture) {
        this.textture = textture;
        wival = this.textture.getWidth();
        heival = this.textture.getHeight();
    }

    public GameObject(Image textture, int xpos, int ypos) {
        this.textture = textture;
        wival = this.textture.getWidth();
        heival = this.textture.getHeight();
        xcoor = xpos;
        ycoor = ypos;
    }

    public abstract void render();

    public abstract void update();

}
