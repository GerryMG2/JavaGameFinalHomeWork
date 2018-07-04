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
import elements.levelcomponents.Bullet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

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
    private int currentB=0;
    
    private int delta=0;    
    
    private Bullet b;
    private static int cuantityBu=10;
    
    private Bullet[] bullets;
    
    private static int FireRate=250;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ugan = new ImagenP(100.0f, 100.0f, 1.0f, 20, 100f, 700f, "res/uganda.gif");
        flip = new Image("res/Img/Character/assets/player/player.png");
        events = gc.getInput();
        jugador = new Character(10, 500, 2, 0, 100.0f, 700f);
        jugador.IniAnimations(flip);
        fondo = new ScrollingBackground("res/Img/Backgorund/back.jpg", 7, gc);
        fondo.setDragSpeed(15);
        jugador2 = new Player("res/Img/Character/assets/player/player.png");
        boundry = new Rectangle(190, 30, (gc.getWidth() - 700), (gc.getHeight() - 60));
        jugador2.setBoundries(boundry);
        
        b = new Bullet(new Vector2f(100, 100), new Vector2f(500, 0)/*vector direccion de la vala primer valor x y segundo y*/);
        
        bullets = new Bullet[cuantityBu];
        
        for(int i =0 ;i<bullets.length;i++){
            bullets[i]= new Bullet();
        }
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.render(gc, grphcs);
        ugan.draw(ugan.position.getX(), ugan.position.getY(), ugan.getEscala());
        jugador.RenderDraw();
        jugador2.render();
        
        for(Bullet bu:bullets){
            bu.render(gc, grphcs);
        }
        
        b.render(gc, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        delta+=i;
        ugan.updatePosition(i);
        jugador.updatePosition(i);
        fondo.update(jugador2);
        if (events.isKeyDown(Input.KEY_D)) {
            jugador.actionClick(Input.KEY_D);
            ugan.actionClick(Input.KEY_D);

        }
        if (events.isKeyDown(Input.KEY_A)) {
            jugador.actionClick(Input.KEY_A);
            ugan.actionClick(Input.KEY_A);

        }
        if (events.isKeyPressed(Input.KEY_X)) {
            jugador.actionClick(Input.KEY_X);
            ugan.actionClick(Input.KEY_X);

        }
        if (!events.isKeyDown(Input.KEY_D) && !events.isKeyDown(Input.KEY_A)) {
            //System.out.println("entro");
            jugador.actionClick(666);
            ugan.actionClick(666);
        }
        jugador2.update(gc, i);
        if(fondo.getcLoop() > 1){
            sbg.enterState(3);
        }
        
        if(delta>FireRate&&gc.getInput().isKeyDown(Input.KEY_SPACE)){
            bullets[currentB]=new Bullet(new Vector2f(200, 300), new Vector2f(500, 50));
            currentB++;
            if(currentB >= bullets.length)currentB=0;
            delta=0;
        }
        
        for(Bullet bu:bullets){
            bu.update(i);
        }
        
        b.update(i);

    }

}
