package clases;

import java.util.ArrayList;

public interface Estrategia{

    public Particion seleccionar(Proceso proceso,ArrayList<Particion> tabla);
    
}