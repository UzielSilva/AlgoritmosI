/**
 * Este ADT maneja la idea de una estructura que 
 * identifica valores con una única llave. Permite
 * buscar, insertar y borrar a través de la misma.
 *
 * @author  Uziel Silva Espino
 * @version 25.11.15
 */
interface IDiccionario<T extends Comparable, U>{

	/**
	 * Método usado para buscar un valor asociado
	 * con cierta llave. Si es encontrado se devuelve
	 * el valor, de otra manera se regresa nulo.
	 * @param llave La llave usada en la búsqueda
	 */
	public U busqueda(T llave);
	
	/**
	 * Método usado para insertar un valor asociado
	 * a una llave dada.
	 * @param llave La llave asociada al valor
	 * @param valor El valor a insertar
	 */
	public void insertar(T llave, U valor);
	
	/**
	 * Método usado para borrar un elemento asociado 
	 * a una llave. Si es encontrado se devuelve
	 * el valor, de otra manera se regresa nulo.
	 * @param llave La llave del elemento a borrar
	 */
	public U borrar(T llave);
}
