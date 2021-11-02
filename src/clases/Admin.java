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
    private boolean finTanda = false;
    
    private int t_to_select;
    private int t_to_free;
    private int t_to_load;
    private int mem_total;
    private int proc_atendidos=0;
    
    private Estrategia estrategia;

    private Queue<Proceso> arribos = new LinkedList<>();
    private ArrayList<Proceso> procesos = new ArrayList<>();

    private ArrayList<Particion> tabla = new ArrayList<>();
    private ArrayList<Particion> buffer_tabla = new ArrayList<>(); //creada para usar de buffer al elimianr particiones

    private ArrayList<Trabajo> trabajos = new ArrayList<>();
    private Queue<Trabajo> salidas = new LinkedList<>();

    
    /* el nuevo plan seria que el admin recorra todas las listas de procesos y 
    particiones consultando sus tiempos de arribo y finalizacion */
    
    
    public Admin(ArrayList<Proceso> procesos){
        this.estrategia = new FirstFit();
        this.t_to_select=1;
        this.t_to_load=1;
        this.t_to_free=1;
        this.mem_total=100;
        this.procesos=procesos;
        System.out.println("Ingresan "+procesos.size()+" al administrador");
        tabla.add(new Particion(this.mem_total)); //El admin inicia con una tabla con una unica particion, que esta libre y es el total de la memoria
    }
    
    public void addProceso(String nombre,int mem, int t_trabajo, int t_arribo){
        Proceso proceso = new Proceso(nombre,mem,t_trabajo,t_arribo);
        this.procesos.add(proceso);
    }
    
    //notifica paso del tiempo
    public void pasoTiempo(){
        System.out.println("Instante de tiempo: "+this.tiempo);
        System.out.println("Se notifica a los procesos y trabajos del paso del tiempo");
        for(Proceso proceso: procesos){
            if(proceso.notificar(this.tiempo)){
                this.arribos.add(proceso);
            }
        }
        
        //System.out.println("Se notifica a las particiones el paso del tiempo");
        for(Trabajo trabajo: trabajos){
            if(trabajo.notificar(this.tiempo)){
                this.salidas.add(trabajo);
            }
        }
    }

    //Obtengo la particion selecionada para cargar el proceso, y la divido en dos
    //La nueva particion se carga posterior a la original
    //Para esto utilizo un buffer, para agregar la nueva particion y luego tabla=buffer
    public void crearParticion(Particion particion,Proceso proceso){
        Particion nueva_particion = new Particion(proceso.getSize(),"Ocupada");
        particion.setSize(particion.getSize()-proceso.getSize());
        for(int i=0;i<=this.tabla.size();i++){
            buffer_tabla.add(tabla.get(i));
            if(this.tabla.get(i)==particion){
                buffer_tabla.add(nueva_particion);
            }
        }
        this.tabla=buffer_tabla;
    }


    public ArrayList<Particion> swapIn(Proceso proceso){
        
        //Seleccion de una particion
        
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo();
        }
        /* va a buscar,selecionar y crear la nueva particion segun la estrategia
        En las salidas de texto de los eventos debe reflejarse la 
        creacion de particiones, el particionamiento que se hace*/

        Particion particion=(estrategia.selecParticion(proceso,this.tabla));
        System.out.println("Se selecciono la particion "+particion.getMyId()+" para "+proceso.getNombre());
        //crearParticion(particion,proceso);
        Particion nueva_particion = new Particion(proceso.getSize(),"Ocupada");
        particion.setSize(particion.getSize()-proceso.getSize());

        //Carga de la particion
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo();
        }
        /*
        System.out.println("Tamanio de la tabla "+this.tabla.size());
        va a cargar en la tabla la particion       
        */
        for (Particion p : this.tabla) {
            if(p.getMyId()==particion.getMyId()){                
                this.buffer_tabla.add(nueva_particion);                
                this.buffer_tabla.add(p);
            }else{
                this.buffer_tabla.add(p);
            }
        
        }
        
        //System.out.println("Tamanio de la tabla "+this.tabla.size());
        //Se crea el trabajo, del proceso cargado en la particion
        Trabajo trabajo = new Trabajo(proceso,particion);
        trabajos.add(trabajo);
        System.out.println("Se creo el trabajo para "+proceso.getNombre()+" y se cargó en la particion "+particion.getMyId());
        return buffer_tabla;
    }

    public void liberarParticion(Trabajo trabajo){
        Particion particion= trabajo.getParticion();
        particion.setEstado(false);
    }

    public void swapOut(Trabajo trabajo){
        System.out.println("Se libera un trabajo");
        int i=0;
        do{
            this.tiempo++;
            this.pasoTiempo();
        }while(i<t_to_free);
        //cambiar particion en donde se almacenaba el trabajo
        this.liberarParticion(trabajo);
        trabajos.remove(trabajo);
        trabajos.trimToSize();
    }
    
    public void administrar() {
        System.out.println("Comienzo de la tanda");
        
        while (!(finTanda)){
            this.pasoTiempo();

            if (salidas.peek()!=null){
                Trabajo trabajo = salidas.remove();
                System.out.println("Termina trabajo "+trabajo.getMyId());
                this.swapOut(trabajo); 
                
                //arribos.trimToSize();
            }
            if ((arribos.peek()!=null)){
                Proceso proc=arribos.remove();
                System.out.println("Arriba proceso "+proc.getNombre());
                this.tabla=this.swapIn(proc);
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


