package clases;

public class Proceso {
    private String nombre;
    private int mem_requerida;
    private int t_trabajo;
    private int t_arribo;
    private boolean atendido;
    private int t_retorno;
    
    public Proceso(String n,int m,int t_a, int t_t){
        
        this.nombre=n;
        this.mem_requerida=m;
        this.t_trabajo=t_t;
        this.t_arribo=t_a;
        this.t_retorno=0;
        System.out.println("Proceso creado, nombre: "+this.nombre+", memoria requerida: "+this.mem_requerida);
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public boolean notificar(int tiempo){
        if(!(atendido)){
            if(tiempo>=this.t_arribo){
                this.atendido=true;
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean isAtendido(){
        return this.atendido;
    }
    public void setRetorno(int t){
        this.t_retorno=t;
    }
    public int getRetorno(){
        return this.t_retorno;
    }
    public int getTtrabajo(){
        return this.t_trabajo;
    }
    public int getTarribo(){
        return this.t_arribo;
    }
    public int getSize(){
        return this.mem_requerida;
    }
    
}