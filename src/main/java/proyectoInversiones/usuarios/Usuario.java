package proyectoInversiones.usuarios;

import java.time.Year;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*import dominio.empresas.Empresa;
import dominio.indicadores.Indicador;
import dominio.indicadores.RepositorioIndicadores;
import dominio.metodologias.Metodologia;
import dominio.metodologias.RepositorioMetodologias;
import dominio.parser.ParserIndicadores;*/

//@Entity
//@Table(name = "usuarios")
public class Usuario {
//	@Id 
//	@GeneratedValue
	private Long idUsuario;
	private String userTag;
	private String password;
	
/*	private static Usuario activo;
	public static Usuario activo(){
		return activo;
	}
	public static void activo(Usuario activo) {
		Usuario.activo = activo;
	}
*/	
	private Usuario(){}//Necesario para persistir la clase
	
	public Usuario(String userTag, String password){
		this.userTag = userTag;
		this.password = password;
	}
	
	public boolean validar(String userTag, String password){
		System.out.printf("entro a validar \n");
		return userTag.equals(this.getUserTag()) && password.equals(this.getPassword());
	}
	
	public Long getId() {
		return idUsuario;
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