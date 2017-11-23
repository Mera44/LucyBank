

var contextRoot = "/" + window.location.pathname.split('/')[1];


$(document).ready(function() {	
	
	deposit = function(accountNumber, typeAccount){	
		let selector = "#" + accountNumber;
		let transactionAmount = parseFloat($(selector).val());
		$.ajax({
			url: contextRoot + '/banker/customer/deposit/'+ transactionAmount +'/' + accountNumber +'/' + typeAccount,
			type: 'GET',
			dataType: "json",
			contentType : 'application/json; charset=utf-8',
			success: function(response){
				var selector = '#' +response.typeAccount;
				console.log(selector);
				$(selector).html("");
				$(selector).append("<p>Account Balance: " + response.balance + "</p>");
			},
			error: function(error){						
				console.log(error);
			}
		});
	}
	
	withdraw = function(accountNumber, typeAccount){	
		let selector = "#" + accountNumber;
		console.log(selector);
		let transactionAmount = parseFloat($(selector).val());
		console.log(transactionAmount)
		$.ajax({
			url: contextRoot + '/banker/customer/withdraw/'+ transactionAmount +'/' + accountNumber + '/' + typeAccount,
			type: 'GET',
			dataType: "json",
			contentType : 'application/json; charset=utf-8',
			success: function(response){
				var selector = '#' +response.typeAccount;
				$(selector).html("");
				$(selector).append("<p>Account Balance: " + response.balance + "</p>");
				console.log(response);
			},
			error: function(error){						
				console.log(error);
			}
		});
	}
});

