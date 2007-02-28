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
			<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu
			</h1>
			</f:verbatim>
			
			<!-- form section -->
			<wf:container styleClass="formSection">				

			<wf:container styleClass="helperText">
			<f:verbatim>
			Veldu rafverktoku			
			</f:verbatim>
			</wf:container>
			
			<wf:container styleClass="formItem required">
					<h:outputLabel for="verktokuDrop" value="Veitustadur"/>
					<x:selectOneMenu onchange="this.form.submit()" id="verktokuDrop" valueChangeListener="#{SkyrslaRafverktaka.onChangeRafverktaka}">
					<f:selectItem itemLabel="Veldu:" itemValue=""/>
					<f:selectItems value="#{RafverktokuListi.raferktokuListiSelects}">
						<!-- f:selectItem itemLabel="Orkuveita Reykjavikur" itemValue="OR"/>
					<f:selectItem itemLabel="Hitaveita Sudurnesja" itemValue="HS"/-->
					</f:selectItems>
					</x:selectOneMenu>
				</wf:container>
			</wf:container>
			
			<wf:container styleClass="formSection">
			<wf:container styleClass="helperText">
			<f:verbatim>
			Upplysingar um rafverktaka
			</f:verbatim>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="rafverktakaFyrirtaeki" value="Rafverktakafyrirtaeki"/>
				<h:inputText id="rafverktakaFyrirtaeki" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="loggilturRafverktaki" value="Loggiltur rafverktaki"/>
				<h:inputText id="loggilturRafverktaki" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.nafn}" disabled="true"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimilisfangRafverktaka" value="Heimilisfang"/>
				<h:inputText id="heimilisfangRafverktaka" disabled="true"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="kennitalaRafverktaka" value="Kennitala rafverktaka"/>
				<h:inputText id="kennitalaRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimasimiRafverktaka" value="Heimasimi"/>
				<h:inputText id="heimasimiRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="vinnusimiRafverktaka" value="Vinnusimi"/>
				<h:inputText id="vinnusimiRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.vinnusimi}" disabled="true" />
			</wf:container>
			</wf:container>
			<wf:container styleClass="formSection">
			<wf:container styleClass="helperText">
			<f:verbatim>
			Upplysingar um veitustad
			</f:verbatim>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="postnumerDrop" value="Postnumer" />
				<h:selectOneMenu id="postnumerDrop" value="#{TilkynningLokVerksBean.postnumer}">
					<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotuDrop" value="Gata"/>
				<h:selectOneMenu id="gotuDrop" value="#{TilkynningLokVerksBean.gata}">
					<f:selectItems value="#{RafverktakaInitialdata.gotuListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotunumer" value="Gotunumer"/>
				<h:inputText id="gotunumer" value="#{TilkynningLokVerksBean.gotunumer}"/>
				<h:outputLabel for="husHluti" value="Hushluti/Haed"/>
				<h:inputText id="husHluti" value="#{TilkynningLokVerksBean.haed}"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="orkukaupandi" value="Nafn orkukaupanda"/>
				<h:inputText id="orkukaupandi" value="#{TilkynningLokVerksBean.nafnOrkukaupanda}"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="kennitalaOrkukaupanda" value="Kennitala"/>
				<h:inputText id="kennitalaOrkukaupanda" value="#{TilkynningLokVerksBean.kennitalaOrkukaupanda}"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="heimasimiOrkukaupanda" value="Heimasimi"/>
				<h:inputText id="heimasimiOrkukaupanda" value="#{TilkynningLokVerksBean.heimasimiOrkukaupanda}"/>
			</wf:container>
			<wf:container styleClass="formItem required">
				<h:outputLabel for="vinnusimiOrkukaupanda" value="Vinnusimi"/>
				<h:inputText id="vinnusimiOrkukaupanda" value="#{TilkynningLokVerksBean.vinnusimiOrkukaupanda}"/>
			</wf:container>
			</wf:container>
				
			<wf:container styleClass="button">
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
