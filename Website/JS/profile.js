$(document).ready(function(){

});

$('a.confLink').click(function(){

		var configurationId = $(this).attr('id');
		removeConfiguration(configurationId);
		
	}
);

function removeConfiguration(configurationId){
  let posting = $.post( "/profile/remove", {id: configurationId});
}
