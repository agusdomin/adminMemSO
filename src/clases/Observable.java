package clases;

public interface Observable{
    public abstract void addObserver(Observer p,String tipo);
    public abstract void deleteObserver(Observer p,String tipo);
    public abstract void notify(int tiempo);
}