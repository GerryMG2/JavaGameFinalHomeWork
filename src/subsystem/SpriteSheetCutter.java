package subsystem;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;

/**
 *
 * @author yury_
 */
public class SpriteSheetCutter {

    private Image sheet;
    private SpriteSheet cuttedSheet;

    public SpriteSheetCutter() {
    }

    /**
     *este metodo retorna un corte como una subimagen de la original
     * esta es solo una referencia asi que si se altera la imagen original
     * tambien esta sufrira cambios
     * @param imagen la imagen con la que trabajara el recorte
     * @param x coordenadas respecto de la imagen
     * @param y coordenadas respecto de la imagen
     * @param wid es el ancho de cada cuadrado de la imagen
     * @param hid es el alto del cuadrado de la subimagen
     * @return un SpriteSheet con los cortes deseados listo para ser animado
     */
    public Image cutsubimg(Image imagen, int x, int y,int wid,int hid) {
        return imagen.getSubImage(x, y, wid, hid);
    }

    /**
     *
     * @param imagen la imagen con la que trabajara el recorte
     * @param htiles es el ancho de cada cuadrado del spritesheet
     * @param vtiles es elalto de cada cuadrado del sprite sheet
     * @return un SpriteSheet con los cortes deseados listo para ser animado
     */
    public SpriteSheet cutimg(Image imagen, int htiles, int vtiles) {
        return new SpriteSheet(imagen, htiles, vtiles);
    }

    /**
     *
     * @param imagen la imagen con la que trabajara el recorte
     * @param htiles es el ancho de cada cuadrado del spritesheet
     * @param vtiles es elalto de cada cuadrado del sprite sheet
     * @return una Animation con los cortes deseados con duracion de 80
     * milisegundos
     */
    public Animation makeAnimation(Image imagen, int htiles, int vtiles) {
        return new Animation(new SpriteSheet(imagen, htiles, vtiles), 80);

    }

    /**
     *
     * @param imagen la imagen con la que trabajara el recorte
     * @param htiles es el ancho de cada cuadrado del spritesheet
     * @param vtiles es elalto de cada cuadrado del sprite sheet
     * @param duracion el tiempo en milisegundos que dura cada frame, 1ms = 1,
     * 100ms = 100 se expresa como int
     * @return una Animation con los cortes deseados y la duracion especificada
     * en milisegundos
     */
    public Animation makeAnimation(Image imagen, int htiles, int vtiles, int duracion) {
        return new Animation(new SpriteSheet(imagen, htiles, vtiles), duracion);
    }

    /**
     *
     * @param imagen Esta es la imagen que desea cortar para creat una animacion
     * @param xcut este es el punto x absoluto donde desea iniciar el corte
     * relativo a su imagen
     * @param ycut este es el punto y absoluto donde desea iniciar el corte
     * relativo a su imagen
     * @param xwid este sera el ancho total del corte por lo que la coordenada
     * final se sumnaria con xcut por ejemplo si corta en x = 50px con ancho de
     * 300px el corte en la imagen seria de 50PX A 350PX
     * @param yheid =este sera el alto total del corte por lo que la coordenada
     * final se sumnaria con ycut por ejemplo si corta en y = 50px con alto de
     * 300px el corte en la imagen seria de 50PX A 350PX
     * @param xtiles este es el numero de cuadros o frames contenidos en el
     * corte horizontalmente
     * @param ytiles este es el numero de cuadros o frames contenidos en el
     * corte vertivalmente
     * @return Animation con los frames cortados con una duracion de 80ms cada
     * frame por defecto
     */
    public Animation makeAnimation(Image imagen, int xcut, int ycut, int xwid, int yheid, int xtiles, int ytiles) {
        sheet = imagen.getSubImage(xcut, ycut, xwid, yheid);
        cuttedSheet = new SpriteSheet(sheet, (sheet.getWidth() / xtiles), (sheet.getHeight() / ytiles));
        return new Animation(cuttedSheet, 80);
    }

}
