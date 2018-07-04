package elements.leveltypes;

/**
 *
 * @author yury_
 */
import elements.gameactor.Player;
import elements.levelcomponents.ScrollingBackground;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SideScrollLevel extends BasicGameState {

    private final int loops, id;
    private ScrollingBackground fondo;
    private Player jugador2;
    private Rectangle boundry;

    public SideScrollLevel(int loops, int id) {
        this.loops = loops;
        this.id = id;
    }

    public void setBackground(Image fondo, GameContainer cnt) {
        this.fondo = new ScrollingBackground(fondo, loops, cnt);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        setBackground(new Image("res/Img/Backgorund/Desierto.jpg"), container);
        fondo.setDragSpeed(25);
        jugador2 = new Player("res/Img/Character/assets/player/player.png");
        boundry = new Rectangle(190, 30, (container.getWidth() - 700), (container.getHeight() - 60));
        jugador2.setBoundries(boundry);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (fondo != null) {
            fondo.render(container, g);
        }
        jugador2.render();
        

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (fondo != null) {
            fondo.update(jugador2);
        }
        jugador2.update(container, delta);
    }

}
