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
        tabla.add(new Particion(this.mem_total,false)); //El admin inicia con una tabla con una unica particion, que esta libre y es el total de la memoria
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
    public Particion crearParticion(Particion particion,Proceso proceso){
        Particion nueva_particion = new Particion((particion.getSize()-proceso.getSize()),false);
        this.tabla.get(this.tabla.indexOf(particion)).setSize(proceso.getSize());
        this.tabla.get(this.tabla.indexOf(particion)).setEstado(true);
        return nueva_particion;
    }
    
    public void cargarParticion(Particion nueva_particion, Particion particion){
        int i=0;
        i=this.tabla.indexOf(particion);
        this.tabla.add(i,nueva_particion);
        System.out.println("Cargar particion");
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

    public void swapIn(Proceso proceso){
        
        //Seleccion de una particion
        for(int i=0;i<t_to_select;i++){
            this.tiempo++;
            this.pasoTiempo();
        }
        
        /* va a buscar y selecionar una particion disponible
        Si existe una, crea la nueva particion y se carga antes de la partcion seleccionada

        En las salidas de texto de los eventos debe reflejarse la 
        creacion de particiones, el particionamiento que se hace*/

        Particion particion=(estrategia.selecParticion(proceso,this.tabla));
        if(particion!=null){
            
            System.out.println("Se selecciono la particion "+particion.getMyId()+" para "+proceso.getNombre());

            // Se crea la nueva partcion
            Particion nueva_particion=crearParticion(particion,proceso);
            
            // Tiempo de carga de la particion
            for(int i=0;i<t_to_load;i++){
                this.tiempo++;
                this.pasoTiempo();
            }
            // va a cargar en la tabla la particion
            cargarParticion(nueva_particion,particion);
            
            //Se crea el trabajo, del proceso arribado cargado en la particion seleccionada
            Trabajo trabajo = new Trabajo(proceso,particion);
            trabajos.add(trabajo);

            System.out.println("Se creo el trabajo para "+proceso.getNombre()+" y se cargó en la particion "+particion.getMyId());
        }
    }

    
    public void administrar() {
        System.out.println("Comienzo de la tanda");
        int atendidos=0;
        
        while (!(finTanda)){
            this.pasoTiempo();

            // Primero me fijo si no existe un trabajo que haya finalizado su ejeccucion
            if (salidas.peek()!=null){
                Trabajo trabajo = salidas.remove();
                System.out.println("Termina trabajo "+trabajo.getMyId());
                this.swapOut(trabajo); 
            }

            // Luego me fijo si hay algun proceso que haya arribado
            if ((arribos.peek()!=null)){
                Proceso proc=arribos.remove();
                System.out.println("Arriba proceso "+proc.getNombre());
                atendidos++;
                this.swapIn(proc);
            }
            
            // Si se atendieron todos los procesos de la tanda, se finaliza
            if(procesos.size()==atendidos){
                finTanda=true;
                break;
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