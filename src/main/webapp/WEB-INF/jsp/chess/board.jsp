<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

	var json = null;
	
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
		xmlhttp.overrideMimeType('text/xml');
	} else if(window.ActiveXObject){
		xmlhttp = new ActiveXObject();
	} else {
		alert("Este navegador não suporta AJAX.")
	}
	
	$(document).ready(function() {
	    $("img").click(function(event) {
	    	
	    	if(window.XMLHttpRequest){
	    		xmlhttp = new XMLHttpRequest();
	    		xmlhttp.overrideMimeType('text/xml');
	    	} else if(window.ActiveXObject){
	    		xmlhttp = new ActiveXObject();
	    	} else {
	    		alert("Este navegador não suporta AJAX.")
	    	}
	    	
	    	if(json == null){
	    		
	    		var id = event.target.id;
		    	
		    	xmlhttp.onreadystatechange = function(){
		    		
		    		if (this.readyState == 4 && this.status == 200) {

						json = JSON.parse(this.responseText);
						
						if ( json.list.length > 0 ) {

							json.list.forEach(function(tile, index){
								
								var id = "" + tile.x + "" + tile.y;
								
								var element = document.getElementById(id);
								
								element.style.background = 'green';
							});	
						}
		    		}
		    	};
		    	xmlhttp.open("GET", "moves/" + id[0] + "/" + id[1], true);
		    	xmlhttp.send();
	    		
	    	} else {
	    	
	    		json.list.forEach(function(tile, index){
	    			
	    			var id = "" + tile.x + tile.y;
					
					var element = document.getElementById(id);
	    		
	    			element.style.background = '';
	    			json = null;
	    			
	    		});
	    		
	    		var id = event.target.id;
	    		
		    	xmlhttp.open("GET", "moves/" + id[0] + "/" + id[1], true);
		    	xmlhttp.send();
		    	location.reload();
	    	}
	    });
	});
	
	<%--
	$(document).ready(function() {
	    $("div").click(function(event) {
	    	
	    	if(window.XMLHttpRequest){
	    		xmlhttp = new XMLHttpRequest();
	    		xmlhttp.overrideMimeType('text/xml');
	    	} else if(window.ActiveXObject){
	    		xmlhttp = new ActiveXObject();
	    	} else {
	    		alert("Este navegador não suporta AJAX.")
	    	}
	    	
	    	var id = event.target.id;
		    	
		    xmlhttp.open("GET", "moves/" + id[1] + "/" + id[2], true);
		    xmlhttp.send();
	    });
	});
	--%>
</script>
<title>Chess</title>
</head>
<body style="background-color: #fefce7">

	<div style="position:absolute;">
		<c:forEach begin="0" end="7" step="1" varStatus="i">
		<c:forEach begin="0" end="7" step="1" varStatus="j">
			<div style="margin: 0; padding: 0; display: inline-block; height: 64px; width: 64px; background-color: ${bv.getTile((i.index + j.index) % 2)};">
			
			<img id="${j.index}${i.index}" style="margin: 0; padding: 0; display: inline-block; height: 64px; width: 64px;" alt="" src="${bv.getPieceImg(slots[i.index][j.index])}">
			
			</div>
		</c:forEach>
		<br>
	</c:forEach>
	</div>

		
	<button style="position:absolute; left:550px;" onclick="myFunction()">Change Theme</button>
	
	<script>
	function myFunction() {

		alert("Test");
	}
	</script>


<%--
	<c:forEach var="lineSlot" items="${slots}">
		<c:forEach var="slot" items="${lineSlot}">
			<c:out value="${slot.getPiece().getName()}"></c:out>
		</c:forEach>
		<br>
	</c:forEach>
 --%>
</body>
</html>