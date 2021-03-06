package elements.powerup;

import TiposGenerales.ContainerS;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import elements.levelcomponents.Bullet;
import org.newdawn.slick.geom.Vector2f;
import TiposGenerales.DinamicObject;

/**
 *
 * @author yury_
 */
public class Arma extends DinamicObject{

    public int x, y;
    private final Input control;
    private Bullet bala;

    public Arma(Image texture, Input control) {
        super(0.0f, 0.0f, 9.8f, 1.0f, 0);
        this.control = control;
        super.IniAnimations(texture);
    }

    public void update(int xcoor, int ycoor, int delta) {
        x = xcoor;
        y = ycoor;
        calcAngle(control.getMouseX(), control.getMouseY());
        if (control.isKeyPressed(Input.KEY_B)) {
            bala = new Bullet(new Vector2f(x+getAncho(), (y+(getAlto()-20)/2)), new Vector2f(500, 0));
            System.out.println("Disparo");
        }

        if (bala != null) {
            bala.update(delta);
        }
    }

    private void calcAngle(int xp, int yp) {
       // angle = (int) getAngle((vx2 - vx0), (vy2-vy1));

    }

    private float getAngle(int ycomp, int xcomp) {
        float angulo;
        //funcion Match.atan solo puede medir angulos entre 45-90 y entre -45 y -90 todo lo demas da 0 o null
        angulo = (float) Math.atan(xcomp / ycomp);
        angulo = (float) Math.toDegrees(angulo);
        System.out.println("" + angulo);
        return angulo;
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void update(int delta, ContainerS con) {
        
    }

}
