package proyectoInversiones.usuarios;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;

import proyectoInversiones.Indicador;

@Entity
@Table(name = "usuarios")
@NamedQuery(name = "buscarUsuarioPorNombre", query = "SELECT u FROM Usuario u WHERE u.userTag LIKE :filtro")
public class Usuario implements Serializable {
	@Id 
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "userTag")
	private String userTag;
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Indicador> indicadoresUsuario;


	//	private static Usuario activo;
	
public List<Indicador> getIndicadoresUsuario() {
		return indicadoresUsuario;
	}

	public void setIndicadoresUsuario(List<Indicador> indicadoresUsuario) {
		this.indicadoresUsuario = indicadoresUsuario;
	}

	/*	private static Usuario activo;
	public static Usuario activo(){
		return activo;
	}
	public static void activo(Usuario activo) {
		Usuario.activo = activo;
	}
*/	
	protected Usuario(){}//Necesario para persistir la clase
	
	
	public Usuario(String userTag, String password){
		this.userTag = userTag;
		this.password = password;
	}
	
	public boolean validar(String userTag, String password){
		return (userTag.equals(this.getUserTag()) && password.equals(this.getPassword()));
	}
	
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	public String getUserTag() {
		return userTag;
	}
	
	public String getPassword() {
		return password;
	}
			
	public int hashCode() {
		return userTag.hashCode();
	}
	
	@Override
	public String toString(){
		return userTag;
	}
	
}