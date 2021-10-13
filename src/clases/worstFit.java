/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author agustin
 */
public class worstFit implements Estrategia{
    
    public worstFit(){
        
    }
    
    @Override
    public Particion seleccionar(Proceso proceso,ArrayList<Particion> tabla){
        return tabla.get(0);
    }
    
}
