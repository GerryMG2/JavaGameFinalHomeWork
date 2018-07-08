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
import TiposGenerales.ContainerS;

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
    private ContainerS mainfrain;
    public Character personaje;
    private Input events;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        cosa = new Platform(new Rectangle(100, 2000, 300, 50));
        cosa1 = new Platform(new Rectangle(900, 2000, 300, 50));
        cosa2 = new Platform(new Rectangle(1300, 2000, 300, 50));
        cosa3 = new Platform(new Rectangle(1800, 2200, 300, 50));
        cosa.setTexture(new Image("res\\Img\\brick.png"));
        cosa1.setTexture(new Image("res\\Img\\brick.png"));
        cosa2.setTexture(new Image("res\\Img\\brick.png"));
        cosa3.setTexture(new Image("res\\Img\\brick.png"));
        nivel = new StaticLevel(2);
        nivel.init(container, "res\\Img\\Backgorund\\ciudad.jpg");
        //personaje = new CharPlayer(100,100,2,1,500,1280);
        //personaje.setBoundry(0, 0, container.getWidth(), container.getHeight());
        personaje = new Character(100f, 100f, 0.3f, 0, 250f, 800f, 0, 0);
        personaje.IniAnimations(new Image("res\\Img\\Character\\assets\\spritesheets\\__soldier_one_black_uniform_aim.png"));
        nivel.setTarget(personaje);
        events = container.getInput();
        mainfrain = new ContainerS(new Platform[4]);
        mainfrain.lista[0] = cosa;
        mainfrain.lista[1] = cosa1;
        mainfrain.lista[2] = cosa2;
        mainfrain.lista[3] = cosa3;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        nivel.render(container, game, g);
        mainfrain.render(g);
        personaje.RenderDraw(g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (events.isKeyDown(Input.KEY_D)) {
            personaje.actionClick(Input.KEY_D);

        }
        if (events.isKeyDown(Input.KEY_A)) {
            personaje.actionClick(Input.KEY_A);

        }
        if (events.isKeyPressed(Input.KEY_X)) {
            personaje.actionClick(Input.KEY_X);

        }
        if (!events.isKeyDown(Input.KEY_D) && !events.isKeyDown(Input.KEY_A)) {
            //System.out.println("entro");
            personaje.actionClick(666);
        }
        if (events.isKeyPressed(Input.KEY_SPACE)) {
            nivel.setTarget(null);
        }
        personaje.updatePosition(delta, mainfrain);
        
        mainfrain.update(nivel.getGlobalX(), nivel.getGlobalY());
        nivel.update(delta);

    }

}
