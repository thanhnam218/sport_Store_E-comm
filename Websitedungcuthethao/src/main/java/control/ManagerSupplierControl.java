package control;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;
import entity.Supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "ManagerSupplierControl", urlPatterns = {"/managerSupplier"})
public class ManagerSupplierControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        DAO dao = new DAO();

        List<Supplier> listAllSupplier = dao.getAllSupplier();
        List<Category> listAllCategory = dao.getAllCategory();


        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);

        request.getRequestDispatcher("NhaPhanPhoi.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
