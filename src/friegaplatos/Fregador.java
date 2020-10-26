package friegaplatos;

import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {
    Bandeja bandeja_sucios;
    Bandeja bandeja_mojados;
    String nombre;

    public Fregador(Bandeja bandeja_sucios, Bandeja bandeja_mojados) {
        this.bandeja_mojados = bandeja_mojados;
        this.bandeja_sucios = bandeja_sucios;
        nombre = "Fregador";
    }

    @Override
    public void run() {
        Plato plato;
        while(!Thread.currentThread().isInterrupted()){
            try{
                plato = bandeja_sucios.sacar(nombre);
                TimeUnit.SECONDS.sleep((int)Math.random()*(4-(8+1))+(8));
                bandeja_mojados.colocar(plato,nombre);
            }catch (InterruptedException e){
                return;
            }
        }
    }

}
