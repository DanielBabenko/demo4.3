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
        <h4><p>Я устал</p>
			<p>Хочу домой</p></h4>
    </header>
    <main>
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
                            out.println("            <tr>");
                            out.println("                <td>" + points.getAreaDataList().get(i).getX() + "</td>");
                            out.println("                <td>" + points.getAreaDataList().get(i).getY() + "</td>");
                            out.println("                <td>" + points.getAreaDataList().get(i).getR() + "</td>");
                            out.println("                <td>" + (points.getAreaDataList().get(i).getResult() ? "Попал" : "Не попал") + "</td>");
                            out.println("                <td>" + points.getAreaDataList().get(i).getCalculationTime() + "</td>");
                            out.println("                <td>" + points.getAreaDataList().get(i).getCalculatedAt() + "</td>");
                            out.println("            </tr>");
                        } %>
                </table>
                </div>
                <div id="results" class="resulting">
                <p> Результат: <%= (points.getAreaDataList().getFirst().getResult() ? "ТОЧНО В ЦЕЛЬ! ТЫ - НАСТОЯЩИЙ АБОБУС!" : "Нууууу... Промах.") %> </p>
                <p><a href="/web2-1.0-SNAPSHOT">Вернуться на главную</a></p>
            </div>
    </main>
    <script>const context = "${pageContext.request.contextPath}";</script>
    <script src="script2.js" charset="utf-8"></script>

</body>
</html>