package clases;

import clases.Admin;

public class Proceso {
    private String nombre;
    private int mem_requerida;
    private int t_trabajo;
    private int t_arribo;
    
    public Proceso(){
        super();
        t_arribo=0;
    }
    
    public void notificar(int tiempo,Admin admin){
        if(tiempo==this.t_arribo){
            admin.arribo(this);
        }
    }
    public int getTtrabajo(){
        return t_trabajo;
    }
    public int getTarribo(){
        return t_arribo;
    }
    
}