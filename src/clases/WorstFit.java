package clases;

import java.util.ArrayList;

public class WorstFit implements Estrategia{
    Particion particion_selec;
    private String nombre;
    
    public WorstFit(){
        super();
        particion_selec=null;
        this.nombre="Worst-Fit";
    }
    @Override
    public Particion selecParticion(Proceso proceso, ArrayList<Particion> tabla) {
        this.particion_selec=null;
        // Analizar toda la tabla para ver cual particion se adapta mejor al proceso
        // Se puede usar un buffer para ir comparando cual es mejor hasta que se termine la tabla
        for(int i=0;i<tabla.size();i++){
            
            if((tabla.get(i).getSize()>=proceso.getSize()) && (!(tabla.get(i).getEstado()))){
                if((particion_selec!=null)&&(particion_selec.getSize()<tabla.get(i).getSize())){
                    System.out.println("Se cambia la particion selecionada a la "+i);
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
    
    public String getNombre(){
        return this.nombre;
    }
    
}
