package clases;
import clases.Admin;

public class Particion{
    private static int id; //hacer una variable de clase y que cada instancia posea un id y aumento el id para la prox instancia
    private int mem_ocupada;
    private String estado; //hacer una variable tipo Enumerado
    private int dir_comienzo;
    
    public Particion(int mem_requerida){
        this.mem_ocupada=mem_requerida;
        this.estado="Libre";
        dir_comienzo=0;
    }
    

    public void setEstado(boolean estado){
        if(estado){
            this.estado="Ocupada";
        }else{
            this.estado="Libre";
        }
    }
    
}