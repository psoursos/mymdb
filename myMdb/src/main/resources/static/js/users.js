//var listUsers;
var removeUser;

$(document).ready(function(){
	

	loadUsers();
	//console.log(removeUser);
	removeUser = $("#user_delete");
	console.log(removeUser);
	//removeUser.
	//$('#user_delete').click( function() {
	//console.log("works");
	//alert('Are you sure you want to delete this user?');
	//deleteUser();
	
	//});

});	
	
	function loadUsers() {
			url = "/app-api/admin/";
	$.get(url, function(responseJson) {
		//usersList.empty();
		
		var user_data = '';
		$.each(responseJson, function(index, user) {
			user_data += '<tr>';
			user_data += '<td>' + user.firstName + '</td>';
			user_data += '<td>' + user.lastName+'</td>';
			user_data += '<td>' + user.username+'</td>';
			user_data += '<td>' + user.email+'</td>';
			user_data += '<td>' + user.role+'</td>';
			user_data += '<td>' + user.confirmed+'</td>';
			user_data += '<td><a value ="'+user.userId+'" class="btn btn-info btn-sm" id="user_delete" onclick=\'updateUser('+user.userId+')\'">Update</a></td>';
			user_data += '<td ><a value ="'+user.userId+'" class="btn btn-danger btn-sm" id="user_delete" onclick=\'deleteUser('+user.userId+')\'">Delete</a></td>';
			user_data += '</tr>';
			//$.each("#user").val().text().appendTo();
			//alert(user.name);
		});
		$('#usersList').append(user_data);
		
	}).done(function() {
		alert('Done');
	}).fail(function(){
		alert('Fail');
	});
	};
	
	
	function deleteUser(id){
			console.log("delete"+id);
	        alert('Are you sure you want to delete this user?');
	};
	
		function updateUser(id){
			console.log("update"+id);
	        alert('Are you sure you want to update this user?');
            url = "/admin/update?id="+id;
            window.location.href = url;
	};
	
/*
$(document).ready(function() {
	
	removeUser = $("#delete-btn");
	removeUser.click(function() {
		consolelog("works");
		alert('Are you sure you want to delete this user?');
	});

	
*/





 




