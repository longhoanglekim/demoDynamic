package com.example.tranings.hlklong.jdbcbasics.servlets;

import com.example.tranings.hlklong.jdbcbasics.daos.AccountDAO;
import com.example.tranings.hlklong.jdbcbasics.utils.DatabaseConnection;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet(urlPatterns = "/read-account-servlet")
public class ReadAccountServlet extends HttpServlet {
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

        try {
            ResultSet resultSet = new AccountDAO().getAccounts(connection);
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<h1>Read Account Servlet</h1>");
            resp.getWriter().println("<table border=1>");
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<th>Account ID</th>");
            resp.getWriter().println("<th>First Name</th>");
            resp.getWriter().println("<th>Last Name</th>");
            resp.getWriter().println("<th>Balance</th>");
            resp.getWriter().println("</tr>");
            while (resultSet.next()) {
                resp.getWriter().println("<tr>");
                resp.getWriter().println("<td>" + resultSet.getInt("accno") + "</td>");
                resp.getWriter().println("<td>" + resultSet.getString("firstname") + "</td>");
                resp.getWriter().println("<td>" + resultSet.getString("lastname") + "</td>");
                resp.getWriter().println("<td>" + resultSet.getInt("bal") + "</td>");
                resp.getWriter().println("</tr>");
            }
            resp.getWriter().println("</table>");
            resp.getWriter().println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
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
