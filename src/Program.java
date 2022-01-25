import java.util.Random;

public class Program {

	public static void main(String[] args) {
		
		Monitor monitor = new Monitor();
		int numCoches = 0;

		while (true) {
			Random rdm1 = new Random();
			int dir = (int) Math.round(rdm1.nextDouble());
			Random rdm2 = new Random();
			double rdmGenerar = rdm2.nextDouble();
			if (rdmGenerar < 0.9) {
				new Coche(dir, monitor);
				numCoches++;
			}
		}
		
	}
	
	
}
