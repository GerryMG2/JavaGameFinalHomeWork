package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import elements.leveltypes.StaticLevel;
import elements.levelcomponents.Platform;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import TiposGenerales.Soldado;
import org.newdawn.slick.Input;

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
    //private DinamicObject algo;
    private Soldado soldier;
    private Input events;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
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
        //algo = new Arma(new Image("res\\Img\\Character\\assets\\weapons\\bala.jpg"), container.getInput());
        soldier = new Soldado(0, 0, 119.8f, 3, 0, 520, 400);
        soldier.IniAnimations(new Image("res\\Img\\Character\\assets\\player\\player.png"));
        nivel.setTarget(soldier);
        events = container.getInput();

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        nivel.render(container, game, g);
        cosa.render();
        cosa1.render();
        cosa2.render();
        cosa3.render();
        //algo.render();
        soldier.render();

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        soldier.update(delta);
        if (events.isKeyDown(Input.KEY_D)) {
            soldier.ActionClick(Input.KEY_D);

        }
        if (events.isKeyDown(Input.KEY_A)) {
            soldier.ActionClick(Input.KEY_A);

        }
        if (events.isKeyPressed(Input.KEY_X)) {
            soldier.ActionClick(Input.KEY_X);

        }
        if (!events.isKeyDown(Input.KEY_D) && !events.isKeyDown(Input.KEY_A)) {
            //System.out.println("entro");
            soldier.ActionClick(666);
        }
        cosa.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa1.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa2.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa3.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        nivel.update(delta);
        //algo.update(delta);
    }

}
