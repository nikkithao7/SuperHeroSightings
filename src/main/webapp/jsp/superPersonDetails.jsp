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
        <title>SuperPerson Details</title>
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
                    <h2>SuperPerson Details</h2>
                    <p>
                        <b>Name:</b>  <c:out value="${superPerson.name}"/>
                    </p>
                    <p>
                        <b>Description:</b> <c:out value="${superPerson.description}"/>
                    </p>
                    <p>
                        <b>Superpower:</b> <c:out value="${superPerson.superpower}"/>
                    </p>
                    <p>
                        <b>Organizations:</b> <c:if test="${empty superPerson.organizations}">--</c:if>
                            <c:forEach var="currentOrg" items="${superPerson.organizations}" varStatus="loop">
                                <c:out value="${currentOrg.name}"/><c:if test="${!loop.last}">,</c:if>
                            </c:forEach> 

                    </p>
                    <p>
                        <b>Locations seen:</b> <c:if test="${empty superPerson.sightings}">--</c:if>
                            <c:forEach var="currentSighting" items="${superPerson.sightings}" varStatus="loop">
                            <ol><c:out value="${currentSighting.location.name}"/> on <c:out value="${currentSighting.dateSeen}"/></ol>
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