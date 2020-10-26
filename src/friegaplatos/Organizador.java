package friegaplatos;

import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable {
    Bandeja bandeja_secos;
    Bandeja alacena;
    String nombre;

    public Organizador(Bandeja bandeja_secos, Bandeja alacena) {
        this.alacena = alacena;
        this.bandeja_secos = bandeja_secos;
        nombre = "Organizador";
    }

    @Override
    public void run() {
        Plato plato;
        while(!Thread.currentThread().isInterrupted()){
            try{
                plato = bandeja_secos.sacar(nombre);
                TimeUnit.SECONDS.sleep((int)Math.random()*(1-(2+1))+(2));
                alacena.colocar(plato,nombre);
            }catch (InterruptedException e){
                return;
            }
        }
    }
}
