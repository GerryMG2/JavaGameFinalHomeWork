/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
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
/**
 *
 * @author yury_
 */
public class MainMenu extends BasicGameState {
    
    private Image gear;
    private Image gear2;
    private int angulo;
    private Shape algo;
    private FontLoader Cfont;
    private Font fotnString;
    private TrueTypeFont leFont;
    private Input control;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game){
        angulo = 0;
        Cfont = new FontLoader("res/Fonts/fuente.ttf");
        fotnString = Cfont.getMyFont(1, 50);
        leFont = new TrueTypeFont(fotnString, true);
        control = container.getInput();
        try {
            gear = new Image("res/refer.png");
            gear2 = new Image("res/refer.png");
            //algo = new RoundedRectangle(10, 60, 450, 60, 50);
            algo = new Rectangle(10, 60, 450, 60);
        } catch (SlickException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g){
        g.setBackground(Color.white);
        gear.draw(0, 0, 626, 626);
        gear2.draw(560, 0, 626,626);
        g.setColor(Color.blue);
        g.fill(algo);
        g.draw(algo);
        leFont.drawString(25, 65, "Press Space", Color.yellow);
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta){
        angulo++;
        if(angulo > 360){
            angulo =0;
        }
        gear.setRotation(angulo);
        gear2.setRotation(-angulo+290);
        if(control.isKeyDown(Input.KEY_SPACE)){
            control.clearKeyPressedRecord();
            game.enterState(1);
        }
    }
    
}
