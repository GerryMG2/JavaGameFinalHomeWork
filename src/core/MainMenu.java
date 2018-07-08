/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    private Image gear;
    private Image gear2;
    private int angulo;
    private Boton playButton;
    private Boton exitButton;
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
        playButton = new Boton();
        exitButton = new Boton();
        Cfont = new FontLoader("res/Fonts/Fuente.ttf");
        fotnString = Cfont.getMyFont(1, 50);
        leFont = new TrueTypeFont(fotnString, true);
        control = container.getInput();
        try {
            gear = new Image("res/buttons.png");
            gear2 = new Image("res/buttons_effect.png");
            forma = new RoundedRectangle(200, 100, 300, 70, 50);
        } catch (SlickException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
        g.setBackground(Color.transparent);
        //gear.draw(0, 0, 626, 626);
        //gear2.draw(560, 0, 626,626);
        g.setColor(Color.transparent);
        g.fill(forma);
        
        leFont.drawString(210,105,"MAIN MENU", Color.yellow);
        
        //Botones
            playButton.setLocation(new Punto(200,200));
            playButton.setSize(500, 100);
            playButton.setFont(leFont);
            playButton.setIcon(gear);
            g.setColor(Color.transparent);
            g.drawImage(gear, playButton.getPunto().getX(), playButton.getPunto().getY());
            playButton.setText("Play", Color.black);
            
            if(control.getMouseX() >= 190 && control.getMouseX() <= 690 &&
                control.getMouseY() >=190 && control.getMouseY() <= 300){
                playButton.setIcon(gear2);
                g.drawImage(gear2, playButton.getPunto().getX(), playButton.getPunto().getY());
                playButton.setText("Play", Color.black);
            }
            
            exitButton.setLocation(new Punto(200,400));
            exitButton.setSize(500, 100);
            exitButton.setFont(leFont);
            exitButton.setIcon(gear);
            g.drawImage(gear, exitButton.getPunto().getX(), exitButton.getPunto().getY());
            exitButton.setText("Exit", Color.black);
            
            if(control.getMouseX() >= 190 && control.getMouseX() <= 690 &&
                control.getMouseY() >=390 && control.getMouseY() <= 500){
                playButton.setIcon(gear2);
                g.drawImage(gear2, exitButton.getPunto().getX(), exitButton.getPunto().getY());
                exitButton.setText("Exit", Color.black);
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
        
        if(control.getMouseX() >= 190 && control.getMouseX() <= 690 &&
                control.getMouseY() >=190 && control.getMouseY() <= 300 && control.isMousePressed(0)){
            game.enterState(4);
        }
        
        if(control.getMouseX() >= 190 && control.getMouseX() <= 690 &&
                control.getMouseY() >= 390 && control.getMouseY() <= 500 && control.isMousePressed(0)){
            System.exit(0);
        }
        
    }
    
}
