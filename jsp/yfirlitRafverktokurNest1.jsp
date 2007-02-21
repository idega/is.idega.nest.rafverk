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
			<div class="left">
				<h1 class="applicationHeading">Yfirlit Rafverktakna</h1>
				<hr/>

				<div class="caseHeader">
					<div class="caseHeading">
						Rafverktokur:
					</div>
					<div class="formItem">
						<label for="filterVerktakaStatuses">Stada verks</label>
						
						<x:selectOneMenu id="filterVerktakaStatuses" value="#{RafverktokuListi.selectedStatus}">
							<f:selectItems value="#{RafverktokuListi.possibleStatusesSelects}"/>
						</x:selectOneMenu>
						<x:commandButton id="filterVerktakaStatusesButton" action="#{RafverktakaListi.filter}" value="Finna"/>
					</div>
				</div>

				<x:dataTable value="#{RafverktokuListi.rafverktokur}" var="verktaka" columnClasses="oddRow evenRow" styleClass="caseTable ruler">
					<h:column>
						<f:facet name="header">
							<h:outputText value="Id"/>
						</f:facet>
						<h:outputText value="#{verktaka.id}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Name"/>
						</f:facet>
						<h:outputText value="#{verktaka.orkufyrirtaeki.name}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Nafn"/>
						</f:facet>
						<h:outputText value="#{verktaka.orkukaupandi.heimilisfang.gata.nafn}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Heimilisfang"/>
						</f:facet>
						<h:outputText value="#{verktaka.orkukaupandi.heimilisfang.heimilisfang}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Hushluti"/>
						</f:facet>
						<h:outputText value="#{verktaka.orkukaupandi.heimilisfang.hushluti}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Nafn"/>
						</f:facet>
						<h:outputText value="#{verktaka.rafverktaki.nafn}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Stada"/>
						</f:facet>
						<h:outputText value="#{verktaka.stada}"/>
					</h:column>
				</x:dataTable>
			
			
			</div>
			
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
