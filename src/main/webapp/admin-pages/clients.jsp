<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <div class="container">
        <div class="row justufy-content-between">
            <div class="col">
                <p>${client.getName()}</p>
            </div>
            <div class="col text-end">
                <p>${client.getEmail()}</p>
            </div>
            <div class="col">
                <form action="index-servlet">
                    <button class="btn-sm btn-primary" >Exit</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <th scope="row"><c:out value = "${client.getId()}"/></th>
                            <td><c:out value = "${client.getName()}"/></td>
                            <td><c:out value = "${client.getEmail()}"/></td>
                            <td>
                                <a class="btn btn-danger" href="admin-servlet?id=${client.getId()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</header>
</body>
</html>
