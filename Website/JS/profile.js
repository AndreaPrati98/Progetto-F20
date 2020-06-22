$(document).ready(function(){
    $('.collapsible').collapsible();
});

$('a.confLink').click(function(){

		var configurationId = $(this).attr('id');
		alert(configurationId);
		removeConfiguration(configurationId);
	}
);

function removeConfiguration(configurationId){
  let dataToSend = "";
  let posting = $.post( "/profile/remove", {id: configurationId});
}