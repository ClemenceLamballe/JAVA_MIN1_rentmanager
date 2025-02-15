<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Modifier les données de la voiture
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">

                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="constructeur" class="col-sm-2 control-label">Marque</label>


                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="constructeur" name="constructeur" placeholder="Marque" value="${vehicle.constructeur}" required>
                                        <c:if test="${not empty requestScope.VehicleConstructeurErrorMessage}">
                                            <span class="text-danger">${requestScope.VehicleConstructeurErrorMessage}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="modele" class="col-sm-2 control-label">Modele</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" value="${vehicle.modele}" required>
                                        <c:if test="${not empty requestScope.VehicleModeleErrorMessage}">
                                            <span class="text-danger">${requestScope.VehicleModeleErrorMessage}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="nb_places" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="nb_places" name="nb_places" placeholder="Nombre de places" value="${vehicle.nb_places}" required>
                                        <c:if test="${not empty requestScope.VehicleNbPlacesErrorMessage}">
                                            <span class="text-danger">${requestScope.VehicleNbPlacesErrorMessage}</span>
                                        </c:if>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" >Modifier</button>


                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
