package elements.levelcomponents;

import elements.gameactor.Player;
import java.awt.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import subsystem.FontLoader;

/**
 *
 * @author yury_
 */
public class ScrollingBackground {

    private final Image backG;
    private final Input contrl;
    private final int FrameWidth, FrameHeight;
    private int loopCont;
    private int x = 0, dizpla = 0, cLoop = 0, dragSpeed = 1;
    private FontLoader Cfont;
    private Font fotnString;
    private TrueTypeFont leFont;

    public ScrollingBackground(Image backG, GameContainer cnt) {
        this.backG = backG;
        contrl = cnt.getInput();
        FrameWidth = cnt.getWidth();
        FrameHeight = cnt.getHeight();
    }

    public ScrollingBackground(String path, int loops, GameContainer cnt) throws SlickException {
        backG = new Image(path);
        loopCont = loops;
        contrl = cnt.getInput();
        FrameWidth = cnt.getWidth();
        dizpla = FrameWidth;
        FrameHeight = cnt.getHeight();
        Cfont = new FontLoader("res/Fonts/fuente.ttf");
        fotnString = Cfont.getMyFont(1, 30);
        leFont = new TrueTypeFont(fotnString, true);
    }
    
        public ScrollingBackground(Image face, int loops, GameContainer cnt){
        backG = face;
        loopCont = loops;
        contrl = cnt.getInput();
        FrameWidth = cnt.getWidth();
        dizpla = FrameWidth;
        FrameHeight = cnt.getHeight();
        Cfont = new FontLoader("res/Fonts/fuente.ttf");
        fotnString = Cfont.getMyFont(1, 30);
        leFont = new TrueTypeFont(fotnString, true);
    }

    public void render(GameContainer container, Graphics g) {
        backG.draw(x, 0, FrameWidth, FrameHeight);
        backG.draw(dizpla, 0, FrameWidth, FrameHeight);
        leFont.drawString(dizpla - 260, 25, ("End of Loop: " + cLoop));
        g.drawLine(dizpla, 0, dizpla, container.getHeight());
    }

    public void update(Player jug) {
        if ((cLoop <= loopCont)) {
            drag(jug.reachXi, jug.reachXf);
        }
    }

    private void drag(boolean start, boolean end) {
        if (end && contrl.isKeyDown(Input.KEY_RIGHT) && (cLoop < loopCont)) {
            x -= dragSpeed;
            dizpla -= dragSpeed;
            if (dizpla <= 0 ) {
                dizpla = FrameWidth;
                x = 0;
                if (cLoop < loopCont) {
                    cLoop++;
                }
            }
        } else if (start && contrl.isKeyDown(Input.KEY_LEFT) && (cLoop > 0)) {
            x += dragSpeed;
            dizpla += dragSpeed;
            if (dizpla >= FrameWidth) {
                dizpla = 0;
                x = -FrameWidth;
                if (cLoop > 0) {
                    cLoop--;
                }
            }
        }
    }

    public void setDragSpeed(int dragSpeed) {
        this.dragSpeed = dragSpeed;
    }

    public int getcLoop() {
        return cLoop;
    }

    
}
