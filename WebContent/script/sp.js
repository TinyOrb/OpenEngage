$(document).ready(function(){
	$("#submit").click(function(){
		
		var pass = $("#pass").val();
		var pass_x = $("#pass_x").val();
		var request_code = window.location.href.split("?")[1].split("=")[1];
		
		if(pass.length < 8){
			alert("Password length is short, minimum character length should be 8")
		}
		else if(pass.length >14)
			{
				alert("Password length should not be more than 14")
			}
		else{
			if(pass == pass_x)
				{
					set_password(pass, request_code)
				}
			else
				{
					alert("password doesn't match")
				}
		}
		
	})
});

function set_password(pass, request_code)
{
			$ajx = $.ajax({
				url: "Set_new_password",
				method: "POST",
				data: {pass : pass, request_code:request_code}
			});
			$ajx.done(function(msg){
				if(msg === "success")
					{
					recovery_status(1);
					}
				else if(msg == "exception")
					{
					recovery_status(-1);
					}
				else if(msg == "fail")
				{
				recovery_status(2);
				}
				else
					{
					recovery_status(99);
					}
			});
			$ajx.fail(function(jqXHR, textStatus){
				recovery_status(3);
			});
		
}

function recovery_status(status)
{
	switch(status)
	{
		case 1: //user exist and send mail with change password to relevant mail address
			console.log("success");
			break;
		case 2: // no such mail exist
			console.log("failed");
			break;
		case 3:
			console.log("Invalid email, enter valid email address");
			break;
		default: //Something went wrong
			console.log("status 500, internal error");
			break;
	}
}