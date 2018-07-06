/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposGenerales;

import elements.levelcomponents.Platform;

/**
 *
 * @author gerar
 */
public class ContainerS {
    public Platform lista[];

    public ContainerS(Platform[] lista) {
        this.lista = lista;
    }
    
    public void render(){
        for(Platform oj : lista)
        {
            if(!oj.transparente)
            {
                //aca el metodo render
            }
        }
    }
    
    public void update(){
        
    }
}
