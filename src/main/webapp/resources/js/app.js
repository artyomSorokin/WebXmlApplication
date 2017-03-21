var app = angular.module('app', ['ngTouch', 'ui.grid', 'ui.grid.pagination', 'ui.grid.autoResize']);

app.controller('MainCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval) {

    var paginationOptions = {
        pageNumber: 1,
        pageSize: 20,
    };
    $scope.currentPage = 1;
    $scope.pageSize = paginationOptions.pageSize;
    $scope.gridOptions = {
        rowHeight: 30,
        enableSorting: true,
        paginationPageSizes: [$scope.pageSize, $scope.pageSize * 2, $scope.pageSize * 3],
        paginationPageSize: paginationOptions.pageSize,
        columnDefs: [{
            name: 'title'
        }, {
            name: 'artist',
            enableSorting: false
        }, {
            name: 'country',
            enableSorting: false
        }, {
            name: 'company',
            enableSorting: false
        }, {
            name: 'year',
            enableSorting: false
        },
           {
            name: 'price',
            enableSorting: false
        }],
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                $scope.pageSize = pageSize;
                $scope.currentPage = newPage;
                $scope.totalPage = Math.ceil($scope.gridOptions.totalItems / $scope.pageSize);
            });
        }
    };

    var loadData = function() {
        var url = 'http://localhost:8080/data/getDataFromXml';
        /*var url = 'https://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/data/100.json';*/
        $http.get(url)
            .success(function(data) {
                $scope.gridOptions.totalItems = data.length;
                $scope.totalPage = Math.ceil($scope.gridOptions.totalItems / $scope.pageSize);
                $scope.gridOptions.data = data;
            });
    };

    loadData();

    // for resize grid's height
    $scope.tableHeight = 'height: 600px';

    function getTableHeight(totalPage, currentPage, pageSize, dataLen) {
        var rowHeight = 30; // row height
        var headerHeight = 50; // header height
        var footerHeight = 60; // bottom scroll bar height
        var totalH = 0;
        if (totalPage > 1) {
            // console.log('hehehehe');
            if (currentPage < totalPage) {
                totalH = pageSize * rowHeight + headerHeight + footerHeight;
            } else {
                var lastPageSize = dataLen % pageSize;
                // console.log(lastPageSize);
                if (lastPageSize === 0) {
                    totalH = pageSize * rowHeight + headerHeight + footerHeight;
                } else {
                    totalH = lastPageSize * rowHeight + headerHeight + footerHeight;
                }
            }
            console.log(totalH);
        } else {
            totalH = dataLen * rowHeight + headerHeight + footerHeight;
        }
        return 'height: ' + (totalH) + 'px';
    }

    $interval(function() {
        $scope.tableHeight = getTableHeight($scope.totalPage,
            $scope.currentPage, $scope.pageSize,
            $scope.gridOptions.data.length);
        console.log($scope.tableHeight);
        $scope.gridApi.grid.handleWindowResize();
        $scope.gridApi.core.refresh();
    }, 200);


}]);
