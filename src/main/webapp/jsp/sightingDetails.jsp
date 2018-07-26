<%-- 
    Document   : contactDetails
    Created on : Jun 13, 2018, 9:03:49 PM
    Author     : nthao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sighting Details</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting">Sighting Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superperson">SuperPerson Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organization Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Location Center</a></li>                
                </ul>    
            </div>
            <!--Main page content will start here!-->   
            <div class="row">
                <div class="col-md-12">
                    <h2>Sighting Details</h2>
                    <p>
                        Sighting ID:  <c:out value="${sighting.sightingId}"/>
                    </p>
                    <p>
                        Date: <c:out value="${sighting.dateSeen}"/>
                    </p>
                    <p>
                        Location: <c:out value="${sighting.location.name}"/>
                    </p>
                    <p>
                        SuperPerson(s) seen: <c:if test="${empty superPersonsAtSighting}">--</c:if>
                            <c:forEach var="personAtSighting" items="${superPersonsAtSighting}" varStatus="loop">
                            <c:out value="${personAtSighting.name}"/><c:if test="${!loop.last}">,</c:if>
                            </c:forEach> 
                    </p>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>