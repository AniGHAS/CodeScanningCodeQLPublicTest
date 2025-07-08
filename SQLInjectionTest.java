import java.sql.*;
import javax.servlet.http.*;

public class VulnerableLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "user", "pass");
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.getWriter().println("Login successful!");
            } else {
                response.getWriter().println("Invalid credentials.");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
