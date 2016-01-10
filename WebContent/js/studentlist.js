$(document).ready(function(){
	var html = $.ajax({
		  url: "/ExMvc/GetJsonServlet?router=student",
		  async: false
		 }).responseText;
	var json = JSON.parse(html);
	for(var i = 0;i<json.length;i++)
	{
		var obj = json[i];
		$("table").append("<tr><td>"+obj.id+"</td><td>"+obj.name+"</td><td>"+obj.age+"</td><td>"+obj.address+"</td><td></td></tr>");
	}
});