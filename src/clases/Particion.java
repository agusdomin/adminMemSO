package clases;
import clases.Admin;

public class Particion{
    public static int id=0;
    private int myId;
    private int mem_ocupada;
    private String estado; //hacer una variable tipo Enumerado
    private int dir_comienzo;
    
    public Particion(int mem_requerida){
        System.out.println("Se creo particion con id "+Particion.getId());
        this.myId=Particion.getId();
        Particion.IncId();
        this.mem_ocupada=mem_requerida;
        this.estado="Libre";
        dir_comienzo=1;
        
    }
    public Particion(int mem_requerida,String estado){
        System.out.println("Se creo particion con id "+Particion.getId());
        this.myId=Particion.getId();
        Particion.IncId();
        this.mem_ocupada=mem_requerida;
        this.estado=estado;
        dir_comienzo=1;
    }
    private static void IncId(){
        Particion.id++;
    }
    private static int getId(){
        return Particion.id++;
    }
    public void setSize(int size){
        this.mem_ocupada=size;
    }
    public String getEstado(){
        return this.estado;
    }
    public int getSize(){
        return this.mem_ocupada;
    }
    public int getMyId(){
        return this.myId;
    }
    public void setEstado(boolean estado){
        if(estado){
            this.estado="Ocupada";
        }else{
            this.estado="Libre";
        }
    }
    
}