
public interface Observable{
    public void addObserver(Observer p);
    public void deleteObserver(Observer p);
    public void notify();
}