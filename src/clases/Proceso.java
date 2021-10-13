package clases;

import clases.Admin;

public class Proceso implements Observer{
    private String nombre;
    private int mem_requerida;
    private int tiempo_trabajo;
    private int tiempo_arribo;
    
    public Proceso(){
        super();
        tiempo_arribo=0;
    }

    public int getTtrabajo(){
        return tiempo_trabajo;
    }
    
    @Override
    public void update(int tiempo){
        if(this.tiempo_arribo==tiempo){
            Admin.arribo(this);
        }
    }
}