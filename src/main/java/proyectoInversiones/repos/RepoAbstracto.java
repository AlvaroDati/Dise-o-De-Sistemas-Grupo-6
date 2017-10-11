package proyectoInversiones.repos;

import java.util.HashSet;
import java.util.Set;

public abstract class RepoAbstracto<T> {
	  private Set<T> items = new HashSet<T>();

//CONSTRUCTOR vacio
	    public RepoAbstracto() {
	    }
	 
//CONSTRUCTOR con data inicial
//// @param initialData
////	     
	    public RepoAbstracto(Set<T> initialData) {
	        if (initialData != null) {
	            items.addAll(initialData);
	        }
	    }

//	Aniade nueva entrada
////   @param item
////   @return
////	   
	    public boolean addItem(T item) {
	        return this.items.add(item);
	    }

//	Borra cierta entrada
////   @param item
////   @return
////
	    public boolean removeItem(T item) {
	        return this.items.remove(item);
	    }

	    public Set<T> getItems() {
	        return items;
	    }
}
