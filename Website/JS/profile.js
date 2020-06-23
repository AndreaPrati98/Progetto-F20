$(document).ready(function(){
    $('.collapsible').collapsible();
});

$('a.confLink').click(function(){

		var configurationId = $(this).attr('id');
		var text = $(this).text();

		if(text == 'remove'){
			removeConfiguration(configurationId);
		}else if(text == 'rename'){
			renameConfiguration(configurationId);
		}else{

		}
	}
);

function removeConfiguration(configurationId){
  let posting = $.post( "/profile/remove", {id: configurationId});
}

function renameConfiguration(configurationId){
  let newName = $('input#' + configurationId).val();
  let posting = $.post( "/profile/rename", {id: configurationId, name: newName});
}