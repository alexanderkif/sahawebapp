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
        name = "Servlet1",
        urlPatterns = {"/multi"}
)
public class Servlet1 extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        try {
            resp.setContentType("text/html;charset=utf-8");
            String form = "<div align=\"center\"><p><h2>Multiplication table</h2></p>\n" +
                    "<form>\n" +
                    "<p>Rows:<br><input name=\"rows\" value="+req.getParameter("rows")+"></p>\n" +
                    "<p>Cols:<br><input name=\"cols\" value="+req.getParameter("cols")+"></p>\n" +
                    "<p><input type=\"submit\" value=\"Submit\"></p>\n" +
                    "</form>";
            int rows, cols;
            if (req.getParameter("rows") == null) rows = 1;
            else rows = Integer.valueOf(req.getParameter("rows"));
            if (req.getParameter("cols") == null) cols = 1;
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
                    + IntStream.range(1, rows + 1)
                        .mapToObj(row -> "<tr>"
                            + IntStream.range(1, cols + 1)
                                    .mapToObj(col -> "<td align=\"center\">" + row * col + "</td>")
                                    .collect(Collectors.joining())
                            + "</tr>"
                    )
                    .collect(Collectors.joining())
                    + "</table>"
                    + "</p></div>").getBytes()
            );
        }catch (Exception e){
            out.write(("Something go wrong...<br>Exception:<br>"+e).getBytes());
        }
        out.flush();
        out.close();
    }
}
