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
		<jsp:include page="head.jsp" />
		</head>
		<body id="body1">
		<br />
		<jsp:include page="top.jsp" />
		<h:form id="form1">
			<!--div class="generalContent"-->
			<h1 class="applicationHeading">Thjonustubeidni</h1>
			<div class="header">
			<h1>1. Upplysingar um neisluveitu</h1>
			<div class="phases">
			<ul>
				<li>1</li>
				<li>2</li>
				<li class="current">3</li>
			</ul>
			</div>
			</div>
			<div class="info">
			<div class="personInfo" id="name">Jon Jonsson</div>
			</div>
			<h1 class="subHeader topSubHeader">Tilkynning um rafverktoku</h1>
			<div class="formSection">
		
			
			<!--  taka  -->
			<fieldset>
			<h:outputText value="Taka mæli..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['taka']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="takaAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="takaDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				id="taka" 
				value="#{maelir.numer}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="taka" 
				value="mæli numer"
				rendered="#{maelir.valid}"/>
				<h:message for="taka"></h:message>
				</h:column>
			</h:dataTable>
			</fieldset>
			
			<!-- fyrir -->
			<fieldset>
			<h:outputText value="Fyrir er..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['fyrir']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="fyrirAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="fyrirDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				id="fyrir" 
				value="#{maelir.numer}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="fyrir" 
				value="mæli numer"
				rendered="#{maelir.valid}"/>
				<h:message for="fyrir"></h:message>
				</h:column>
			</h:dataTable>
			</fieldset>
			
			<!-- setja maeli -->	
			<fieldset>
			<h:outputText value="Setja mæli..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['setja']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="setjaAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="setjaDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:selectOneRadio 
				value="#{maelir.fasa}"
				rendered="#{maelir.valid}">
					<f:selectItems value="#{RafverktakaInitialdata.maeliListiSelects}"/>
				</h:selectOneRadio>
				<h:outputLabel 
				for="setja" 
				value="Stærð"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				size="4" 
				id="setja" 
				value="#{maelir.ampere}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="setja" 
				value="A"
				rendered="#{maelir.valid}"/>
				<h:message for="setja"></h:message>
				<h:outputLabel  
				for="setjaT" 
				value="Taxti"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				size="4" 
				id="setjaT" 
				value="#{maelir.taxti}"
				rendered="#{maelir.valid}"/>
				</h:column>
				</h:dataTable>
			</fieldset>
			
			<!--  flutt a  -->
			<fieldset>
			<h:outputText value="Flutt á..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['fluttA']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="fluttAAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="fluttADelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				id="fluttA" 
				value="#{maelir.numer}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="fluttA" 
				value="mæli numer"
				rendered="#{maelir.valid}"/>
				<h:message for="fluttA"></h:message>
				</h:column>
			</h:dataTable>
			</fieldset>
			
			<!-- flutt af -->
			<fieldset>
			<h:outputText value="Flutt af..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['fluttAf']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="fluttAfAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="fluttAfDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				id="fluttAf" 
				value="#{maelir.numer}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="fluttAf" 
				value="mæli numer"
				rendered="#{maelir.valid}"/>
				<h:message for="fluttAf"></h:message>
				</h:column>
			</h:dataTable>
			</fieldset>

			<!-- hjalpataeki -->
			<fieldset>
			<h:outputText value="Setja hjálpatæki..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['hjalpataeki']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="hjalpataekiAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="hjalpataekiDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				id="hjalpataeki" 
				value="#{maelir.hjalpataeki}"
				rendered="#{maelir.valid}"/>
				</h:column>
			</h:dataTable>
			</fieldset>
			
			<!-- setja maeli -->
			<fieldset>
			<h:outputText value="Setja straumspennamæli..."/>
			<h:dataTable value="#{TilkynningVertakaBean.list['straumspenna']}" var="maelir">
				<h:column>
				<h:commandButton 
				id="straumspennaAdd"
				action="#{maelir.add}" 
				value="+"
				rendered="#{! maelir.valid}"/>
				<h:commandButton 
				id="straumspennaDelete"
				action="#{maelir.delete}" 
				value="-"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="straumspenna" 
				value="Stærð"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				size="4" 
				id="straumspenna" 
				value="#{maelir.ampere}"
				rendered="#{maelir.valid}"/>
				<h:outputLabel 
				for="straumspenna" 
				value="A"
				rendered="#{maelir.valid}"/>
				<h:message for="straumspenna"></h:message>
				<h:outputLabel  
				for="straumspennaT" 
				value="Taxti"
				rendered="#{maelir.valid}"/>
				<h:inputText 
				size="4" 
				id="straumspennaT" 
				value="#{maelir.taxti}"
				rendered="#{maelir.valid}"/>
				</h:column>
				</h:dataTable>
			</fieldset>
			
			<!--  26  -->
			<div class="formItem">
				<h:outputLabel for="skyringar">Skyringar</h:outputLabel>
			<h:inputTextarea id="skyringar" rows="3" cols="60" value="#{TilkynningVertakaBean.skyringar}"/>
			</div>
			
			<div class="button">
				<h:commandButton 
				action="back" 
				value="til baka"/>
				<h:commandButton 
				action="#{TilkynningVertakaBean.store}" 
				value="geyma"/>
				<h:commandButton 
				action="#{TilkynningVertakaBean.send}" 
				value="senda"/>
			</div>
			<!--/div-->
			<br />
			</div>
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
