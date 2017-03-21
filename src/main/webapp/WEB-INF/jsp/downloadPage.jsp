<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <title>Download Page</title>
    <link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"  rel="stylesheet" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css">
</head>
<body class="content">

<div class="download">
    <h1>Download Xml File</h1>
    Click on below links to see FileDownload in action.<br/><br/>
    <a href="<c:url value='/downloadXml' />">Download This File</a>
    <br/>
</div>
</body>
</html>
