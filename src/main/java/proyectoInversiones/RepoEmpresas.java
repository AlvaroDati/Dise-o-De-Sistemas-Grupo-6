package proyectoInversiones;

import java.util.HashSet;
import java.util.Set;

import proyectoInversiones.Empresa;

public class RepoEmpresas {
//	public RepoEmpresas(Set<Empresa> bancos) {
//        super(bancos);
//    }
//
//    /**
//     * Searches for entries that match given parameters
//     *
//     * @param bancoQuery
//     * @param servicioQuery
//     * @return
//     */
//    public Set<Empresa> buscar(String bancoQuery, String servicioQuery) {
//        boolean searchByBanco = bancoQuery.trim().length() > 0;
//        boolean searchByServicio = servicioQuery.trim().length() > 0;
//
//        bancoQuery = searchByBanco ? ".*" + bancoQuery.toLowerCase() + ".*" : "";
//        servicioQuery = searchByServicio ? ".*" + servicioQuery.toLowerCase() + ".*" : "";
//
//        if (searchByBanco) {
//            if (searchByServicio) {
//                return searchByBancoAndServicio(bancoQuery, servicioQuery);
//            } else {
//                return searchByBanco(bancoQuery);
//            }
//        } else if(searchByServicio) {
//            return searchByServicio(servicioQuery);
//        } else {
//            // return everything (DANGER! this is just because there are few elements)
//            return new HashSet<>(this.getItems());
//        }
//    }
}
