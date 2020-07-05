var modals;
$(document).ready(function(){
    $('.collapsible').collapsible();
    let ranges  = document.querySelectorAll("input[type=range]");
    M.Range.init(ranges);
    var elems = document.querySelectorAll('.modal');
	modals = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, {edge:'right'});
});

$('.selection').change(function() {
	//Svuoto quello che dice o no invalid configuration
	$("#configurationCheckResultText").text('');
    let modelString = $(this).attr('id');
    let numberOfComponent = $("#"+modelString+"_number").val();
    console.log(numberOfComponent)
    if (this.checked) {
        add(modelString, numberOfComponent);
    } else {
         let listItemParent = $('.collection').find( "li[name='"+modelString+"']" );
         console.log(listItemParent);
         remove(modelString,listItemParent,numberOfComponent);
    }
});

$('#checkBtn').click(function(){
		checkConfiguration();	
	}
);

$("#resetBtn").click(function(){
	let previouslyCheckedCheckbox = $('.selection:checkbox:checked');
	previouslyCheckedCheckbox.prop("checked", false);
	previouslyCheckedCheckbox.trigger('change');
	$(".collection-item").remove();
	$("#totalPrice").text("0");
	setTimeout(function () {
		$("#performanceLabel").text("0%");
		$("#performanceValue").css("width", "0%");
    }, 1500);
	
});

$("#saveBtn").click(function(){
	//Serve a manipolare solo quelli checkati altrimenti mando più richiesta di rimozione del
	$("#saveBtn").val('true');
	let confName = $("#confRename").val();
	//Richiesta ajx
	if(confName != "")
		save(confName);
	else
		alert("Devi inserire un nome!");
	
});

$("#perfBtn").click(function(){
	performance();
});


$(".numberInput").bind('keyup mouseup', function () {
    console.log("changed");            
    let previousValueString = $(this).data("previousValue");
    let previousValue = parseInt(previousValueString);
    let actualValueString = $(this).val();
    let actualValue = parseInt(actualValueString);
    
    if(actualValue != previousValue){
    	//Significa che qualcosa è cambiato, quindi devo agire
    	
    	//La prima cosa da controllare è se la corrispettiva checkbox è checked
    	//Se è checked vuol dire che devo agire cancellando dal server e poi raggiungendo
    	//tutto, altrimenti non faccio niente
    	
    	//Ricavo dall'id dell'input number l'id della checkbox
    	
    	let inputNumberId = $(this).attr('id');
    	let checkboxId = inputNumberId.substring(0, inputNumberId.indexOf("_number"));
    	
    	let respectiveCheckbox = $("#"+checkboxId);
    	
    	if(respectiveCheckbox.prop("checked") == true){
    		console.log("La checkbox è checkata, devi agire!");
        	//Ora recupero il modello e poi lo elimino per poi raggiungere il numero
    		//corretto
    		//Oss: la checkboxId è il nome del modello
            let listItemParent = $('.collection').find( "li[name='"+checkboxId+"']" );
    		remove(checkboxId, listItemParent, previousValue);
    		//Ora che ha rimosso correttamente le vecchie istanze, posso fare 
    		//le aggiunte del nuovo numero corretto
    		add(checkboxId, actualValue);
    	}
    	

    	//Aggiorno il valore del previousValue
    	$(this).data("previousValue",actualValue);
    	
    }
    
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

function add(modelString, numberOfComponent){
  let dataToSend = "";
  console.log("Numero compo "+numberOfComponent);
  let posting = $.post( "/configuration/add", {model: modelString, number: numberOfComponent});
  
  posting.done(function(data) {
	console.log(data);
	var convertedData =  JSON.parse(data);
	//alert('pippo');
	console.log(convertedData);
	console.log(convertedData['response']);
	  
	
    if(convertedData['response'] == 'ok'){
     	//alert("ok post");
     	var price=parseFloat($("span[name="+modelString+"_price]").text());
     	var totalPrice=parseFloat(convertedData['price']);
     	totalPrice = totalPrice.toFixed(2);
     	$("#totalPrice").text(totalPrice);
	    let addedComponentHtmlList = $(".collection");
	    addedComponentHtmlList.append("<li class='collection-item' name='"+modelString+"'><div>"+modelString+"<a href='#!' class='secondary-content'>"+numberOfComponent+"</a></div></li>");
	 }else if(convertedData['response'] == 'redirect'){
		 window.location.replace("/logout");
	 }else{
		alert("Hai violato i segunti vincoli: "+convertedData['error']); 
	 	$('#'+modelString).prop("checked", false);
	 	$('#'+modelString+"_number").val(1);
	 }
  });
}

function remove(modelString,listItemParent, numberOfComponent=1){
  let dataToSend = "";
  let posting = $.post( "/configuration/remove", {model: modelString});
  posting.done(function(data) {
	  var convertedData =  JSON.parse(data);
	  console.log(convertedData);
		 
	  if(convertedData['response'] == 'ok'){
		let price=parseFloat($("span[name="+modelString+"_price]").text());
     	let totalPrice=parseFloat(convertedData['price']);
		totalPrice = totalPrice.toFixed(2);
		$("#totalPrice").text(totalPrice);
		listItemParent.remove();	
	  }else if(convertedData['response'] == 'redirect'){
			 window.location.replace("/logout");
	  }
  });
  
}
  
function save(confName){
	let posting = $.post( "/configuration/save", {name: confName});
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

	  $('.modal').modal('close');


		
	});  
}

function performance(){
	let posting = $.post( "/configuration/performance");

	posting.done(function(data) {
		  var convertedData =  JSON.parse(data);
		  console.log(convertedData['response']);

		  if(convertedData['response'] != '-1'){
			  	console.log(convertedData['response']);
			  	let value = parseFloat(convertedData['response']);
			  	let str = value+"%";
			  	$("#performanceValue").css('width',str);
			  	$("#performanceLabel").text(str);
		  }else if(convertedData['response'] == 'redirect'){
				 window.location.replace("/logout");
		  }else if(convertedData['response'] == '-1'){
			  alert("Alcuni componenti non sono valutabili");
		  }	
	}); 
	
}