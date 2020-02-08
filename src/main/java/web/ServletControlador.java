
package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletContolador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        request.setAttribute("clientes", clientes);//lista clientes que se usara en clientes.jsp
        request.setAttribute("totalClientes", clientes.size());//tamaño de la lista
        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));//funcion saldoTotal
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }
    
    
    private double calcularSaldoTotal(List<Cliente> clientes){
        double saldoTotal = 0;
        for(Cliente cliente: clientes){
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
