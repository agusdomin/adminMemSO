package clases;

import java.util.ArrayList;

public class FirstFit implements Estrategia{
    private Particion particon_selec;
    private Trabajo trabajo;

    public FirstFit(){
        super();
    }
    
    @Override
    public Particion selecParticion(Proceso proceso, ArrayList<Particion> tabla){
        int i=0;
        boolean seleccionado=false;
        while (!(seleccionado)&&(i<=tabla.size())){
            if((tabla.get(i).getSize()>=proceso.getSize()) && (tabla.get(i).getEstado().equals("Libre"))){
                particon_selec=tabla.get(i);
                seleccionado=true;
                System.out.println("AAA");
            }else{
                i++;
                System.out.println("AAA");
            }
        }
        return particon_selec;
    }    
}
