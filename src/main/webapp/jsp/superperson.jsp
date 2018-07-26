<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SuperPerson Center</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O Sighting Tracker</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sighting Center</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/superperson">SuperPerson Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organization Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Location Center</a></li>                
                </ul>    
            </div>
            <!--Main page content will start here!-->            
            <div class="row">
                <div class="col-md-12">
                    <h2>All SuperPeople</h2>
                    <table id="superPersonTable" class="table table-hover">
                        <tr>
                            <th width="20%">SuperPerson Name</th>
                            <th width="25%">Description</th>
                            <th width="25%">Super Power(s)</th>
                            <th width="20%">Organization(s)</th>
                            <th width="5%"></th>
                            <th width="5%"></th>

                        </tr>
                        <c:forEach var="currentSuperPerson" items="${superPersonList}">
                            <tr>
                                <td>
                                    <a href="displaySuperPersonDetails?superPersonId=${currentSuperPerson.superPersonId}">
                                        <c:out value="${currentSuperPerson.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.superpower}"/>
                                </td>
                                <td>
                                    <c:if test="${empty currentSuperPerson.organizations}">--</c:if>
                                    <c:forEach var="currentOrg" items="${currentSuperPerson.organizations}" varStatus="loop">
                                        <c:out value="${currentOrg.name}"/><c:if test="${!loop.last}">,</c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="displayEditSuperPerson?superPersonId=${currentSuperPerson.superPersonId}">Edit</a>
                                </td>
                                <td>
                                    <a href="deleteSuperPerson?superPersonId=${currentSuperPerson.superPersonId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <form class="form-horizontal" 
                          role="form" method="GET" 
                          action="displayAddSuperPerson">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" style="float: right;" value="Add New SuperPerson"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>            

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

