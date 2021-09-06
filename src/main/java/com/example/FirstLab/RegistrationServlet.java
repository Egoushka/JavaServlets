package com.example.FirstLab;

import com.example.FirstLab.managers.ClientManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registrationServlet", value = "/authorization/registration-servlet")
public class RegistrationServlet extends HttpServlet{
    ClientManager clientManager;
    public void init() {
        try {
            clientManager = new ClientManager();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String name = request.getParameter("name");
        if(!repeatPassword.equals(password) || password.equals(""))
        {
            getServletContext().getRequestDispatcher("/authorization/registration.jsp").forward(request, response);
            return;
        }
        var clientDto = clientManager.addClient(name, email, password);

        response.sendRedirect("../client-servlet");
    }
}
