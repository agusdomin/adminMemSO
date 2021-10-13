package clases;
import java.util.*;
import clases.Proceso;
import clases.Particion;

public class Admin{

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
    
    /* el nuevo plan seria que el admin recorra todas las listas de procesos y 
    particiones consultando sus tiempos de arribo y finalizacion */
    
    public Admin(/*aca iran todos los datos del contexto*/){
        this.estrategia = new worstFit();
    }
    
    //notifica paso del tiempo
    public void pasoTiempo(int tiempo){
        for(Proceso proceso: procesos){
            proceso.notificar(tiempo,this);
        }
        for(Particion particion: tabla){
            particion.notificar(tiempo,this);
        }
    }
    
    
    public void arribo(Proceso proceso){
        this.arribos.add(proceso);
    }

    
    public void swapIn(Proceso proceso){
        
        //Seleccion de una particion
        int i=0;
        do{
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }while(i<t_to_select);
        // va a buscar,selecionar y crear la nueva particion segun la estrategia
        Particion particion=(estrategia.seleccionar(proceso,this.tabla));
        
        //Carga de la particion
        int j=0;
        do{
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }while(j<t_to_load); 
        // va a cargar en la tabla la particion       
        tabla.add(particion);
    }

    
    public void swapOut(Particion particion){
        int i=0;
        do{
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }while(i<t_to_free);
        tabla.remove(particion);
        tabla.trimToSize();
    }

    
    public void administrar(){
        this.pasoTiempo(this.tiempo);
        while (!(finTanda)){
            if (!(arribos.isEmpty())){
                this.swapIn(arribos.get(0)); 
                arribos.remove(0);
                arribos.trimToSize();
            }
            this.tiempo++;
        }
    }
   
}

