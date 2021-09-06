<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap-5.1.0-dist/css/bootstrap.min.css">
    <script src="bootstrap-5.1.0-dist/js/bootstrap.min.js"></script>
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
                        <tfoot>
                            <tr>
                                <td>
                                    <form action="payment-servlet" method="get">
                                        <button  type="submit" class="btn-sm btn-primary col-3">
                                            Add new payment
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </tfoot>
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Text</th>
                                <th scope="col">Amount</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="payment" items="${payments}">
                                <tr>
                                    <th scope="row"><c:out value = "${payment.getId()}"/></th>
                                    <td><c:out value = "${payment.getText()}"/></td>
                                    <td><c:out value = "${payment.getAmount()}"/></td>
                                    <td>
                                        <a class="btn btn-danger" href="client-servlet?id=${payment.getId()}">Delete</a>
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
