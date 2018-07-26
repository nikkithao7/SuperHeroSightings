<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sighting Center</title>
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
                    <h2>All Sightings</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="10%">ID</th>
                            <th width="20%">Date Seen</th>
                            <th width="30%">Location Name</th>
                            <th width="20%"></th>
                            <th width="20%"></th>

                        </tr>


                        <c:forEach var="currentSighting" items="${spSightList}">
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
                                <td>
                                    <a href="displayEditSighting?sightingId=${currentSighting.sightingId}">Edit</a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>


                    </table>

                    <form class="form-horizontal" 
                          role="form" method="GET" 
                          action="displayAddSighting">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" style="float: right;" value="Add New Sighting"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>            

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
