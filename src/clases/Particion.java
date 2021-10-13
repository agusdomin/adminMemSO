package clases;
import clases.Admin;

public class Particion{
    private int id;
    private int mem_ocupada;
    private int t_ocupada;
    private boolean estado;
    private int dir_comienzo;
    private Proceso proceso;
    
    public Particion(){
        this.t_ocupada=100;
        this.estado=false;
    }
    
    public boolean finUsoMemoria(){
        if(this.t_ocupada==this.proceso.getTtrabajo()){
            return true;
        }else{
            return false;
        }
    }
    
    public void notificar(int tiempo,Admin admin){
        if(this.t_ocupada==tiempo){
            admin.swapOut(this);
        }
    }
    public int getOcupada(){
        return this.t_ocupada;
    }
    
}