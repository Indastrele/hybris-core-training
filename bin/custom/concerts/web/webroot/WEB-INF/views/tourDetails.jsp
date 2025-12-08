<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tour</title>
</head>
<body>
    <a onclick="history.back()">&larr; Back to the band</a>
    <h1>${tour.tourName}</h1>
    <hr>
    <p>${tour.description}</p>
    <hr>
    <h3>Tours</h3>
    <ul>
        <c:forEach var="concert" items="${tour.concerts}">
            <li>
                <div style="border: lightgray 1px dashed;">
                    <p>Venue: <b>${concert.venue}</b></p>
                    <br>
                    <p>Date: <b>${concert.date}</b></p>
                    <br>
                    <p>Type: ${concert.type}</p>
                </div>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
