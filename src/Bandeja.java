import java.util.concurrent.ArrayBlockingQueue;


public class Bandeja {

    private static final int CAPACIDAD = 50;

    private final ArrayBlockingQueue<Plato> bandeja = new ArrayBlockingQueue<>(CAPACIDAD);

    public void soltarPlato (Plato plato) throws InterruptedException {
        bandeja.put(plato);
    }

    public Plato cogerPlato() throws InterruptedException {
        return bandeja.take();
    }

}