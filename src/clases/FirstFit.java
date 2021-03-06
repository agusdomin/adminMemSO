package clases;

import java.util.ArrayList;

public class FirstFit implements Estrategia{

    Particion particon_selec;
    private String nombre;
    public FirstFit(){
        super();
        particon_selec=null;
        this.nombre="First-fit";
    }
    
    @Override
    public Particion selecParticion(Proceso proceso, ArrayList<Particion> tabla){
        int i=0;
        boolean seleccionado=false;
        
        while (!(seleccionado)&&(i<tabla.size())){
            if((tabla.get(i).getSize()>=proceso.getSize()) && (!(tabla.get(i).getEstado()))){
                particon_selec=tabla.get(i);
                seleccionado=true;
                System.out.println("Se selecciono la particion "+tabla.get(i).getMyId()+" con tamaño "+tabla.get(i).getSize());
            }else{
                i++;
            }
        }
        if(!(seleccionado)){
            particon_selec=null;
            System.out.println("No hay particiones libres");
        }
        
        return particon_selec;
    }    
    
    
    public String getNombre(){
        return this.nombre;
    }
}
