package clases;

import javax.xml.transform.Templates;

public class Trabajo {
    private static int id;
    private int myId;
    private int t_fin_ejecucion;
    private int t_ejecutado=0;

    private Particion particion;
    private Proceso proceso;

    public Trabajo(Proceso proceso, Particion particion){
        this.myId=id;
        id++;
        this.t_fin_ejecucion=proceso.getTtrabajo();
        this.proceso=proceso;
        this.particion=particion;
    }

    public boolean notificar(int t){ //esta mal, necesita un contador de los tiempos q va a ejecutandos
        this.t_ejecutado++;
        if(this.t_fin_ejecucion==this.t_ejecutado){ //usar otra variable como contador?
            return true;
        }
        return false;
    }

    public int getMyId(){
        return this.myId;
    }

    public Particion getParticion(){
        return this.particion;
    }
    public Proceso getProceso(){
        return this.proceso;
    }
}

