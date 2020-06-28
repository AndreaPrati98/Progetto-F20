 $(document).ready(function() {
 	console.log("ready");

 	$("#SignUpForm").submit(function(e){
		console.log("submito");		
		if($("#Password").val() != $("#RipetiPassword").val()){
			console.log("Devo prevenire il submit")
			e.preventDefault();
			alert("Password e conferma devono essere uguali");
		}
	});
 });

