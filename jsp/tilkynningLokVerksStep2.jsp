<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"ƒ
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui"
	xmlns:x="http://myfaces.apache.org/extensions"
	xmlns:wf="http://xmlns.idega.com/com.idega.webface">
	<jsp:directive.page contentType="text/html;charset=UTF-8"
		pageEncoding="UTF-8" />
	<f:view>
		<h:form id="form1">
			<!--div class="generalContent"-->
			
			<f:verbatim><h1 class="applicationHeading">Skyrsla</h1></f:verbatim>
			
			<wf:container styleClass="header">
			
			<f:verbatim><h1>1. Upplysingar um neisluveitu
			</h1></f:verbatim>
			
			<!-- phases -->
			<wf:container styleClass="phases">
			
			<f:verbatim>
			<!-- ul -->
			<ul>
				<li>1
				</li>
				<li class="current">2
				</li>
				<li>3
				</li>
			</ul>
			<!-- end of ul -->
			</f:verbatim>
			
			</wf:container>
			<!-- end of phases -->
			
			</wf:container>
			<!-- end of header -->

			<!-- form section -->
			<wf:container styleClass="info">
			
			<wf:container styleClass="personInfo" id="name">
			<f:verbatim>
			Jon Jonsson
			</f:verbatim>
			</wf:container>
					
			</wf:container>
			<!-- end of  formsection-->
			
			<f:verbatim>
			<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu
			</h1>
			</f:verbatim>
			
			<!-- form section -->
			<wf:container styleClass="formSection">				

			
			<wf:container styleClass="fieldsetContainer">
			<wf:container styleClass="formItem required">
				<h:outputLabel for="tilkynnt" value="Tilkynnt er"/>
			</wf:container>
			<wf:container styleClass="checkboxItem">
				<h:selectOneRadio  id="tilkynnt" value="#{TilkynningLokVerksBean.tilkynnt}">
					<f:selectItems value="#{RafverktakaInitialdata.tilkynntListiSelects}"/>
				</h:selectOneRadio>
			</wf:container>
			<wf:container styleClass="formItem">
				<h:outputLabel for="annadTilkynnt" value="Annað"/>
				<h:inputText size="24" id="annadTilkynnt" value="#{TilkynningLokVerksBean.tilkynntAnnad}"/>
			</wf:container>
			</wf:container> 
		
			<wf:container styleClass="fieldsetContainer">
			<wf:container styleClass="formItem required">
				<h:outputLabel for="husnaedis" value="Notkun húsnæðis"/>
			</wf:container>
			<wf:container styleClass="checkboxItem">
				<h:selectOneMenu  id="notkunarflokkar" value="#{TilkynningLokVerksBean.notkunarflokkur}">
					<f:selectItems value="#{RafverktakaInitialdata.notkunarflokkurListiSelects}"/>
				</h:selectOneMenu>
			</wf:container>
			
			<wf:container styleClass="formItem">
				<h:outputLabel for="starfsemi" value="Starfsemi hver"/>
				<h:inputText size="8" id="starfsemi" value="#{TilkynningLokVerksBean.starfsemi}" />
			</wf:container>	
			</wf:container>
			
			<wf:container styleClass="formItem">
				<h:outputLabel for="skyringLokVerks" value="Nánari skyring á þvi sem tilkynnt er"/>
			<h:inputTextarea id="skyringLokVerks" value="#{TilkynningLokVerksBean.skyring}" rows="3" cols="60"/>
			</wf:container>
			
			 
			<wf:container styleClass="fieldsetContainer">
			<wf:container styleClass="formItem required">
				<h:outputLabel for="veitukerfi" value="Veitukerfi"/>
			</wf:container>
			<wf:container styleClass="checkboxItem">
				<h:selectOneRadio  id="veitukerfi" value="#{TilkynningLokVerksBean.spennukerfi}">
					<f:selectItems value="#{RafverktakaInitialdata.spennukerfiListiSelects}"/>
				</h:selectOneRadio>
			</wf:container>
			
			<wf:container styleClass="formItem">
				<h:outputLabel for="annadVeitukerfi" value="Annað"/>
				<h:inputText size="8" id="annadVeitukerfi" value="#{TilkynningLokVerksBean.annad}"/>
			</wf:container>	
			</wf:container>
			
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="varnarradstoefunLokVerks" value="Varnarráðstöfun"/>
			</wf:container>
			<wf:container styleClass="checkboxItem">
				<h:selectManyCheckbox  id="varnarradstoefunLokVerks" value="#{TilkynningLokVerksBean.varnarradstoefun}">
					<f:selectItems value="#{RafverktakaInitialdata.varnarradstoefunListiSelects}"/>
				</h:selectManyCheckbox>
			</wf:container>
			
			<wf:container styleClass="fieldsetContainer">
			<wf:container styleClass="formItem">
				<h:outputLabel for="jardskaut" value="Jarðskaut/sp.jöfnun"/>
			</wf:container>
			<wf:container styleClass="checkboxItem">
				<h:selectManyCheckbox  id="jardskaut" value="#{TilkynningLokVerksBean.jardskaut}">
					<f:selectItems value="#{RafverktakaInitialdata.jardskautListiSelects}"/>
				</h:selectManyCheckbox>
			</wf:container>
			

			<wf:container styleClass="formItem">
				<h:outputLabel for="annadJardskaut" value="Annað"/>
				<h:inputText size="8" id="annadJardskaut" value="#{TilkynningLokVerksBean.jardskautAnnad}" />
			</wf:container>	
			</wf:container>
			
			<wf:container styleClass="fieldsetContainer">
			

			<wf:container styleClass="formItem">
				<h:outputLabel for="maelisNumerLokVerks" value="Númer mælis rafveitu"/>
				<h:inputText id="maelisNumerLokVerks" value="#{TilkynningLokVerksBean.maelir.numer}"/>
				<h:message for="maelisNumerLokVerks"></h:message>
			</wf:container>	
						

			<wf:container styleClass="formItem">
				<h:outputLabel for="stadurMaelisLokVerks" value="Staður mælis"/>
				<h:inputText size="8" id="stadurMaelisLokVerks" value="#{TilkynningLokVerksBean.maelir.stadur}"/>
			</wf:container>	
			</wf:container>		
			

			<wf:container styleClass="formItem">
				<h:outputLabel for="skyringarLokVerks" value="Skyringar"/>
			<h:inputTextarea 
			id="skyringarLoksVerks" 
			value="#{TilkynningLokVerksBean.skyringar}"
			rows="3" 
			cols="60"/>
			</wf:container>
			
			</wf:container>
				
			<wf:container styleClass="button">
				<h:commandButton 
				action="back" 
				value="til baka"/>
				<h:commandButton 
				action="next" 
				value="afram"/>
				
				<h:outputText value=" "/>
			
				<h:commandButton 
				action="#{TilkynningLokVerksBean.store}" 
				value="geyma"/>
				
			</wf:container>

			
		</h:form>

	</f:view>
</jsp:root>
