/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystem;

import elements.levelcomponents.Platform;
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
import org.newdawn.slick.geom.Rectangle;
import subsystem.SpriteSheetCutter;

/**
 *
 * @author mcdre
 */
public class LeverLoader {

    private final SpriteSheetCutter tijeras;
    private File archivoCFG;
    private ArrayList<String> cfgfFile;
    private ArrayList<String> levelcfg;

    public LeverLoader() {
        tijeras = new SpriteSheetCutter();
        loadcfg("res/launcher.cfg");
    }

    private void loadcfg(String path) {
        cfgfFile = getFileContent(path);
    }

    private ArrayList<String> getFileContent(String filename) {
        ArrayList<String> filecontent = new ArrayList<>();
        String cadenaAux;
        archivoCFG = new File(filename);
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader(archivoCFG);
            try (BufferedReader lectorLines = new BufferedReader(lectorArchivo)) {
                while ((cadenaAux = lectorLines.readLine()) != null) {
                    filecontent.add(cadenaAux);
                }
            }
            lectorArchivo.close();
            archivoCFG = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeverLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se encontro el archivo " + archivoCFG.getName());
            System.out.println("\033[34mCreando nuevo;");

        } catch (IOException ex) {
            Logger.getLogger(LeverLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se puede leer el archivo " + archivoCFG.getName());
        }
        return filecontent;
    }

    public void prepareLevel(int index) {
        String conten;
        for (String aux : cfgfFile) {
            conten = aux.split(" ")[0];
            if (Integer.parseInt(conten) == index) {
                levelcfg = getFileContent(aux.split(" ")[1]);
            }
        }
        if (levelcfg == null) {
            System.err.println("No se pudo leer el nivel: " + index);
        }
    }
    
    public float getScale(){
        float escala = 1.0f;
         for (String str : levelcfg) {
            if (str.split(" ")[0].equals("ecl")) {
                escala = Float.parseFloat(str.split(" ")[1]);
            }
        }
    return escala;
    }

    public Image getBackgroiund() throws SlickException {
        String semipath;
        Image laimg = null;
        for (String str : levelcfg) {
            semipath = str.split(" ")[0];
            if (semipath.equals("b")) {
                laimg = new Image(str.split(" ")[1]);
            }
        }
        return laimg;
    }

    public Platform[] getPlataformas() throws SlickException {
        ArrayList<Platform> platsList = new ArrayList<>();
        Platform[] plat;
        for (String slam : levelcfg) {
            if (slam.split(" ")[0].equals("p")) {
                platsList.add(platmaker(slam));
            }
        }
        if (platsList.isEmpty()) {
            plat = new Platform[1];
            plat[0] = new Platform(new Rectangle(0, 0, 500, 500));
            plat[0].setTexture(new Image("res\\Img\\Levelcomponents\\platforms\\brick.png"));
        } else {
            plat = new Platform[platsList.size()];
            for (int i = 0; i < plat.length; i++) {
                plat[i] = platsList.get(i);
            }
        }
        return plat;
    }

    private Platform platmaker(String param) throws SlickException {
        Platform lepat;
        String[] order = param.split(" ");
        int x = Integer.parseInt(order[1]);
        int y = Integer.parseInt(order[2]);
        int wid = Integer.parseInt(order[3]);
        int hid = Integer.parseInt(order[4]);
        String texDir = order[5];
        lepat = new Platform(new Rectangle(x, y, wid, hid));
        lepat.setTexture(new Image(texDir));
        return lepat;
    }

}
