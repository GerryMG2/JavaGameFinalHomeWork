/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegov1;

/**
 *
 * @author gerar
 */
public class Punto {

    public float x, y;

    public Punto() {
        x = 0f;
        y = 0f;
    }

    public Punto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = (float) x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = (float) y;
    }

    public EstadosPlayer getEstate(Punto other, int anchoPantalla, int altoPantalla) {
        EstadosPlayer respuesta = EstadosPlayer.nulo;
        if (this.x - other.x > anchoPantalla) {

        } else {
            
        }

        return respuesta;
    }

}
