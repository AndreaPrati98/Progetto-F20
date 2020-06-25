 $(document).ready(function(){
    $('.tabs').tabs();
    $('select').formSelect();
  });
  
  
$("select.typeComp").change(function(){
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator/getCompForm", {typeComp: selected});
    
    posting.done(function(data) {
	  	var convertedData =  JSON.parse(data);
	  	console.log(convertedData);  	
	  		
		$("#typeComponentDiv").empty();
		
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
			$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="' + i + '"/>');
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
	json = json + '"type": "' + $("select").children("option:selected").text();
	
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
	  		$("#removeComponentForm").append('<p><label><input type="checkbox" value="' + convertedData[i] + '" name="' + 'checkBox' + '"/><span>' + convertedData[i] + '</span></label></p>');
		}
		
		$("#removeComponentForm").append('<button id="submit" class="btn waves-effect waves-light" type="submit" name="action">Submit</button>');
	});
});

function addTypeC(){
	
	var name=$("#ntc").val();
	var isN=$("#radioB:checked").val();
	
	
	let posting = $.post( "/administrator/newTypeComp", {newTypeOfC: name, needed: isN});
	
	posting.done(function(data){
		var convertedData= JSON.parse(data);
		alert(convertedData['Ok']);
	});									
}

function addStdAtt(){
	
	alert("sono un alert");
	var name=$("#ntc").val();
	var isN=$("#radioB:checked").val();
								
}

function addBound(){
	
	alert("sono un alert");
								
}