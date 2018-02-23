/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed_Mokhtar
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    public static ArrayList<User> usersList = new ArrayList<>();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String signUpName = request.getParameter("signUpName");
        String signUpPass = request.getParameter("signUpPass");
        
        if (signUpName.isEmpty() || signUpPass.isEmpty()) {
            response.sendRedirect("signup.html");
        } else {
            usersList.add(new User(signUpName, signUpPass));
            response.sendRedirect("index.html");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String loginName = request.getParameter("loginName");
        String loginPass = request.getParameter("loginPass");
        
        boolean isRegistered = false;
        
        for (User user : usersList) {
            if (loginName.equals(user.getName()) && loginPass.equals(user.getPassword())) {
                user.setStatus(1);
                isRegistered = true;
            }
        }
        
        if (isRegistered) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", new String("true"));
            session.setAttribute("usersRegistered", usersList);
            session.setAttribute("currentUser", loginName);
            
            RequestDispatcher rd = request.getRequestDispatcher("ChatServlet");
            rd.forward(request, response);
            
        } else {
            response.sendRedirect("index.html");
        }
    }
}
