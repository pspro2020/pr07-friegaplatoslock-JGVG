package friegaplatos;

public class Plato {

    private int num_serie;

    public Plato(int num_serie){
        this.num_serie = num_serie;
    }

    @Override
    public String toString() {
        return "plato número " + num_serie;
    }

    public int getNum_serie() {
        return num_serie;
    }
}
