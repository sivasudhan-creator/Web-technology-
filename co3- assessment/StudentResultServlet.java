import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentResultServlet")
public class StudentResultServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String regNo = request.getParameter("regNo");
        String mark1String = request.getParameter("mark1");
        String mark2String = request.getParameter("mark2");
        String mark3String = request.getParameter("mark3");

        if (name == null || name.trim().isEmpty() ||
            regNo == null || regNo.trim().isEmpty() ||
            mark1String == null || mark1String.trim().isEmpty() ||
            mark2String == null || mark2String.trim().isEmpty() ||
            mark3String == null || mark3String.trim().isEmpty()) {

            out.println("<h2>Error</h2>");
            out.println("<p>All fields are required.</p>");
            return;
        }

        try {

            int mark1 = Integer.parseInt(mark1String);
            int mark2 = Integer.parseInt(mark2String);
            int mark3 = Integer.parseInt(mark3String);

            if (mark1 < 0 || mark1 > 100 ||
                mark2 < 0 || mark2 > 100 ||
                mark3 < 0 || mark3 > 100) {

                out.println("<h2>Invalid Marks</h2>");
                out.println("<p>Marks must be between 0 and 100.</p>");
                return;
            }

            int total = mark1 + mark2 + mark3;

            double average = total / 3.0;

            int highest = Math.max(mark1, Math.max(mark2, mark3));

            String status;

            if (mark1 >= 40 &&
                mark2 >= 40 &&
                mark3 >= 40) {

                status = "PASS";

            } else {

                status = "FAIL";
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Result</title>");

            out.println("<style>");
            out.println("body { font-family: Arial; background:#f2f5f9; padding:40px; }");
            out.println(".result { width:500px; margin:auto; background:white; padding:25px; border-radius:10px; box-shadow:0 4px 12px rgba(0,0,0,0.15); }");
            out.println("h2 { text-align:center; color:#1f4e79; }");
            out.println("p { font-size:17px; }");
            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='result'>");

            out.println("<h2>Student Result</h2>");

            out.println("<p><b>Name:</b> " + name + "</p>");
            out.println("<p><b>Register Number:</b> " + regNo + "</p>");
            out.println("<p><b>Subject 1:</b> " + mark1 + "</p>");
            out.println("<p><b>Subject 2:</b> " + mark2 + "</p>");
            out.println("<p><b>Subject 3:</b> " + mark3 + "</p>");
            out.println("<p><b>Total:</b> " + total + "</p>");
            out.println("<p><b>Average:</b> " + String.format("%.2f", average) + "</p>");
            out.println("<p><b>Highest Mark:</b> " + highest + "</p>");
            out.println("<p><b>Status:</b> " + status + "</p>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException e) {

            out.println("<h2>Invalid Input</h2>");
            out.println("<p>Please enter valid numeric marks.</p>");
        }
    }
}