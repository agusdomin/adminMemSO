package clases;

import clases.Admin;

public class Proceso {
    private String nombre;
    private int mem_requerida;
    private int t_trabajo;
    private int t_arribo;
    
    public Proceso(String n,int m,int t_t, int t_a){
        
        this.nombre=n;
        this.mem_requerida=m;
        this.t_trabajo=t_t;
        this.t_arribo=t_a;
        System.out.println("Proceso creado, nombre: "+this.nombre+", memoria requerida: "+this.mem_requerida);
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public boolean notificar(int tiempo){
        if(tiempo==this.t_arribo){
            return true;
        }else{
            return false;
        }
                
    }
    public int getTtrabajo(){
        return t_trabajo;
    }
    public int getTarribo(){
        return t_arribo;
    }
    
}