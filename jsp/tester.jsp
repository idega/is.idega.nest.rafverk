<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui"
	xmlns:x="http://myfaces.apache.org/extensions">
	<jsp:directive.page language="java"
		contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<f:view>
		<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1" />
		<title>Insert title here</title>
		</head>
		<body>
		<!--h:form id="myform2">
			<x:outputLabel value="Flippari" for="mytextinput" /> 
				<h:inputText
				id="mytextinput" value="#{MyTestBean.flippTexti}">
			</h:inputText>
			<h:commandLink id="afram" immediate="true"
				action="#{MyTestBean.doFlip}">
				<h:outputText value="klikka her3" />
			</h:commandLink>
		</h:form-->
		<h:form id="MyTestBeanForm">
			<h:panelGrid columns="3">
				<h:outputText value="Flipp Texti:"/>
				<h:inputText id="flippTexti" value="#{MyTestBean.flippTexti}"/>
				<h:message for="flippTexti"/>
				<h:outputText value="Keys:"/>
				<h:selectOneMenu id="keys">
					<f:selectItems value="#{MyTestBean.keys}"/>
				</h:selectOneMenu>
				<h:message for="keys"/>
				<h:outputText value="Manys:"/>
				<h:selectManyCheckbox id="manys">
					<f:selectItems value="#{MyTestBean.keys}"/>
				</h:selectManyCheckbox>
				<h:message for="manys"/>
			</h:panelGrid>
			<h:commandButton action="#{MyTestBean.doForm}" value="Submit"/>
		</h:form>
		</body>
		</html>
	</f:view>
</jsp:root>
