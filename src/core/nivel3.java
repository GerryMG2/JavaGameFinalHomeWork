package core;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Input;
import TiposGenerales.ContainerS;
import subsystem.LeverLoader;
import juegov1.Character;
import elements.leveltypes.StaticLevel;
import elements.levelcomponents.Platform;

/**
 *
 * @author yury_
 */
public class nivel3 extends BasicGameState {

    private StaticLevel nivel;
    private ContainerS mainfrain;
    public Character personaje;
    private Input events;
    private LeverLoader cargador;
    private Platform[] babosadas;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        cargador = new LeverLoader();
        cargador.prepareLevel(1);
        nivel = new StaticLevel(2);
        nivel.init(container, cargador.getBackgroiund());
        personaje = new Character(100f, 100f, 0.3f, 0, 250f, 800f, 0, 0);
        personaje.IniAnimations(new Image("res\\Img\\Character\\assets\\spritesheets\\__soldier_one_black_uniform_aim.png"));
        nivel.setTarget(personaje);
        events = container.getInput();
        babosadas = new Platform[3];
        babosadas[0] = new Platform(new Rectangle(100, 1300, 300, 50));
        babosadas[1] = new Platform(new Rectangle(500, 1300, 300, 50));
        babosadas[2] = new Platform(new Rectangle(900, 1300, 300, 50));
        babosadas[0].setTexture(new Image("res/Img/ladrillo_2_texture.png"));
        babosadas[1].setTexture(new Image("res/Img/ladrillo_2_texture.png"));
        babosadas[2].setTexture(new Image("res/Img/ladrillo_2_texture.png"));
        mainfrain = new ContainerS(babosadas);
        
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
