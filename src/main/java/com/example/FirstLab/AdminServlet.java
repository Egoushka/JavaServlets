package com.example.FirstLab;

import com.example.FirstLab.managers.ClientManager;
import com.example.FirstLab.managers.IClientManager;
import com.example.FirstLab.models.DTO.ClientDto;
import com.example.FirstLab.qualifiers.ClientDaoQualifier;
import com.example.FirstLab.qualifiers.ClientManagerQualifier;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "adminServlet", value = "/admin-servlet")

public class AdminServlet extends HttpServlet {
    @Inject
    @ClientManagerQualifier
    IClientManager clientManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var param = request.getParameter("id");
        if(param != null)
            doDelete(request, response);
        HttpSession session = request.getSession();

        ClientDto clientDto = (ClientDto) session.getAttribute("client");

        var clients = clientManager.getAll();

        request.setAttribute("clients", clients);

        getServletContext().getRequestDispatcher("/admin-pages/clients.jsp").forward(request, response);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id =Integer.parseInt( request.getParameter("id"));
        if(id > 0){
            clientManager.delete(id);

        }
    }
}
