
public class Monitor {
	
private boolean[] direccionCoche = new boolean[2]; // 0 norte, 1 sur
private int vecesCruzadoNorte = 0;
private int vecesCruzadoSur = 0;
	
	// Inicialmente, ningún coche está cruzando el puente
	public Monitor() {
		for (int i = 0; i < 2; i++) {
			direccionCoche[i] = false;
		}
	}
	
	// i: direccion - 0 norte, 1 sur
	public synchronized void obtenerDireccion(int direccion) {
		
		int dirContraria;
		if (direccion == 0) {
			// Coche va hacia el norte
			dirContraria = 1;
		} else {
			// Coche va hacia el sur
			dirContraria = 0;
		}
		
		// el coche espera a que haya otro coche en su misma dirección o no haya ningún coche
		while(direccionCoche[dirContraria]) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// actualizamos direcciones
		direccionCoche[direccion] = true;
		direccionCoche[dirContraria] = false;
		
	}
	
	public synchronized void liberarCarril(int direccion) {
		direccionCoche[direccion] = false;
		
		String strDir;
		if (direccion == 0) {
			vecesCruzadoNorte++;
			System.out.println("Número de veces cruzando en dirección NORTE: " + vecesCruzadoNorte);
		} else {
			strDir = "SUR";
			vecesCruzadoSur++;
			System.out.println("Número de veces cruzando en dirección SUR: " + vecesCruzadoSur);
			
		}
		
		// Notificamos al filósofo que está esperando (en el bucle while de la línea 18) que los tenedores están libres, 
		// por lo que pueden comprobar otra vez
		notify();
		
	}
	

}
