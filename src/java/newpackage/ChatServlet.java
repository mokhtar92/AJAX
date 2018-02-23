/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static newpackage.LoginServlet.usersList;

/**
 *
 * @author Ahmed_Mokhtar
 */
@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {

    public static ArrayList<User> usersList;
    public static ArrayList<Message> allMessages = new ArrayList<>();
    private String currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String message = request.getParameter("msg");

        if (message != null) {
            allMessages.add(new Message(currentUser, message));
        }

        Gson gson = new Gson();
        out.write(gson.toJson(allMessages));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.html");

        } else {
            String loggedIn = (String) session.getAttribute("loggedIn");
            if (!loggedIn.equals("true")) {
                response.sendRedirect("index.html");
            }
            usersList = (ArrayList<User>) session.getAttribute("usersRegistered");
            currentUser = (String) session.getAttribute("currentUser");
            RequestDispatcher rd = request.getRequestDispatcher("/chat.html");
            rd.forward(request, response);
        }
    }

    private void ChangeToOfflineStatus(ArrayList<User> users, String name) {

        for (User user : users) {
            if (name.equals(user.getName())) {
                user.setStatus(0);
            }
        }
    }
}
