/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.*;

/**
 *
 * @author david
 */
public class Boton{
    
    private Punto posicion;
    private int height;
    private int width;
    private Image imag;
    private Color colorEntrada;
    private TrueTypeFont fuente;
    private Rectangle forma;
    private int cant;
    
    public Boton(){}
    
    public Boton(Color entrada){
        colorEntrada = entrada; 
    }
    //Metodos para el boton.
    public void setLocation(Punto posicion){
        this.posicion = posicion;
    }
    
    public void setSize(int width, int height){
        forma = new Rectangle(posicion.getX(), posicion.getY(), width, height);
    }
    
    public void setText(String message, Color color){
        fuente.drawString((posicion.getX()+40), (posicion.getY()+10), message, color);
    }
    
    //Metodo para colorear si se acerca el puntero del mouse
    public void setColor(Color colorEntrada){
        
        this.colorEntrada = colorEntrada;
    }
    
    public void setIcon(Image imag) throws SlickException{
        this.imag = imag;
    }
    
    public void setFont(TrueTypeFont fuente){
        this.fuente = fuente;
    }

    //Getters
    public Image getImag() {
        return imag;
    }
    
    public Rectangle getForma(){
        return forma;
    }
    
    public Punto getPunto(){
        return posicion;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public int getCant(){
        return cant;
    }
    
    public Color getColor(){
        return colorEntrada;
    }
}
