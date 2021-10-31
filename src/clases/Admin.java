package clases;
import java.util.*;
import clases.Proceso;
import clases.Trabajo;
import clases.Particion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin{

    private int tiempo=0;
    private ArrayList<Proceso> arribos = new ArrayList<>();
    private ArrayList<Trabajo> salidas = new ArrayList<>();
    private boolean finTanda = false;
    private ArrayList<Proceso> procesos;

    private int t_to_select;
    private int t_to_free;
    private int t_to_load;
    private int mem_total;
    
    private Estrategia estrategia;

    private ArrayList<Particion> tabla = new ArrayList<>();
    private ArrayList<Particion> buffer_tabla = new ArrayList<>(); //creada para usar de buffer al elimianr particiones
    private ArrayList<Trabajo> trabajos = new ArrayList<>();

    
    /* el nuevo plan seria que el admin recorra todas las listas de procesos y 
    particiones consultando sus tiempos de arribo y finalizacion */
    
    
    public Admin(ArrayList<Proceso> procesos){
        this.estrategia = new worstFit();
        this.t_to_select=1;
        this.t_to_load=1;
        this.t_to_free=1;
        this.mem_total=100;
        this.procesos=procesos;
        System.out.println(this.procesos.size());
        tabla.add(new Particion(this.mem_total)); //El admin inicia con una tabla con una unica particion, que esta libre y es el total de la memoria
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
                this.arribos.add(proceso);
            }
        }
        
        System.out.println("Se notifica a las particiones el paso del tiempo");
        for(Trabajo trabajo: trabajos){
            if(trabajo.notificar(t)){
                this.salidas.add(trabajo);
            }
        }
    }
    
    public void swapIn(Proceso proceso){
        
        //Seleccion de una particion
        
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }
        Particion particion=this.tabla.get(0);/*(estrategia.seleccionar(proceso,this.tabla));    */
        System.out.println("Se selecciono una particion para "+proceso.getNombre());
        
        // va a buscar,selecionar y crear la nueva particion segun la estrategia
        
        //Carga de la particion
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }
        trabajos.add(new Trabajo(1,proceso,particion));
        System.out.println("Se creo el trabajo para "+proceso.getNombre());
        // va a cargar en la tabla la particion       
        tabla.add(particion);
        System.out.println(proceso.getNombre()+" se cargo a la tabla");
    }


    public void liberarParticion(Trabajo trabajo){
        Particion particion= trabajo.getParticion();
        particion.setEstado(false);
    }


    public void swapOut(Trabajo trabajo){
        int i=0;
        do{
            this.tiempo++;
            this.pasoTiempo(this.tiempo);
        }while(i<t_to_free);
        //cambiar particion en donde se almacenaba el trabajo
        this.liberarParticion(trabajo);
        trabajos.remove(trabajo);
        trabajos.trimToSize();
    }

    
    public void administrar() {
        System.out.println(procesos.get(0).getNombre()+" "+procesos.get(0).getTarribo());
        System.out.println("Comienzo de la tanda");
        //this.pasoTiempo(this.tiempo);
        
        while (!(finTanda)){
            System.out.println("Instante de tiempo: "+this.tiempo);
            this.pasoTiempo(this.tiempo);
            if (!(salidas.isEmpty())){
                System.out.println("Termina trabajo "+salidas.get(0).getId());
                this.swapOut(salidas.get(0)); 
                arribos.remove(0);
                arribos.trimToSize();
            }
            if (!(arribos.isEmpty())){
                System.out.println("Arriba proceso "+arribos.get(0).getNombre());
                this.swapIn(arribos.get(0)); 
                arribos.remove(0);
                arribos.trimToSize();
            }
            
            this.tiempo++;
            
            
         
          /* 
            -ANALIZAR COMO FINALIZAR LA TANDA
            -COMO LEER UN ARCHIVO CON VARIAS LINES SEPARADAS POR COMA
            -
           */
        }
    }
   
}


