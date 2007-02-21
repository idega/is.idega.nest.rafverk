<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui"
	xmlns:x="http://myfaces.apache.org/extensions">
	<jsp:directive.page contentType="text/html;charset=UTF-8"
		pageEncoding="UTF-8" />
	<f:view>
		<html id="html1">
		<head id="head1">
		<link id="link1" url="/resources/stylesheet.css" />
		<jsp:include page="head.jsp" />
		</head>
		<body id="body1">
		<br />
		<jsp:include page="top.jsp" />
		<h:form id="form1">
			<div class="generalContent">
				<h1 class="applicationHeading">Senda til skodunar</h1>
				<div class="header">
					<h1>Senda i skodun til</h1>
				</div>
				<div class="formSection">
					
					<div class="formItem">
						<label for="orkuveituDrop">Senda til orkufyrirtaekis:</label>
						<x:selectOneMenu id="orkuveituDrop" required="true">
							<f:selectItem itemLabel="Veldu:" itemValue=""/>
							<!--f:selectItems value="#{RafverktakaInitialdata.rafveituListiSelects}">
							</f:selectItems-->
							<f:selectItem itemLabel="Orkuveita Reykjavikur" itemValue="OR"/>
							<f:selectItem itemLabel="Hitaveita Sudurnesja" itemValue="HS"/>
							
						</x:selectOneMenu>
					</div>
					<div class="formItem">
						<x:commandButton id="sendaSkodunButton" value="Senda"></x:commandButton>
					</div>
				</div>
			</div>
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
