
package control;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;
import entity.Review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "DetailControl", urlPatterns = {"/detail"})
public class DetailControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        DAO dao = new DAO();
        Product p = dao.getProductByID(id);
        
        int cateIDProductDetail = dao.getCateIDByProductID(id);
        List<Product> listRelatedProduct = dao.getRelatedProduct(cateIDProductDetail);
        
        List<Review> listAllReview = dao.getAllReviewByProductID(id);
        int countAllReview = listAllReview.size();
        
        List<Account> listAllAcount = dao.getAllAccount();
        
        Product last = dao.getLast();

        request.setAttribute("detail", p);
        request.setAttribute("listRelatedProduct", listRelatedProduct);
        request.setAttribute("listAllReview", listAllReview);
        request.setAttribute("listAllAcount", listAllAcount);
        request.setAttribute("countAllReview", countAllReview);
        request.setAttribute("p", last);
        request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
