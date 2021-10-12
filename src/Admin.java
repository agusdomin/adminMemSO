public class Admin implements Observer{

    int tiempo=0;
    List procesos Proceso
    List arribos Proceso
    boolean finTanda = false;

    int t_to_select
    int t_to_free
    int t_to_load
    int mem_total
    
    Estrategia estrategia

    ArrayList<Particion> tabla
    ArrayList<Particion> particion
    
    @Override
    addObserver(Proceso proceso){
        procesos.add(proceso);
    }
    
    @Override
    deleteObserver(Proceso proceso){
        procesos.remove(proceso);
    }

    @Override
    notify(int tiempo){
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
        Particion particion=(estrategia.selecionarParticion(proceso,tabla)) 
        
        //Carga de la particion
        int j=0
        do{
            this.tiempo++;
            notify(this.tiempo);
        }while(j<t_to_load); 
        // va a cargar en la tabla la particion       
        tabla.add(particion);
    }

    public void swapOut(Particion particion){
        int i=0
        do{
            this.tiempo++;
            notify(this.tiempo);
        }while(i<t_to_free);
        tabla.remove(particion);
    }

    public static void main(String[] args){
        notify(this.tiempo);
        while !(finTanda){
            if (!(arribos.empty())){
                swapIn(arribos.getFirst()); 
                //si getFirst no elimia el primer elemnto, removerlo
            }else{
                this.tiempo++;
                notify(this.tiempo);
            }
        }
    }

    public void arribo(Proceso proceso){
        this.arribios.add(proceso);
    }
}

