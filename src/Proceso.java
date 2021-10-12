public class Proceso implements Observer{
    String nombre
    int mem_requerida
    int tiempo_trabajo
    int tiempo_arribo


    public int getTtrabajo(){
        return tiempo_trabajo;
    }
    public void update(int tiempo){
        if(this.tiempo_arribo==tiempo){
            Admin.arribo(this);
        }
    }
}