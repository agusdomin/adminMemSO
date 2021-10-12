public class Particion implements Observer{
    int id
    int mem_ocupada
    int t_ocupada
    boolean estado
    int dir_comienzo
    Proceso proceso

    public void update(int tiempo){
        t_ocupada++;
        if(this.t_ocupada==this.proceso.getTtrabajo()){
            Admin.swapOut(this);
        }
    }
}