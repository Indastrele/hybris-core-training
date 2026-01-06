<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Producer</title>
</head>
<body>
  <a href="${pageContext.request.contextPath}/">&larr; Back to all bands</a>
  <br>
  <h1>${producer.name} ${producer.surname} ${producer.patronymic}</h1>
  <hr>
  <h3>Description</h3>
  <p>${producer.description}</p>
  <hr>
  <h3>Bands</h3>
  <ul>
    <c:forEach var="band" items="${producer.bands}">
      <li>
        <a href="https://localhost:9002/bands/${band.id}">${band.name}</a>
      </li>
    </c:forEach>
  </ul>
</body>
</html>
