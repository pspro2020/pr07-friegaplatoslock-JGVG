package friegaplatos;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Bandeja bandeja_sucios = new Bandeja(10);
        Bandeja bandeja_mojados = new Bandeja(0);
        Bandeja bandeja_secos = new Bandeja(0);
        Bandeja alacena = new Bandeja(0);

        Thread fregador = new Thread(new Fregador(bandeja_sucios,bandeja_mojados));
        Thread secador = new Thread(new Secador(bandeja_mojados,bandeja_secos));
        Thread organizador = new Thread(new Organizador(bandeja_secos,alacena));

        fregador.start();
        secador.start();
        organizador.start();

        try {
            TimeUnit.SECONDS.sleep(60);
            System.out.println("Parad que voy a soplar las velas!!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fregador.interrupt();
        secador.interrupt();
        organizador.interrupt();

        System.out.println("FELIZ CUMPLEAÑOS!!!!!!!!!!!");

    }
}
