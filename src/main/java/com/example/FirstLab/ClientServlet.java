package com.example.FirstLab;

import com.example.FirstLab.managers.PaymentManager;
import com.example.FirstLab.models.dto.ClientDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "clientServlet", value = "/client-servlet")
public class ClientServlet extends HttpServlet {

    PaymentManager paymentManager ;
    public ClientServlet() throws SQLException {
        paymentManager = new PaymentManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var param = req.getParameter("id");
        if(param != null)
            doDelete(req, resp);
        HttpSession session = req.getSession();

        ClientDto clientDto = (ClientDto) session.getAttribute("client");
        var payments  =paymentManager.getClientPayments(clientDto.getId());

        req.setAttribute("payments", payments);

        getServletContext().getRequestDispatcher("/client-pages/user-home-page.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id =Integer.parseInt( request.getParameter("id"));
        if(id > 0){
            paymentManager.delete(id);

        }
    }
}
