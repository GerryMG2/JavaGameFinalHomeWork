/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import juegov1.Boton;

import juegov1.Punto;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;
import subsystem.FontLoader;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.RoundedRectangle;
/**
 *
 * @author yury_
 */
public class MainMenu extends BasicGameState {
    
    private Image play;
    private Image exit;
    private Image menu;
    private int angulo; 
    private FontLoader Cfont;
    private Font fotnString;
    private TrueTypeFont leFont;
    private Input control;
    private RoundedRectangle forma;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game){
        angulo = 0;
        Cfont = new FontLoader("res/Fonts/Fuente.ttf");
        fotnString = Cfont.getMyFont(1, 50);
        leFont = new TrueTypeFont(fotnString, true);
        control = container.getInput();
        try {
            play = new Image("res/play_button.png");
            exit = new Image("res/exit_button.png");
            menu = new Image("res/menu.png");
            forma = new RoundedRectangle(200, 100, 300, 70, 50);
        } catch (SlickException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
        g.setBackground(Color.transparent);
        g.drawImage(menu, 0, 0);

        //Botones
            if(control.getMouseX() >= 350 && control.getMouseX() <= 960 &&
                control.getMouseY() >=245 && control.getMouseY() <= 355){
                g.drawImage(play,358, 244);
            }
            if(control.getMouseX() >= 476 && control.getMouseX() <= 800 &&
                control.getMouseY() >=390 && control.getMouseY() <= 465){
                g.drawImage(exit,476 ,396 );
            }

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException{
        angulo++;
        if(angulo > 360){
            angulo =0;
        }
        //gear.setRotation(angulo);
        //gear2.setRotation(-angulo+290);
        
        if(control.getMouseX() >= 350 && control.getMouseX() <= 960 &&
                control.getMouseY() >=245 && control.getMouseY() <= 355 && control.isMousePressed(0)){
            game.enterState(4);
        }
        
        if(control.getMouseX() >= 476 && control.getMouseX() <= 800 &&
                control.getMouseY() >= 390 && control.getMouseY() <= 465 && control.isMousePressed(0)){
            System.exit(0);
        }
        
    }
    
}
