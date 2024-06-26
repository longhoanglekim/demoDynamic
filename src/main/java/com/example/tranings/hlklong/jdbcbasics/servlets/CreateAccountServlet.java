package com.example.tranings.hlklong.jdbcbasics.servlets;

import com.example.tranings.hlklong.jdbcbasics.daos.AccountDAO;
import com.example.tranings.hlklong.jdbcbasics.utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet (urlPatterns = "/create-account-servlet")
public class CreateAccountServlet extends HttpServlet {
    private Connection connection;
    @Override
    public void init() throws ServletException {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int bal = Integer.parseInt(req.getParameter("balance"));

        if (isValidInput(firstname, lastname, bal)) {
            if (new AccountDAO().addAccount(firstname, lastname, bal, connection)) {
                req.setAttribute("message", "Account added successfully");
            } else {
                req.setAttribute("message", "Account not added");
            }
            resp.getWriter().println(req.getAttribute("message"));
        } else {
            resp.getWriter().println("Invalid input");
        }
    }

    private boolean isValidInput(String firstname, String lastname, int bal) {
        if (firstname == null || firstname.isEmpty()) {
            System.out.println("First name is null or empty");
            return false;
        }
        if (lastname == null || lastname.isEmpty()) {
            System.out.println("Last name is null or empty");
            return false;
        }
        if (bal <= 0) {
            System.out.println("Balance must be greater than 0");
            return false;
        }
        return true;
    }

    private void handleSQLException(SQLException e, HttpServletResponse resp) throws IOException {
        e.printStackTrace(); // Consider using a logger here
        resp.getWriter().println("An error occurred while creating the account. Please try again later.");
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
