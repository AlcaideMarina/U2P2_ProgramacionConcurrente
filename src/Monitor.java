
public class Monitor {
	
private boolean[] direccionCoche = new boolean[2]; // 0 norte, 1 sur
private int vecesCruzadoNorte = 0;
private int vecesCruzadoSur = 0;
	
	// Inicialmente, ning�n coche est� cruzando el puente
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
		
		// el coche espera a que haya otro coche en su misma direcci�n o no haya ning�n coche
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
			System.out.println("N�mero de veces cruzando en direcci�n NORTE: " + vecesCruzadoNorte);
		} else {
			strDir = "SUR";
			vecesCruzadoSur++;
			System.out.println("N�mero de veces cruzando en direcci�n SUR: " + vecesCruzadoSur);
			
		}
		
		// Notificamos al fil�sofo que est� esperando (en el bucle while de la l�nea 18) que los tenedores est�n libres, 
		// por lo que pueden comprobar otra vez
		notify();
		
	}
	

}
