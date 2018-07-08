/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import subsystem.SpriteSheetCutter;

/**
 *
 * @author mcdre
 */
public class Base {
    
    private static SpriteSheetCutter aux;
    
    public static int lastIDReserva = 0;
    public static int lastIDFactura = 0;
    public static final String personaje = "soldado";
    public static final String enemigo = "enemigo";
    public static final String HABITACIONES = "habitaciones";
    private File archivoTXT;
    
    public Base() {
    }
    
    private ArrayList<String> getFileContent(String filename) {
        ArrayList<String> filecontent = new ArrayList<>();
        String cadenaAux;
        archivoTXT = new File(filename + ".txt");
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader(archivoTXT);
            try (BufferedReader lectorLines = new BufferedReader(lectorArchivo)) {
                while ((cadenaAux = lectorLines.readLine()) != null) {
                    filecontent.add(cadenaAux);
                }
            }
            lectorArchivo.close();
            archivoTXT = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se encontro el archivo " + archivoTXT.getName());
            System.out.println("\033[34mCreando nuevo;");

        } catch (IOException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se puede leer el archivo " + archivoTXT.getName());
        }
        return filecontent;
    }
    
    public ArrayList<Animation> getListHabitacion() throws SlickException {
        
        ArrayList<Animation> listaAux = new ArrayList<>();
        for (String linea : getFileContent(HABITACIONES)) {
            listaAux.add(createAnimation(linea));
        }
        return listaAux;
    }
    
    public static Animation createAnimation(String master) throws SlickException {
        String nombre = master.split(" ")[0];
        int x = Integer.parseInt(master.split(" ")[1]);
        int y = Integer.parseInt(master.split(" ")[2]);
        int ancho = Integer.parseInt(master.split(" ")[3]);
        int alto = Integer.parseInt(master.split(" ")[4]);
        int cortesX = Integer.parseInt(master.split(" ")[5]);
        int cortesY = Integer.parseInt(master.split(" ")[6]);
        Image image = new Image(nombre);
        return  aux.makeAnimation(image, x,y,ancho,alto,cortesX, cortesY);

    }
}
