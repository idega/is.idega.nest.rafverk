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
			<h1 class="applicationHeading">Fyrirspurn til rafverkaka</h1>
			<div class="header">
				<h1>Senda fyrirspurn</h1>
			</div>
			<div class="info">
				<div class="personInfo" id="name">Neytendastofa</div>
			</div>
			<h1 class="subHeader topSubHeader">Skrifadu inn texta</h1>
			<div class="formSection">
				<div class="formItem required">
					<label for="messageSubject">Fyrirsogn</label>
					<x:inputText id="messageSubject"/>
				</div>
				<div class="formItem required">
					<label for="messageBody">Meginmal</label>
					<x:inputTextarea id="messageBody"/>
				</div>
			</div>
			<div class="bottom">
				<h:commandLink id="msgSendLink" styleClass="button">
					<span class="buttonSpan">
						<span class="left"></span>
						<span class="middle">Senda</span>
						<span class="right"></span>
					</span>
				</h:commandLink>
			</div>
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
