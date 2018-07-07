package core;
import juegov1.Character;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import elements.leveltypes.StaticLevel;
import elements.levelcomponents.Platform;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
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
    public Character soldier2;
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
        cosa.setTexture(new Image("res\\Img\\brick.png"));
        cosa1.setTexture(new Image("res\\Img\\brick.png"));
        cosa2.setTexture(new Image("res\\Img\\brick.png"));
        cosa3.setTexture(new Image("res\\Img\\brick.png"));
        nivel = new StaticLevel(2);
        nivel.init(container, "res\\Img\\Backgorund\\ciudad.jpg");
        //algo = new Arma(new Image("res\\Img\\Character\\assets\\weapons\\bala.jpg"), container.getInput());
        soldier2 = new Character(100.0f,100.0f,3.0f,0,250f,700f);
        soldier2.IniAnimations(new Image("res\\Img\\Character\\assets\\player\\player.png"));
        nivel.setTarget(soldier2);
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
        soldier2.RenderDraw();

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        soldier2.updatePosition(delta);
        if (events.isKeyDown(Input.KEY_D)) {
            soldier2.actionClick(Input.KEY_D);

        }
        if (events.isKeyDown(Input.KEY_A)) {
            soldier2.actionClick(Input.KEY_A);

        }
        if (events.isKeyPressed(Input.KEY_X)) {
            soldier2.actionClick(Input.KEY_X);

        }
        if (!events.isKeyDown(Input.KEY_D) && !events.isKeyDown(Input.KEY_A)) {
            //System.out.println("entro");
            soldier2.actionClick(666);
        }
        cosa.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa1.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa2.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa3.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        nivel.update(delta);
        
       // algo.update(delta);
    }

}
