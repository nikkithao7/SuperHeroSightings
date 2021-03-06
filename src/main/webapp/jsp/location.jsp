<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Location Center</title>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superperson">SuperPerson Center</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organization Center</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location">Location Center</a></li>                
                </ul>    
            </div>

            <!--Main page content will start here!-->            
            <div class="row">
                <div class="col-md-12">
                    <h2>All Locations</h2>
                    <table id="locationTable" class="table table-hover">
                        <tr>
                            <th width="20%">Location Name</th>
                            <th width="20%">Description</th>
                            <th width="15%">Street Address</th>
                            <th width="10%">City</th>
                            <th width="5%">State</th>
                            <th width="5%">Zipcode</th>
                            <th width="10%">Latitude</th>
                            <th width="10%">Longitude</th>
                            <th width="10%"></th>
                            <th width="10%"></th>

                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <c:out value="${currentLocation.name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.streetInfo}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.city}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.state}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.zipcode}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.latitude}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.longitude}"/>
                                </td>
                                <td>
                                    <a href="displayEditLocation?locationId=${currentLocation.locationId}">Edit</a>
                                </td>
                                <td>
                                    <a href="deleteLocation?locationId=${currentLocation.locationId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    
                    <form class="form-horizontal" 
                          role="form" method="GET" 
                          action="displayAddLocation">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" style="float: right;" value="Add New Location"/>
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

