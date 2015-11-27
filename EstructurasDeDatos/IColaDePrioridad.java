/**
 * Este ADT maneja la idea de una cola en la cual los 
 * elementos tienen un cierto orden o prioridad.
 *
 * @author  Uziel Silva Espino
 * @version 25.11.15
 */
public interface IColaDePrioridad<T extends Comparable>{

	/**
	 * Método usado para insertar una llave entera 
	 * y acomodarla en la cola de prioridad para un
	 * acceso eficiente.
	 * @param llave La llave a insertar
	 */
	public void insertar(T llave);

	/**
	 * Método usado para eliminar y devolver el elemento 
	 * con mayor prioridad de la cola, a su vez haciendo los
	 * ajustes necesarios para que la misma conserve su 
	 * eficiencia. 
	 */
	public T borrar();
	
}
