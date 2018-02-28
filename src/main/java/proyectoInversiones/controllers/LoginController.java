package proyectoInversiones.controllers;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.repositorio.UsuariosRepo;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class LoginController {

	private RepositorioServicio repositorioServicio;
	

	public ModelAndView login(Request req, Response res) throws Exception {
		//RepositorioServicio.getInstance().buscarUsuarioPorNombre("");
		return new ModelAndView(null, "Index.html");
	}
	
	
	public ModelAndView validate(Request req, Response res) {
		
		String userTag = req.queryParams("userTag");
		String password = req.queryParams("password"); 
		System.out.printf("Usuario: %s queriendose conectar con contraseña: %s !\n", userTag,password);
		repositorioServicio = RepositorioServicio.getInstance();
		try{
			Usuario usuarioLoggeado = repositorioServicio.buscarUsuarioPorNombre(userTag);
			System.out.printf("USUARIO: %s CON CONTRASEÑA: %s SACADOS CON ÉXITO DE LA BASE DE DATOS \n\n",usuarioLoggeado.getUserTag(),usuarioLoggeado.getPassword());
			
//			UsuariosRepo repo = new UsuariosRepo(emanager);
			Long idUsuario =  usuarioLoggeado.getId();
		if(userTag.equals(usuarioLoggeado.getUserTag()) && password.equals(usuarioLoggeado.getPassword())){
			
			res.cookie("userTag", userTag);
			res.cookie("usuario", userTag);
			res.cookie("idUsuario", idUsuario.toString());
			res.redirect("/cuentas");
		}else{
			res.removeCookie("userTag");
			res.removeCookie("usuario");
			res.removeCookie("idUsuario");
			res.redirect("/");
			//return new ModelAndView(null,"public/LoginError.html");
		};
			
			
		} catch ( Exception e ){
			res.removeCookie("userTag");
			res.removeCookie("usuario");
			res.removeCookie("idUsuario");
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
			//return new ModelAndView(null,"public/LoginError.html");
		}
		return null;
	}		
}
