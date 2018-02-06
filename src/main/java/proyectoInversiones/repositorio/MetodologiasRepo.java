package proyectoInversiones.repositorio;


import javax.persistence.EntityManager;
import proyectoInversiones.Metodologia;



public class MetodologiasRepo extends Repositorio {
     MetodologiasRepo(EntityManager emanager) {
		super(emanager);
	}

	public Metodologia buscarPorId(Long id) {
		return emanager.find(Metodologia.class, id);
	}

	public void persistir(Metodologia unaMetodologia) {
		emanager.getTransaction().begin();
		emanager.persist(unaMetodologia);
		emanager.getTransaction().commit();
	}
}