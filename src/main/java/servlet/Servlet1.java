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

        int n;
        String str = req.getParameterMap().toString();
        if(req.getParameter("n")==null)
            n=1;
        else n=Integer.valueOf(req.getParameter("n"));
//        PrintWriter out = resp.getWriter();
        ServletOutputStream out = resp.getOutputStream();
        out.write(("<h2>From Servlet1</h2><br>Give me \"?n= \" parameter"
                +"<br>Method: " + req.getMethod()
                +"<br>URL: "+ req.getRequestURL().toString()
                +"<br>pathInfo: "+ req.getPathInfo()
                +"<br>sessionId: "+ req.getSession().getId()
                +"<br>parameters: "+ str
                +"<br><br><table border=1>"
                + IntStream.range(1, n)
                .mapToObj(s-> "<tr>"+
                        IntStream.range(1,10)
                        .mapToObj(s1->"<td>"+s*s1+"</td>")
                        .collect(Collectors.joining())
                        +"</tr>"
                )
                .collect(Collectors.joining())
                +"</table>").getBytes()
        );
        out.flush();
        out.close();
    }
}
