<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Ember Starter Kit</title>
	<link rel="stylesheet" href="resources/css/normalize.css">
	<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<header>
		CATALOGO DE CURSOS
	</header>

	<script type="text/x-handlebars">
		Hello, <strong>{{firstName}} {{lastName}}</strong>!
    </script>

	<div>
		1: {{outlet}}
	</div>

	<script type="text/x-handlebars">
  		<div>
    		2: {{outlet}}
  		</div>
	</script>

<script type="text/x-handlebars">	
{{#each people}}
  Hello, {{name}}!
{{else}}
  Sorry, nobody is here.
{{/each}}
</script>

<script type="text/x-handlebars">	
{{#if appplication}}
  Hello, {{appplication.firstName}}!
{{/if}}
</script>
	
	<footer>
		&copy;2013 Carlos Alonso González
	</footer>
	
	<script src="resources/js/libs/jquery-1.9.1.js"></script>
	<script src="resources/js/libs/handlebars-1.0.0-rc.3.js"></script>
	<script src="resources/js/libs/ember-1.0.0-rc.3.js"></script>
	<!-- <script src="resources/js/app.js"></script> -->
	<script type="text/javascript">
	window.App = Ember.Application.create();

	App.ApplicationController = Ember.Controller.extend({
	  firstName: "Trek",
	  lastName: "Glowacki"
	});
	</script>
</body>
</html>