package puentesobrerio;

public class Programa {

	public static void main(String[] args) {
		MonitorPuente pasoPuente = new MonitorPuente();

		// Creo los nuevos objetos que son los coches que van a pasar por el puente
		new Coche("Audi", pasoPuente);
		new Coche("Mercedes", pasoPuente);
		new Coche("Renault", pasoPuente);
		new Coche("Hyundai", pasoPuente);
		new Coche("Jeep", pasoPuente);

	}

}
