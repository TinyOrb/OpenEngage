$(document).ready(function(){
	var ajx = $.ajax({
		url:"getPublicPost",
		method:"POST",
		data:{},
		async:false
		});
	ajx.done(function(msg){
		if(msg != "No Data"){
			data = JSON.parse(msg);
			var post = new Array()
			for(d in data){
				url = "/jeera/"+data[d]["getPAuthor"]+"/"+data[d]["getIdPublish_Post"]+".html";
				div = "<div class='graybox'><a href='"+"/jeera/"+data[d]["getPAuthor"]+"/"+data[d]["getIdPublish_Post"]+".html"+"'>"+data[d]["getHeading"]+"</a></div>";
				post.push({url:url,div:div})
				$("div#hbody").append("<a href='"+"/jeera/"+data[d]["getPAuthor"]+"/"+data[d]["getIdPublish_Post"]+".html"+"'><div class='graybox'><b>"+data[d]["getHeading"]+"</b></div></a>");
			}
			console.log(post)
		}
		else{
			console.log(msg);
		}
	});
	
	$("#search_button").click(function(){
		var search = $("#search_string").val();
		var query = $.ajax({
			url:"searchPublicPost",
			method:"POST",
			data:{q:search},
			async:false
			});
		query.done(function(msg){
			if(msg != "No Data"){
				console.log(msg);
			}
			else{
				console.log(msg);
			}
		});
	});
});