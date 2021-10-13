package clases;
public interface Observer{
    public void update(int tiempo);
}

/* Los procesos y particones actuarán como observadores
para así poder determinar cuando arriba un proceso
o cuando una particion debe ser liberada  */