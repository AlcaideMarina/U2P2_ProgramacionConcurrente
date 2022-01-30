import java.util.ArrayList;

public class MonitorPuente {

	ArrayList<Integer> cruzandoNorte = new ArrayList<Integer>();
	ArrayList<Integer> cruzandoSur = new ArrayList<Integer>();

	public synchronized void esperar(int id, String direccion) {

		if (direccion == "N") {

			while (cruzandoSur.size() != 0) {
				try {
					wait();
				} catch (Exception e) {
					System.out.println("Error en esperar(): " + e.toString());
					e.printStackTrace();
				}
			}

			cruzandoNorte.add(id);

		} else {

			while (cruzandoNorte.size() != 0) {
				try {
					wait();
				} catch (Exception e) {
					System.out.println("Error en esperar(): " + e.toString());
					e.printStackTrace();
				}
			}

			cruzandoSur.add(id);

		}
	}

	public synchronized void finalizarPuente(int id, String direccion) {

		if (direccion.equals("N")) {
			cruzandoNorte.remove(new Integer(id));
		} else {
			cruzandoSur.remove(new Integer(id));
		}

		notify();

	}
}
