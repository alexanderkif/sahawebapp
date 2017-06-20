package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
        urlPatterns = {"/hello"}
)
public class Servlet1 extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String form = "<p><h2>Multiplication table</h2></p>\n" +
                "<form>\n" +
                "<p>Rows:</p>\n" +
                "<p><input name=\"rows\"</p>\n" +
                "<p>Cols:</p>\n" +
                "<p><input name=\"cols\"</p>\n" +
                "<p><input type=\"submit\" value=\"Submit\"></p>\n" +
                "</form>";
        int rows, cols;
        if(req.getParameter("rows")==null) rows=1;
        else rows=Integer.valueOf(req.getParameter("rows"));
        if(req.getParameter("cols")==null) cols=1;
        else cols=Integer.valueOf(req.getParameter("cols"));
//        PrintWriter out = resp.getWriter();
        ServletOutputStream out = resp.getOutputStream();
        out.write((form
                +"<p>Method: " + req.getMethod()
                +"<br>URL: "+ req.getRequestURL().toString()
                +"<br>sessionId: "+ req.getSession().getId()
                +"</p>\n"
                +"<p>Rows: "+ req.getParameter("rows")
                +"<br>Cols: "+ req.getParameter("cols")
                +"</p>\n"
//                +"<br>parameters: "+ req.getParameterMap().toString()
                +"<p><table border=1>"
                + IntStream.range(1, rows+1)
                .mapToObj(s-> "<tr>"+
                    IntStream.range(1, cols+1)
                    .mapToObj(s1->"<td>"+s*s1+"</td>")
                    .collect(Collectors.joining())
                    +"</tr>"
                )
                .collect(Collectors.joining())
                +"</table>"
                +"</p>\n").getBytes()
        );
        out.flush();
        out.close();
    }
}
