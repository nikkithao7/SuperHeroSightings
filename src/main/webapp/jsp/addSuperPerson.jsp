<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SuperPerson Center - Add SuperPerson</title>
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
            <div class="col-md-12">
                <h2>Add New SuperPerson</h2>
                <sf:form class="form-horizontal" role="form" modelAttribute="superperson" 
                         action="createSuperPerson" method="POST">
                    <div class="form-group">
                        <label for="add-superperson-name" class="col-md-2 control-label">SuperPerson Name:</label>
                        <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="name" placeholder="SuperPerson Name"/>
                            <sf:errors path="name" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-superperson-description" class="col-md-2 control-label">Description:</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="description" placeholder="Description of SuperPerson"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-superperson-superpower" class="col-md-2 control-label">Super Power(s):</label>
                            <div class="col-md-10">
                            <sf:input type="text" class="form-control" path="superpower" placeholder="Super Power(s)"/>
                            <sf:errors path="superpower" cssclass="error"></sf:errors>
                            <sf:hidden path="superPersonId"/>
                        </div>
                    </div>

                    <spring:bind path="organization">
                        <div class="form-group">
                            <label for="add-superperson-org-list" class="col-md-2 control-label">Organizations Associated With:</label>
                            <div class="col-md-10">
                                <select name="orgSelection" multiple>
                                    <option value="none" selected>None</option>
                                    <c:forEach var="currentOrg" items="${orgList}">
                                        <option value="${currentOrg.organizationId}">
                                            <c:out value="${currentOrg.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </spring:bind>    

                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create SuperPerson"/>
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

