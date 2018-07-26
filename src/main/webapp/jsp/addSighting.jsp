<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sighting Center - Add Sighting</title>
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
            <div class="col-md-12">
                <h2>Add New Sighting</h2>
                <sf:form class="form-horizontal" role="form" modelAttribute="sighting" 
                         action="createSighting" method="POST">
                    <div class="form-group">
                        <label for="add-sighting-date" class="col-md-2 control-label">Date Seen:</label>
                        <div class="col-md-10">
                            <input type="date" name="dateSeenInput" class="form-control" placeholder="YYYY-MM-dd" required/>
                            </div>
                        </div>
                        <spring:bind path="location">
                            <div class="form-group">
                                <label for="add-sighting-location-list" class="col-md-2 control-label">Location Seen:</label>
                                <div class="col-md-10">
                                    <select name="locationSelected">
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option value="${currentLocation.locationId}">
                                            <c:out value="${currentLocation.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </spring:bind>   

                    <spring:bind path="superperson">
                        <div class="form-group">
                            <label for="add-sighting-superperson-list" class="col-md-2 control-label">SuperPerson Seen:</label>
                            <div class="col-md-10">
                                <select name="superPersonSelected" multiple required>
                                    <c:forEach var="currentSuperPerson" items="${superPersonList}">
                                        <option value="${currentSuperPerson.superPersonId}">
                                            <c:out value="${currentSuperPerson.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </spring:bind>    

                </div>

                <div class="form-group">
                    <div class="col-md-offset-2 col-md-8">
                        <input type="submit" class="btn btn-default" value="Create Sighting"/>
                    </div>
                </div>
            </sf:form>

        </div>

    </div>            

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

