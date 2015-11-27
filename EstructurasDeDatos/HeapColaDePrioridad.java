/**
 * Esta clase implementa el ADT IColaDePrioridad basándose
 * en un heap que usa un arreglo incremental.
 *  
 * @author  Uziel Silva Espino
 * @version 25.11.15
 */

public class HeapColaDePrioridad<T extends Comparable<T>> implements IColaDePrioridad<T>{
	
	private final int CIMA = 0;
	ArregloListaIndice<T> arreglo;
	
	public HeapColaDePrioridad(){
		arreglo = new ArregloListaIndice<T>();
	}
	/**
	 * {@inheritDoc}
	 */
	public void insertar(T llave){
		
		// Se inserta la nueva llave en el arreglo incremental

		arreglo.add(llave);

		// Se declaran variables para los índices:

		int hijo = arreglo.size() - 1;
		int padre = (hijo - 1)/2;

		while(padre >= 0) {

			// Se verifica si el padre es mayor al nuevo hijo,
			// si es más grande, se intercambian y se verifica la
			// propiedad con el nuevo padre y el abuelo.

			if(arreglo.get(padre).compareTo(arreglo.get(hijo)) < 0){
				intercambiar(padre, hijo);
				hijo = padre;
				padre = (hijo - 1)/2;
			}else{
				break;
			}
		}
	}
	/**
	 * {@inheritDoc}
	 */
	
	public T borrar(){
	
		// Se verifica que el arreglo no esté vacío, 
		// de otra manera lanzamos una excepción.

		if(arreglo.size() == 0){
			throw new IndexOutOfBoundsException("La cola está vacía");
		}
		// Se obtiene el elemento mayor, el de la cima y
		// se coloca el ultimo elemento en su lugar.
	
		T cima = arreglo.get(CIMA);
		T ultimoElemento = arreglo.remove(arreglo.size() - 1);
		
		// Si ya no quedan elementos en la cola de prioridad no hay nada que hacer.

		if(arreglo.size() == 0) return cima;
		
		arreglo.set(CIMA, ultimoElemento);
		
		// Se declaran variables para los índices:
		
		int padre = 0;
		int hijo = 1;
		
		// Caso 1, tamano > 2:
		
		while (hijo < arreglo.size() - 1){
		
			// Se escoge el hijo más grande
			
			if (arreglo.get(hijo).compareTo(arreglo.get(hijo + 1)) < 0){ 
				hijo = hijo + 1;
			}
			
			// Se verifica si el padre es mayor al hijo mas grande,
			// si es más grande, se intercambian y se verifica el
			// siguente subárbol.
			
			if (arreglo.get(padre).compareTo(arreglo.get(hijo)) < 0){
				intercambiar(padre, hijo);
				padre = hijo;
				hijo = 2*hijo;
			} else return cima;
		}
		
		// Caso 2, tamano == 2:
		
		if(arreglo.size() == 2 && arreglo.get(padre).compareTo(arreglo.get(hijo)) < 0){
			intercambiar(padre, hijo);
		}
		
		return cima;
	}
	
	/** 
	 * Método para intercambiar valores del heap.
	 * @param  idx1 indice de intercambio 1
	 * @param  idx2 indice de intercambio 2
	 */

	private void intercambiar(int idx1, int idx2){
		
		// Se guarda el valor del padre en una variable temporal  
		
		T temp = arreglo.get(idx1);
	
		// Se intercambian los valores	
		
		arreglo.set(idx1, arreglo.get(idx2));
		arreglo.set(idx2, temp);
		
	}
	
}
