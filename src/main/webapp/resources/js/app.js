var app = angular.module('app', []);
app.factory('dataService', function ($http, $q) {
    return {
        getData: function () {
            var deferred = $q.defer();
            var getAllURL = "http://" + window.location.host + "/restful/getall";
            $http({method: 'GET', url: getAllURL}).
            success(function (data, status, headers, config) {
                deferred.resolve(data);
            })
            .error(function (data, status, headers, config) {
                deferred.reject(status);
            });
            return deferred.promise;
        }
    }
});

app.controller('squareController', function ($scope, $http, $interval, dataService) {
    $scope.grid = createSquare(40,40);
    $scope.flagStarting = false;

    $scope.uncovercell = function(cell) {
        cell.color = "==";
    };
    $scope.numberShipTypeA = 0;
    $scope.numberShipTypeD = 0;
    $scope.numberShipTypeP = 0;
    $scope.errorMessage = "";
    var siteURL = "http://" + window.location.host + "/";
    $interval( function(){ $scope.load(); }, 200);

    $scope.load = function () {
        var promiseObj = dataService.getData();
        promiseObj.then(function (value) {
            $scope.actualSquare = value;
            for(var i = 0; i < 40; i++) {
                for(var j = 0; j < 40; j++) {
                    $scope.grid[i][j].color = value[i*40 + j].color;
                }
            }
        });
    }

    $scope.startMoving = function () {
        var startURL = siteURL + "restful/start";
        numberShip = {
            numberShipTypeA: $scope.numberShipTypeA,
            numberShipTypeD: $scope.numberShipTypeD,
            numberShipTypeP: $scope.numberShipTypeP
        }
        if (!$scope.testNumberShip()) {
            var request = {
                method: 'POST',
                url: startURL,
                headers: {'Content-Type': 'application/json; charset: UTF-8'},
                data: angular.toJson(numberShip)
            }
            $http(request).success(function (data) {
                    data = angular.toJson(numberShip);
                })
                .error(function (data, status, headers, config) {
                    //  Do some error handling here
                });
        }
    }

    $scope.testNumberShip = function () {
        var isError = false;

        if (($scope.numberShipTypeA == 0) && ($scope.numberShipTypeD == 0) && ($scope.numberShipTypeP == 0)) {
            $scope.errorMessage = "specify the value of the number of ships of at least one type, not more than 6";
            isError = true;
        }
        if ((isNaN(parseInt($scope.numberShipTypeA, 10))) || (isNaN(parseInt($scope.numberShipTypeD, 10))) || (isNaN(parseInt($scope.numberShipTypeP, 10)))) {
            $scope.errorMessage = "value must be a number";
            isError = true;
        }
        if (($scope.numberShipTypeA > 6) || ($scope.numberShipTypeD > 6) || ($scope.numberShipTypeP > 6)) {
            $scope.errorMessage = "value of the number of ships can not be more than 6";
            isError = true;
        }
        if (($scope.numberShipTypeA < 0) || ($scope.numberShipTypeD < 0) || ($scope.numberShipTypeP < 0)) {
            $scope.errorMessage = "value of the number of ships can not be less than 0";
            isError = true;
        }
        return isError;
    }

    $scope.stopMoving = function () {
        $scope.flagStarting = false;
        var stopURL = siteURL + "restful/stop";
        var request = {
            method: 'POST',
            url: stopURL,
            headers: {'Content-Type': 'application/json; charset: UTF-8'},
            data: $scope.flagStarting
        }
        $http(request).success(function (data) {
            data = $scope.flagStarting;
        })
        .error(function (data, status, headers, config) {
        });
    }

    $scope.initialSquare = function () {
        var clearURL = siteURL + "restful/clear";
        var request = {
            method: 'POST',
            url: clearURL,
            headers: {'Content-Type': 'application/json; charset: UTF-8'},
            data: $scope.flagStarting
        }

        $http(request).success(function (data) {
                data = $scope.flagStarting;
            })
            .error(function (data, status, headers, config) {
                //  Do some error handling here
            });
    }

});

function createSquare(width, height) {
    var grid = [], row, x, y;
    for (y = 0; y < height; y++) {
        row = [];
        for (x = 0; x < width; x++) {
            row.push({
                color: ".."
            });
        }
        grid.push(row);
    }
    return grid;
}
