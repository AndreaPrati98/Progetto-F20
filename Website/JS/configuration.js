$(document).ready(function(){
    $('.collapsible').collapsible();
});

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, {edge:'right'});
});

$('.selection').change(function() {
	//Svuoto quello che dice o no invalid configuration
	$("#configurationCheckResultText").text('');
    let modelString = $(this).attr('id');	 
    if (this.checked) {
        add(modelString);
    } else {
         let listItemParent = $('.collection').find( "li[name='"+modelString+"']" );
         console.log(listItemParent);
         remove(modelString,listItemParent);
    }
});

$('#checkBtn').click(function(){
		checkConfiguration();	
	}
);

$("#resetBtn").click(function(){
	//Serve a manipolare solo quelli checkati altrimenti mando più richiesta di rimozione del
	//dovuto 
	//TODO
	//(in realtà non sono riuscito a farlo e quindi brutarlmente invia la richiesta di rimozione al server tutti i componenti anche
	//se questi non sono stati checkati lol )
    $(".selection").prop("checked", false); 
    $(".selection").trigger('change');
});

$("#saveBtn").click(function(){
	//Serve a manipolare solo quelli checkati altrimenti mando più richiesta di rimozione del
	$("#saveBtn").val('true');
	
	//Richiesta ajx
	save();
	
});

//TODO Da mettere a posto perchè se faccio la redirezione via codice chiede conferma

//Se l'utente non ha mai schiacciato save, gli dice che perderà i dati se esce dalla pagina.
//window.onbeforeunload = function() {
//	console.log('Stiamo uscendo');
//	if( $("#alreadySaved").val() == 'false'){
//		//TODO
//	    return "";
//	}
//    return "";
//}


function checkConfiguration(){
  let dataToSend = "";
  let posting = $.post( "/configuration/check");
  

  posting.done(function(data) {
	console.log(data);
	var convertedData =  JSON.parse(data);
	//alert('pippo');
	console.log(convertedData);
	console.log(convertedData['response']);

	if(convertedData['response'] == 'ok'){
		$("#configurationCheckResultText").text('Valid configuration');
	}else if(convertedData['response'] == 'not'){
		$("#configurationCheckResultText").text('Invalid Configuration');
	}else{
		 window.location.replace("/logout");		
	}
	
  });
}

function add(modelString){
  let dataToSend = "";
  //alert("ok click");
  let posting = $.post( "/configuration/add", {model: modelString});
  
  posting.done(function(data) {
	console.log(data);
	var convertedData =  JSON.parse(data);
	//alert('pippo');
	console.log(convertedData);
	console.log(convertedData['response']);
	  
	
    if(convertedData['response'] == 'ok'){
     	//alert("ok post");
     	var price=parseFloat($("span[name="+modelString+"_price]").text());
     	var totalPrice=parseFloat($("#totalPrice").text());
     	totalPrice=totalPrice+price;
     	totalPrice = totalePrice.toFixed(2);
     	$("#totalPrice").text(totalPrice);
	    var addedComponentHtmlList = $(".collection");
	    addedComponentHtmlList.append("<li class='collection-item' name='"+modelString+"'><div>"+modelString+"<a href='#!' class='secondary-content'></a></div></li>");
	 }else if(convertedData['response'] == 'redirect'){
		 window.location.replace("/logout");
	 }else{
		alert("Hai violato i segunti vincoli: "+convertedData['error']); 
	 	$('#'+modelString).prop("checked", false);
	 }
  });
}

function remove(modelString,listItemParent){
  let dataToSend = "";
  //alert("ok click");
  let posting = $.post( "/configuration/remove", {model: modelString});
  posting.done(function(data) {
	  var convertedData =  JSON.parse(data);
	  //alert('pippo');
	  console.log(convertedData);
	  console.log(convertedData['response']);
		 
	  if(convertedData['response'] == 'ok'){
		//alert("ok remove "+modelString);
		var price=parseFloat($("span[name="+modelString+"_price]").text());
		var totalPrice=parseFloat($("#totalPrice").text());
		totalPrice=totalPrice-price;
		$("#totalPrice").text(totalPrice);
		listItemParent.remove();	
	  }else if(convertedData['response'] == 'redirect'){
			 window.location.replace("/logout");
	  }
  });
  
}
  
function save(){
	let posting = $.post( "/configuration/save");
	posting.done(function(data) {
	  var convertedData =  JSON.parse(data);
	  if(convertedData['response'] == 'ok'){
			//alert("ok remove "+modelString);
			alert("Salvata con successo")
			//window.location.replace("/profile");
	  }else if(convertedData['response'] == 'redirect'){
			 window.location.replace("/logout");
	  }else{
		  alert("Qualcosa è andato storto");
	  }
		
		
	});  
}