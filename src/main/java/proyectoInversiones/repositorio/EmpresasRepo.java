package proyectoInversiones.repositorio;

import javax.persistence.EntityManager;
import java.util.List;
import proyectoInversiones.Empresa;


public class  EmpresasRepo extends Repositorio {
	EmpresasRepo(EntityManager emanager) {
		super(emanager);
	}

	public Empresa buscarPorId(Long id) {
		return emanager.find(Empresa.class, id);
	}

	public void persistir(Empresa unaEmpresa) {
		emanager.getTransaction().begin();
		emanager.persist(unaEmpresa);
		emanager.getTransaction().commit();
	}
	
	
	public List <Empresa> buscarEmpresaPorNombre(String nombre){
		List<Empresa> listaEmpresasFiltradas = null;
		listaEmpresasFiltradas = emanager.createNamedQuery("buscarEmpresaPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();
		return listaEmpresasFiltradas;
	}
	
/*	public List <Cuenta> buscarCuentaPorNombreYEmpresa(Cuenta cuentaBuscada, Empresa empresa){
		
		String nombreCuenta = cuentaBuscada.getNombre();
		List<Cuenta> listaCuentasFiltradas = null;
		
		listaCuentasFiltradas = emanager.createNamedQuery("buscarCuentaPorNombreYEmpresa").setParameter("filtro", "%" + nombreCuenta + "%").getResultList();
		
		return listaCuentasFiltradas;
	} */
	
}











