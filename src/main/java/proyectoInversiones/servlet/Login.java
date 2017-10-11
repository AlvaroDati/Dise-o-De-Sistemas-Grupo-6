package proyectoInversiones.servlet;

import proyectoInversiones.Spring.LoginRegistry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
@Controller
@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet{
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String token = LoginRegistry.getInstance().login(username, password);

        if (token != null) {
            resp.getOutputStream().print(token);
        } else {
            throw new ServletException("Usuario o contraseņa invalidos.");
        }

    }
	



	
}
