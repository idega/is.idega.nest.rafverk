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
				<li class="current">2</li>
				<li>3</li>
			</ul>
			</div>
			</div>
			
			<div class="info">
			<div class="personInfo" id="name">Jon Jonsson</div>
			</div>
			
			<h1 class="subHeader topSubHeader">Tilkynning um rafverktoku</h1>

			<div class="formSection">
			
			<div class="helperText">Adrar upplysingar</div>
			
			<!-- 5 -->
			<div class="formItem required">
				<h:outputLabel for="notkunarflokkar">Notkunarflokkur</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneMenu  id="notkunarflokkar" value="#{TilkynningVertakaBean.notkunarflokkur}">
					<f:selectItems value="#{RafverktakaInitialdata.notkunarflokkurListiSelects}"/>
				</h:selectOneMenu>
			</div>
			
			<!-- 6 -->
			<div class="formItem required">
				<h:outputLabel for="heimtaug">Heimtaug</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio id="heimtaug" value="#{TilkynningVertakaBean.heimtaug}">
					<f:selectItems value="#{RafverktakaInitialdata.heimtaugListiSelects}"/>
				</h:selectOneRadio>
			</div>
			
			<!-- 7 -->
			<div class="formItem required">
				<h:outputLabel for="heimtaugTengist">Heimtaug tengist i</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio id="heimtaugTengist" value="#{TilkynningVertakaBean.heimtaugTengist}">
					<f:selectItems value="#{RafverktakaInitialdata.heimtaugTengistListiSelects}"/>
				</h:selectOneRadio>
			</div>
			
			<!-- 8 -->
			<div class="formItem required">
				<h:outputLabel for="stofnKvisl1">Stofn/kvisl</h:outputLabel>
			</div>
				<div>
				<h:inputText size="4" id="stofnKvisl1" value="#{TilkynningVertakaBean.stofn1}"/>
				<h:outputLabel for="stofnKvisl1"> x </h:outputLabel>
				<h:message for="stofnKvisl1"></h:message>
				<h:inputText size="4" id="stofnKvisl2" value="#{TilkynningVertakaBean.stofn2}"/>
				<h:outputLabel  for="stofnKvisl2"> + </h:outputLabel>
				<h:message for="stofnKvisl2"></h:message>
				<h:inputText size="4" id="stofnKvisl3" value="#{TilkynningVertakaBean.stofn3}"/>
				<h:outputLabel for="stofnKvisl3"> mm&#178; </h:outputLabel>
				<h:message for="stofnKvisl3"></h:message>
				</div>

			<!-- 9 -->
			<div class="formItem required">
				<h:outputLabel for="adaltafla">Adaltafla/Maelitafla</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio id="adaltafla" value="#{TilkynningVertakaBean.adaltafla}">
					<f:selectItems  value="#{RafverktakaInitialdata.adaltaflaListiSelects}"/>
				</h:selectOneRadio>
			</div>
			
			<!-- 10 -->
			<div class="formItem required">
				<h:outputLabel for="adaltafla">Varnarráðstöfun</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectManyCheckbox id="varnaradstoefun" value="#{TilkynningVertakaBean.varnarradstoefun}">
					<f:selectItems  value="#{RafverktakaInitialdata.varnarradstoefunListiSelects}"/>
				</h:selectManyCheckbox>
			</div>

			<fieldset>
			<!-- 11 -->
			<div class="formItem required">
				<h:outputLabel for="beidni">Beiðni um</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectManyCheckbox  id="beidni" value="#{TilkynningVertakaBean.beidniUm}">
					<f:selectItems value="#{RafverktakaInitialdata.beidniListiSelects}"/>
				</h:selectManyCheckbox>
			</div>
			
			<!-- 12 -->
			<div class="formItem">
				<h:outputLabel for="beidniKiloWatt">Uppsett afl </h:outputLabel>
			</div>
			<div>
				<h:inputText size="8" id="beidniKiloWatt" value="#{TilkynningVertakaBean.uppsett}"/>
				<h:outputLabel for="beidniKiloWatt"> kW</h:outputLabel>
				<h:message for="beidniKiloWatt"></h:message>
			</div>	
			</fieldset>
			
			<!-- 13a -->
			<div class="formItem required">
				<h:outputLabel for="stadurMaelis">Staður mælis</h:outputLabel>
			<h:inputText size="24" id="stadurMaelis" value="#{TilkynningVertakaBean.stadurMaelir.stadur}"/>
			</div>
				
			<!-- 13b -->
			<div class="formItem required">
				<h:outputLabel for="numerToeflu">Númer töflu</h:outputLabel>
			<h:inputText size="24" id="numerToeflu" value="#{TilkynningVertakaBean.numerToeflu}"/>
			<h:message for="numerToeflu"></h:message>
			</div>
				
			<fieldset>
			<!-- 14 -->	
			<div class="formItem required">
				<h:outputLabel for="spennukerfi">Spennukerfi</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio  id="spennukerfi" value="#{TilkynningVertakaBean.spennukerfi}">
					<f:selectItems value="#{RafverktakaInitialdata.spennukerfiListiSelects}"/>
				</h:selectOneRadio>
			</div>
			
			<!-- 15 -->
			<div class="formItem">
				<h:outputLabel for="annadSpennukerfi">Annað</h:outputLabel>
				<h:inputText size="24" id="annadSpennukerfi" value="#{TilkynningVertakaBean.annad}"/>
			</div>
			</fieldset> 
			
			<!--  formsection end -->
			</div>	

			<div class="button">
				<h:commandButton 
				action="back" 
				value="til baka"/>
				<h:commandButton 
				action="next" 
				value="afram"/>
			</div>
			<!--/div-->
			<br />
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
