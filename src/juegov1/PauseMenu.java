/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import elements.levelcomponents.Bullet;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import subsystem.FontLoader;


/**
 *
 * @author gerar
 */
public class PauseMenu extends BasicGameState {
    
    private Image pauseMenu;
    private Image resume;
    private Image restart;
    private Image quit;
    private Input control;
    
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        control = gc.getInput();
        try {
            pauseMenu = new Image("res/pause_menu.png");
            resume = new Image("res/resume_button.png");
            restart = new Image("res/restart_button.png");
            quit = new Image("res/quit_button.png");
        } catch (SlickException ex) {
            Logger.getLogger(PauseMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
         grphcs.setBackground(Color.transparent);
        grphcs.drawImage(pauseMenu, 0, 0);
        
        if(control.getMouseX() >= 455 && control.getMouseX() <= 783 &&
                control.getMouseY() >= 294 && control.getMouseY() <= 357){
                grphcs.drawImage(resume,457, 294);
        }
        
        if(control.getMouseX() >= 457 && control.getMouseX() <= 783 &&
                control.getMouseY() >= 393 && control.getMouseY() <= 455){
                grphcs.drawImage(restart,457, 392);
        }
        
        if(control.getMouseX() >= 458 && control.getMouseX() <= 783 &&
                control.getMouseY() >= 487 && control.getMouseY() <= 548){
                grphcs.drawImage(quit,458, 486);
        }
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(control.getMouseX() >= 458 && control.getMouseX() <= 783 &&
                control.getMouseY() >= 487 && control.getMouseY() <= 548 && control.isMousePressed(0)){
                sbg.enterState(0);
        }
    }

}
