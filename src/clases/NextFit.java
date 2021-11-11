package clases;

import java.util.ArrayList;

public class NextFit implements Estrategia{
    private int end;
    Particion particon_selec;

    public NextFit(){
        super();
        this.end=0;
    }

    @Override
    public Particion selecParticion(Proceso proceso, ArrayList<Particion> tabla) {
        int peek=this.end;
        boolean seleccionado=false;
        System.out.println("La tabla se analiza desde "+peek);
        while (!(seleccionado)){
            System.out.println("Se analiza el elemento "+peek+" de la tabla");
            if((tabla.get(peek).getSize()>=proceso.getSize()) && (!(tabla.get(peek).getEstado()))){
                particon_selec=tabla.get(peek);
                seleccionado=true;
                this.end=peek;
                System.out.println("Se selecciono la particion "+tabla.get(peek).getMyId()+" con tamaño "+tabla.get(peek).getSize());
            }else{
                //Si es el final de la lista, vuelvo al principio
                if(peek==(tabla.size()-1)){
                    System.out.println("Se llego al final de la tabla");
                    peek=0;
                //Si la particion no esta libre o no tiene el suficiente espacio, avanzo una particion
                }else{
                    System.out.println("La particion no puede seleccionarse se analiza la siguiente particion");
                    peek++;
                }

                if(peek==this.end){
                    System.out.println("No hay particiones libres");
                    particon_selec=null;
                    seleccionado=true;
                    this.end=peek;
                }
            }
        }
        
        return particon_selec;
    }
    
    
}
