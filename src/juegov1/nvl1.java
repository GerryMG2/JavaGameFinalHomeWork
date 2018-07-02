/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import elements.levelcomponents.ScrollingBackground;
import elements.gameactor.Player;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author gerar
 */
public class nvl1 extends BasicGameState {

    private Input events;
    private ImagenP ugan;
    private Image flip;
    private Character jugador;
    private ScrollingBackground fondo;
    private Player jugador2;
    private Rectangle boundry;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ugan = new ImagenP(100.0f, 100.0f, 1.0f, 20, 5.0f, 15.0f, "res/uganda.gif");
        flip = new Image("res/Img/Character/assets/player/player.png");
        events = gc.getInput();
        jugador = new Character(10, 500, 2, 0, 10.0f);
        jugador.IniAnimations(flip);
        fondo = new ScrollingBackground("res/Img/Backgorund/back.jpg", 7, gc);
        fondo.setDragSpeed(15);
        jugador2 = new Player("res/Img/Character/assets/player/player.png");
        boundry = new Rectangle(190, 30, (gc.getWidth() - 700), (gc.getHeight() - 60));
        jugador2.setBoundries(boundry);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.render(gc, grphcs);
        ugan.draw(ugan.position.getX(), ugan.position.getY(), ugan.getEscala());
        jugador.RenderDraw();
        jugador2.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        ugan.updatePosition(i);
        jugador.updatePosition(i);
        jugador.actionClick(events);
        jugador.ActionMove(i);
        ugan.actionClick(events);
        jugador2.update(gc, i);
        fondo.update(jugador2);
    }

}
