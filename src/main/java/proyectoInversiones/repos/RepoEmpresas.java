package proyectoInversiones.repos;

import java.util.HashSet;


import java.util.Set;

import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;


public class RepoEmpresas extends RepoAbstracto<Empresa> {

	//CONSTRUCTORES
	public RepoEmpresas(){
		super();
	}
	public RepoEmpresas(Set<Empresa> initialData){
		super();
	}
	//end CONSTRUCTORES
	
	
    public Set<Empresa> buscar(String empresaQuery, int periodoQuery) {
        boolean buscarPorEmpresa = empresaQuery != "";
        boolean buscarPorPeriodo = periodoQuery != 0;

        empresaQuery = buscarPorEmpresa ? ".*" + empresaQuery.toLowerCase() + ".*" : "";
        periodoQuery = buscarPorPeriodo ?  periodoQuery  : 0;

        if (buscarPorEmpresa) {
            if (buscarPorPeriodo) {
                return buscarPorEmpresaYPeriodo(empresaQuery, periodoQuery);
            } else {
                return buscarPorEmpresa(empresaQuery);
            }
        } else if(buscarPorPeriodo) {
            return buscarPorPeriodo(periodoQuery);
        } else {
            // return everything (DANGER! this is just because there are few elements)
            return new HashSet<>(this.getItems());
        }
    }
    

    private Set<Empresa> buscarPorEmpresa(String empresaQuery) {
        Set<Empresa> result = new HashSet<>();
        for (Empresa empresa : this.getItems()) {
            if (empresa.getNombre().toLowerCase().matches(empresaQuery)) {
                result.add(empresa);
            }
        }
        return result;
    }

    private Set<Empresa> buscarPorPeriodo(int periodoQuery) {
        Set<Empresa> result = new HashSet<>();
        for (Empresa empresa : this.getItems()) {
            for (Periodo periodo : empresa.getPeriodos()) {
                if (periodo.getAnio() == periodoQuery) {
                    result.add(empresa);
                }
            }
        }
        return result;
    }

    private Set<Empresa> buscarPorEmpresaYPeriodo(String empresaQuery, int periodoQuery) {
        Set<Empresa> result = new HashSet<>();
        for (Empresa empresa : this.getItems()) {
            if (empresa.getNombre().toLowerCase().matches(empresaQuery)) {
            	for (Periodo periodo : empresa.getPeriodos()) {
            		if (periodo.getAnio() == periodoQuery) {
            	}
                        result.add(empresa);
                        break;
                    }
                }
            }
        return result;
        }
    }

