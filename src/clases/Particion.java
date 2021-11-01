package clases;
import clases.Admin;

public class Particion{
    private static int id;
    private int myId;
    private int mem_ocupada;
    private String estado; //hacer una variable tipo Enumerado
    private int dir_comienzo;
    
    public Particion(int mem_requerida){
        this.myId=getId();
        incId();
        this.mem_ocupada=mem_requerida;
        this.estado="Libre";
        dir_comienzo=0;
    }
    public int getId(){
        return Particion.id++;
    }
    public void incId(){
        Particion.id++;
    }

    public int getMyId(){
        return this.myId;
    }
    public void setEstado(boolean estado){
        if(estado){
            this.estado="Ocupada";
        }else{
            this.estado="Libre";
        }
    }
    
}