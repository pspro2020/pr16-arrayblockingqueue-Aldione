import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.Random;

public class Organizador implements Runnable{


    private final Bandeja bandejaSecos;
    private final Bandeja alacena;

    public Organizador(Bandeja bandejaSecos, Bandeja alacena) {
        Objects.requireNonNull(bandejaSecos);
        Objects.requireNonNull(alacena);
        this.bandejaSecos = bandejaSecos;
        this.alacena = alacena;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                alacena.soltarPlato(alacena(bandejaSecos.cogerPlato()));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private Plato alacena(Plato plato) throws InterruptedException {
        Thread.sleep((new Random().nextInt(2-1)+1) * 1000);
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        System.out.printf("%s - El %s ha colocado el plato %d en la alacena\n", formato.format(hora), this.getClass().getSimpleName(), plato.getPlatoID());
        return plato;
    }
}
