import java.util.Random;

public class Coche extends Thread {

	private enum STATE{WAITING, CROSSING, FINISH}
	private STATE state;
	private int direccion; // 0 norte, 1 sur
	private int id;
	private static int numCoches;
	private Monitor monitor;
	
	public Coche(int d, Monitor m) {
		id = numCoches++;
		direccion = d;
		state = STATE.WAITING;
		monitor = m;
		start();
	}
	
	private void carIsWaiting() {
		
		String strDir;
		if (direccion == 0) {
			strDir = "NORTE";
		} else {
			strDir = "SUR";
		}
		
		System.out.println("Coche " + id + " está esperando (" + strDir + ").");
		monitor.obtenerDireccion(direccion);
		
		state = state.CROSSING;
		
	}
	
	private void carIsCrossing() {
		
		String strDir;
		if (direccion == 0) {
			strDir = "NORTE";
		} else {
			strDir = "SUR";
		}
		
		System.out.println("Coche " + id + " está cruzando (" + strDir + ").");
		Random rdm = new Random();
		int crossingTime = rdm.nextInt(250 - 50 + 1) + 50;
		
		try {
			sleep(crossingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Coche " + id + " ha terminado de cruzar (" + strDir + ").");
		
		monitor.liberarCarril(direccion);
		state = state.FINISH;
		
	}
	
	@Override
	public void run() {
		
		while(true) {
			switch(state) {
			
			case WAITING:
				carIsWaiting();
				break;
				
			case CROSSING:
				carIsCrossing();
				break;
				
			}
		}
	}
	
}
