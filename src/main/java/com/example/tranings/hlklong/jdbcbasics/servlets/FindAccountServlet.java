package com.example.tranings.hlklong.jdbcbasics.servlets;

import com.example.tranings.hlklong.jdbcbasics.daos.AccountDAO;
import com.example.tranings.hlklong.jdbcbasics.models.Account;
import com.example.tranings.hlklong.jdbcbasics.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet (urlPatterns = "/find-account-servlet")
public class FindAccountServlet extends HttpServlet {
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("findAccount.jsp");
        if (req.getAttribute("account") != null) {
            System.out.println("Removing account attribute");
            req.removeAttribute("account");
        }
        if (req.getParameter("accno") == null || req.getParameter("accno").isEmpty()) {
            System.out.println("Account number is null or empty");
        }
        if (req.getAttribute("account") == null) {
            System.out.println("Account attribute is null");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("accno") != null && !request.getParameter("accno").isEmpty()) {
            int accno = Integer.parseInt(request.getParameter("accno"));
            Account account = null;
            try {
                account = new AccountDAO().getAccountById(connection, accno);
                if (account != null) {
                    request.setAttribute("account", account);
                    request.getRequestDispatcher("findAccount.jsp").forward(request, response);
                } else {
                    response.sendRedirect("findAccount.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("findAccount.jsp");
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
