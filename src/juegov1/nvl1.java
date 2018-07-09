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

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        events = gc.getInput();
        cargador = new LeverLoader();
        cargador.prepareLevel(4);
        nivel = new StaticLevel(cargador.getScale());
        nivel.init(gc, cargador.getBackgroiund());
        jugador = new Character(100f, 100f, 0.3f, 0, 350f, 2000f, 0, 0);
        jugador.IniAnimations(new Image("res\\Img\\Character\\assets\\spritesheets\\__soldier_one_black_uniform_aim.png"));
        nivel.setTarget(jugador);
        mainfrain = new ContainerS(cargador.getPlataformas());

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        nivel.render(gc, sbg, grphcs);
        mainfrain.render(grphcs);
        jugador.RenderDraw(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
        jugador.updatePosition(i, mainfrain);
        nivel.update(i);
        mainfrain.update(nivel.getGlobalX(), nivel.getGlobalY());
        nivel.update(i);
    }

}
