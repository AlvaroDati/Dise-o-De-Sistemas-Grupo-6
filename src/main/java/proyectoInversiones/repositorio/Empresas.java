package proyectoInversiones.repositorio;

import javax.persistence.EntityManager;
import java.util.List;
import proyectoInversiones.Empresa;
import proyectoInversiones.indicadores.ArmadorIndicador;


public class  Empresas extends Repositorio {
	Empresas(EntityManager emanager) {
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
	
	/*
	 * 
	 * Entrega4Indicadores
	 * 
	 * */
	public void persistirIndicador(ArmadorIndicador indicador){
		emanager.getTransaction().begin();
		emanager.persist(indicador);
		emanager.getTransaction().commit();
	}
	/*
	 * 
	 * Entrega4Indicadores
	 * 
	 * */
	
	public List <Empresa> buscarEmpresaPorNombre(String nombre){
		List<Empresa> listaEmpresasFiltradas = null;
		listaEmpresasFiltradas = emanager.createNamedQuery("buscarEmpresaPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();
		return listaEmpresasFiltradas;
	}
	
}











