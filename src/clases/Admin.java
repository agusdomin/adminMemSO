package clases;
import java.util.*;
import clases.Proceso;
import clases.Particion;
import clases.Observable;
import clases.Observer;

public class Admin implements Observable{

    private int tiempo=0;
    private ArrayList<Proceso> procesos = new ArrayList<>();
    private ArrayList<Proceso> arribos = new ArrayList<>();
    private boolean finTanda = false;

    private int t_to_select;
    private int t_to_free;
    private int t_to_load;
    private int mem_total;
    
    private Estrategia estrategia;

    private ArrayList<Particion> tabla = new ArrayList<>();
    private ArrayList<Particion> particion = new ArrayList<>();
    
    public void arribo(Proceso proceso){
        this.arribos.add(proceso);
    }
    
    @Override
    public void addObserver(Observer p,String tipo){
        if(tipo.equals("particion")){
            
        }else if(tipo.equals("proceso")){
            procesos.add(p);
        }
    }
    @Override
    public void deleteObserver(Observer p){
        procesos.remove(p);
    }
    @Override
    public void notify(int tiempo){
        for(Proceso proceso: procesos){
            proceso.update(tiempo);    
        }
        for(Particion particion: tabla){
            particion.update(tiempo);
        }
    }

    public void swapIn(Proceso proceso){
        
        //Seleccion de una particion
        int i=0;
        do{
            this.tiempo++;
            notify(this.tiempo);
        }while(i<t_to_select);
        // va a buscar,selecionar y crear la nueva particion segun la estrategia
        Particion particion=(estrategia.selecionarParticion(proceso,tabla));
        
        //Carga de la particion
        int j=0;
        do{
            this.tiempo++;
            notify(this.tiempo);
        }while(j<t_to_load); 
        // va a cargar en la tabla la particion       
        tabla.add(particion);
    }

    public void swapOut(Particion particion){
        int i=0;
        do{
            this.tiempo++;
            notify(this.tiempo);
        }while(i<t_to_free);
        tabla.remove(particion);
    }

    public void administrar(){
        notify(this.tiempo);
        while (!(finTanda)){
            if (!(arribos.empty())){
                swapIn(arribos.getFirst()); 
                //si getFirst no elimia el primer elemnto, removerlo
            }else{
                this.tiempo++;
                notify(this.tiempo);
            }
        }
    }
   
}

