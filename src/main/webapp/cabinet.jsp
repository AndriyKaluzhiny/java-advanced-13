<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xml:lang="EU">
<head>
<meta charset="ISO-8859-1">
<title>cabinet</title>
</head>
<body>
	<jsp:include page="header.jsp" />


	<div class="container-fluid" style="min-height: 520px;">
      <div class="row">
        <div class="col">
          <div id="productCards">
          
          </div>
        </div>
      </div> 
    </div>

    <jsp:include page="footer.jsp" />

	<script src="js/effects.js"></script>
    <script src="js/ajaxCalls.js"></script>
    <script src="js/cabinet.js"></script>
</body>
</html>