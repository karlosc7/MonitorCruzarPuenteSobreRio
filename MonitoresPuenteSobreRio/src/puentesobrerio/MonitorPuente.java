package puentesobrerio;

public class MonitorPuente {

	private boolean[] pasos = new boolean[5];

	// Clase monitor para el puente
	public MonitorPuente() {
		for (int i = 0; i < 5; i++) {
			pasos[i] = true;
		}
	}

	// Para obetener el paso dejamos una espera
	public synchronized void obtenerPaso(int i) {
		while (!pasos[i] || !pasos[(i + 1) % 4]) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pasos[i] = false;
		pasos[(i + 1) % 4] = false;
	}

	// notificamos que el paso está liberado
	public synchronized void liberarPaso(int i) {
		pasos[i] = true;
		pasos[(i + 1) % 4] = true;
		notify();

	}
}
