import java.util.Random;

public class Program {
	
	public static void main(String[] args) {
		
		MonitorPuente monitorPuente = new MonitorPuente();
		String dir;
		
		while (true) {
			// Utilizamos un random para decidir la dirección
			Random rdm1 = new Random();
			if (rdm1.nextDouble() < 0.5) {
				dir = "N";
			} else {
				dir = "S";
			}
			// Utilizamos otro random para ver si ese coche cruza o no
			Random rdm2 = new Random();
			if (rdm2.nextDouble() < 0.9) {
				new Coche(dir, monitorPuente);
			} else {
				System.out.println("No cruza");
			}
		}
	}

	
}
