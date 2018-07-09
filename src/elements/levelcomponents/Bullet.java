/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements.levelcomponents;

import TiposGenerales.ContainerS;
import TiposGenerales.DinamicObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author mcdre
 */
public class Bullet extends DinamicObject {

    private Vector2f pos;
    private Vector2f speed;
    private int lived = 0;
    protected int DAMAGE = 5;

    private boolean active = true;

    private static int MAX_LIFETIME = 3000;

    public Bullet(float x, float y, float gravity, float escala, int desfase) {
        super(x, y, gravity, escala, desfase);
        active = false;
    }

    public Bullet(float x, float y, float gravity, float escala, int desfase, Vector2f pos, Vector2f speed) {
        super(x, y, gravity, escala, desfase);
        this.pos = pos;
        this.speed = speed;
        this.active = true;
    }

    public void update(int t) {
        if (active) {

            this.position.y = (float) this.position.y
                    - (float) (this.vey0 * tiempo)
                    + (float) (0.5f * (gravity) * (float) (Math.pow(tiempo, 2)));
            this.vey0 = this.vey0 + (this.gravity * tiempo);

            this.shape.setY(this.position.y);

            this.position.x = JuegoV1.contenedor.getWidth() - this.getAncho();
            Vector2f realSpeed = speed.copy();
            realSpeed.scale((t / 1000.0f));
            pos.add(realSpeed);
            lived += t;

            if (lived > MAX_LIFETIME) {
                active = false;
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (active) {
            g.setColor(Color.yellow);
            g.fillOval(pos.getX() - 10, pos.getY() - 10, 20, 20);
        }

    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean estado) {
        this.active = estado;
    }

    public boolean collideWith(Vector2f otherPos, int tamannoObjectocolisionar) {
        int dis = (int) otherPos.copy().sub(pos).lengthSquared();

        if (dis < (tamannoObjectocolisionar + 10/*tammanodelabala*/)) {
            return true;
        }
        return false;
    }

    public int getDamage() {
        return DAMAGE;
    }

    @Override
    public void update(int delta, ContainerS con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
