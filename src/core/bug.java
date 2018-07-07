package core;

/**
 *
 * @author yury_
 */
import TiposGenerales.ContainerS;
import TiposGenerales.DinamicObject;
import org.newdawn.slick.Input;

public class bug extends DinamicObject {

    private Input control;
    private int spped;

    public bug(float x, float y, float gravity, float escala, int desfase) {
        super(x, y, gravity, escala, desfase);
    }

    public void setSpped(int spped) {
        this.spped = spped;
    }

    @Override
    public void update(int delta) {
        position.x += spped;
        position.y += spped;

    }

    public void retarget(int Gx, int Gy, int dels) {
        update(dels);
        if (dels > 17) {
            System.out.println("DXD: " + Gx);
            System.out.println("DYD: " + Gy);
            System.out.println("El bug esta en: (" + position.x + "," + position.y + ")");
        }
        position.x += Gx;
        position.y += Gy;
    }

    @Override
    public void update(int delta, ContainerS con) {
        if (control.isKeyDown(Input.KEY_J)) {
            position.x -= spped;
        }
        if (control.isKeyDown(Input.KEY_L)) {
            position.x += spped;
        }
        if (control.isKeyDown(Input.KEY_I)) {
            position.y -= spped;
        }
        if (control.isKeyDown(Input.KEY_K)) {
            position.y += spped;
        }
    }

    public void setControl(Input control) {
        this.control = control;
    }
}
