<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/styles.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">

    <script src="${pageContext.request.contextPath}/resources/js/angular.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"
            type="text/javascript"></script>


</head>
<body>
<div class="container">
    <div ng-app="app" ng-controller="squareController">
        <h2 class="h2 page-header">Monitoring virtual ships</h2>

            <div class="col-sm-7 col-md-7 col-lg-7">
                <div class="panel panel-primary">
                    <div class="panel-heading">Square</div>
                    <div class="panel-body">
                        <br>

                        <table class="square elem">
                            <tr ng-repeat="row in grid track by $index">
                                <td ng-repeat="cell in row track by $index" ng-click="uncovercell(cell)">
                                    <img ng-if="cell.color == 'mark_a'" src="${pageContext.request.contextPath}/resources/images/ff0000.png" />
                                    <img ng-if="cell.color == 'mark_d'" src="${pageContext.request.contextPath}/resources/images/ffff00.png" />
                                    <img ng-if="cell.color == 'mark_p'" src="${pageContext.request.contextPath}/resources/images/00cc00.png" />
                                    <img ng-if="cell.color == 'mark_ad'" src="${pageContext.request.contextPath}/resources/images/ffcc33.png" />
                                    <img ng-if="cell.color == 'mark_ap'" src="${pageContext.request.contextPath}/resources/images/33cccc.png" />
                                    <img ng-if="cell.color == 'mark_dp'" src="${pageContext.request.contextPath}/resources/images/cccc33.png" />

                                    <img ng-if="cell.color == 'mark_ada'" src="${pageContext.request.contextPath}/resources/images/ffcc99.png" />
                                    <img ng-if="cell.color == 'mark_add'" src="${pageContext.request.contextPath}/resources/images/ffffcc.png" />
                                    <img ng-if="cell.color == 'mark_adp'" src="${pageContext.request.contextPath}/resources/images/ccffcc.png" />

                                    <img ng-if="cell.color == 'mark_dpa'" src="${pageContext.request.contextPath}/resources/images/ffccff.png" />
                                    <img ng-if="cell.color == 'mark_dpd'" src="${pageContext.request.contextPath}/resources/images/ccff99.png" />
                                    <img ng-if="cell.color == 'mark_dpp'" src="${pageContext.request.contextPath}/resources/images/99cc99.png" />

                                    <img ng-if="cell.color == 'mark_apa'" src="${pageContext.request.contextPath}/resources/images/ccccff.png" />
                                    <img ng-if="cell.color == 'mark_apd'" src="${pageContext.request.contextPath}/resources/images/ccffcc.png" />
                                    <img ng-if="cell.color == 'mark_app'" src="${pageContext.request.contextPath}/resources/images/cccc99.png" />

                                    <img ng-if="cell.color == 'ship_a'" src="${pageContext.request.contextPath}/resources/images/ff0000-ship.png" />
                                    <img ng-if="cell.color == 'ship_d'" src="${pageContext.request.contextPath}/resources/images/ffff00-ship.png" />
                                    <img ng-if="cell.color == 'ship_p'" src="${pageContext.request.contextPath}/resources/images/00cc00-ship.png" />
                                    <img ng-if="cell.color == '##'" src="${pageContext.request.contextPath}/resources/images/empty.png" />
                                    <img ng-if="cell.color == '..'" src="${pageContext.request.contextPath}/resources/images/covered.png" />
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div class="panel-footer">watch the movement of ships</div>
                </div>
            </div>
            <div class="col-sm-5 col-md-5 col-lg-5">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">Control virtual ships</h3>
                    </div>
                    <div class="panel-body">
                        <h4>Please enter the initial data for the model:</h4>
                        <div ng-if="testNumberShip()" class="alert alert-danger" role="alert">{{errorMessage}}</div>

                        <div class="well">
                            <div class="row">
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <img src="${pageContext.request.contextPath}/resources/images/big-ship_red.jpg" />
                                </div>
                                <div class="col-lg-1 col-sm-1 col-md-1">
                                    <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
                                </div>
                                <div class="col-lg-8 col-sm-8 col-md-8">
                                    <p class="text-input-dialog"><label for="numberShipTypeA">Enter number of ships of type A: </label>
                                        <input id="numberShipTypeA" ng-model="numberShipTypeA">
                                    </p>
                                    <span class="badge badge-important">movement in all directions</span>
                                </div>
                            </div>
                        </div>
                        <div class="well">
                            <div class="row">
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <img src="${pageContext.request.contextPath}/resources/images/big-ship_yellow.jpg" />
                                </div>
                                <div class="col-lg-1 col-sm-1 col-md-1">
                                    <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
                                </div>
                                <div class="col-lg-8 col-sm-8 col-md-8">
                                    <p><label for="numberShipTypeD">Enter number of ships of type D: </label>
                                        <input id="numberShipTypeD" ng-model="numberShipTypeD">
                                    </p>
                                    <span class="badge badge-success">movement only on the diagonal</span>
                                </div>
                            </div>
                        </div>
                        <div class="well">
                            <div class="row">
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <img src="${pageContext.request.contextPath}/resources/images/big-ship_green.jpg" />
                                </div>
                                <div class="col-lg-1 col-sm-1 col-md-1">
                                    <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
                                </div>
                                <div class="col-lg-8 col-sm-8 col-md-8">
                                    <p><label for="numberShipTypeP">Enter number of ships of type P: </label>
                                        <input id="numberShipTypeP" ng-model="numberShipTypeP">
                                    </p>
                                    <span class="badge badge-warning">movement only vertical and horizontal</span>
                                </div>
                            </div>
                        </div>

                        <div class="well">
                            <div class="row">
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <button type="button" class="btn btn-primary" ng-click="startMoving()">
                                        <span class="glyphicon glyphicon-play-circle" aria-hidden="true"></span> Start
                                    </button>
                                </div>
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <button type="button" class="btn btn-danger" ng-click="stopMoving()">
                                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span> Stop
                                    </button>
                                </div>
                                <div class="col-lg-6 col-sm-6 col-md-6">
                                    <button type="button" class="btn btn-success" ng-click="initialSquare()">
                                        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Clear Square
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="panel-footer">adjustment entered data for model</div>
                </div>
            </div>
        </div>

        <!--
        <div class="row">
            <h2 class="h2 page-header">Extracting data from JSON</h2>
            <table class="table table-striped">
                <tr>
                    <th>coordinate X</th>
                    <th>coordinate Y</th>
                    <th>color</th>
                </tr>
                <tr ng-repeat="mysquare in actualSquare">
                    <td>{{mysquare.coordinateX}}</td>
                    <td>{{mysquare.coordinateY}}</td>
                    <td>{{mysquare.color}}</td>
                </tr>
            </table>
        </div>
        -->

    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/app.js"
        type="text/javascript"></script>

</body>
</html>