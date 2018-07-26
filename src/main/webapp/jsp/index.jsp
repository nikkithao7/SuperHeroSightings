<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O Sighting Tracker</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O Sighting Tracker</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sighting Center</a></li>                    
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superperson">SuperPerson Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organization Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Location Center</a></li>
                </ul>    
            </div>
            <h2>About the application</h2>
            <p>Info about application will go here.</p>

            <h2>Latest Sightings</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="10%">ID</th>
                            <th width="20%">Date Seen</th>
                            <th width="30%">Location Name</th>
                        </tr>

                        <c:forEach var="currentSighting" items="${mostRecentSightings}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.sightingId}"/></a>
                                    
                                </td>
                                <td>
                                    <c:out value="${currentSighting.dateSeen}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.location.name}"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

