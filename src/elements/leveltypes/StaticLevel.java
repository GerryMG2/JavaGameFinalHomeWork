package elements.leveltypes;

import elements.gameactor.Player;
import juegov1.Character;
import java.awt.Dimension;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author yury_
 */
public class StaticLevel extends BasicGameState {

    private Dimension tamano;
    private Image fondo, flip;
    private float scal;
    private int cwid, chei, id;
    private int Gx, Gy, Lx, Ly, Co;
    private Player jugador2;
    private Character target;
    private Input control;
    private Shape dezone;
    private Rectangle platform;

    public StaticLevel(int id) {
        this.id = id;
        scal = 1;
    }

    public StaticLevel(int indicador, float scalar) {
        id = indicador;
        scal = scalar;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Gx = 0;
        Gy = 0;
        fondo = new Image("res/Img/Backgorund/ciudad.jpg");
        cwid = container.getWidth();
        chei = container.getHeight();
        tamano = new Dimension((int) (fondo.getWidth() * scal), (int) (fondo.getHeight() * scal));
        Lx = Gx + cwid;
        Ly = Gy + chei;
        jugador2 = new Player("res/Img/Character/assets/player/player.png");
        jugador2.setBoundries(0, 0, cwid, chei);
        control = container.getInput();
        flip = new Image("res/Img/Character/assets/player/player.png");
        target = new Character(10, 300, 2, 0, 100.0f, 700f);
        target.IniAnimations(flip);
        setTarget(target);
        platform = new Rectangle(10, 450, 300, 25);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (target == null) {
            freeScroll(delta);
        } else {
            targetScroll();
        }
        //jugador2.update(container, delta);
        if (platform.intersects(dezone)) {
            target.position.y = platform.getY() - target.getAlto();
        }
        target.updatePosition(delta);
        if (control.isKeyPressed(Input.KEY_X)) {
            target.actionClick(Input.KEY_X);
        }
        if (control.isKeyPressed(Input.KEY_A)) {
            target.actionClick(Input.KEY_A);
        }
        if (control.isKeyPressed(Input.KEY_D)) {
            target.actionClick(Input.KEY_D);
        }
        if (platform.intersects(dezone)) {
            target.position.y = platform.getY() - target.getAlto();
        }

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw(0, 0, cwid, chei, Gx, Gy, Lx, Ly);
        //jugador2.render();
        target.RenderDraw();
        g.fill(platform);
        g.draw(platform);
    }

    public void setTarget(Character mainThing) {
        target = mainThing;
        dezone = target.shape;
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
        Gx = (int) target.position.x;
        Gy = (int) target.position.y;
        Lx = Gx + cwid;
        Ly = Gy + chei;
        if (Lx >= fondo.getWidth()) {
            Lx = fondo.getWidth();
            Gx = Lx - cwid;
        }
        if (Ly >= fondo.getHeight()) {
            Ly = fondo.getHeight();
            Gy= Ly - cwid;
        }
    }

}
