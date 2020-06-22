$(document).ready(function(){
    $('.collapsible').collapsible();
});

$('a.confLink').click(function(){

		var configurationId = $(this).attr('id');
		var text = $(this).text();
		alert(configurationId);
		if(text == 'remove'){
			removeConfiguration(configurationId);
		}else if(text == 'rename'){
			alert('rename');
		}else{
			alert("errore");
		}
	}
);

function removeConfiguration(configurationId){
  let dataToSend = "";
  let posting = $.post( "/profile/remove", {id: configurationId});
}