<%--
  Created by IntelliJ IDEA.
  User: SuperElectro
  Date: 05/05/2022
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.gsnotes.web.models.ExportModule" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="i" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/resources/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/assets/img/favicon.png">
    <title>
        ENSAH APP
    </title>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"/>
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-icons.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-svg.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/assets/css/hr.css" rel="stylesheet"/>
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-svg.css" rel="stylesheet"/>
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/resources/assets/css/argon-dashboard.css?v=2.0.2"
          rel="stylesheet"/>
</head>

<body class="g-sidenav-show   bg-gray-100">
<div class="min-height-300 bg-primary position-absolute w-100"></div>
<aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 "
       id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
           aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="${pageContext.request.contextPath}/admin/showAdminHome"
           target="_blank">
            <img src="${pageContext.request.contextPath}/resources/assets/img/logo-ct-dark.png"
                 class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold">ENSAH APP</span>
        </a>
    </div>
    <hr class="horizontal dark mt-0">
    <div class="min-height-300 bg-primary position-absolute w-100"></div>
    <aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 " id="sidenav-main">
        <div class="sidenav-header">
            <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
            <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/argon-dashboard/pages/dashboard.html " target="_blank">
                <img src="${pageContext.request.contextPath}/resources/img/log.png" class="navbar-brand-img h-150" alt="main_logo">
                <span class="ms-1 font-weight-bold">ENSAH APP</span>
            </a>
        </div>
        <hr class="horizontal dark mt-0">
        <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="./pages/dashboard.html">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-tv-2 text-primary text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Export data</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/manageAccounts">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-calendar-grid-58 text-warning text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">List Account</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/createAccounts">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-credit-card text-success text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Add Account</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/managePersons">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-app text-info text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Manage Persons</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/showForm?typePerson=1">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-world-2 text-danger text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Add Prof</span>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/showForm?typePerson=2">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-single-02 text-dark text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Add Student</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/admin/showForm?typePerson=3">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-single-copy-04 text-warning text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">Add Cadre Admin</span>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link " href="${pageContext.request.contextPath}/logout">
                        <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="ni ni-collection text-info text-sm opacity-10"></i>
                        </div>
                        <span class="nav-link-text ms-1">LOGOUT</span>
                    </a>
                </li>
                <li class="nav-item px-1 p-4 d-flex align-items-center">





            </ul>
        </div>

    </aside>


    </div>
</aside>
<main class="main-content position-relative border-radius-lg ">
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg m-3 px-0 mx-4 shadow-none border-radius-xl "
         style="background-color:#5e72e4 !important; " id="navbarBlur" data-scroll="false">
        <div class="container-fluid py-1 px-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                    <li class="breadcrumb-item text-sm"><a class="opacity-5 text-white" href="javascript:;">Dashboard</a>
                    </li>
                    <li class="breadcrumb-item text-sm text-white active" aria-current="page">Export Data</li>
                </ol>
                <h6 class="font-weight-bolder text-white mb-0">Export Data</h6>
            </nav>
            <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
                <div class="ms-md-auto pe-md-3 d-flex align-items-center">
                    <div class="input-group">
                        <li class="nav-item"><form
                                action="${pageContext.request.contextPath}/admin/serachPerson"
                                class="d-flex" method="GET">
                            <input name="cin" class="form-control me-2" type="search"
                                   placeholder="Saisir CIN" aria-label="Search">
                            <button class="btn btn-outline-info" type="submit">Search</button>
                        </form></li>
                    </div>
                </div>

                <ul class="navbar-nav  justify-content-end">
                    <li class="nav-item d-flex align-items-center">
                        <a href="javascript:;" class="nav-link text-white font-weight-bold px-0">
                            <i class="fa fa-user me-sm-1"></i>
                            <span class="d-sm-inline d-none">



                                	<s:authorize access="isAuthenticated()">

                                        <s:authentication property="principal.username" />

                                    </s:authorize>
                            </span>
                        </a>
                    </li>


                    </li>

                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    </div>
    <!-- End Navbar -->


    <div class="container mt-5">

        <div class="astrodivider">
            <div class="astrodividermask"></div>
            <span><i>&#10038;</i></span></div>

        <!--the first form-->

        <f:form action="/export/dataByModule" method="POST" modelAttribute="exportModule">
            <div class="form-group row">
                <label class="col-sm-2 " style="font-size:16px;">Choisir La Session: </label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">

                        <f:radiobutton  class="form-check-input" id="inlineRadio1" path="session" value="Normale"/>
                        <label class="form-check-label"  for="inlineRadio1">Normale</label>
                    </div>
                    <div class="form-check form-check-inline">

                        <f:radiobutton class="form-check-input" id="inlineRadio2" path="session" value="Rattrapage"/>
                        <label class="form-check-label" for="inlineRadio2">Rattrapage</label>
                    </div>
                </div>

                <div class="col-sm-10">
                    <label class="col-sm-2 " style="font-size:16px;" >Choisir Un Module : </label>
                    <f:select path="nomModule" cssClass="form-control">
                        <f:options items="${modulesName}"  />
                    </f:select>
                </div>


                <div class="col-sm-10 pt-3">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>

            </div>


        </f:form>

        <div class="astrodivider">
            <div class="astrodividermask"></div>
            <span><i>&#10038;</i></span></div>



        <!--the second form-->

        <f:form action="/export/allData" method="POST" modelAttribute="exportAllModel">


            <div class="form-group row">
                <label class="col-sm-2" style="font-size:16px;" >Choisir La Session: </label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">

                        <f:radiobutton  class="form-check-input" id="inlineRadio1" path="session" value="Normale"/>
                        <label class="form-check-label" for="inlineRadio1">Normale</label>
                    </div>
                    <div class="form-check form-check-inline">

                        <f:radiobutton class="form-check-input" id="inlineRadio2" path="session" value="Rattrapage"/>
                        <label class="form-check-label" for="inlineRadio2">Rattrapage</label>
                    </div>
                </div>

                <div class="col-sm-10">
                    <label class="col-sm-2" style="font-size:16px;" >Choisir Un Niveau : </label>

                       <%-- <f:select path="nomModule" items="${modules}"  cssClass="form-control">



                                <f:option value="${p.titre}"  >${p.titre}</f:option>


                        </f:select>--%>

                           <f:select path="nomNiveau" cssClass="form-control">
                               <f:options items="${niveauxName}"  />
                           </f:select>



                </div>


                <div class="col-sm-10 pt-3">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>


            </div>


        </f:form>

    </div>

</main>


<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/resources/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugins/chartjs.min.js"></script>
<script>
    var ctx1 = document.getElementById("chart-line").getContext("2d");

    var gradientStroke1 = ctx1.createLinearGradient(0, 230, 0, 50);

    gradientStroke1.addColorStop(1, 'rgba(94, 114, 228, 0.2)');
    gradientStroke1.addColorStop(0.2, 'rgba(94, 114, 228, 0.0)');
    gradientStroke1.addColorStop(0, 'rgba(94, 114, 228, 0)');
    new Chart(ctx1, {
        type: "line",
        data: {
            labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "Mobile apps",
                tension: 0.4,
                borderWidth: 0,
                pointRadius: 0,
                borderColor: "#5e72e4",
                backgroundColor: gradientStroke1,
                borderWidth: 3,
                fill: true,
                data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
                maxBarThickness: 6

            }],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    display: false,
                }
            },
            interaction: {
                intersect: false,
                mode: 'index',
            },
            scales: {
                y: {
                    grid: {
                        drawBorder: false,
                        display: true,
                        drawOnChartArea: true,
                        drawTicks: false,
                        borderDash: [5, 5]
                    },
                    ticks: {
                        display: true,
                        padding: 10,
                        color: '#fbfbfb',
                        font: {
                            size: 11,
                            family: "Open Sans",
                            style: 'normal',
                            lineHeight: 2
                        },
                    }
                },
                x: {
                    grid: {
                        drawBorder: false,
                        display: false,
                        drawOnChartArea: false,
                        drawTicks: false,
                        borderDash: [5, 5]
                    },
                    ticks: {
                        display: true,
                        color: '#ccc',
                        padding: 20,
                        font: {
                            size: 11,
                            family: "Open Sans",
                            style: 'normal',
                            lineHeight: 2
                        },
                    }
                },
            },
        },
    });
</script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${pageContext.request.contextPath}/resources/assets/js/argon-dashboard.min.js?v=2.0.2"></script>
</body>

</html>
