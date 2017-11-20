

var contextRoot = "/" + window.location.pathname.split('/')[1];


$(document).ready(function() {	
	
	deposit = function(accountNumber){	
		var selector = "#" + accountNumber;
		var transactionAmount = parseFloat($(selector).val());
		$.ajax({
			url: contextRoot + '/banker/customer/deposit/'+ transactionAmount +'/' + accountNumber,
			type: 'GET',
			dataType: "json",
			contentType : 'application/json; charset=utf-8',
			success: function(response){
				var selector = '#' +response.typeAccount;
				console.log(selector);
				$(selector).html("");
				$(selector).append("<h4>Account Balance" + response.balance + "</h4>");
				console.log(response);
			},
			error: function(error){						
				console.log(error);
			}
		});
	}
	
	withdraw = function(accountNumber){	
		var selector = "#" + accountNumber;
		var transactionAmount = parseFloat($(selector).val());
		$.ajax({
			url: contextRoot + '/banker/customer/deposit/'+ transactionAmount +'/' + accountNumber,
			type: 'GET',
			dataType: "json",
			contentType : 'application/json; charset=utf-8',
			success: function(response){
				var selector = '#' +response.typeAccount;
				$(selector).html("");
				$(selector).append("<h4>Account Balance" + response.balance + "</h4>");
				console.log(response);
			},
			error: function(error){						
				console.log(error);
			}
		});
	}
});

