package proyectoInversiones.usuarios;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.lang.reflect.Type;


//import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;




public class LeerUsuarios {
	
	
	ArrayList<Usuario> usuarios = this.leerArchivo();
	public ArrayList<Usuario> leerArchivo() {

		String ruta = "usuarios.json";
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

		try {

			FileReader fr = new FileReader(ruta);
			Type tipoUsuario = new TypeToken<ArrayList<Usuario>>() {}.getType();
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(fr);
			listaUsuarios = gson.fromJson(reader, tipoUsuario);
			return listaUsuarios;

		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo indicado. El path provisto fue:" + ruta);
			e.printStackTrace();
		}

		return listaUsuarios;
	}
	

	public Long obtenerId(String userTag, String password) throws Exception {
		Usuario usuarioFiltrado = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = this.leerArchivo();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUserTag().equals(userTag) && usuarios.get(i).getPassword().equals(password)) {
				System.out.printf("Valido el usuario: %s\n", usuarios.get(i).getUserTag());
				usuarioFiltrado = usuarios.get(i);
			} else {
				//throw new Exception();
			}

		}
		return usuarioFiltrado.getId();
	}
	
	
	
	@SuppressWarnings("unused")
	public Usuario obtenerUsuario(String userTag){
		Usuario usuarioBuscado = new Usuario();
		for (Usuario head:usuarios){
			if (head.getUserTag()==userTag){
				usuarioBuscado = head;
				return usuarioBuscado;
			}
		}
		return null;	
	}
	
	
}
