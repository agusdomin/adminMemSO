package clases;

import java.util.ArrayList;

public class BestFit implements Estrategia {
    Particion particion_selec;
    @Override
    public Particion selecParticion(Proceso proceso, ArrayList<Particion> tabla) {
        this.particion_selec=null;
        // Analizar toda la tabla para ver cual particion se adapta mejor al proceso
        // Se puede usar un buffer para ir comparando cual es mejor hasta que se termine la tabla
        for(int i=0;i<=tabla.size();i++){
            
            if((tabla.get(i).getSize()>=proceso.getSize()) && (!(tabla.get(i).getEstado()))){
                if((particion_selec!=null)&&(particion_selec.getSize()>tabla.get(i).getSize())){
                    System.out.println("Se selecciona la particion "+i+" cambiando la "+particion_selec.getMyId());
                    particion_selec=tabla.get(i);
                }else if(particion_selec==null){
                    System.out.println("La primer particion selecionada es la "+i);
                    particion_selec=tabla.get(i);
                }
            }                
        }
        if(particion_selec==null){
            System.out.println("No hay particiones libres");
        }
        return particion_selec;
    }
    
}
