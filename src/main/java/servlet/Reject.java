package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by tln on 22.06.2017.
 */
@WebServlet(
        name = "Reject",
        urlPatterns = {"/reject"}
)
public class Reject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String text = request.getParameter("text");
        String letters = request.getParameter("letters");
        if (text==null || letters==null){
            out.println("<div align=\"center\"><p><h2>Reject letters from text</h2></p>\n" +
                    "<form >\n" +
                    "<p>Text:<br><textarea name=\"text\" rows=\"10\" cols=\"45\"></textarea></p>\n" +
                    "<p>Letters to reject:<br><input name=\"letters\"></p>\n" +
                    "<p><input type=\"submit\" value=\"Submit\"></p>\n" +
                    "</form>" +
                    "<br><p align=\"center\"><a href=\"/\">to main page</a></p></div>");
        }
        else {
            String[] splittedText = text.split("");
            String result = Arrays.stream(splittedText)
                    .filter(t-> !letters.contains(t))
                    .collect(Collectors.joining());

            out.println("<div align=\"center\"><p><h2>Reject letters from text</h2></p>\n<br>" +
                    "<p>text: " + text + "</p>" +
                    "<p>letters: " + letters + "</p><br><br><br><div>" +
                    "<table  border=1px bordercolor=\"#ccc\" cellspacing=0 cellpadding=25>" +
                    "<tr><td align=\"center\">result:<br>" +
                    result + "</td></tr></table></div>" +
                    "<br><br><p align=\"center\"><a href=\"/\">to main page</a></p></div>");
        }
    }
}
