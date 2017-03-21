<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <title>Upload</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.css">

</head>
<body class="content">
<div class="main">
    <h1>Please choose upload file</h1>
    <div class="form">
        <form method="POST" action="/upload" enctype="multipart/form-data">
            <input class="btn btn-primary btn-block" type="file" name="file" accept="text/xml"/><br/>
            <input class="btn btn-primary btn-block" type="submit" value="Submit" />
        </form>
    </div>

    <c:if test="${message == 'Please select a file to upload'}" >
        <div>
            Please select a file to upload
        </div>
    </c:if>
</div>
</body>
</html>
