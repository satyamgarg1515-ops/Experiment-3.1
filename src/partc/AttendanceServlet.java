package partc;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nimbusdb", "root", "1234");

            PreparedStatement ps = con.prepareStatement("INSERT INTO Attendance VALUES(?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();

            out.println("<h3>Attendance recorded successfully!</h3>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
