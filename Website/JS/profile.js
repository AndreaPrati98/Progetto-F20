$(document).ready(function(){
    $('.collapsible').collapsible();
});

$('a.confLink').click(function(){

		var configurationId = $(this).attr('id');
		var text = $(this).text();

		alert(text);

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
  let posting = $.post( "/profile/rename", {id: configurationId});
}