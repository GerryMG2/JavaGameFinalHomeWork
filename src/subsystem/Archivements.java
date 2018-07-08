package subsystem;

import java.io.*;
import java.util.ArrayList;

/**
 * @Roberto
 */
public class Archivements {
    private File LogrosTXT;
    private ArrayList<String> Lista = new ArrayList<>();
    public <E> void InicioGameArchivements(){
            FileWriter fichero;
            PrintWriter pw;
            Lista.add("Logros \n");
            Lista.add("1. La caída del Reich: Se consigue cuando se mata al jefe final Adelfos Hatlaris.#false\n");
            Lista.add("2. The Coward: cuando pasa un nivel sin matar a ningún enemigo en nivel uno o dos posibles.#false\n");
            Lista.add("3. U fin da wae: Este logro se desbloquea en el momento que terminamos el juego.#false\n");
            Lista.add("4. Aliencide: Matar a todos los aliens de cada nivel en una sola partida y ganar a Adelfos Hatlaris.#false\n");
            Lista.add("5. Ugandan Rambo: Derrote todos los jefes utilizando únicamente la metralleta.#false\n");
            Lista.add("6. Ugandan Graves: Derrote todos los jefes utilizando únicamente la escopeta derrotar al jefe del nivel únicamente con esta arma.#false\n");
            Lista.add("7. Ugandan Heimerdinger: Derrote todos los jefes utilizando únicamente el lanzagranadas.#false\n");
            Lista.add("8. Migate no Ugandan: Completa el juego sin perder una sola vida.#false\n");
            Lista.add("9. Ugandan sanic: Completa el juego en (por definirse) cantidad de tiempo.#false\n");
            Lista.add("10. U don’t now da wey, spit on him: Pierde todas tus vidas en el primer nivel.#false\n");
            Lista.add("11. R u de Quin?: Completa el nivel tres, tres veces, sin perder una sola vida.#false\n");
            Lista.add("12. U r de Quin: Completa el nivel cuatro, tres veces, sin perder una sola vida.#false\n");
            Lista.add("13. Wakandan bronx: En el nivel tres completarlo únicamente matando a los blancos.#false\n");
            Lista.add("14. White supremacy: En el nivel cuatro completarlo únicamente matando a los rubios.#false\n");
            Lista.add("15. Ugandan Wolf: Derrota únicamente al jefe del nivel cuatro y sin perder una sola vida.#false\n");
            LogrosTXT = new File("Logros.txt");
            try {
                fichero = new FileWriter(LogrosTXT);
                pw = new PrintWriter(fichero);
                for (String elemento : Lista) {
                    pw.print(elemento);
                }
                pw.close();
                fichero.close();
                LogrosTXT = null;
            } catch (IOException e) {
                System.err.println("Imposible escribir a " + LogrosTXT.getName() + "correctamente");
            }
        }

    }
