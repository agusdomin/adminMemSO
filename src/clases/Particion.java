package clases;
import clases.Admin;

public class Particion implements Observer{
    private int id;
    private int mem_ocupada;
    private int t_ocupada;
    private boolean estado;
    private int dir_comienzo;
    private Proceso proceso;

    public void update(int tiempo){
        this.t_ocupada++;
        if(this.t_ocupada==this.proceso.getTtrabajo()){
            Admin.swapOut(this);
        }
    }
}