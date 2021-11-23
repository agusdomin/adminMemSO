package clases;

import java.util.ArrayList;
public interface Estrategia{
    public Particion selecParticion(Proceso proceso,ArrayList<Particion> tabla);
    public String getNombre();
}