package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;
import TiposGenerales.ContainerS;
import elements.levelcomponents.Bullet;
import subsystem.LeverLoader;
import juegov1.Character;
import elements.leveltypes.StaticLevel;
import juegov1.IAcontroller;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author yury_
 */
public class nivel3 extends BasicGameState {

    private float wait = 0;
    private StaticLevel nivel;
    private ContainerS mainfrain;
    public Character personaje;
    private Input events;
    private LeverLoader cargador;
    private Character malo;

    @Override
    public int getID() {
        return 1;
    }
    private IAcontroller IA;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        cargador = new LeverLoader();
        cargador.prepareLevel(1);
        nivel = new StaticLevel(cargador.getScale());
        nivel.init(container, cargador.getBackgroiund());
        personaje = new Character(100f, 100f, 0.3f, 0, 250f, 800f, 0, 0, 0);
        personaje.IniAnimations("res/Proto/player.cfg");
        personaje.setVida(20);
        malo = new Character(500f, 100f, 0.3f, 0, 250, 800f, 0, 0, 1);
        malo.IniAnimations("res/Proto/enemigo1.cfg");
        malo.setVida(15);
        IA = new IAcontroller();
        nivel.setTarget(personaje);
        events = container.getInput();
        mainfrain = new ContainerS(cargador.getPlataformas());

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        nivel.render(container, game, g);
        mainfrain.render(g);
        if (personaje.isAlive()) {
            personaje.RenderDraw(container, g);
        }
        if (malo.isAlive()) {
            malo.RenderDraw(container, g);
        }

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        wait += (float) delta / 1000f;
        // System.out.println("delta");
        //System.out.println(wait);
        if (personaje.isAlive()) {
            if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && personaje.getTiempoBa() > personaje.getDelay()) {
                personaje.fireBullet(new Vector2f(container.getInput().getMouseX(), container.getInput().getMouseY()), new Bullet());
            }

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
            if (events.isKeyPressed(Input.KEY_ESCAPE)) {
                game.enterState(2);
            }
            if (wait >= 10f) {
                wait = 0f;
                malo.actionClick(IA.getkey(personaje.position, malo.position, personaje.shape, malo.shape, mainfrain));

                malo.fireBullet(new Vector2f(personaje.position.x, personaje.position.y), new Bullet());
            }
            personaje.updatePosition(delta, mainfrain);
            personaje.checkBulletCollision(malo.getBullets());
            if (!malo.isAlive()) {
                refreshnivel(container, 2);
            }
        }
        if (malo.isAlive()) {

            malo.checkBulletCollision(personaje.getBullets());
            //malo.updatePosition(delta, mainfrain);
            malo.updatePosition(delta, mainfrain, true, nivel);
            // malo.position.x = malo.position.x - nivel.getGlobalX();
            //malo.position.y = malo.position.y - nivel.getGlobalY();

        }

        mainfrain.update(nivel.getGlobalX(), nivel.getGlobalY());
        nivel.update(delta);

    }

    private void refreshnivel(GameContainer con, int index) throws SlickException {
        System.out.println("nivel " + index);
        cargador.prepareLevel(2);
        nivel = new StaticLevel(cargador.getScale());
        nivel.init(con, cargador.getBackgroiund());
        personaje = new Character(100f, 100f, 0.3f, 0, 250f, 800f, 0, 0, 0);
        personaje.IniAnimations("res/Proto/player.cfg");
        personaje.setVida(20);
        malo = new Character(500f, 100f, 0.3f, 0, 250, 800f, 0, 0, 1);
        malo.IniAnimations("res/Proto/enemigo2.cfg");
        malo.setVida(20);
        nivel.setTarget(personaje);
        IA = new IAcontroller();
        mainfrain = new ContainerS(cargador.getPlataformas());

    }

}
