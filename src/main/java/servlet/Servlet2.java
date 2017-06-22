package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by tln on 22.06.2017.
 */
@WebServlet(
        name = "Servlet2",
        urlPatterns = {"/safe"}
)
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("sid");
        Boolean login = false;
        if (sid==null || Objects.equals(sid, "")){
            out.println("<div align=\"center\"><p><h3>Who are you?</h3></p>\n" +
                    "<form>\n" +
                    "<p>Name:<br><input name=\"name\"></p>\n" +
                    "<p>Password:<br><input name=\"pass\"></p>\n" +
                    "<p><input type=\"submit\" value=\"Submit\"></p>\n" +
                    "</form>" +
                    "<br><p align=\"center\"><a href=\"/\">to main page</a></p>");
            return;
        }

        String name = request.getParameter("name");
        if(name!=null && !Objects.equals(name, "")){
        }

    }
}
