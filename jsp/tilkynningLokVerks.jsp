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
				<li class="current">1</li>
				<li>2</li>
				<li>3</li>
			</ul>
			</div>
			</div>
			<div class="info">
			<div class="personInfo" id="name">Jon Jonsson</div>
			</div>
			<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu</h1>
			<div class="formSection">
				<div class="helperText">Veldu rafverktoku</div>
				<div class="formItem required">
					<label for="verktokuDrop">Veitustadur</label>
					<x:selectOneMenu onchange="this.form.submit()" id="verktokuDrop" valueChangeListener="#{SkyrslaRafverktaka.onChangeRafverktaka}">
					<f:selectItem itemLabel="Veldu:" itemValue=""/>
					<f:selectItems value="#{RafverktokuListi.raferktokuListiSelects}">
						<!-- f:selectItem itemLabel="Orkuveita Reykjavikur" itemValue="OR"/>
					<f:selectItem itemLabel="Hitaveita Sudurnesja" itemValue="HS"/-->
					</f:selectItems>
					</x:selectOneMenu>
				</div>
			</div>

			<div class="formSection">
				<h:outputText value="Valid: #{SkyrslaRafverktaka.rafverktaka.id}"/>
				<h:outputText value="Rafverktaki: #{SkyrslaRafverktaka.rafverktaka.rafverktaki.nafn}"/>
			</div>
			
			<div class="formSection">
			<div class="helperText">Upplysingar um rafverktaka</div>
			<div class="formItem required">
				<label for="rafverktakaFyrirtaeki">Rafverktakafyrirtaeki</label>
				<x:inputText id="rafverktakaFyrirtaeki" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
			</div>
			<div class="formItem required">
				<label for="loggilturRafverktaki">Loggiltur rafverktaki</label>
				<x:inputText id="loggilturRafverktaki" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.nafn}" disabled="true"/>
			</div>
			<div class="formItem required">
				<label for="heimilisfangRafverktaka">Heimilisfang</label>
				<x:inputText id="heimilisfangRafverktaka" disabled="true"/>
			</div>
			<div class="formItem required">
				<label for="kennitalaRafverktaka">Kennitala rafverktaka</label>
				<x:inputText id="kennitalaRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
			</div>
			<div class="formItem required">
				<label for="heimasimiRafverktaka">Heimasimi</label>
				<x:inputText id="heimasimiRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
			</div>
			<div class="formItem required">
				<label for="vinnusimiRafverktaka">Vinnusimi</label>
				<x:inputText id="vinnusimiRafverktaka" value="#{SkyrslaRafverktaka.rafverktaka.rafverktaki.vinnusimi}" disabled="true" />
			</div>
			</div>
			<div class="formSection">
			<div class="helperText">Upplysingar um veitustad</div>
			<div class="formItem required">
				<h:outputLabel for="postnumerDrop">Postnumer</h:outputLabel>
				<h:selectOneMenu id="postnumerDrop" value="#{TilkynningLokVerksBean.postnumer}">
					<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotuDrop">Gata</h:outputLabel>
				<h:selectOneMenu id="gotuDrop" value="#{TilkynningLokVerksBean.gata}">
					<f:selectItems value="#{RafverktakaInitialdata.gotuListiSelects}"/>
				</h:selectOneMenu>
				<h:outputLabel for="gotunumer">Gotunumer</h:outputLabel>
				<h:inputText id="gotunumer" value="#{TilkynningLokVerksBean.gotunumer}"/>
				<h:outputLabel for="husHluti">Hushluti/Haed</h:outputLabel>
				<h:inputText id="husHluti" value="#{TilkynningLokVerksBean.haed}"/>
			</div>
			<div class="formItem required">
				<h:outputLabel for="orkukaupandi">Nafn orkukaupanda</h:outputLabel>
				<h:inputText id="orkukaupandi" value="#{TilkynningLokVerksBean.nafnOrkukaupanda}"/>
			</div>
			<div class="formItem required">
				<h:outputLabel for="kennitalaOrkukaupanda">Kennitala</h:outputLabel>
				<h:inputText id="kennitalaOrkukaupanda" value="#{TilkynningLokVerksBean.kennitalaOrkukaupanda}"/>
			</div>
			<div class="formItem required">
				<h:outputLabel for="heimasimiOrkukaupanda">Heimasimi</h:outputLabel>
				<h:inputText id="heimasimiOrkukaupanda" value="#{TilkynningLokVerksBean.heimasimiOrkukaupanda}"/>
			</div>
			<div class="formItem required">
				<h:outputLabel for="vinnusimiOrkukaupanda">Vinnusimi</h:outputLabel>
				<h:inputText id="vinnusimiOrkukaupanda" value="#{TilkynningLokVerksBean.vinnusimiOrkukaupanda}"/>
			</div>
			</div>
				
			<div class="button">
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
