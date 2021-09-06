package com.example.FirstLab;

import com.example.FirstLab.dao.ClientDao;
import com.example.FirstLab.managers.ClientManager;
import com.example.FirstLab.models.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name = "loginServlet", value = "/login-servlet")

public class LoginServlet extends HttpServlet {
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
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        var clientDto = clientManager.getClient(email, password);
        if(clientDto == null){
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        session.setAttribute("client", clientDto);
        if(!clientDto.getEmail().equals("yehor@gmail.com"))
            response.sendRedirect("client-servlet");
        else
            response.sendRedirect("admin-servlet");

    }

}
