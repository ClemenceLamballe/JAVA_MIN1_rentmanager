/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-04-03 14:58:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class details_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1584087032000L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1584087032000L));
    _jspx_dependants.put("/WEB-INF/views/common/head.jsp", Long.valueOf(1584087032000L));
    _jspx_dependants.put("/WEB-INF/views/common/sidebar.jsp", Long.valueOf(1709566513553L));
    _jspx_dependants.put("/WEB-INF/views/common/js_imports.jsp", Long.valueOf(1584087032000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <title>Rent Manager</title>\n");
      out.write("    <!-- Tell the browser to be responsive to screen width -->\n");
      out.write("    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\n");
      out.write("    <!-- Bootstrap 3.3.7 -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/bootstrap/dist/css/bootstrap.min.css\">\n");
      out.write("    <!-- Font Awesome -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/font-awesome/css/font-awesome.min.css\">\n");
      out.write("    <!-- Ionicons -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/Ionicons/css/ionicons.min.css\">\n");
      out.write("    <!-- Theme style -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/AdminLTE.min.css\">\n");
      out.write("    <!-- AdminLTE Skins. Choose a skin from the css/skins\n");
      out.write("         folder instead of downloading all of them to reduce the load. -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/skins/_all-skins.min.css\">\n");
      out.write("\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("    <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n");
      out.write("    <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n");
      out.write("    <![endif]-->\n");
      out.write("\n");
      out.write("    <!-- Google Font -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">\n");
      out.write("</head>");
      out.write("\n");
      out.write("<body class=\"hold-transition skin-blue sidebar-mini\">\n");
      out.write("<div class=\"wrapper\">\n");
      out.write("\n");
      out.write("    ");
      out.write("<header class=\"main-header\">\n");
      out.write("    <!-- Logo -->\n");
      out.write("    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/home\" class=\"logo\">\n");
      out.write("        <!-- mini logo for sidebar mini 50x50 pixels -->\n");
      out.write("        <span class=\"logo-mini\"><b>R</b>M</span>\n");
      out.write("        <!-- logo for regular state and mobile devices -->\n");
      out.write("        <span class=\"logo-lg\"><b>Rent</b> Manager</span>\n");
      out.write("    </a>\n");
      out.write("    <!-- Header Navbar: style can be found in header.less -->\n");
      out.write("    <nav class=\"navbar navbar-static-top\">\n");
      out.write("        <!-- Sidebar toggle button-->\n");
      out.write("        <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">\n");
      out.write("            <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("        </a>\n");
      out.write("    </nav>\n");
      out.write("</header>");
      out.write("\n");
      out.write("    <!-- Left side column. contains the logo and sidebar -->\n");
      out.write("    ");
      out.write("<!-- Left side column. contains the logo and sidebar -->\n");
      out.write("<aside class=\"main-sidebar\">\n");
      out.write("    <!-- sidebar: style can be found in sidebar.less -->\n");
      out.write("    <section class=\"sidebar\">\n");
      out.write("        <!-- /.search form -->\n");
      out.write("        <!-- sidebar menu: : style can be found in sidebar.less -->\n");
      out.write("        <ul class=\"sidebar-menu\" data-widget=\"tree\">\n");
      out.write("            <li class=\"header\">NAVIGATION</li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/clients/list\">\n");
      out.write("                    <i class=\"fa fa-user\"></i> <span>Utilisateurs</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/vehicles/list\">\n");
      out.write("                    <i class=\"fa fa-car\"></i> <span>Voitures</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/reservations/list\">\n");
      out.write("                    <i class=\"fa fa-pencil\"></i> <span>Reservations</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </section>\n");
      out.write("    <!-- /.sidebar -->\n");
      out.write("</aside>");
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Content Wrapper. Contains page content -->\n");
      out.write("    <div class=\"content-wrapper\">\n");
      out.write("        <!-- Main content -->\n");
      out.write("        <section class=\"content\">\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-3\">\n");
      out.write("\n");
      out.write("                    <!-- Profile Image -->\n");
      out.write("                    <div class=\"box box-primary\">\n");
      out.write("                        <div class=\"box-body box-profile\">\n");
      out.write("                            <h3 class=\"profile-username text-center\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.prenom}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.nom}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(' ');
      out.write('(');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(")</h3>\n");
      out.write("\n");
      out.write("                            <ul class=\"list-group list-group-unbordered\">\n");
      out.write("                                <li class=\"list-group-item\">\n");
      out.write("                                    <b>Reservation(s)</b> <a class=\"pull-right\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reservations.size()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n");
      out.write("                                </li>\n");
      out.write("                                <li class=\"list-group-item\">\n");
      out.write("                                    <b>Voiture(s)</b> <a class=\"pull-right\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehiclesunique.size()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n");
      out.write("                                </li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /.box-body -->\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /.box -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /.col -->\n");
      out.write("                <div class=\"col-md-9\">\n");
      out.write("                    <div class=\"nav-tabs-custom\">\n");
      out.write("                        <ul class=\"nav nav-tabs\">\n");
      out.write("                            <li class=\"active\"><a href=\"#rents\" data-toggle=\"tab\">Reservations</a></li>\n");
      out.write("                            <li><a href=\"#cars\" data-toggle=\"tab\">Voitures</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                        <div class=\"tab-content\">\n");
      out.write("                            <div class=\"active tab-pane\" id=\"rents\">\n");
      out.write("                                <div class=\"box-body no-padding\">\n");
      out.write("                                    <table class=\"table table-striped\">\n");
      out.write("                                        <tr>\n");
      out.write("                                            <th style=\"width: 10px\">#</th>\n");
      out.write("                                            <th>Voiture</th>\n");
      out.write("                                            <th>Date de debut</th>\n");
      out.write("                                            <th>Date de fin</th>\n");
      out.write("                                        </tr>\n");
      out.write("                                        ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                    </table>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <!-- /.tab-pane -->\n");
      out.write("                            <div class=\"tab-pane\" id=\"cars\">\n");
      out.write("                                <!-- /.box-header -->\n");
      out.write("                                <div class=\"box-body no-padding\">\n");
      out.write("                                    <table class=\"table table-striped\">\n");
      out.write("                                        <tr>\n");
      out.write("                                            <th style=\"width: 10px\">#</th>\n");
      out.write("                                            <th>Modele</th>\n");
      out.write("                                            <th>Constructeur</th>\n");
      out.write("                                            <th style=>Nombre de places</th>\n");
      out.write("                                        </tr>\n");
      out.write("                                        ");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                    </table>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <!-- /.tab-pane -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /.tab-content -->\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /.nav-tabs-custom -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /.col -->\n");
      out.write("            </div>\n");
      out.write("            <!-- /.row -->\n");
      out.write("\n");
      out.write("        </section>\n");
      out.write("        <!-- /.content -->\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      out.write("<footer class=\"main-footer\">\n");
      out.write("    <div class=\"pull-right hidden-xs\">\n");
      out.write("        <b>Version</b> 1.0\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<!-- ./wrapper -->\n");
      out.write("\n");
      out.write("<!-- jQuery 3 -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/jquery/dist/jquery.min.js\"></script>\n");
      out.write("<!-- Bootstrap 3.3.7 -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n");
      out.write("<!-- FastClick -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/fastclick/lib/fastclick.js\"></script>\n");
      out.write("<!-- AdminLTE App -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/adminlte.min.js\"></script>\n");
      out.write("<!-- AdminLTE for demo purposes -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/demo.js\"></script>");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/views/users/details.jsp(55,40) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/users/details.jsp(55,40) '${reservations}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${reservations}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/views/users/details.jsp(55,40) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("reservation");
    // /WEB-INF/views/users/details.jsp(55,40) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                            <tr>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reservation.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicles[status.index].constructeur}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(' ');
          out.write('#');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicles[status.index].id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(" </td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reservation.debut}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reservation.fin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                            </tr>\n");
          out.write("                                        ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/views/users/details.jsp(77,40) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/users/details.jsp(77,40) '${vehiclesunique}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${vehiclesunique}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/views/users/details.jsp(77,40) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("vehicle");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                            <tr>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.modele}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.constructeur}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.nb_places}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("                                            </tr>\n");
          out.write("                                        ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }
}
