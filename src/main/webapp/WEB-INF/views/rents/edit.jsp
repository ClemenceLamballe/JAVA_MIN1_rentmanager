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
                Modification de la reservation
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <form class="form-horizontal" method="post" >
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="vehicle_id" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="vehicle_id" name="vehicle_id">
                                            <c:forEach items="${vehicles}" var="vehicle">
                                                <c:choose>
                                                    <c:when test="${vehicle.id eq vehicleSelected.id}">
                                                        <option value="${vehicleSelected.id}" selected>${vehicleSelected.constructeur} ${vehicleSelected.modele}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${vehicle.id}">${vehicle.constructeur} ${vehicle.modele}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client_id" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client_id" name="client_id">
                                            <c:forEach items="${clients}" var="client">
                                                <c:choose>
                                                    <c:when test="${client.id eq clientSelected.id}">
                                                        <option value="${clientSelected.id}" selected>${clientSelected.nom} ${clientSelected.prenom}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${client.id}">${client.nom} ${client.prenom}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="start_date" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="start_date" name="start_date" required
                                               data-inputmask="'alias': 'dd/MM/yyyy'" data-mask value="${reservation.getDebut().format(formatter)}">
                                        <c:if test="${not empty requestScope.errorMessageStartDateFormat}">
                                            <div class="text-danger">${requestScope.errorMessageStartDateFormat}</div>
                                        </c:if>
                                        <c:if test="${not empty requestScope.errorMessageDateValid}">
                                            <div class="text-danger">${requestScope.errorMessageDateValid}</div>
                                        </c:if>
                                        <c:if test="${not empty requestScope.StartDateErrorMessage}">
                                            <div class="text-danger">${requestScope.StartDateErrorMessage}</div>
                                        </c:if>
                                        <c:if test="${not empty requestScope.ConsecutiveDaysErrorMessage}">
                                            <div class="text-danger">${requestScope.ConsecutiveDaysErrorMessage}</div>
                                        </c:if>
                                        <c:if test="${not empty requestScope.ConsecutiveDaysVehicleErrorMessage}">
                                            <div class="text-danger">${requestScope.ConsecutiveDaysVehicleErrorMessage}</div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end_date" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="end_date" name="end_date" required
                                               data-inputmask="'alias': 'dd/MM/yyyy'" data-mask value="${reservation.getFin().format(formatter)}">
                                        <c:if test="${not empty requestScope.errorMessageEndDateFormat}">
                                            <div class="text-danger">${requestScope.errorMessageEndDateFormat}</div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
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
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
</body>
</html>
