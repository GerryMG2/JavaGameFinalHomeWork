package core;

import TiposGenerales.Soldado;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import elements.leveltypes.StaticLevel;
import elements.levelcomponents.Platform;
import juegov1.Character;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author yury_
 */
public class nivel3 extends BasicGameState {

    private Platform cosa;
    private Platform cosa1;
    private Platform cosa2;
    private Platform cosa3;
    private StaticLevel nivel;
    private Character jugador;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        jugador = new Character(0, 0, 2, 0, 500, 700);
        jugador.IniAnimations(new Image("res\\Img\\Character\\assets\\player\\player.png"));
        cosa = new Platform(new Rectangle(0, 500, 300, 50));
        cosa1 = new Platform(new Rectangle(500, 800, 300, 50));
        cosa2 = new Platform(new Rectangle(800, 600, 300, 50));
        cosa3 = new Platform(new Rectangle(1200, 1500, 300, 50));
        cosa.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa1.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa2.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa3.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        nivel = new StaticLevel(2);
        nivel.init(container, "res\\Img\\Backgorund\\nivel3.jpg");
        nivel.setTarget(jugador);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        nivel.render(container, game, g);
        jugador.RenderDraw();
        cosa.render();
        cosa1.render();
        cosa2.render();
        cosa3.render();

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        cosa.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa1.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa2.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa3.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        jugador.updatePosition(delta);
        if ((jugador.position.x+jugador.getAncho() > cosa3.getContenedor().getX())) {
            jugador.position.x = cosa3.getContenedor().getX()-jugador.getAncho();
        }
        nivel.update(container, game, delta);
    }

}
