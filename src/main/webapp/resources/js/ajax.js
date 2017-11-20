

var contextRoot = "/" + window.location.pathname.split('/')[1];

$(document).ready(function() {	
	deposit = function(accountNumber){
		
		alert(accountNumber);
		var selector = "#" + accountNumber;
		console.log(selector);
		var transactionAmount = parseFloat($(selector).val());
		console.log(transactionAmount);
		var data = JSON.stringify({"transactionAmount": "transaction", "accountNumber": "Number"});
		$.ajax({
			url: contextRoot + '/banker/customer/deposit',
			type: 'POST',
			dataType: "json",
			contentType : 'application/json',
			data: data,
			success: function(response){
				alert("Product Successfully added to the Cart!");
			},
			error: function(error){						
				console.log(error);
			}
		});
	}
});