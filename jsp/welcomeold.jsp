<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:loadBundle basename="resources.application" var="bundle" />
<f:view>
	<html>
	<head>
	<title><h:outputText value="#{bundle['welcome.title']}" /></title>
	</head>
	<body>

	<h3><h:outputText value="#{bundle['welcome.heading']}" /></h3>

	<p><h:outputText value="#{bundle['welcome.message']}" /></p>

	</body>
	</html>
</f:view>