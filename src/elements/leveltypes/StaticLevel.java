package elements.leveltypes;

import juegov1.Character;
import java.awt.Dimension;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Rectangle;
import elements.powerup.Arma;

/**
 *
 * @author yury_
 */
public class StaticLevel {

    private Dimension tamano;
    private Image fondo;
    private final float scal;
    private int cwid, chei;
    private int Gx, Gy, Lx, Ly;
    private Character target;
    private Input control;
    private Rectangle chaBoundry;
    private Arma weapon;

    public StaticLevel() {
        scal = 1;
    }

    public StaticLevel(float scalar) {
        scal = scalar;
    }

    public void init(GameContainer container, String path) throws SlickException {
        Gx = 0;
        Gy = 0;
        fondo = new Image(path);
        fondo = fondo.getScaledCopy(scal);
        cwid = container.getWidth();
        chei = container.getHeight();
        tamano = new Dimension((int) fondo.getWidth(), (int) fondo.getHeight());
        Lx = Gx + cwid;
        Ly = Gy + chei;
        control = container.getInput();
        weapon = new Arma(new Image("res\\Img\\Character\\assets\\weapons\\large.png"), control);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (target == null) {
            freeScroll(delta);
        } else {
            targetScroll();
        }
        if (control.isKeyPressed(Input.KEY_X)) {
            target.actionClick(Input.KEY_X);
        }
        if (control.isKeyPressed(Input.KEY_A)) {
            target.actionClick(Input.KEY_A);
        }
        if (control.isKeyPressed(Input.KEY_D)) {
            target.actionClick(Input.KEY_D);
        }
        weapon.update(((int)target.position.x+target.getAncho()), ((int)target.position.y+(target.getAlto()/2 )), delta);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw(0, 0, cwid, chei, Gx, Gy, Lx, Ly);
        target.RenderDraw();
        weapon.render(container, g);
    }

    public void setTarget(Character mainThing) {
        target = mainThing;
        calcBoundry();
    }

    private void calcBoundry() {
        int x0, y0, x1, y1;
        x0 = (int) ((int) cwid * 0.2f);
        x1 = (int) ((int) cwid * 0.6f);
        y0 = (int) ((int) chei * 0.2f);
        y1 = (int) ((int) chei * 0.6f);
        chaBoundry = new Rectangle(x0, y0, x1, y1);
    }

    private void freeScroll(int delta) {
        if (control.isKeyDown(Input.KEY_RIGHT)) {
            Gx += (0.25 * delta);
            Lx = Gx + cwid;
            if (Lx > tamano.width) {
                Gx = tamano.width - cwid;
                Lx = Gx + cwid;
            }
        } else if (control.isKeyDown(Input.KEY_LEFT)) {
            Gx -= (0.25 * delta);
            if (Gx < 0) {
                Gx = 0;
            }
            Lx = Gx + cwid;
        }
        if (control.isKeyDown(Input.KEY_DOWN)) {
            Gy += (0.25 * delta);
            Ly = Gy + chei;
            if (Ly > tamano.height) {
                Gy = tamano.height - chei;
                Ly = Gy + chei;
            }
        } else if (control.isKeyDown(Input.KEY_UP)) {
            Gy -= (0.25 * delta);
            if (Gy < 0) {
                Gy = 0;
            }
            Ly = Gy + chei;
        }

    }

    private void targetScroll() {
        int tempx, tempy;
        tempx = (int) target.position.x;
        tempy = (int) target.position.y;
        if ((target.position.x > chaBoundry.getCenterX()) && (Lx < tamano.width)) {
            target.position.x = chaBoundry.getCenterX();
            Gx += (tempx - target.position.x);
            Lx = Gx + cwid;
        } else if ((target.position.x < chaBoundry.getCenterX()) && (Gx > target.getAncho())) {
            target.position.x = chaBoundry.getCenterX();
            Gx -= (target.position.x - tempx);
            Lx = Gx + cwid;
        }
        if ((target.position.y > chaBoundry.getCenterY()) && (Ly < (tamano.getHeight() - target.getAlto()))) {
            target.position.y = chaBoundry.getCenterY();
            Gy += (tempy - target.position.y);
            Ly = Gy + chei;
        } else if ((target.position.y < chaBoundry.getCenterY()) && (Gy > target.getAlto())) {
            target.position.y = chaBoundry.getCenterY();
            Gy -= (target.position.y - tempy);
            Ly = Gy + chei;
        }

    }

}
