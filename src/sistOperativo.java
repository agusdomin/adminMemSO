public class sistOperativo implements sistOperativoObservable{

    int tiempo=0;
    List procesos Proceso
    List arribos Proceso
    Admin admin
    boolean finTanda = false;
    
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
    }
    public static void main(String[] args){
        while !(finTanda){
            notify(this.tiempo);
            if (!(arribos.empty())){
                int i=0
                do{
                    this.tiempo++
                    notify(this.tiempo);
                }while(i<t_to_select);
                Particion particion=admin.toSelect(arribos.getFirst());
                int j=0
                do{
                    this.tiempo++
                    notifyProcesos(this.tiempo);
                }while(j<t_to_load);
                admin.swapIn(particion);
                //si getFirst no elimia el primer elemnto, removerlo
            }

            this.tiempo++;
        }
    }

    funciton arribo(Proceso proceso){
        this.arribios.add(proceso);
    }
    
    /*admin.toSelect(proceso)
        for(i=0;i<tSelect;i++){
            this.tiempo++;
            notifyProcesos();
        }
        
        admin.swapIn(particion)
        for(i=0;i<tLoad;i++){
            this.tiempo++;
        }*/
}