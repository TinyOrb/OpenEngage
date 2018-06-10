$(document).ready(function(){
	$("#submit").click(function(){
		
		var email_id = $("#email_id").val();
		init_password_recovery(email_id);
		
	})
});

function init_password_recovery(email_id)
{
	if(validate(email_id))
		{
			$ajx = $.ajax({
				url: "init_password_recovery",
				method: "POST",
				data: {email : email_id}
			});
			$ajx.done(function(msg){
				if(msg === "success")
					{
					recovery_status(1);
					}
				if(msg == "exception")
					{
					recovery_status(-1);
					}
				if(msg == "fail")
				{
				recovery_status(2);
				}
			});
			$ajx.fail(function(jqXHR, textStatus){
				recovery_status(3);
			});
		}
	else{
		recovery_status(3);
	}
}

function validate(sEmail)
{
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    if (filter.test(sEmail)) {
	        return true;
	    }
	    else {
	        return false;
	    }
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