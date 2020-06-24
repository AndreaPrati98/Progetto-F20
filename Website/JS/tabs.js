 $(document).ready(function(){
    $('.tabs').tabs();
    $('select').formSelect();
  });
  
  
$("select.typeComp").change(function(){
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator", {typeComp: selected});
    
    posting.done(function(data) {
    	alert("asd");
	  	var convertedData =  JSON.parse(data);
	  	console.log(convertedData);
	  	var response1 = convertedData['Ciao sono una risposta'];
	  	var response2 = convertedData['Ciao sono unaltra risposta'];
	  	$("input#1").attr("placeholder", response1);
	  	$("input#2").attr("placeholder", response2);
    });
});