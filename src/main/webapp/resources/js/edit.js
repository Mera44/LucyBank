$(document).ready(function() {	

var contextRoot = "/" + window.location.pathname.split('/')[1];
alert (contextRoot);
	
	editSubmit = function(customerId) {
		//var dataToSend = JSON.stringify(serializeObject($('#editProfileForm')));
		//console.log(dataToSend);
		var dataToSend = {
    			
    		    	street : $("#street").val(),
    		    	state : $("#state").val(),
    		    	zipcode : $("#zipcode").val()
    		   
    		    
    	}
		alert("Iam inside edit submit");
		console.log("formData before post: " + dataToSend);
		$.ajax({
			type : 'POST',
			url : contextRoot + '/teller/edit/customer/'+customerId,
			dataType : "json", // Accept header
			data : JSON.stringify(dataToSend),
			contentType : 'application/json', // Sends - Content-type
			success : function(response) {
				//alert(response.address.state)
				$('#errors').html("");
				$("#result").append('<H4 align="center">Customers profile has been Succesfully update<H4>');
				$('#result').show();
				//Code her
			},
			error : function(errorObject) {
				// error: function(jqXHR, textStatus, HTTPStatus ){
				// jqXHR = jQuery XMLHttpRequest superset of XMLHttpRequest
				// EXAMPLE values: error: function(jQuery XMLHttpRequest, "error",
				// "Bad Request" ){
				// see http://api.jquery.com/jquery.ajax/
				console.log(errorObject);

				if (errorObject.responseJSON.errorType == "ValidationError") {
					$('#success').html("");
					$('#errors').html("");
					$("#errors").append('<H3 align="center"> Error(s)!! <H3>');
					$("#result").append('<p>');

					var errorList = errorObject.responseJSON.errors;
					$.each(errorList, function(i, error) {
						$("#errors").append(error.message + '<br>');
					});
					$("#errors").append('</p>');
					$('#result').show();
				} else {
					alert(errorObject.responseJSON.errors(0)); // "non" Validation
					// Error
				}
			}
		});
	}
});



