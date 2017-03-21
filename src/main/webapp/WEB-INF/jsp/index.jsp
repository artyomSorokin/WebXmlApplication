<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html ng-app="app">

<head>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/release/ui-grid.js"></script>
    <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
</head>

<body>

<div ng-controller="MainCtrl">
    <div ui-grid="gridOptions" ui-grid-pagination id="grid" style="{{tableHeight}}"></div>
    <div>
        <p>Current Page: {{ currentPage }}</p>
        <p>Total Page: {{ totalPage }}</p>
        <p>Page Size: {{ pageSize }}</p>
        <p>Table Height: {{tableHeight}}</p>
    </div>
</div>
<div>
    <a href="/download" class="btn btn-primary btn-mini">Download Xml file</a>
    <a href="/download" class="btn btn-primary btn-mini">Upload new Xml file</a>
</div>



<script src="/resources/js/app.js"></script>
</body>

</html>
