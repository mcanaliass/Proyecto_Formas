package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase principal del modelo: aloja las colecciones de figuras y cuerpos
 * instanciados en el programa, y permite operar sobre ellas de forma
 * agregada usando ligado dinámico (cada figura/cuerpo calcula su propia
 * área, perímetro y volumen según su tipo real en tiempo de ejecución).
 */
public class Coleccion {

    private List<Figura> figuras;
    private List<Cuerpo> cuerpos;

    public Coleccion() {
        this.figuras = new ArrayList<>();
        this.cuerpos = new ArrayList<>();
    }

    public void agregarFigura(Figura figura) {
        figuras.add(figura);
    }

    public void agregarCuerpo(Cuerpo cuerpo) {
        cuerpos.add(cuerpo);
    }

    public boolean eliminarFigura(Figura figura) {
        return figuras.remove(figura);
    }

    public boolean eliminarCuerpo(Cuerpo cuerpo) {
        return cuerpos.remove(cuerpo);
    }

    public List<Figura> listarFiguras() {
        return Collections.unmodifiableList(figuras);
    }

    public List<Cuerpo> listarCuerpos() {
        return Collections.unmodifiableList(cuerpos);
    }

    public double calcularAreaTotal() {
        double total = 0;
        for (Figura figura : figuras) {
            total += figura.calcularArea();
        }
        for (Cuerpo cuerpo : cuerpos) {
            total += cuerpo.calcularArea();
        }
        return total;
    }

    public double calcularPerimetroTotal() {
        double total = 0;
        for (Figura figura : figuras) {
            total += figura.calcularPerimetro();
        }
        for (Cuerpo cuerpo : cuerpos) {
            total += cuerpo.calcularPerimetro();
        }
        return total;
    }

    public double calcularVolumenTotal() {
        double total = 0;
        for (Cuerpo cuerpo : cuerpos) {
            total += cuerpo.calcularVolumen();
        }
        return total;
    }

    public void setFiguras(List<Figura> figuras) {
        this.figuras = figuras;
    }

    public void setCuerpos(List<Cuerpo> cuerpos) {
        this.cuerpos = cuerpos;
    }
}
