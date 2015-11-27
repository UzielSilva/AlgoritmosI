/**
 * Esta clase se encarga de probar la implementación del ADT
 * IColaDePrioridad bajo la clase HeapColaDePrioridad.
 *
 * @author  Uziel Silva Espino
 * @version 25.11.15
 */

public class PruebaHeapColaDePrioridad{
	public static void main(String arg[]){

		// Se crea un arreglo de 10 elementos con enteros aleatorios 
		// del 0 al 99, y se muestra al usuario.

		int[] arreglo = new int[10];
		for(int i = 0; i < 10; i++){
			arreglo[i] = numeroAleatorio(0, 100);
		}

		System.out.println("Valores iniciales del arreglo:");
		imprimeArreglo(arreglo);

		// Se insertan y posteriormente remueven de una cola de 
		// prioridad los elementos del arreglo, para después 
		// devolverlos ya ordenados al mismo arreglo.

		IColaDePrioridad<Integer> c = new HeapColaDePrioridad<Integer>();
		for(int i = 0; i < 10; i++){
			c.insertar(arreglo[i]);
		}

		for(int i = 0; i < 10; i++){
			arreglo[i] = c.borrar();
		}

		// Se muestra el resultado al usuario

		System.out.println("Valores finales del arreglo:");
		imprimeArreglo(arreglo);
		
	}
	
	/**
	 * Método que consiste en generar enteros aleatorios.
	 * @param min Entero mínimo posible a obtener.
	 * @param max Cota superior del nuevo entero a obtener, 
	 * sin incluír la posibilidad de que sea el entero.
	 */

	private static int numeroAleatorio(int min, int max){
		return min + (int)(Math.random()*(max - min));
	}

	/**
	 * Método para imprimir arreglos.
	 * @param arreglo El arreglo a imprimir.
	 */
	private static void imprimeArreglo(int[] arreglo){
		String s = "[";
		for(int i = 0; i < arreglo.length; i++){
			s += arreglo[i];
			if(i == arreglo.length - 1){
				s += "]";
			}else{
				s += ", ";
			}
		}
		System.out.println(s);
	}
}
