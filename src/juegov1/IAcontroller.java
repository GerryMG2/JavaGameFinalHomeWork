/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

import TiposGenerales.ContainerS;
import java.util.Random;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gerar
 */
public class IAcontroller {

    public static Random rn = new Random();
    public Punto targetP;
    public Shape heroe;
    public Shape malo;

    public IAcontroller() {
    }
    public int keytosent = 0;
    public int disparar;

    public void update(Punto player, Shape heroe, Shape malo) {
        targetP = player;
        this.heroe = heroe;
        this.malo = malo;
    }

    public void think(Punto enemy, ContainerS con) {
        switch (targetP.getEstate(enemy, JuegoV1.contenedor.getWidth(), JuegoV1.contenedor.getHeight())) {
            case ArribaDerecha:
                if (rn.nextInt(2) == 1) {
                    keytosent = Input.KEY_X;
                } else {
                    keytosent = Input.KEY_D;
                }

                break;
            case ArribaIzquierda:
                if (rn.nextInt(2) == 1) {
                    keytosent = Input.KEY_X;
                } else {
                    keytosent = Input.KEY_A;
                }
                break;
            case AbajoDerecha:
                keytosent = Input.KEY_D;
                break;
            case AbajoIzquierda:
                keytosent = Input.KEY_A;
                break;
            case DerechaEnseguida:
                keytosent = Input.KEY_D;
                break;
            case IzquierdaEnseguida:
                keytosent = Input.KEY_A;
                break;
            case DerechaLejos:
                keytosent = Input.KEY_D;
                break;
            case IzquierdaLejos:
                keytosent = Input.KEY_A;
                break;
            case ParedDerecha:
                break;
            case ParedIzquierda:
                break;
            case ParedArriba:
                break;
            case EncimaPlataforam:
                break;
            case nulo:
                break;
            case Arriba:
                if (rn.nextInt(2) == 1) {
                    this.keytosent = Input.KEY_X;

                } else {
                    keytosent = Input.KEY_D;
                }
                break;
            case Abajo:
                keytosent = 666;
                break;
            case Izquierda:
                keytosent = Input.KEY_A;
                disparar = 1;
                break;
            case Derecha:
                keytosent = Input.KEY_D;
                disparar = 1;

                break;
            case poralgunarazonelmismositio:
                if (rn.nextInt(2) == 1) {
                    keytosent = Input.KEY_A;
                } else {
                    keytosent = Input.KEY_D;
                }

                break;
            default:
            // throw new AssertionError(targetP.getEstate(enemy, juegov1.JuegoV1.contenedor.getWidth(),juegov1.JuegoV1.contenedor.getHeight() ).name());

        }
        System.out.println("key:");
        System.out.println(this.keytosent);

    }

    /**
     *
     * @param player
     * @param bad
     * @param malo
     * @param bueno
     * @param con
     * @return
     */
    public int getkey(Punto player, Punto bad, Shape bueno, Shape malo, ContainerS con) {
        int sent = 0;
        try {
            // System.out.println("Llego");
            this.update(player, bueno, malo);

            this.think(bad, con);
            // System.out.println("Llego");
        } catch (Exception error) {

        }
        if(bueno.intersects(malo)  || Math.abs(bueno.getMaxX() - malo.getMaxX()) <= 200){
            if(sent == Input.KEY_D){
                sent = Input.KEY_A;
            }
            else
            {
                sent = Input.KEY_D;
            }
        }
        else
        {
            
             sent = this.keytosent;
        }
       
        return sent;
    }
}
