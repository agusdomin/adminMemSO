package clases;

import javax.xml.transform.Templates;

public class Trabajo {
    private int id;
    private int t_fin_ejecucion;
    private boolean atendido;

    private Particion particion;
    private Proceso proceso;

    public Trabajo(int id,Proceso proceso, Particion particion){
        this.id=id;
        this.t_fin_ejecucion=proceso.getTtrabajo();

        this.proceso=proceso;
        this.particion=particion;
    }

    public boolean notificar(int t){
        if(!(atendido)){
            if(this.t_fin_ejecucion==t){ //usar otra variable como contador?
                this.atendido=true;
                return true;
            }
            return false;
        }
        return false;
    }

    public int getId(){
        return this.id;
    }

    public Particion getParticion(){
        return this.particion;
    }
}

