package puentesobrerio;

import java.util.Random;

public class Coche extends Thread {

	private enum STATE {
		THINK, CROSS, SEE
	};

	private STATE state;
	private String nombre;
	private int id;
	private static int totalCoches;
	private MonitorPuente monitor;
	private int vecesPaso = 0;

	// Creamos la clase Coche con sus atributos
	public Coche(String n, MonitorPuente m) {
		id = totalCoches++;
		nombre = n;
		state = STATE.THINK;
		monitor = m;
		start();
	}

	// Creamos el método para dejar cruzar durante un tiempo random para que pase el
	// coche que ya está
	private void think() {
		System.out.println(nombre + " empieza a cruzar el puente. Id: " + id);
		Random rnd = new Random();
		int thingingTime = rnd.nextInt(250 - 50 + 1) + 50;
		try {
			sleep(thingingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(nombre + " deja cruzar. Id: " + id);
		state = STATE.CROSS;
	}

	// Este método mira si tiene paso en el hilo
	private void see() {
		System.out.println(nombre + " tiene que cruzar y mira para ver si puede pasar. Id: " + id);
		monitor.obtenerPaso(id);

		state = STATE.SEE;
	}

	// Este método es el que empieza a cruzar el coche con un tiempo random
	private void cross() {
		System.out.println(nombre + " empieza a cruzar. Id: " + id);

		Random rnd = new Random();
		int crossTime = rnd.nextInt(250 - 50 + 1) + 50;
		try {
			sleep(crossTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		vecesPaso++;
		System.out.println(nombre + " termina de cruzar. (Veces que ha cruzado: " + vecesPaso + " ID: " + id);

		monitor.liberarPaso(id);
		state = STATE.THINK;
	}

	// Este método ejecuta los hilos de cada funcionalidad cruzar, mirar y pensar si
	// puede pasar
	public void run() {
		while (true) {
			switch (state) {
			case CROSS:
				cross();
				break;
			case SEE:
				see();
				break;
			case THINK:
				think();
				break;
			}
		}
	}
}
