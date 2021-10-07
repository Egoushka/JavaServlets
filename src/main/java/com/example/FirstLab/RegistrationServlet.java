package com.example.FirstLab;

import com.example.FirstLab.managers.ClientManager;
import com.example.FirstLab.managers.IClientManager;
import com.example.FirstLab.qualifiers.ClientManagerQualifier;
import com.example.FirstLab.qualifiers.PaymentManagerQualifier;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registrationServlet", value = "/authorization/registration-servlet")
public class RegistrationServlet extends HttpServlet{
    @Inject
    @ClientManagerQualifier
    IClientManager clientManager;
    @Inject
    public RegistrationServlet(@ClientManagerQualifier IClientManager clientManager){
        this.clientManager = clientManager;
    }
    public void init() {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String name = request.getParameter("name");
        HttpSession session = request.getSession();
        if(!repeatPassword.equals(password) || password.equals(""))
        {
            getServletContext().getRequestDispatcher("/authorization/registration.jsp").forward(request, response);
            return;
        }
        var clientDto = clientManager.addClient(name, email, password);
        session.setAttribute("client", clientDto);

        response.sendRedirect("../index.jsp");
    }
}
