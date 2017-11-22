package proyectoInversiones.repos;

import java.util.HashSet;

import java.util.Set;

import proyectoInversiones.Cuenta;


public class RepoCuentas extends RepoAbstracto<Cuenta> {
	
	public RepoCuentas(Set<Cuenta> cuentas) {
	super(cuentas);
}

	public RepoCuentas() {
		super();
}
	
//public Set<Empresa> buscar(String bancoQuery, String servicioQuery) {
//  boolean searchByBanco = bancoQuery.trim().length() > 0;
//  boolean searchByServicio = servicioQuery.trim().length() > 0;
//
//  bancoQuery = searchByBanco ? ".*" + bancoQuery.toLowerCase() + ".*" : "";
//  servicioQuery = searchByServicio ? ".*" + servicioQuery.toLowerCase() + ".*" : "";
//
//  if (searchByBanco) {
//      if (searchByServicio) {
//          return searchByBancoAndServicio(bancoQuery, servicioQuery);
//      } else {
//          return searchByBanco(bancoQuery);
//      }
//  } else if(searchByServicio) {
//      return searchByServicio(servicioQuery);
//  } else {
//      // return everything (DANGER! this is just because there are few elements)
//      return new HashSet<>(this.getItems());
//  }
//}

}
