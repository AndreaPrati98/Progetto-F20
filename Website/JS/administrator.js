$(document).ready(function(){
    $('.tabs').tabs();
    $('select').formSelect();
  });
  
  
$("select.typeComp").change(function(){
    $("#typeComponentDiv").show();
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator/getCompForm", {typeComp: selected});
    
    posting.done(function(data) {
	  	var convertedData =  JSON.parse(data);	
	  		
		$("#typeComponentDiv").empty();
		
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
			$("#typeComponentDiv").append('<input class="newCompType" type="text" placeholder="' + convertedData[i] +  '" name="routename" id="' + i + '"/>');
		}
		
		$("#typeComponentDiv").append('<input type="text" placeholder="Name" name="routename" id="name"/>');
		
		$("#typeComponentDiv").append('<input type="text" placeholder="Price" name="routename" id="price"/><br><br>');
		
		$("#typeComponentDiv").append('<button onClick="saveComponent(' + convertedData['num'] + ')" id="submit" class="btn waves-effect waves-light" type="submit" name="action" style="background-color: #0097a7;">Submit</button>');
    });
});


function saveComponent(num) {
	var value;
	var name;
	var json;
	var price;
	json = '{"';
	
	for (i = 0; i < num; i++) {
		name = $("input#" + i).attr("placeholder");
		value = $("input#" + i).val();
		json = json + name + '": "' + value ;
		
		json = json + '", "';
		
		if(value == ''){
			let text = "You can't leave " + name + " empty!";
			M.toast({html: text});
			return false;
		}
	}
	
	price = $("input#price").val();
	name = $("input#name").val();
	
	if(price == ''){
		let text = "You can't leave price empty!";
		M.toast({html: text});
		return false;
	}
	
	if(name == ''){
		let text = "You can't leave name empty!";
		M.toast({html: text});
		return false;
	}
	json = json + 'price": "' + price + '",';
	json = json + '"name": "' + name + '",';
	json = json + '"type": "' + $("select.typeComp").children("option:selected").text();
	
	json = json + '"}';
	
	let posting = $.post("administrator/addComp", json);
	
	posting.done(function(data) {
		var convertedData =  JSON.parse(data);
	
		if(convertedData['response'] == 'ok'){
			window.location.replace("/administrator?tab=1");
		}else{
			M.toast({html: 'Error'});
		}
	});
}

$("select.removeComp").change(function(){
	$("#typeComponentDiv").hide();
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator/getAllComp", {typeComp: selected});
    
    posting.done(function(data) {
	  	var convertedData =  JSON.parse(data);
	  	
	  	$("#removeComponentForm").empty();
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
	  		$("#removeComponentForm").append('<p><label><input class="removeCompCheckbox"type="checkbox" value="' + convertedData[i] + '" name="' + 'checkBox' + '"/><span>' + convertedData[i].split("@")[0] + '</span></label></p>');
		}
		
		$("#removeComponentForm").append('<button id="removeCompButt" onclick="removeComp()" type="button" class="btn waves-effect waves-light" style="background-color: #0097a7;" name="action">Submit</button>');
	});
});

function removeComp(){
 	let checked = $(".removeCompCheckbox:checked").length;

	if(!checked) {
		M.toast({html: "You must check at least one checkbox"});
	    return false;
	}else{
		$("#removeComponentForm").submit();
	}
}

function addTypeC(){
	
	var name=$("#ntc").val();
	var isN=$("#radioB:checked"). val();
	
	
	let posting = $.post( "/administrator/newTypeComp", {newTypeOfC: name, needed: isN});
	
	posting.done(function(data){
		var convertedData= JSON.parse(data);
	});									
}

function addStdAtt(){
	
	var name=$("#stdAttName").val();
	var type=$("#stdAttSelectType option:selected").text();
	var bound=$("#stdAttSelectBound option:selected").text();
	var cat=$("#stdAttSelectCat option:selected").text();
	var isPresentable=$("#stdAttIsPres:checked").val();

	let posting = $.post( "/administrator/newTypeComp", {stdAttName: name, stdAttType: type, stdAttBound: bound, stdAttCat: cat, stdAttIsPres: isPresentable});
								
	posting.done(function(data){
		var convertedData= JSON.parse(data);
	});						
}
function checkAddAdmin(){
	
	var mail=$("#addAdmin").val();
	let posting=$.post("/administrator/checkAdmin",{email:mail})
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var flag=convertedData['Ok'];
		if (flag) {
			let text = mail + " exists!";
			M.toast({html: text});
		}else{
			M.toast({html: "Wrong email!"});
		}
		
	});
}
	
function checkRemoveAdmin(){
	
	var mail=$("#removeAdmin").val();
	let posting=$.post("/administrator/checkAdmin",{email:mail})
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var flag=convertedData['Ok'];
		if (flag) {
			let text = mail + " exists!";
			M.toast({html: text});
		}else{
			M.toast({html: "Wrong email!"});
		}
		
	});
}
function addAdmin(){
	var mail=$("#addAdmin").val();
	let posting=$.post("/administrator/addAdmin",{email:mail})
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var message=convertedData['response'];
		window.location.replace("/administrator?tab=3");
	})						
}

function removeAdmin(){
	var mail=$("#removeAdmin").val();

	let posting=$.post("/administrator/removeAdmin",{email:mail})
	
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var message=convertedData['response'];
		window.location.replace("/administrator?tab=4");
	})
								
}
