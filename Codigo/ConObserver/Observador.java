import java.util.ArrayList;
import java.util.List;

// --- Interfaz Observer --- //
interface ObservadorClimatico {
    void actualizar(Clima clima);
}

// --- Clima (Objeto compartido) --- //
class Clima {
    private double temperatura;
    private double humedad;

    // Constructor para inicializar los datos climáticos
    public Clima(double temperatura, double humedad) {
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    // Métodos para obtener los datos climáticos
    public double getTemperatura() { return temperatura; }
    public double getHumedad() { return humedad; }
}

// --- Sujeto: Central Meteorológica --- //
class CentralMeteorologica {
    private List<ObservadorClimatico> observadores = new ArrayList<>();
    private Clima climaActual;

    public void agregarObservador(ObservadorClimatico o) {
        observadores.add(o);
    }

    public void setClima(Clima clima) {
        this.climaActual = clima;
        notificarObservadores();
    }

    private void notificarObservadores() {
        for (ObservadorClimatico o : observadores) {
            o.actualizar(climaActual);
        }
    }
}

// --- Observadores Concretos --- //
class PanelVisual implements ObservadorClimatico {
    @Override
    public void actualizar(Clima clima) {
        System.out.println("Panel: " + clima.getTemperatura() + "C, " + clima.getHumedad() + "% humedad");
    }
}

class SistemaAlertas implements ObservadorClimatico {
    @Override
    public void actualizar(Clima clima) {
        if (clima.getTemperatura() > 30) {
            System.out.println("ALERTA: Temperatura peligrosa (" + clima.getTemperatura() + "C)");
        }
    }
}

//class SistemaNotificaciones implements ObservadorClimatico {
//    @Override
//    public void actualizar(Clima clima) {
//        System.out.println("Notificacion: Clima actualizado a " + clima.getTemperatura() + "C y " + clima.getHumedad() + "% humedad");
//    }
//}

// --- Uso --- //
public class Observador {
    public static void main(String[] args) {
        CentralMeteorologica central = new CentralMeteorologica();
        central.agregarObservador(new PanelVisual());
        central.agregarObservador(new SistemaAlertas());
        central.setClima(new Clima(25.0, 50)); 

        central.setClima(new Clima(32.5, 70));

        //System.out.println("Agregando un nuevo observador...");
        //central.agregarObservador(new SistemaNotificaciones());
        //central.setClima(new Clima(28.0, 60));
        
    }
}