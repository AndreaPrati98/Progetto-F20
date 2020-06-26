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
	  	console.log(convertedData);  	
	  		
		$("#typeComponentDiv").empty();
		
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
			$("#typeComponentDiv").append('<input class="newCompType" type="text" placeholder="" name="routename" id="' + i + '"/>');
			$("#typeComponentDiv").append('<label id="label' + i + '" for="' + i + '">' + convertedData[i] +  '</label>');
		}
		
		$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="name"/>');
		$("#typeComponentDiv").append('<label id="label' + i + '" for="name">Name</label>');
		
		$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="price"/>');
		$("#typeComponentDiv").append('<label id="label' + i + '" for="price">Price</label>');
		
		$("#typeComponentDiv").append('<button onClick="saveComponent(' + convertedData['num'] + ')" id="submit" class="btn waves-effect waves-light" type="submit" name="action">Submit</button>');
    });
});


function saveComponent(num) {
	var value;
	var name;
	var json;
	json = '{"';
	
	for (i = 0; i < num; i++) {
		value = $("input#" + i).val();
		name = $("#label" + i).text();
		json = json + name + '": "' + value ;
		
		json = json + '", "';
	}
	
	json = json + 'price": "' + $("input#price").val() + '",';
	json = json + '"name": "' + $("input#name").val() + '",';
	json = json + '"type": "' + $("select.typeComp").children("option:selected").text();
	
	json = json + '"}';
	
	console.log(json);
	
	let posting = $.post("administrator/addComp", json);
	alert("asdasdasd");
}

$("select.removeComp").change(function(){
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator/getAllComp", {typeComp: selected});
    
    posting.done(function(data) {
	  	var convertedData =  JSON.parse(data);
	  	console.log(convertedData);  	
	  	
	  	$("#removeComponentForm").empty();
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
	  		$("#removeComponentForm").append('<p><label><input type="checkbox" value="' + convertedData[i] + '" name="' + 'checkBox' + '"/><span>' + convertedData[i].split("@")[0] + '</span></label></p>');
		}
		
		$("#removeComponentForm").append('<button id="submit" class="btn waves-effect waves-light" type="submit" name="action">Submit</button>');
	});
});

function addTypeC(){
	
	var name=$("#ntc").val();
	var isN=$("#radioB:checked"). val();
	
	
	let posting = $.post( "/administrator/newTypeComp", {newTypeOfC: name, needed: isN});
	
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		alert(convertedData['Ok']);
	});									
}

function addStdAtt(){
	
	alert("sono un alert");
	var name=$("#stdAttName").val();
	var type=$("#stdAttSelectType option:selected").text();
	var bound=$("#stdAttSelectBound option:selected").text();
	var cat=$("#stdAttSelectCat option:selected").text();
	var isPresentable=$("#stdAttIsPres:checked").val();

	console.log(name + type + bound + cat + isPresentable);

	let posting = $.post( "/administrator/newTypeComp", {stdAttName: name, stdAttType: type, stdAttBound: bound, stdAttCat: cat, stdAttIsPres: isPresentable});
								
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		alert(convertedData['Ok']);
	});						
}
function checkAddAdmin(){
	
	var mail=$("#addAdmin").val();
	let posting=$.post("/administrator/checkAdmin",{email:mail})
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var flag=convertedData['Ok'];
		if (flag) {
			alert("Ok")
		}else{
			alert("Wrong email!")
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
			alert("Ok")
		}else{
			alert("Wrong email!")
		}
		
	});
}
function addAdmin(){
	var mail=$("#addAdmin").val();
	let posting=$.post("/administrator/addAdmin",{email:mail})
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var message=convertedData['response'];
		alert(message);
		window.location.replace("/administrator?tab=3");
	})						
}

function removeAdmin(){
	var mail=$("#removeAdmin").val();

	let posting=$.post("/administrator/removeAdmin",{email:mail})
	
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		var message=convertedData['response'];
		alert(message);
		window.location.replace("/administrator?tab=4");
	})
								
}
