<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
   <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> cart.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> cart.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> cart.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">

 	<section>
 			<div id="prod" class="container"  >
   			     <div id="result" style="display:none" > </div>
			</div>
 	</section>	

 	<section class="container" >
 		<div>

			<div>
				 <a href="#" class="btn btn-success pull-right"> <span
					class="glyphicon-shopping-cart glyphicon"></span> Check out
				</a>
			</div>
			<table class="table table-hover">
				<tr>
					<th>---ID---</th>
					<th>Name</th>
					<th>Unit price</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</table>
	<table id="cart_table" class="table table-hover">
	
		<c:forEach  var="item" items ="${cart.cartItems}" >
				<tr>
					<td>${item.value.product.productId}</td>
					<td>${item.value.product.name}</td>
					<td>${item.value.product.unitPrice}</td>
					<td>${item.value.quantity}</td>
					<td>${item.value.totalPrice}</td>
					<td><a href="#" class="label label-danger" onclick="removeFromCart('${item.value.product.productId}');"> <span
							class="glyphicon glyphicon-remove" /></span> Remove
					</a></td>
				</tr>
		</c:forEach>
  
				<tr>
					<th></th>
					<th></th>
					<th>Grand Total</th>
					<th>${cart.grandTotal}</th>
					<th></th>
				</tr>
			</table>
			
			<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Continue shopping
			</a>
		</div>
	</section>
    </tiles:putAttribute>

</tiles:insertDefinition>

</html>
