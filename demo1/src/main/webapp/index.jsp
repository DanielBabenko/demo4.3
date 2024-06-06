<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:set var="points" value="${sessionScope.get(\"points\")}" /> --%>
<jsp:useBean id="points" class="model.UserAreaDatas" scope="session" />
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
     <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Проверка точки</title>
	<link rel="shortcut icon" href="images/amogus.png" type="image/x-icon" >
</head>
<body>
    <header>
        <h4><p>Автор: Бабенко Даниил Александрович, группа № P3234</p>
			<p>Вариант №3412</p></h4>
    </header>
    <main>
        <form id="form2" action="${pageContext.request.contextPath}/controller" method="GET">
            <label for="x">X:</label>
            <input type="text" id="x" name="x" placeholder="Введите X от -3 до 3" step="any" required>

        	<label for="y">Y:</label>
        	<select name="y" id="y">
                <option value="placeholder-like">Выберите Y от -3 до 5</option>
                <% for (int i = -3; i <= 5; i++) {
                    out.println("<option value='" + i + "'>" + i + "</option>");
                 } %>
            </select>

            <label for="r">R:</label>
             <div class="checkbox-group">
                <p>
               <% for (int i = 1; i <= 3; i++) {
                  out.println("<label><input type='radio' name='r' value='" + i + "'>" + i + "</label>");
                } %>
                </p>
                <p>
                <% for (int i = 4; i <= 5; i++) {
                   out.println("<label><input type='radio' name='r' value='" + i + "'>" + i + "</label>");
               } %>
                </p>
             </div>

            <button type="submit">Проверить</button>
        </form>
 <%--
        <div id="result" class="resulting">
                <table>
                <tr>
                <th> X </th>
                <th> Y </th>
                <th> R </th>
                <th> Результат </th>
                <th> Время выполнения </th>
                <th> Дата </th>
                </tr>
                        <% for (int i = 0; i < points.getAreaDataList().size(); i++) {
                            out.println("<tr>");
                            out.println("<td>" + points.getAreaDataList().get(i).getX() + "</td>");
                            out.println("<td>" + points.getAreaDataList().get(i).getY() + "</td>");
                            out.println("<td>" + points.getAreaDataList().get(i).getR() + "</td>");
                            out.println("<td>" + (points.getAreaDataList().get(i).getResult() ? "Попал" : "Не попал") + "</td>");
                            out.println("<td>" + points.getAreaDataList().get(i).getCalculationTime() + "</td>");
                            out.println("<td>" + points.getAreaDataList().get(i).getCalculatedAt() + "</td>");
                            out.println("</tr>");
                        } %>
                </table>
                </div>
                --%>
		<a href="https://media.tenor.com/OhLjIu0_Fq8AAAAd/among-us-amogus.gif"><img id="amogus" alt="Among Us" src = "images/Yellow.jpeg" class = "aboba"/></a>
        <canvas id="plot" width="500" height="500" align=></canvas>
		<a href="https://media.tenor.com/OhLjIu0_Fq8AAAAd/among-us-amogus.gif"><img id="dead" alt="Among Us" src = "images/lose.png" class = "aboba"/></a>

    </main>
    <script>const context = "${pageContext.request.contextPath}";</script>
    <script src="script2.js" charset="utf-8"></script>

</body>
</html>