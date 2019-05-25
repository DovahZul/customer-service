<html>
<head>
<TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>
 
<style type="text/css">
body {
    background-image:
        url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>

<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    function doAjaxPost() {

        $.ajax({
            type: "GET",
            url: "subView",
            success: function(response) {
                $("#subViewDiv").html( response );
            }
        });
    }
</script>

 

</head>
 
<body>
    <input type="button" value="GO!" onclick="doAjaxPost();" />
	<div id="subViewDiv"></div>
</body>
</html>