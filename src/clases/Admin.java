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
    
    private Estrategia estrategia;

    private Queue<Proceso> arribos = new LinkedList<>();
    private ArrayList<Proceso> procesos = new ArrayList<>();

    private ArrayList<Particion> tabla = new ArrayList<>();

    private ArrayList<Trabajo> trabajos = new ArrayList<>();
    private Queue<Trabajo> salidas = new LinkedList<>();
    

    public Admin(){
        this.estrategia = new WorstFit();
        this.t_to_select=1;
        this.t_to_load=1;
        this.t_to_free=1;
        this.mem_total=260;
        Particion.id=0;
        System.out.println("Ingresan "+procesos.size()+" al administrador");
        tabla.add(new Particion(this.mem_total,false)); //El admin inicia con una tabla con una unica particion, que esta libre y es el total de la memoria
    }
    
    public Admin(Estrategia estrategia, int mem_total, int t_to_select,int t_to_load, int t_to_free){
        this.estrategia = estrategia;
        this.t_to_select=t_to_select;
        this.t_to_load=t_to_load;
        this.t_to_free=t_to_free;
        this.mem_total=mem_total;
        Particion.id=0;
        System.out.println("Ingresan "+procesos.size()+" al administrador");
        tabla.add(new Particion(this.mem_total,false)); //El admin inicia con una tabla con una unica particion, que esta libre y es el total de la memoria
    }

    public void crearProceso(String nombre,int mem_req, int t_arribo, int t_trabajo){
        Proceso proceso = new Proceso(nombre,mem_req,t_arribo,t_trabajo);
        this.procesos.add(proceso);
    }
    //Notifica paso del tiempo
    public void pasoTiempo(){
        System.out.println("");
        System.out.println("######################");
        System.out.println("");
        System.out.println("Instante de tiempo: "+this.tiempo);
        for(Proceso proceso: procesos){
            if(proceso.notificar(this.tiempo)){
                this.arribos.add(proceso);
            }
        }
        
        for(Trabajo trabajo: trabajos){
            if(trabajo.notificar(this.tiempo)){
                this.salidas.add(trabajo);
            }
        }

        System.out.println("----ESTADO DE LA TABLA----");
        for(Particion particion: tabla){
            System.out.println("Particion: "+particion.getMyId()+"; estado: "+particion.getEstado()+"; tamaño: "+particion.getSize());
        }
        System.out.println("----EVENTOS----");
    }


    //Obtengo la particion selecionada para cargar el proceso, y la divido en dos
    //La nueva particion se carga posterior a la original
    //Para esto utilizo un buffer, para agregar la nueva particion y luego tabla=buffer
    public Particion crearParticion(Particion particion,Proceso proceso){
        Particion nueva_particion;
        if((particion.getSize()-proceso.getSize())>0){
            nueva_particion = new Particion((particion.getSize()-proceso.getSize()),false);
        }else{
            nueva_particion=null;
        }
        this.tabla.get(this.tabla.indexOf(particion)).setSize(proceso.getSize());
        this.tabla.get(this.tabla.indexOf(particion)).setEstado(true);
        
        return nueva_particion;
    }
    

    //Se encarga de cargar la nueva particion libre creada y la ocupada
    public void cargarParticion(Particion nueva_particion, Particion particion){
        int i=0;
        i=this.tabla.indexOf(particion);
        this.tabla.add(i+1,nueva_particion);
        System.out.println("Cargar particion");
    }


    //Se encarga de liberar una particion y de compactar particiones libres proximas
    public void liberarParticion(Trabajo trabajo){
        
        Particion particion= trabajo.getParticion();
        this.tabla.get(this.tabla.indexOf(particion)).setEstado(false);
        System.out.println("Se libero la particion: "+particion.getMyId()+"; del trabajo: "+trabajo.getMyId()+"; perteneciente al proceso: "+trabajo.getProceso().getNombre());

        //Se analiza si hay que unificar particiones libres proximas a la liberada
        
        //Si la particion anterior a la del trabajo que esta finalizando esta libre, las unifico        
        try {
            ArrayList<Particion> buffer = new ArrayList<>();
            int particion_prev = this.tabla.indexOf(particion)-1;    
            
            if (! this.tabla.get(particion_prev).getEstado() ){
                this.tabla.get(this.tabla.indexOf(particion)).compactar(  this.tabla.get(particion_prev).getSize());;
                this.tabla.remove(particion_prev);
                for(Particion p: tabla){
                    if(p!=null){buffer.add(p);}
                }
                this.tabla=buffer;
            }   
        } catch (IndexOutOfBoundsException e) {}


        //Si la particion posterior a la del trabajo que esta finalizando esta libre, las unifico        
        try {
            ArrayList<Particion> buffer = new ArrayList<>();
            int particion_pos = this.tabla.indexOf(particion)+1;    
            
            if (! this.tabla.get(particion_pos).getEstado() ){
                this.tabla.get(this.tabla.indexOf(particion)).compactar(  this.tabla.get(particion_pos).getSize());;
                this.tabla.remove(particion_pos);
                for(Particion p: tabla){
                    if(p!=null){buffer.add(p);}
                }
                this.tabla=buffer;
            }   
        } catch (IndexOutOfBoundsException e) {}
    }


    //Se encarga de finalizar un trabajo y de liberar particiones
    public void swapOut(Trabajo trabajo){

        this.liberarParticion(trabajo);
        System.out.println("Se termina el trabajo: "+trabajo.getMyId());
        trabajos.remove(trabajo);

        for(int i=0;i<t_to_free;i++){
            this.tiempo++;
            this.pasoTiempo();
        }
        //cambiar particion en donde se almacenaba el trabajo
    }


    //Se encarga de seleccionar partciones, crearlas y crear trabajos a partir del proceso que arribo
    public void swapIn(Proceso proceso, Particion particion){    
            // Se crea la nueva partcion
            Particion nueva_particion=crearParticion(particion,proceso);
            if(nueva_particion!=null){
                // va a cargar en la tabla la particion
                cargarParticion(nueva_particion,particion);
            }
            //Se crea el trabajo, del proceso arribado cargado en la particion seleccionada
            Trabajo trabajo = new Trabajo(proceso,particion);
            trabajos.add(trabajo);
            System.out.println("Se creo el trabajo para "+proceso.getNombre()+" y se cargó en la particion "+particion.getMyId());

            for(int i=0;i<t_to_load;i++){
                this.tiempo++;
                this.pasoTiempo();
            }
    }

    // Funcion principal, se encarga de dar paso al tiempo, verificar si algun proceso arribo o si algun trabajo finalizo su ejecucion
    public void administrar() {
        System.out.println("Comienzo de la tanda");
        int atendidos=0;
        int terminados=0;
        
        while (!(finTanda)){
            this.pasoTiempo();
            
            // Primero me fijo si no existe un trabajo que haya finalizado su ejeccucion
            if (salidas.peek()!=null){
                Trabajo trabajo = salidas.remove();
                System.out.println("El trabajo "+trabajo.getMyId()+" termina su ejecucion");
                terminados++;
                this.swapOut(trabajo); 
            }

            // Luego me fijo si hay algun proceso que haya arribado
            if ((arribos.peek()!=null)){
                System.out.println("Se atiende al proceso "+arribos.peek().getNombre());
                
                /* va a buscar y selecionar una particion disponible
                Si existe una, crea la nueva particion y se carga antes de la partcion seleccionada
                En las salidas de texto de los eventos debe reflejarse la 
                creacion de particiones, el particionamiento que se hace*/
                Particion particion=(estrategia.selecParticion(arribos.peek(),this.tabla));
                
                // Se verifica que exista una particion para seleccionar
                for(int i=0;i<t_to_select;i++){
                    this.tiempo++;
                    this.pasoTiempo();
                }
                
                
                //Si existe una particion libre se atiende al proceso
                if(particion!=null){
                    System.out.println("Se selecciono la particion "+particion.getMyId()+" para "+arribos.peek().getNombre());
                    Proceso proc=arribos.remove();
                    atendidos++;
                    this.swapIn(proc,particion);
                //Si no existe ninguna particion libre, el proceso espera hasta que algun trabajo finalize
                }
            }
            // Si se atendieron todos los procesos que entraron y todos los trabajos terminaron, se finaliza la tanda
            if((procesos.size()==atendidos)&&(atendidos==terminados)){
                finTanda=true;
                System.out.println("Se atendieron a "+atendidos+" procesos y "+terminados+" trabajos finalizaron");
                break;
            }
            this.tiempo++;
        }
        this.pasoTiempo();
    }
}