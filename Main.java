// --- Clase de Datos Climáticos --- //
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

// --- Componentes Acoplados --- //
class PanelVisual {
    public void mostrar(Clima clima) {
        System.out.println("Panel: " + clima.getTemperatura() + "C, " + clima.getHumedad() + "% humedad");
    }
}

class SistemaAlertas {
    public void verificarAlerta(Clima clima) {
        if (clima.getTemperatura() > 30) {
            System.out.println("ALERTA: Temperatura peligrosa (" + clima.getTemperatura() + "C)");
        }
    }
}

// --- Central Meteorológica (Sujeto) --- //
class CentralMeteorologica {
    private PanelVisual panel;
    private SistemaAlertas alertas;

    public CentralMeteorologica() {
        this.panel = new PanelVisual();
        this.alertas = new SistemaAlertas();
    }

    public void setClima(Clima clima) {
        panel.mostrar(clima);    // Acoplamiento fuerte
        alertas.verificarAlerta(clima); // ¿Y si añadimos más componentes?
    }
}


public class Main {
    public static void main(String[] args) {
        CentralMeteorologica central = new CentralMeteorologica();
        central.setClima(new Clima(32.5, 70));
    }
}