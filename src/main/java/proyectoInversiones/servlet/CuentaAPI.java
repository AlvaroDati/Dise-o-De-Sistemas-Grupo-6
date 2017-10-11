package proyectoInversiones.servlet;

import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.repos.RepoEmpresas;
import proyectoInversiones.Resources;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/api/cuenta") 
public class CuentaAPI {
	
	private RepoEmpresas repoEmpresas;

	
//	@RequestMapping(method = RequestMethod.GET)
//    public String get() {
//    return "redirect:/Cuentas.html";
//    }
	
    public CuentaAPI(){
    	repoEmpresas = Resources.getInstance().getRepoEmpresas();
    }

    @RequestMapping(method = POST)
    public void crear(@RequestBody Empresa empresa) {
        repoEmpresas.addItem(empresa);
    }

    @RequestMapping(method = GET)
    public Set<Empresa> buscar(@RequestParam(value = "empresa", defaultValue = "", required = false) String empresa, 
    						   @RequestParam(value = "periodo", defaultValue = "", required = false) int periodo) {
        return repoEmpresas.buscar(empresa, periodo);
    }


}

