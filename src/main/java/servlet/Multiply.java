package servlet;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Multiply",
        urlPatterns = {"/multi"}
)
public class Multiply extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("text/html;charset=utf-8");
        try {
            String form = "<div align=\"center\"><p><h2>Multiplication table</h2></p>\n" +
                    "<form>\n" +
                    "<p>Rows:<br><input name=\"rows\"></p>\n" +
                    "<p>Cols:<br><input name=\"cols\"></p>\n" +
                    "<p><input type=\"submit\" value=\"Submit\"></p>\n" +
                    "</form>";
            int rows, cols;
            if (req.getParameter("rows") == null || req.getParameter("rows") == "") rows = 1;
            else rows = Integer.valueOf(req.getParameter("rows"));
            if (req.getParameter("cols") == null || req.getParameter("cols") == "") cols = 1;
            else cols = Integer.valueOf(req.getParameter("cols"));
            out.write((form
                    + "<p>Method: " + req.getMethod()
                    + "<br>URL: " + req.getRequestURL().toString()
                    + "<br>sessionId: " + req.getSession().getId()
                    + "</p>\n"
                    + "<p>Rows: " + req.getParameter("rows")
                    + "<br>Cols: " + req.getParameter("cols")
                    + "</p>\n"
                    + "<p><table border=3px bordercolor=\"#888888\" cellspacing=0 cellpadding=3>"
                    + "<tr>"
                    + IntStream.range(1, cols + 1)
                        .mapToObj(col -> "<td align=\"center\"><b>" + col + "</b></td>")
                        .collect(Collectors.joining())
                    + "</tr>"
                    + IntStream.range(2, rows + 1)
                        .mapToObj(row -> "<tr>"
                            +"<td align=\"center\"><b>" + row + "</b></td>"
                            + IntStream.range(2, cols + 1)
                                    .mapToObj(col -> "<td align=\"center\">" + row * col + "</td>")
                                    .collect(Collectors.joining())
                            + "</tr>"
                    )
                    .collect(Collectors.joining())
                    + "</table>"
                    + "</p></div><br><p align=\"center\"><a href=\"/\">to main page</a></p>").getBytes()
            );
        }catch (Exception e){
            out.write(("<h3>It's not a number.</h3>Exception:<br>"+e+"<h3><a href=\"/multi\">go back</a></h3>").getBytes());
        }
        out.flush();
        out.close();
    }
}
