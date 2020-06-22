$(document).ready(function(){
    $('.collapsible').collapsible();
});

$('a.confLink').click(function(){
		alert("Inizio JQuery");
		var configurationId = $("#test").text();
		alert("Meta JQuery");
		removeConfiguration(configurationId);
		alert("Fine JQuery");
	}
);

function removeConfiguration(configurationId){
  let dataToSend = "";
  alert("removeCon");
  let posting = $.post( "/profile/remove", {id: configurationId});
  alert("post fatto");

  posting.done(function(data) {
	console.log(data);
	var convertedData =  JSON.parse(data);
	alert('pippo');
	console.log(convertedData);
	console.log(convertedData['response']);
	
  });
}