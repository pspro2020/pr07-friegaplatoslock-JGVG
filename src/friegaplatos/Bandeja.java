package friegaplatos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {
    ArrayList<Plato> bandeja_platos = new ArrayList();

    private Lock reentrantLock = new ReentrantLock(true);
    Condition conditionIsEmpty = reentrantLock.newCondition();

    public Bandeja( int num_platos){
        for(int i = 0; i<num_platos; i++){
            bandeja_platos.add(new Plato(i+1));
        }
    }

    public Plato sacar(String nombre) throws InterruptedException {
        Plato plato;
        reentrantLock.lock();
        try{
            while(bandeja_platos.isEmpty()){
                System.out.println(nombre+" espera a que haya platos -> "+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond());
                conditionIsEmpty.await();
            }
            plato = bandeja_platos.remove(0);
            System.out.println(nombre+" ha cogido el "+plato.toString()+" -> "+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond());
            return plato;
        }finally {
            reentrantLock.unlock();
        }

    }

    public void colocar(Plato plato, String nombre) throws InterruptedException {
        reentrantLock.lock();
        try{
            bandeja_platos.add(plato);
            System.out.println(nombre + " ha colocado el " +plato.toString()+" en bandeja -> "+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond());
            conditionIsEmpty.signal();
        }finally {
            reentrantLock.unlock();
        }
    }
}
