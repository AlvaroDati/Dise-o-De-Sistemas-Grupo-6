package proyectoInversiones.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import proyectoInversiones.Empresa;
import proyectoInversiones.CargaBatch.CargaBatch;
import proyectoInversiones.server.Router;
import proyectoInversiones.usuarios.Usuario;

public class UsuariosRepo extends Repositorio implements WithGlobalEntityManager{
	
	
	public UsuariosRepo(EntityManager emanager) { //Antes estaba sin public
		super(emanager);
	}

	public Usuario buscarPorId(Long id) {
		return emanager.find(Usuario.class, id);

	}
	
	public void persistir(Usuario unUsuario) {
		emanager.getTransaction().begin();
		emanager.persist(unUsuario);
		emanager.getTransaction().commit();
	}

	public Usuario buscarUsuarioPorNombre(String nombre) {
		List<Usuario> usuarioALoguearse = new ArrayList<Usuario>();
//		usuarioALoguearse = emanager.createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro","%" + nombre + "%").getResultList();

		// usuarioALoguearse = this.getEM().createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();
		
//		usuarioALoguearse = Router.obtenerUsuariosSegunNombre(nombre); 
		
		 
	usuarioALoguearse = entityManager().createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();

		System.out.println("Usuario: " + usuarioALoguearse.get(0).getPassword());
		Usuario usuarioALoguearse2 = null;
		for (int i = 0; i < usuarioALoguearse.size(); i++) {
			if (usuarioALoguearse.get(i).getUserTag().equals(nombre)) {
				usuarioALoguearse2 = usuarioALoguearse.get(i);
			}
		}

		return usuarioALoguearse2;
	}
	
}


