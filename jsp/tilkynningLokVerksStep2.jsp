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
				<li class="current">2</li>
				<li>3</li>
			</ul>
			</div>
			</div>
			
			<div class="info">
			<div class="personInfo" id="name">Jon Jonsson</div>
			</div>
			
			<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu</h1>
			
			<div class="formSection">
			
			<fieldset>
			<div class="formItem required">
				<h:outputLabel for="tilkynnt">Tilkynnt er</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio  id="tilkynnt" value="#{TilkynningLokVerksBean.tilkynnt}">
					<f:selectItems value="#{RafverktakaInitialdata.tilkynntListiSelects}"/>
				</h:selectOneRadio>
			</div>
			<div class="formItem">
				<h:outputLabel for="annadTilkynnt">Annað</h:outputLabel>
				<h:inputText size="24" id="annadTilkynnt" value="#{TilkynningLokVerksBean.tilkynntAnnad}"/>
			</div>
			</fieldset> 
		
			<fieldset>
			<div class="formItem required">
				<h:outputLabel for="husnaedis">Notkun húsnæðis</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneMenu  id="notkunarflokkar" value="#{TilkynningLokVerksBean.notkunarflokkur}">
					<f:selectItems value="#{RafverktakaInitialdata.notkunarflokkurListiSelects}"/>
				</h:selectOneMenu>
			</div>
			
			<div class="formItem">
				<h:outputLabel for="starfsemi">Starfsemi hver</h:outputLabel>
				<h:inputText size="8" id="starfsemi" value="#{TilkynningLokVerksBean.starfsemi}" />
			</div>	
			</fieldset>
			
			<div class="formItem">
				<h:outputLabel for="skyringLokVerks">Nánari skyring á þvi sem tilkynnt er</h:outputLabel>
			<h:inputTextarea id="skyringLokVerks" value="#{TilkynningLokVerksBean.skyring}" rows="3" cols="60"/>
			</div>
			
			 
			<fieldset>
			<div class="formItem required">
				<h:outputLabel for="veitukerfi">Veitukerfi</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectOneRadio  id="veitukerfi" value="#{TilkynningLokVerksBean.spennukerfi}">
					<f:selectItems value="#{RafverktakaInitialdata.spennukerfiListiSelects}"/>
				</h:selectOneRadio>
			</div>
			
			<div class="formItem">
				<h:outputLabel for="annadVeitukerfi">Annað</h:outputLabel>
				<h:inputText size="8" id="annadVeitukerfi" value="#{TilkynningLokVerksBean.annad}"/>
			</div>	
			</fieldset>
			
			
			<div class="formItem required">
				<h:outputLabel for="varnarradstoefunLokVerks">Varnarráðstöfun</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectManyCheckbox  id="varnarradstoefunLokVerks" value="#{TilkynningLokVerksBean.varnarradstoefun}">
					<f:selectItems value="#{RafverktakaInitialdata.varnarradstoefunListiSelects}"/>
				</h:selectManyCheckbox>
			</div>
			
			<fieldset>
			<div class="formItem">
				<h:outputLabel for="jardskaut">Jarðskaut/sp.jöfnun</h:outputLabel>
			</div>
			<div class="checkboxItem">
				<h:selectManyCheckbox  id="jardskaut" value="#{TilkynningLokVerksBean.jardskaut}">
					<f:selectItems value="#{RafverktakaInitialdata.jardskautListiSelects}"/>
				</h:selectManyCheckbox>
			</div>
			

			<div class="formItem">
				<h:outputLabel for="annadJardskaut">Annað</h:outputLabel>
				<h:inputText size="8" id="annadJardskaut" value="#{TilkynningLokVerksBean.jardskautAnnad}" />
			</div>	
			</fieldset>
			
			<fieldset>
			

			<div class="formItem">
				<h:outputLabel for="maelisNumerLokVerks">Númer mælis rafveitu</h:outputLabel>
				<h:inputText id="maelisNumerLokVerks" value="#{TilkynningLokVerksBean.maelir.numer}"/>
				<h:message for="maelisNumerLokVerks"></h:message>
			</div>	
						

			<div class="formItem">
				<h:outputLabel for="stadurMaelisLokVerks">Staður mælis</h:outputLabel>
				<h:inputText size="8" id="stadurMaelisLokVerks" value="#{TilkynningLokVerksBean.maelir.stadur}"/>
			</div>	
			</fieldset>		
			

			<div class="formItem">
				<h:outputLabel for="skyringarLokVerks">Skyringar</h:outputLabel>
			<h:inputTextarea 
			id="skyringarLoksVerks" 
			value="#{TilkynningLokVerksBean.skyringar}"
			rows="3" 
			cols="60"/>
			</div>
			
			</div>
				
			<div class="button">
				<h:commandButton 
				action="back" 
				value="til baka"/>
				<h:commandButton 
				action="next" 
				value="afram"/>
			</div>

			
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
