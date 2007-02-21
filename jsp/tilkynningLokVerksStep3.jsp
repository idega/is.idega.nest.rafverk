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
			<h1 class="applicationHeading">Skyrsla</h1>
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
			
			<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu</h1>
			
			<div class="formSection">
			
			<fieldset>
			<!-- 16 -->
			<div class="formItem">
				<h:outputLabel for="hringrasarvidnam">Hringrásarviðnám aðal-/greinitöflu</h:outputLabel>
			</div>
			<div>
				<h:inputText id="hringrasarvidnam" value="#{TilkynningLokVerksBean.hringrasarvidam}" />
				<h:outputLabel for="hringrasarvidnam">&#937;</h:outputLabel>
				<h:message for="hringrasarvidnam"></h:message>
			</div>	
						
			<!-- 17 -->
			<div class="formItem">
				<h:outputLabel for="skammhlaupsstraumur">Skammhlaupsstraumur aðal-/greinitöflu</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="skammhlaupsstraumur" value="#{TilkynningLokVerksBean.skammhlaupsstraumur}" />
				<h:outputLabel for="skammhlaupsstraumur">A</h:outputLabel>
				<h:message for="skammhlaupsstraumur"></h:message>
			</div>	
			</fieldset>
			
			<fieldset>
			<!-- 19 -->
			<div class="formItem">
				<h:outputLabel for="einangrunNeysluveitu">Einangrun neysluveitu</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="einangrunNeysluveitu" value="#{TilkynningLokVerksBean.einangrunNeysluveitu}"/>
				<h:outputLabel for="einangrunNeysluveitu">M&#937;</h:outputLabel>
				<h:message for="einangrunNeysluveitu"></h:message>
			</div>
			<!-- 20 -->
			<div class="formItem">
				<h:outputLabel for="hringrasarvidnamJardskaut">Hringrásarviðnámsmæling jarðskauts/sp.jöfnunar</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="hringrasarvidnamJardskaut" value="#{TilkynningLokVerksBean.hringrasarvidnamJardskaut}"/>
				<h:outputLabel for="hringrasarvidnamJardskaut">&#937;</h:outputLabel>
				<h:message for="hringrasarvidnamJardskaut"></h:message>
			</div>	
			</fieldset>
			
			<fieldset>
			<!-- 21 -->
			<div class="formItem">
				<h:outputLabel for="skammhlaupsstraumurNeysluveitu">Skammhlaupsstraumur neysluveitu</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="skammhlaupsstraumurNeysluveitu" value="#{TilkynningLokVerksBean.skammhlaupsstraumurNeysluveitu}"/>
				<h:outputLabel for="skammhlaupsstraumurNeysluveitu">A</h:outputLabel>
				<h:message for="skammhlaupsstraumurNeysluveitu"></h:message>
			</div>	
			<!-- 22 -->
			<div class="formItem">
				<h:outputLabel for="hringrasarvidnamNeysluveitu">Hringrásarviðnámsmæling neysluveitu</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="hringrasarvidnamNeysluveitu" value="#{TilkynningLokVerksBean.hringrasarvidnamNeysluveitu}"/>
				<h:outputLabel for="hringrasarvidnamNeysluveitu">&#937;</h:outputLabel>
				<h:message for="hringrasarvidnamNeysluveitu"></h:message>
			</div>	
			</fieldset>
			
			<fieldset>
			<!-- 23 -->
			<div class="formItem">
				<h:outputLabel for="maeldSpennaFasiN">Maeld spenna Fasi-N</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="maeldSpennaFasiN" value="#{TilkynningLokVerksBean.maeldSpennaFasiN}"/>
				<h:outputLabel for="maeldSpennaFasiN">V</h:outputLabel>
				<h:message for="maeldSpennaFasiN"></h:message>
			</div>	
			<!-- 24 -->
			<div class="formItem">
				<h:outputLabel for="maeldSpennaFasiFasi">Maeld spenna Fasi-Fasi</h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="maeldSpennaFasiFasi" value="#{TilkynningLokVerksBean.maeldSpennaFasiFasi}"/>
				<h:outputLabel for="maeldSpennaFasiFasi">V</h:outputLabel>
				<h:message for="maeldSpennaFasiFasi"></h:message>
			</div>
			</fieldset>
			
			<fieldset>
			<!-- 25  -->
			<div class="formItem required">
				<h:outputLabel for="lekastraumsrofi">Lekastraumsrofi</h:outputLabel>
			</div>
			<div class="checkboxItem">
			<h:selectOneRadio id="lekastraumsrofi" value="#{TilkynningLokVerksBean.lekastraumsrofi}">
				<f:selectItems value="#{RafverktakaInitialdata.lekastraumsrofiListiSelects}"/>
			</h:selectOneRadio>
			</div>
			
			<!-- 27 -->
			<div>
				<h:inputText size="4" id="spennuhaekkunUtleysingVolt" 
				value="#{TilkynningLokVerksBean.spennuhaekkunUtleysingVolt}"/>
				<h:outputLabel for="spennuhaekkunUtleysingVolt">V</h:outputLabel>
				<h:message for="spennuhaekkunUtleysingVolt"></h:message>
			</div>
			
			<!-- 29 -->
			<div>
				<h:inputText size="4" id="lekastraumsrofaUtleysingMillisecond" 
				value="#{TilkynningLokVerksBean.lekastraumsrofaUtleysingMillisecond}"/>
				<h:outputLabel for="lekastraumsrofaUtleysingMillisecond">ms</h:outputLabel>
				<h:message for="lekastraumsrofaUtleysingMillisecond"></h:message>
			</div>
			</fieldset>
			
			
			<!--  30  -->
			<div class="formItem">
				<h:outputLabel for="skyringarMaelingarLokVerks">Skyringar</h:outputLabel>
			<h:inputTextarea 
				id="skyringarMaelingarLoksVerks" 
				rows="3" 
				cols="60"
				value="#{TilkynningLokVerksBean.skyringarMaelingar}"/>
			</div>
			
			
			<!-- formsection ends -->
			</div>
				
			<div class="button">
				<h:commandButton 
				action="back" 
				value="til baka"/>
				<h:commandButton 
				action="#{TilkynningLokVerksBean.store}" 
				value="geyma"/>
				<h:commandButton 
				action="#{TilkynningLokVerksBean.send}" 
				value="senda"/>
			</div>

			
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
