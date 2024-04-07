<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <div class="content-wrapper">
    <section class="content">

      <div class="row">
        <div class="col-md-3">

          <div class="box box-primary">
            <div class="box-body box-profile">
              <h3 class="profile-username text-center"> Reservation #${reservation.id}</h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Debut</b> <a class="pull-right">${reservation.debut}</a>
                </li>
                <li class="list-group-item">
                  <b>Fin</b> <a class="pull-right">${reservation.fin}</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#users" data-toggle="tab">Client</a></li>
              <li><a href="#cars" data-toggle="tab">Voiture</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="users">
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Nom</th>
                      <th>Prenom</th>
                      <th>Naissance</th>
                      <th>Email</th>
                    </tr>
                      <tr>
                        <td>${client.id}</td>
                        <td>${client.nom}</td>
                        <td>${client.prenom}</td>
                        <td>${client.naissance}</td>
                        <td>${client.email}</td>
                      </tr>

                  </table>
                </div>
              </div>
              <div class="tab-pane" id="cars">
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Modele</th>
                      <th>Constructeur</th>
                      <th style=>Nombre de places</th>
                    </tr>
                      <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.modele}</td>
                        <td>${vehicle.constructeur}</td>
                        <td>${vehicle.nb_places}</td>
                      </tr>
                  </table>
                </div>
              </div>
            </div>
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
