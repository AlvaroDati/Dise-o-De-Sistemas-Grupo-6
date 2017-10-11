package proyectoInversiones.servlet;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyectoInversiones.Cuenta;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/api/cuenta") 
public class CuentaAPI {
	@RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "redirect:/Cuentas.html";
    }
}
