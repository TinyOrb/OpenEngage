/*$(document).ready(function(){
	$("#searchbutton").click(function(){
		key = $("#searchkey").val()
		console.log(key);
	})
});*/

function handleAPILoaded() {
	  $('#searchbutton').attr('disabled', false);
	}

	// Search for a specified string.
function search() {
	  var q = $('#searchkey').val();
	  var request = gapi.client.youtube.search.list({
	    q: q,
	    part: 'snippet'
	  });

	  request.execute(function(response) {
	    var str = JSON.stringify(response.result);
	    $('#api-search').html('<pre>' + str + '</pre>');
	  });
	}