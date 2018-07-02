package subsystem;

/**
 *
 * @author yury_
 */
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    private File fontTTF;
    private InputStream is;
    private Font custonfont;

    public FontLoader() {
    }

    public FontLoader(String fontpath) {
        try {
            //Se carga la fuente de disco
            fontTTF = new File(fontpath);
            //convirtiendola a Stream para poder ser leida por SLICK2D
            is = new FileInputStream(fontTTF);
            custonfont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontpath + " No se cargo la fuente");
            custonfont = new Font("Arial", Font.PLAIN, 14);
        }
    }

    public void loadFont(String fontpath) {
        try {
            //Se carga la fuente de disco
            fontTTF = new File(fontpath);
            //convirtiendola a Stream para poder ser leida por SLICK2D
            is = new FileInputStream(fontTTF);
            custonfont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontpath + " No se cargo la fuente");
            custonfont = new Font("Arial", Font.PLAIN, 14);
        }
    }

    /**
     *
     * @param estilo
     * @param tamanio
     * @return Font
     *
     * Font.PLAIN = 0 Font.BOLD = 1 Font.ITALIC = 2 tamanio = float
     */
    public Font getMyFont(int estilo, float tamanio) {
        Font tfont = custonfont.deriveFont(estilo, tamanio);
        return tfont;
    }

}
