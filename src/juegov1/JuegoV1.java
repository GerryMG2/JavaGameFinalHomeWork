/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import core.MainMenu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import elements.leveltypes.*;
import core.nivel3;

/**
 *
 * @author gerar
 */
public class JuegoV1 extends StateBasedGame {

    public static AppGameContainer contenedor;

    /**
     * @param args the command line arguments
     * @throws org.newdawn.slick.SlickException
     */
    public static void main(String[] args) throws SlickException {
        try {
            contenedor = new AppGameContainer(new JuegoV1("JuegoJava"));
            contenedor.setDisplayMode(1280, 720, false);
            contenedor.setVSync(true);
            contenedor.start();
        } catch (SlickException slick) {
            slick.printStackTrace();
        }
    }

    public JuegoV1(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
         addState(new nivel3());
        this.addState(new nvl1());
        this.addState(new Menu());
    }

}
