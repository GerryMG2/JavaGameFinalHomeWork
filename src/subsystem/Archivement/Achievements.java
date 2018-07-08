package subsystem.Archivement;

import java.io.*;
import java.util.ArrayList;
import org.newdawn.slick.Animation;

/**
 * @Roberto
 */
public class Achievements {
    private File ArchievementsTXT;
    private ArrayList<String> Archievements = new ArrayList<>();
    public void InicioGameArchievements(){
            FileWriter fichero;
            PrintWriter pw;
            Archievements.add("1. La caída del Reich: Se consigue cuando se mata al jefe final Adelfos Hatlaris.#false\n");
            Archievements.add("2. The Coward: cuando pasa un nivel sin matar a ningún enemigo en nivel uno o dos posibles.#false\n");
            Archievements.add("3. U fin da wae: Este logro se desbloquea en el momento que terminamos el juego.#false\n");
            Archievements.add("4. Aliencide: Matar a todos los aliens de cada nivel en una sola partida y ganar a Adelfos Hatlaris.#false\n");
            Archievements.add("5. Ugandan Rambo: Derrote todos los jefes utilizando únicamente la metralleta.#false\n");
            Archievements.add("6. Ugandan Graves: Derrote todos los jefes utilizando únicamente la escopeta derrotar al jefe del nivel únicamente con esta arma.#false\n");
            Archievements.add("7. Ugandan Heimerdinger: Derrote todos los jefes utilizando únicamente el lanzagranadas.#false\n");
            Archievements.add("8. Migate no Ugandan: Completa el juego sin perder una sola vida.#false\n");
            Archievements.add("9. Ugandan sanic: Completa el juego en (por definirse) cantidad de tiempo.#false\n");
            Archievements.add("10. U don’t now da wey, spit on him: Pierde todas tus vidas en el primer nivel.#false\n");
            Archievements.add("11. R u de Quin?: Completa el nivel tres, tres veces, sin perder una sola vida.#false\n");
            Archievements.add("12. U r de Quin: Completa el nivel cuatro, tres veces, sin perder una sola vida.#false\n");
            Archievements.add("13. Wakandan bronx: En el nivel tres completarlo únicamente matando a los blancos.#false\n");
            Archievements.add("14. White supremacy: En el nivel cuatro completarlo únicamente matando a los rubios.#false\n");
            Archievements.add("15. Ugandan Wolf: Derrota únicamente al jefe del nivel cuatro y sin perder una sola vida.#false\n");
            ArchievementsTXT = new File("Logros.txt");
            try {
                ArrayList<Achievement> listaAux = new ArrayList<>();
                
                fichero = new FileWriter(ArchievementsTXT);
                
                pw = new PrintWriter(fichero);
                
                for (String element : Archievements) {
                    pw.print(element);
                    Achievement a=new Achievement();
                    listaAux.add( a.Logros(element));
                }
                pw.close();
                fichero.close();
                ArchievementsTXT = null;
            } catch (IOException e) {
                System.err.println("Imposible escribir a " + ArchievementsTXT.getName() + "correctamente");
            }
        }
    public void RefreshArchievements(ArrayList<Achievement> Logros){
        FileReader Archivo;
    }
}
