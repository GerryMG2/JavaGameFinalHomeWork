package core;

/**
 *
 * @author yury_
 */
import TiposGenerales.ContainerS;
import TiposGenerales.DinamicObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class bug extends DinamicObject {

    private Input control;
    private int spped;
    private int acumR, acumD;
    private boolean right, down;

    public bug(float x, float y, float gravity, float escala, int desfase) {
        super(x, y, gravity, escala, desfase);
        right = true;
        down = true;
        acumD = 0;
        acumR = 0;
    }

    public void setSpped(int spped) {
        this.spped = spped;
    }

    @Override
    public void update(int delta) {
        if (control.isKeyDown(Input.KEY_J)) {
            position.x = 100;
            position.y = 100;
            acumR = 0;
            acumD = 0;
        }
        if (right) {
            if (!down) {
                position.x += spped;
                acumR += spped;
            }
            if (acumR > 800) {
                right = false;
            }
        } else {
            position.x -= spped;
            acumR -= spped;
            if (acumR < 100) {
                right = true;
            }
        }

    }

    public void retarget(int Dxd, int Dyd, int dels) {
        update(dels);
        if (dels > 17) {
            System.out.println("DXD: " + Dxd);
            System.out.println("DYD: " + Dyd);
            System.out.println("El bug esta en: (" + position.x + "," + position.y + ")");
        }
        position.x += Dxd;
        position.y += Dyd;
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
