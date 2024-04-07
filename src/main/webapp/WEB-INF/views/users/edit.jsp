<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Modification du client
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <form class="form-horizontal" method="post" >
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom" value="${client.nom}">
                                        <% if (request.getAttribute("LastNameErrorMessage") != null) { %>
                                        <span class="text-danger"><%= request.getAttribute("LastNameErrorMessage") %></span>
                                        <% } %>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom" value="${client.prenom}">
                                        <% if (request.getAttribute("NameErrorMessage") != null) { %>
                                        <span class="text-danger"><%= request.getAttribute("NameErrorMessage") %></span>
                                        <% } %>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${client.email}">
                                        <% if (request.getAttribute("EmailErrorMessage") != null) { %>
                                        <span class="text-danger"><%= request.getAttribute("EmailErrorMessage") %></span>
                                        <% } %>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="birthdate" class="col-sm-2 control-label">Birthdate</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="Birthdate" value="${client.naissance}">
                                        <% if (request.getAttribute("BirthdateErrorMessage") != null) { %>
                                        <span class="text-danger"><%= request.getAttribute("BirthdateErrorMessage") %></span>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
