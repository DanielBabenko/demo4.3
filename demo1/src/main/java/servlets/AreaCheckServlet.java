package servlets;

import jakarta.servlet.ServletException;
import model.AreaData;
import model.UserAreaDatas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;

@WebServlet(name = "areaCheckServlet", value = "/area-check")
public class AreaCheckServlet extends HttpServlet {

    boolean isValid;
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final long startExec = System.nanoTime();

        final String ctx = this.getServletContext().getContextPath();

        final String x = request.getParameter("x");
        final String y = request.getParameter("y");
        final String r = request.getParameter("r");

        final double new_x;
        final double new_y;
        final double new_r;

        try {
            new_x = Double.parseDouble(x);
            new_y = Double.parseDouble(y);
            new_r = Double.parseDouble(r);
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(400);
            return;
        }

        final double dx = roundCoordinates(new_x);
        final double dy = roundCoordinates(new_y);
        final double dr = roundCoordinates(new_r);


        final boolean result = checkArea(dx, dy, dr);

        final HttpSession currentSession = request.getSession();
        UserAreaDatas datas = (UserAreaDatas) currentSession.getAttribute("points");
        if (datas == null) {
            datas = new UserAreaDatas();
            currentSession.setAttribute("points", datas);
        }
        if (datas.getAreaDataList() == null)
            datas.setAreaDataList(new LinkedList<>());

        final long endExec = System.nanoTime();
        final long executionTime = endExec - startExec;
        final LocalDateTime executedAt = LocalDateTime.now();

        final AreaData data = new AreaData();
        data.setX(dx);
        data.setY(dy);
        data.setR(dr);
        data.setResult(result);
        data.setCalculationTime(executionTime);
        data.setCalculatedAt(executedAt);

        datas.getAreaDataList().addFirst(data);

        // Hello
//        response.setContentType("text/html;charset=UTF-8");
//        final PrintWriter out = response.getWriter();
//
//        out.println("<!DOCTYPE html>");
//        out.println("<html lang=\"en\">");
//        out.println("<head>");
//        out.println("    <meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
//        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//        out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
//        out.println("    <title>Calculation result</title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("    <header>");
//        out.println("        <h4>Результат вычислений</h4>");
//        out.println("    </header>");
//        out.println("    <div id=\"result\" class=\"resulting\">");
//        out.println("        <table>");
//        out.println("            <tr>");
//        out.println("                <th> X </th>");
//        out.println("                <th> Y </th>");
//        out.println("                <th> R </th>");
//        out.println("                <th> Результат </th>");
//        out.println("                <th> Время выполнения </th>");
//        out.println("                <th> Дата </th>");
//        out.println("            </tr>");
//        for (int i = 0; i < datas.getAreaDataList().size(); i++) {
//            out.println("            <tr>");
//            out.println("                <td>" + datas.getAreaDataList().get(i).getX() + "</td>");
//            out.println("                <td>" + datas.getAreaDataList().get(i).getY() + "</td>");
//            out.println("                <td>" + datas.getAreaDataList().get(i).getR() + "</td>");
//            out.println("                <td>" + (datas.getAreaDataList().get(i).getResult() ? "Попал" : "Не попал") + "</td>");
//            out.println("                <td>" + datas.getAreaDataList().get(i).getCalculationTime() + "</td>");
//            out.println("                <td>" + datas.getAreaDataList().get(i).getCalculatedAt() + "</td>");
//            out.println("            </tr>");
//        }
//        out.println("        </table>");
//        out.println("    </div>");
//        out.println("    <div id=\"results\" class=\"resulting\">");
//        out.println("        <p> Результат: " + (result ? "ТОЧНО В ЦЕЛЬ! ТЫ - НАСТОЯЩИЙ АБОБУС!" : "Нууууу... Промах.") + "</p>");
//        out.println("        <p><a href=\"" + ctx + "\">Вернуться на главную</a></p>");
//        out.println("    </div>");
//        out.println("</body>");
//        out.println("</html>");
//
//        out.close();

        getServletContext().getRequestDispatcher("/table.jsp").forward(request, response);
    }

    private boolean checkArea(final double x, final double y, final double r) {
       if (x>-3 && x<3 && y>=-3 && y<=5 && r >= 1 && r <= 5) {

           final boolean firstArea = x >= -r / 2 && x <= 0 && y <= 0 && y >= -r;
           final boolean secondArea = (x >= 0 && y <= 0 && (x * x + y * y <= r * r));
           final boolean thirdArea = (x <= 0 && y >= 0 && y - x <= r);

           final boolean result = firstArea || secondArea || thirdArea;

           return result;
       } else {
           isValid = false;
           return false;
       }
    }

    private double roundCoordinates(final double value) {
        double scale = Math.pow(10, 4);
        double result = Math.ceil(value * scale) / scale;
        return result;
    }
}
