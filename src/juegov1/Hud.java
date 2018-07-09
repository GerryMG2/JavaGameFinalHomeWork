/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import TiposGenerales.personaje;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author david
 */
public class Hud {
    
    public static void drawLifeBar(Graphics g, int vida){
        Color color = new Color(18,142,28);

        g.drawString("Life Bar", 20, 120);
        g.setColor(Color.white);
        g.drawRect(19, 99, 102, 17);
        
        g.setColor(Color.green);
        g.fillRect(20, 100, 200, 5);
        
        g.setColor(color);
        g.fillRect(20, 105, 200, 10);
    }
    
    public static void drawAmmoBar(Graphics g, int municion){
        g.drawString("Ammo",600,120);
        g.setColor(Color.yellow);
        
    }
}
