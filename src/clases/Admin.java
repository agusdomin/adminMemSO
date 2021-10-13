package clases;
import java.util.*;
import clases.Proceso;
import clases.Particion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        t_to_select=1;
        t_to_load=1;
        t_to_free=1;
        mem_total=100;
        tabla.add(new Particion());
    }
    
    public void addProceso(String nombre,int mem, int t_trabajo, int t_arribo){
        Proceso proceso = new Proceso(nombre,mem,t_trabajo,t_arribo);
        this.procesos.add(proceso);
    }
    
    //notifica paso del tiempo
    public void pasoTiempo(int t){
        System.out.println("Se notifica a los procesos del paso del tiempo");
        for(Proceso proceso: procesos){
            if(proceso.notificar(t)){
                this.arribo(proceso);
            }
        }
        
        
        System.out.println("Se notifica a las particiones el paso del tiempo");
        for(Particion particion: tabla){
            particion.notificar(t,this);
        }
    }
    
    
    public void arribo(Proceso proceso){
        this.arribos.add(proceso);
    }

    
    public void swapIn(Proceso proceso){
        
        //Seleccion de una particion
        
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }
        Particion particion=(estrategia.seleccionar(proceso,this.tabla));    
        System.out.println("Se selecciono una particion para "+proceso.getNombre());
        
        // va a buscar,selecionar y crear la nueva particion segun la estrategia
        
        
        
        
        //Carga de la particion
        int j=0;
        do{
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
            j++;
        }while(j<t_to_load); 
        // va a cargar en la tabla la particion       
        tabla.add(particion);
        System.out.println(proceso.getNombre()+" se cargo a la tabla");
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

    
    public void administrar() {

        System.out.println("Comienzo de la tanda");
        this.pasoTiempo(this.tiempo);
        
        while (!(finTanda)){
            System.out.println("tiempo: "+this.tiempo);
            if (!(arribos.isEmpty())){
                System.out.println("Arriba proceso "+arribos.get(0).getNombre());
                this.swapIn(arribos.get(0)); 
                arribos.remove(0);
                arribos.trimToSize();
            }else{
                this.tiempo++;
                this.pasoTiempo(this.tiempo);
            }
         
          /* 
            -ANALIZAR COMO FINALIZAR LA TANDA
            -COMO LEER UN ARCHIVO CON VARIAS LINES SEPARADAS POR COMA
            -
           */
        }
    }
   
}

