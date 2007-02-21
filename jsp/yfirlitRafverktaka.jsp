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

				<x:saveState id="yfirlitRafverktakaSaveState" value="#{YfirlitRafverktaka}"/>
				
				<h1 class="applicationHeading"><h:outputText value="Yfirlit rafverktaka"/><hr/></h1>
				
				<!--div class="searchFilter">	
					<div class="formItem required">
						<label for="nafnRafverktakaSearch">Nafn rafverktaka</label>
						<x:inputText id="nafnRafverktakaSearch" value="#{RafverktakaListi.searchFilterString}"/>
						<x:commandButton id="searchButton" action="#{RafverktakaListi.filter}" value="Leita"/>
					</div>
				</div-->
					
				<div>
					<h3><h:outputText value="Upplysingar um rafverktaka #{YfirlitRafverktaka.selectedRafverktaki.nafn}"/><hr/></h3>
				</div>
				
				<div>
					<div class="formItem">
					    <label for="nafnVerktakaInput">Nafn</label>
						<h:inputText id="nafnVerktakaInput" value="#{YfirlitRafverktaka.selectedRafverktaki.nafn}"/>
					</div>
					<div class="formItem">
					    <label for="nafnVerktakaFyrirtaekisInput">Nafn fyrirtaekis</label>
						<h:inputText id="nafnVerktakaFyrirtaekisInput" value="#{YfirlitRafverktaka.selectedRafverktaki.nafnFyrirtaekis}"/>
					</div>
					<div class="formItem">
					    <label for="stadaVerktakaMenu">Stada</label>
					    <x:selectOneMenu id="stadaVerktakaMenu" value="#{YfirlitRafverktaka.selectedRafverktaki.stada}">
							<f:selectItem itemLabel="Virkur" itemValue="VIRKUR"/>
							<f:selectItem itemLabel="Ovirkur" itemValue="OVIRKUR"/>
						</x:selectOneMenu>
					</div>
					<div class="formItem">
						<h:commandButton id="updateRafverktakiButton" action="#{YfirlitRafverktaka.updateSelectedRafverktaki}" value="Uppfaera"/>
					</div>
				</div>	
				
				<div class="caseHeader">
					<div class="caseHeading">
						<h:outputText value="Skyrslur" />
					</div>
				</div>
				<div>
					<div style="padding: 10px;" >
						<h:outputLink value="/pages/skyrslaFyrriSkodanirRafverktaka.jsf?rafverktakiId=#{YfirlitRafverktaka.selectedRafverktaki.id}" ><h:outputText value="Skyrsla um fyrri skodanir"/></h:outputLink>
					</div>
				</div>
				
				
				<div class="caseHeader">
					<div class="caseHeading">
						<h:outputText value="Rafverktokur a vegum #{YfirlitRafverktaka.selectedRafverktaki.nafn}:" />
					</div>
				</div>
				<div>
					<div>
						<label for="filterVerktakaStatuses">Stada verks</label>
						
						<x:selectOneMenu id="filterVerktakaStatuses" value="#{YfirlitRafverktaka.selectedStatus}">
							<f:selectItems value="#{YfirlitRafverktaka.possibleStatusesSelects}"/>
						</x:selectOneMenu>
						<x:commandButton id="filterVerktakaStatusesButton" action="#{RafverktakaListi.filter}" value="Finna"/>
					</div>
				</div>
				<div>
					<x:dataTable value="#{YfirlitRafverktaka.rafverktokur}" var="verktaka" styleClass="caseTable ruler" rendered="#{RafverktakaListi.rafverktakiSelected}">
						<h:column>
							<f:facet name="header">
								<h:outputText value="Id"/>
							</f:facet>
							<h:outputText value="#{verktaka.id}"/>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputText value="Orkufyrirtaeki"/>
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
						<h:column>
							<f:facet name="header">
								<h:outputText value="Medhondla"/>
							</f:facet>
							<h:outputLink value="/pages/sendaTilSkodunar.jsf?raferktakaId=#{verktaka.id}" ><h:outputText value="Senda til skodunar"/></h:outputLink>
						</h:column>
					</x:dataTable>
				</div>
			</div>
			
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
