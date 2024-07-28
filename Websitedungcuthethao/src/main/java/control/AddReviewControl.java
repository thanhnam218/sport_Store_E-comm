/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Product;
import entity.Review;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "AddReviewControl", urlPatterns = {"/addReview"})
public class AddReviewControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if(a==null) {
        	response.sendRedirect("login");
        	return;
        }
        int accountID = a.getId();
        String contentReview = request.getParameter("contentReview");
        int productID = Integer.parseInt(request.getParameter("productID"));
        
        DAO dao = new DAO();
        dao.insertReview(accountID, productID, contentReview);
      
        Review newReview = new Review();
        newReview = dao.getNewReview(accountID, productID);
        PrintWriter out = response.getWriter();

        
            out.println(" <div class=\"media mt-3 mb-4\">\r\n"
            		+ "              <img class=\"d-flex mr-3 z-depth-1\" src=\"https://mdbootstrap.com/img/Photos/Others/placeholder1.jpg\"\r\n"
            		+ "                width=\"62\" alt=\"Generic placeholder image\">\r\n"
            		+ "              <div class=\"media-body\">\r\n"
            		+ "                <div class=\"d-flex justify-content-between\">\r\n"
            		+ "                  <p class=\"mt-1 mb-2\">\r\n"
            		+ "                    <strong>"+a.getUser()+"</strong>\r\n"
            		+ "                    <span>� </span><span>"+newReview.getDateReview()+"</span>\r\n"
            		+ "                  </p>\r\n"
            		+ "                </div>\r\n"
            		+ "                <p class=\"mb-0\">"+newReview.getContentReview()+"</p>\r\n"
            		+ "              </div>\r\n"
            		+ "            </div>\r\n"
            		+ "            <hr>    ");
       
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
