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
			
			<f:verbatim><h1>Thjonustubeidni</h1></f:verbatim>
			
			<wf:container styleClass="header">
			
			<f:verbatim><h1>1. Upplysingar um neisluveitu
			</h1></f:verbatim>
			
			<!-- phases -->
			<wf:container styleClass="phases">
			
			<f:verbatim>
			<!-- ul -->
			<ul>
				<li class="current">1
				</li>
				<li>2
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
			<h1 class="subHeader topSubHeader">Tilkynning um rafverktoku
			</h1>
			</f:verbatim>
			
			<!-- form section -->
			<wf:container styleClass="formSection">

			<wf:container styleClass="helperText">
			<f:verbatim>
			Upplysingar um rafveitu/orkuveitu
			</f:verbatim>
			</wf:container>
			
			<wf:container styleClass="formItem required">
			<h:outputLabel for="orkuveituDrop" value="Orkuveitufyrirtaeki"/>
			<h:selectOneMenu id="orkuveituDrop" value="#{TilkynningVertakaBean.orkuveitufyrirtaeki}">
				<f:selectItems value="#{RafverktakaInitialdata.rafveituListiSelects}">
					<!-- f:selectItem itemLabel="Orkuveita Reykjavikur" itemValue="OR"/>
				<f:selectItem itemLabel="Hitaveita Sudurnesja" itemValue="HS"/-->
				</f:selectItems>
			</h:selectOneMenu>
			</wf:container>
			
			</wf:container>
			<!-- end of  formsection-->
			
			<!-- form section -->
			<wf:container styleClass="formSection">
			
			<wf:container styleClass="helperText">
			<f:verbatim>
			Upplysingar um rafverktaka
			</f:verbatim>
			</wf:container>
			
			<wf:container styleClass="formItem required">
			<h:outputLabel for="rafverktakaFyrirtaeki" value="Rafverktakafyrirtaeki"/>
				<h:inputText id="rafverktakaFyrirtaeki" value="#{UpphafstilkynningRafverktoku.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="loggilturRafverktaki" value="Loggiltur rafverktaki"/>
				<h:inputText id="loggilturRafverktaki" value="#{UpphafstilkynningRafverktoku.rafverktaka.rafverktaki.nafn}" disabled="true"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimilisfangRafverktaka" value="Heimilisfangbel"/>
				<h:inputText id="heimilisfangRafverktaka" disabled="true"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="kennitalaRafverktaka" value="Kennitala rafverktaka"/>
				<h:inputText id="kennitalaRafverktaka" value="#{UpphafstilkynningRafverktoku.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimasimiRafverktaka" value="Heimasimi"/>
				<h:inputText id="heimasimiRafverktaka" value="#{UpphafstilkynningRafverktoku.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="vinnusimiRafverktaka" value="Vinnusimi"/>
				<h:inputText id="vinnusimiRafverktaka" value="#{UpphafstilkynningRafverktoku.rafverktaka.rafverktaki.vinnusimi}" disabled="true"/>
			</wf:container>
			
			
			</wf:container>
			<!-- end of  formsection-->
			
			<!-- form section -->
			<wf:container styleClass="formSection"> 

			<wf:container styleClass="helperText">
			<f:verbatim>
			Upplysingar um veitustad
			</f:verbatim>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="postnumerDrop" value="Postnumer"/>
				<h:selectOneMenu id="postnumerDrop" value="#{TilkynningVertakaBean.postnumer}">
					<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotuDrop" value="Gata" />
				<h:selectOneMenu id="gotuDrop" value="#{TilkynningVertakaBean.gata}">
					<f:selectItems value="#{RafverktakaInitialdata.gotuListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotunumer" value="Gotunumer" />
				<h:inputText id="gotunumer" value="#{TilkynningVertakaBean.gotunumer}"/>
				<h:outputLabel for="husHluti" value="Hushluti/Haed" />
				<h:inputText id="husHluti" value="#{TilkynningVertakaBean.haed}"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:commandButton id="flettaUppIFasteignaskraButton" action="#{UpphafstilkynningRafverktoku.flettaUppIFasteignasrka}" value="Fletta upp i Landskra Fasteigna"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="orkukaupandi" value="Nafn orkukaupanda" />
				<h:inputText id="orkukaupandi" value="#{TilkynningVertakaBean.nafnOrkukaupanda}"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="kennitalaOrkukaupanda" value="Kennitala" />
				<h:inputText id="kennitalaOrkukaupanda" value="#{TilkynningVertakaBean.kennitalaOrkukaupanda}"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimasimiOrkukaupanda" value="Heimasimi"/>
				<h:inputText id="heimasimiOrkukaupanda" value="#{TilkynningVertakaBean.heimasimiOrkukaupanda}"/>
			</wf:container>
			
			<wf:container styleClass="formItem required">
				<h:outputLabel for="vinnusimiOrkukaupanda" value="Vinnusimi" />
				<h:inputText id="vinnusimiOrkukaupanda" value="#{TilkynningVertakaBean.vinnusimiOrkukaupanda}"/>
			</wf:container>
			
			</wf:container>
			<!--  end form section -->

			<wf:container styleClass="button">
				<h:commandButton 
				id="tilkynningVertaka" 
				action="next" 
				value="afram"/>
				
				<h:outputText value=" "/>
				
				<h:commandButton 
				action="#{TilkynningVertakaBean.store}" 
				value="geyma"/>
			</wf:container>
		</h:form>
	</f:view>
</jsp:root>
