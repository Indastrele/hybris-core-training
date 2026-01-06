<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Band</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/">&larr; Back to all bands</a>
    <br>
    <p><img src="${band.imageURL}"/></p>
    <h1>${band.name}</h1>
    <hr>
    <p>${band.description}</p>
    <hr>
    <p>Album sales: <b>${band.albumsSold}</b></p>
    <hr>
    <h3>Members:</h3>
    <ul>
        <c:forEach var="member" items="${band.members}">
            <li>
                ${member} - ${band.membersToInstruments[member]}
            </li>
        </c:forEach>
    </ul>
    <hr>
    <h3>Tours</h3>
    <ul>
        <c:forEach var="tour" items="${band.tours}">
            <li><a href="https://localhost:9002/concerts/${tour.id}">${tour.tourName}</a></li>
        </c:forEach>
    </ul>
    <hr>
    <h3>Producers</h3>
    <ul>
        <c:forEach var="producer" items="${band.producers}">
            <li><a href="https://localhost:9002/bands/producers/${producer.id}">${producer.name} ${producer.surname} ${producer.patronymic}</a></li>
        </c:forEach>
    </ul>
</body>
</html>