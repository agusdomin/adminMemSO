package clases;

public class Particion{
    public static int id=0;
    private int myId;
    private int mem_ocupada;
    private boolean ocupada; //hacer una variable tipo Enumerado
    private int dir_comienzo;
    
    
    public Particion(int mem_requerida,boolean ocupada){
        this.myId=id;
        id++;
        this.mem_ocupada=mem_requerida;
        this.ocupada=ocupada;
        dir_comienzo=1;
        System.out.println("Se creo particion con id "+this.myId+" con tamanio "+this.mem_ocupada);
    }
    
    public void setSize(int size){
        this.mem_ocupada=size;
    }

    public void compactar(int size){
        this.mem_ocupada+=size;
    }
    public boolean getEstado(){
        return this.ocupada;
    }
    public int getSize(){
        return this.mem_ocupada;
    }
    public int getMyId(){
        return this.myId;
    }
    public void setEstado(boolean ocupada){
        if(ocupada){
            this.ocupada=true;
        }else{
            this.ocupada=false;
        }
    }
    
}