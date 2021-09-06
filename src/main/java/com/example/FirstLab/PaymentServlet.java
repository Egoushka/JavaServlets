package com.example.FirstLab;

import com.example.FirstLab.managers.ClientManager;
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

@WebServlet(name ="paymentServlet", value = "/payment-servlet")
public class PaymentServlet extends HttpServlet {
    PaymentManager paymentManager;
    public void init() {
        try {
            paymentManager = new PaymentManager();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/client-pages/add-new-payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ClientDto clientDto = (ClientDto) session.getAttribute("client");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String text = request.getParameter("text");

        paymentManager.add(amount, clientDto.getId(), text);

        response.sendRedirect("client-servlet");
    }

}