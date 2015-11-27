/**
 * Esta clase implementa el ADT IDiccionario basándose
 * en un árbol binario de búsqueda.
 *  
 * @author  Uziel Silva Espino
 * @version 26.11.15
 */
public class BSTDiccionario<T extends Comparable, U> implements IDiccionario<T, U>{
	
	/**
	 * Clase de los nodos del árbol binario de búsqueda.
	 */
	class Nodo<T extends Comparable, U> {

		private T llave;
		private U valor;
		private Nodo<T, U> hijoIzq;
		private Nodo<T, U> hijoDer;
	
		public static final int LLAVE = 0;
		public static final int VALOR = 1;
		public static final int NODO = 2;

		public Nodo(T llave, U valor){
			this.llave = llave;
			this.valor = valor;
		}
	
		// Métodos accesores y mutadores
	
		public void setHijoIzq(Nodo<T,U> hijoIzq){
			this.hijoIzq = hijoIzq;
		}
	
		public void setHijoDer(Nodo<T,U> hijoDer){
			this.hijoDer = hijoDer;
		}
	
		public Nodo<T, U> getHijoIzq(){
			return hijoIzq;
		}
	
		public Nodo<T, U> getHijoDer(){
			return hijoDer;
		}
	
		public T getLlave(){
			return llave;
		}
	
		public void setLlave(T llave){
			this.llave = llave;
		}
	
		public U getValor(){
			return valor;
		}
	
		public void setValor(U valor){
			this.valor = valor;
		}
	
		/**
		 * Método que regresa un arreglo con los nodos del árbol
		 * ordenados en inorden
		 */
		public Object[] regresaInorden(int tipo){
	
			// Se pide el inorden de los subárboles hijos del nodo
		
			Object[] inordenIzq, inordenDer;
		
			if(hijoIzq != null){
				inordenIzq = hijoIzq.regresaInorden(tipo);
			}else{
				inordenIzq = new Object[0];
			}
		
			if(hijoDer != null){
				inordenDer = hijoDer.regresaInorden(tipo);
			}else{
				inordenDer = new Object[0];
			}
		
			// Se crea un arreglo que mezcla el inorden de los hijos
		
			Object[] inorden = new Object[inordenIzq.length + inordenDer.length + 1];
		
			for(int i = 0; i < inordenIzq.length; i++){
				inorden[i] = inordenIzq[i];
			}
			switch(tipo){
				case LLAVE:
					inorden[inordenIzq.length] = llave;
					break;
				case VALOR:
					inorden[inordenIzq.length] = valor;
					break;
				case NODO:
					inorden[inordenIzq.length] = this;
					break;
			}
		
			for(int i = 0; i < inordenDer.length; i++){
				inorden[inordenIzq.length + 1 + i] = inordenDer[i];
			}
			return inorden;
		}
	}
	
	private Nodo<T, U> raiz;

	// Método accesor

	public Nodo<T, U> getRaiz(){
		return raiz;
	}
	
	/**
	 * Método auxiliar para la búsqueda
	 */
	private Nodo<T, U> busqueda(T llave, Nodo<T, U> nodo){
		
		// Si ya se encontró el nodo con la llave se regresa.

		if(nodo == null || 
			llave.compareTo(nodo.getLlave()) == 0){ 
			return nodo;
		}

		// De otra manera se sigue buscando en los hijos según
		// el orden de la llave.
		
		if(llave.compareTo(nodo.getLlave()) < 0){
			return busqueda(llave, nodo.getHijoIzq());
		}else{
			return busqueda(llave, nodo.getHijoDer());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public U busqueda(T llave){
	
		// Se busca al nodo que tenga la llave.

		Nodo<T, U> n = busqueda(llave, raiz);
		
		// Se regresa, o en su defecto se devuelve nulo si no se encontró.

		if(n != null){
			return n.getValor();
		}else return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void insertar(T llave, U valor){
		
		// Se declaran las variables para guardar nodos temporalmente.

		Nodo<T, U> nuevoNodo, padre, nodo;

		// Creamos un nuevo nodo para la llave y valor a insertar.

		nuevoNodo = new Nodo<T, U>(llave, valor);
		
		// Si la raíz es nula, terminamos.

		if(raiz == null){
			raiz = nuevoNodo;
			return;
		}
		
		// Sino, entonces validamos que no haya algún elemento con la
		// llave a insertar. Recorremos el árbol hasta llegar a un 
		// nodo hoja, y luego insertamos el nodo.

		boolean llaveValida = true;

		padre = raiz;
		nodo = raiz; // para que no sea nulo
		
		while(nodo != null && llaveValida){
			if(llave.compareTo(nodo.getLlave()) == 0){
				llaveValida = false;
			}else{
				padre = nodo;
				if(llave.compareTo(nodo.getLlave()) < 0){
					nodo = nodo.getHijoIzq();
				}else{
					nodo = nodo.getHijoDer();
				}
			}
		}
		if(llaveValida){
			if(llave.compareTo(padre.getLlave()) < 0){
				padre.setHijoIzq(nuevoNodo);
			}else{
				padre.setHijoDer(nuevoNodo);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public U borrar(T llave){

		// Se declaran las variables para guardar nodos temporalmente.

		Nodo<T, U> nodo, padre;

		padre = raiz; // para que no sea nulo
		nodo = raiz;
		
		// Buscamos al nodo con la llave guardando al padre.

		while(nodo != null && nodo.getLlave().compareTo(llave) != 0){
			padre = nodo;
			if(llave.compareTo(nodo.getLlave()) < 0){
				nodo = nodo.getHijoIzq();
			}else{
				nodo = nodo.getHijoDer();
			}
		}
		
		if(nodo == null){ // el nodo no está
			return null;
		}
		if(nodo != raiz){

			// Caso 1, cuando el nodo no tiene hijo izquierdo:
			
			if(nodo.getHijoIzq() == null){
				if(llave.compareTo(padre.getLlave()) < 0){
					padre.setHijoIzq(nodo.getHijoDer());
				}else{
					padre.setHijoDer(nodo.getHijoDer());
				}
				return nodo.getValor();
			}	
			
			// Caso 2, cuando el nodo no tiene hijo derecho:

			if(nodo.getHijoDer() == null){
				if(llave.compareTo(padre.getLlave()) < 0){
					padre.setHijoIzq(nodo.getHijoIzq());
				}else{
					padre.setHijoDer(nodo.getHijoIzq());
				}
				return nodo.getValor();
			}

			// Caso 3, cuando el nodo tiene ambos hijos:
			
			// Buscamos el predecesor del nodo, guardando el padre para
			// hacer el debido ajuste y eliminar el nodo después de 
			// intercambiarlo con el nodo que queremos quitar.

			Nodo<T, U> padre1, nodo1;
			padre1 = nodo;
			nodo1 = nodo.getHijoIzq();
			while(nodo1.getHijoDer() != null){
				padre1 = nodo1;
				nodo1 = nodo1.getHijoDer();
			}
			
			U valor = nodo.getValor(); // guardamos el valor para regresarlo

			// Intercambiamos:

			nodo.setLlave(nodo1.getLlave());
			nodo.setValor(nodo1.getValor());

			// Eliminamos el nodo intercambiado:

			if(padre1 == nodo){
				padre1.setHijoIzq(null);
			}else{
				padre1.setHijoDer(null);
			}
			return valor;
		}else{ // el arbol consta sólo de la raíz
			raiz = null;
			return nodo.getValor();
		}
	}
}
