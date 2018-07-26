<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Location Center - Add Location</title>
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
            <div class="col-md-12">
                <h2>Add New Location</h2>
                <sf:form class="form-horizontal" role="form" modelAttribute="location" 
                         action="createLocation" method="POST">
                    <div class="form-group">
                        <label for="add-location-name" class="col-md-2 control-label">Location Name:</label>
                        <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="name" placeholder="Location Name"/>
                            <sf:errors path="name" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-description" class="col-md-2 control-label">Description:</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="description" placeholder="Description of Location"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-street" class="col-md-2 control-label">Street Address:</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="streetInfo" placeholder="123 Main St."/>
                            <sf:errors path="streetInfo" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-city" class="col-md-2 control-label">City:</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="city" placeholder="City"/>
                            <sf:errors path="city" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-state" class="col-md-2 control-label">State:</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="state" placeholder="State"/>
                            <sf:errors path="state" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-zipcode" class="col-md-2 control-label">Zipcode:</label>
                            <div class="col-md-10">
                            <sf:input type="number" class="form-control" path="zipcode" placeholder="Zipcode"/>
                            <sf:errors path="zipcode" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-latitude" class="col-md-2 control-label">Latitude:</label>
                            <div class="col-md-10">
                            <sf:input type="decimal" class="form-control" path="latitude" placeholder="00.000000"/>
                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-longitude" class="col-md-2 control-label">Longitude:</label>
                            <div class="col-md-10">
                            <sf:input type="decimal" class="form-control" path="longitude" placeholder="000.000000"/>
                            <sf:errors path="longitude" cssclass="error"></sf:errors>
                            <sf:hidden path="locationId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Location"/>
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

