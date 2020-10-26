package friegaplatos;

import java.util.concurrent.TimeUnit;

public class Secador implements Runnable {
    Bandeja bandeja_mojados;
    Bandeja bandeja_secos;
    String nombre;

    public Secador(Bandeja bandeja_mojados, Bandeja bandeja_secos) {
        this.bandeja_mojados = bandeja_mojados;
        this.bandeja_secos = bandeja_secos;
        nombre = "Secador";
    }

    @Override
    public void run() {
        Plato plato;
        while(!Thread.currentThread().isInterrupted()){
            try{
                plato = bandeja_mojados.sacar(nombre);
                TimeUnit.SECONDS.sleep((int)Math.random()*(1-(3+1))+(3));
                bandeja_secos.colocar(plato,nombre);
            }catch (InterruptedException e){
                return;
            }
        }
    }
}
