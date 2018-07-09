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
        if (Math.abs(this.x - other.x) > anchoPantalla) {
           
        } else {
            if (Math.abs(this.y - other.y) > altoPantalla) {
                
            } else {
                 System.out.println("Si entro pero que pasa");
                if (this.y > other.y) {
                    System.out.println("Si entro pero que pasa2");
                    if (this.y - other.y >= 100) {
                        if (this.x > other.x) {
                            if (this.x - other.x >= 100) {
                                respuesta = EstadosPlayer.ArribaIzquierda;
                            } else {
                                respuesta = EstadosPlayer.Arriba;
                            }

                        } else {
                            if (this.x == other.x) {
                                respuesta = EstadosPlayer.Arriba;

                            } else {
                                if (other.x - this.x >= 100) {
                                    respuesta = EstadosPlayer.ArribaDerecha;
                                } else {
                                    respuesta = EstadosPlayer.Arriba;
                                }
                            }

                        }

                    } else {
                        if (this.x > other.x) {
                            respuesta = EstadosPlayer.Derecha;
                        } else {
                            if (this.x == other.x) {
                                respuesta = EstadosPlayer.poralgunarazonelmismositio;
                            } else {
                                respuesta = EstadosPlayer.Izquierda;
                            }
                        }

                    }
                } else {
                    if (this.y == other.y) {
                        if (this.x > other.x) {
                            respuesta = EstadosPlayer.Derecha;
                        } else {
                            if (this.x == other.x) {
                                respuesta = EstadosPlayer.poralgunarazonelmismositio;
                            } else {
                                respuesta = EstadosPlayer.Izquierda;
                            }
                        }
                    } else {
                        if (other.y - this.y >= 100) {
                            if (this.x > other.x) {
                                if (this.x - other.x >= 100) {
                                    respuesta = EstadosPlayer.AbajoIzquierda;
                                } else {
                                    respuesta = EstadosPlayer.Abajo;
                                }

                            } else {
                                if (this.x == other.x) {
                                    respuesta = EstadosPlayer.Abajo;

                                } else {
                                    if (this.x - other.x >= 100) {
                                        respuesta = EstadosPlayer.AbajoDerecha;
                                    } else {
                                        respuesta = EstadosPlayer.Abajo;
                                    }
                                }

                            }

                        } else {
                            if (this.x > other.x) {
                                respuesta = EstadosPlayer.Derecha;
                            } else {
                                if (this.x == other.x) {
                                    respuesta = EstadosPlayer.poralgunarazonelmismositio;
                                } else {
                                    respuesta = EstadosPlayer.Izquierda;
                                }
                            }

                        }
                    }
                }
            }
        }

        return respuesta;
    }

}
