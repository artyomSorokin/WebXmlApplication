<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html ng-app="sampleApp">
<head>
    <title>Upload</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.css">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
    <script src="/resources/js/valid.js"></script>
</head>
<body class="content">
<div class="main">
    <h1>Please choose upload file</h1>
<div class="form">
<form name="form" method="POST" action="/upload" enctype="multipart/form-data">
    <input class="btn btn-primary btn-block" name="file" type="file" accept="text/xml" ng-model="document" valid-file required>
    <input class="btn btn-primary btn-block" type="submit" value="{{ !form.$valid && 'Choose file' || 'Send' }}">
</form>
</div>
</div>
</body>
</html>
