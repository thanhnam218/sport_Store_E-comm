/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "CategoryControl", urlPatterns = {"/category"})
public class CategoryControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cateID = request.getParameter("cid");
        DAO dao = new DAO();
        List<Product> list = dao.getProductByCID(cateID);
        //in list p day
        PrintWriter out = response.getWriter();
        for(Product o : list) {
        	out.println(" <div class=\"product col-12 col-md-6 col-lg-4\">\r\n"
        			+ "                                <div class=\"card\">\r\n"
        			+ "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\r\n"
        			+ "                                    <div class=\"card-body\">\r\n"
        			+ "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid="+o.getId()+"\" title=\"View Product\">"+o.getName()+"</a></h4>\r\n"
        			+ "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"</p>\r\n"
        			+ "                                        <div class=\"row\">\r\n"
        			+ "                                            <div class=\"col\">\r\n"
        			+ "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\r\n"
/*                			+ "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\r\n"*/
        			+ "                                            </div>\r\n"
        			+ "                                            <div class=\"col\">\r\n"
        			+ "                                                <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\r\n"
        			+ "                                            </div>\r\n"
        			+ "                                        </div>\r\n"
        			+ "                                    </div>\r\n"
        			+ "                                </div>\r\n"
        			+ "                            </div>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
