/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import TiposGenerales.ContainerS;
import elements.levelcomponents.Platform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import elements.levelcomponents.Bullet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import elements.leveltypes.StaticLevel;
import subsystem.LeverLoader;

/**
 *
 * @author gerar
 */
public class nvl1 extends BasicGameState {

    private StaticLevel nivel;
    private ContainerS mainfrain;
    public Character jugador;
    private Input events;
    private LeverLoader cargador;
    private Platform[] babosadas;
    private Bullet[] bullets;
    private static int FireRate = 250;
    private Platform cosa;
    private Platform cosa1;
    private Platform cosa2;
    private Platform cosa3;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        events = gc.getInput();
        cargador = new LeverLoader();
        cargador.prepareLevel(1);
        nivel = new StaticLevel(3);
        nivel.init(gc, cargador.getBackgroiund());
        cosa = new Platform(new Rectangle(10, 900, 100, 20));
        cosa1 = new Platform(new Rectangle(310, 900, 100, 20));
        cosa2 = new Platform(new Rectangle(610, 900, 100, 20));
        cosa3 = new Platform(new Rectangle(1200, 900, 100, 20));
        cosa.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa1.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa2.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        cosa3.setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        jugador = new Character(100f, 100f, 0.3f, 0, 250f, 800f, 0, 0);
        jugador.IniAnimations(new Image("res\\Img\\Character\\assets\\spritesheets\\__soldier_one_black_uniform_aim.png"));

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        nivel.render(gc, sbg, grphcs);
        cosa.render();
        cosa1.render();
        cosa2.render();
        cosa3.render();
        jugador.RenderDraw(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //     jugador.updatePosition(i);
        if (events.isKeyDown(Input.KEY_D)) {
            jugador.actionClick(Input.KEY_D);

        }
        if (events.isKeyDown(Input.KEY_A)) {
            jugador.actionClick(Input.KEY_A);

        }
        if (events.isKeyPressed(Input.KEY_X)) {
            jugador.actionClick(Input.KEY_X);

        }
        if (!events.isKeyDown(Input.KEY_D) && !events.isKeyDown(Input.KEY_A)) {
            jugador.actionClick(666);
        }

        nivel.update(i);
        cosa.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa1.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa2.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
        cosa3.relocalizar(nivel.getGlobalX(), nivel.getGlobalY());
    }

}
