<!DOCTYPE html>
<html xml:lang="EU">
<head>
    <title>MyShop</title>
</head>

<style>
      .row {  
        justify-content: space-evenly;
      }

	  .btn-primary {
		color: #fff!important;
   	 	background-color: #505050!important;
    	border-color: #000000!important;
	  }
</style>

<body>
    <jsp:include page="header.jsp"></jsp:include>

		<div class="row container-fluid">

			<form class="createProduct" method="POST" style="min-height: 520px;">
				<div class="form-group">
					<input type="text" class="form-control productName" 
						placeholder="enter product name">
				</div>

				<div class="form-group">
					<input type="text" class="form-control productDescription" 
						placeholder="enter product description">
				</div>

				<div class="form-group">
					<input type="number" class="form-control productPrice" 
						placeholder="enter product price">
				</div>

				<button class="btn btn-primary createProduct">Submit</button>
			</form>
        </div>


    <jsp:include page="footer.jsp"></jsp:include>
    
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

        <script src="js/effects.js"></script>
	<script src="js/ajaxCalls.js"></script>

</body>
</html>