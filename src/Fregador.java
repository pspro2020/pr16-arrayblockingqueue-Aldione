import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.Random;

public class Fregador implements Runnable {

    private final Bandeja bandejaLimpios;
    private int num = 1;
    Plato plato;

    public Fregador(Bandeja bandejaLimpios) {
        Objects.requireNonNull(bandejaLimpios);
        this.bandejaLimpios = bandejaLimpios;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                bandejaLimpios.soltarPlato(limpiar());
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private Plato limpiar() throws InterruptedException {
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);

        Thread.sleep((new Random().nextInt(8-4)+4) * 1000);
        plato = new Plato(num++);
        System.out.printf("%s - El %s ha limpiado el plato %d\n",formato.format(hora), this.getClass().getSimpleName(), plato.getPlatoID());

        return plato;
    }

}