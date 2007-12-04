<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:x="http://myfaces.apache.org/tomahawk"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"
version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_345" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk">
<f:verbatim>
<script type="text/javascript" src="/dwr/interface/NestService.js"><!-- dont remove --></script>

<script type="text/javascript" src="/dwr/engine.js"><!-- dont remove --></script>

<script type="text/javascript" src="/dwr/util.js"><!-- dont remove --></script>

<script type="text/javascript">

var image1 = new Image();
image1.src = '/idegaweb/bundles/com.idega.core.bundle/resources/loading_notext.gif';

var image2 = new Image();
image2.src='/idegaweb/bundles/com.idega.core.bundle/resources/style/images/transparent.png';

var image3 = new Image();
image3.src='/idegaweb/bundles/com.idega.core.bundle/resources/style/images/whitetransparent.png';


function useLoadingMessage(message) {
var loadingMessage;
if (message) loadingMessage = message;
else loadingMessage = "please wait";

DWREngine.setPreHook(function() {
var disabledZone = $('busybuddy');
if (!disabledZone) {
var outer = document.createElement('div');
outer.setAttribute('id', 'busybuddy');
outer.setAttribute('class', 'LoadLayer');
//IE class workaround:
outer.setAttribute('className', 'LoadLayer');

var middle = document.createElement('div');
middle.setAttribute('id', 'busybuddy-middle');
middle.setAttribute('class', 'LoadLayerMiddle');
//IE class workaround:
middle.setAttribute('className', 'LoadLayerMiddle');
outer.appendChild(middle);

var inner = document.createElement('div');
inner.setAttribute('id', 'busybuddy-contents');
inner.setAttribute('class', 'LoadLayerContents');
//IE class workaround:
inner.setAttribute('className', 'LoadLayerContents');
middle.appendChild(inner);

var image = document.createElement('img');
image.setAttribute('id', 'loadingimage');
image.setAttribute('src',image1.src);
image.src=image1.src;
inner.appendChild(image);

var span = document.createElement('span');
span.setAttribute('id', 'loadingtext');
inner.appendChild(span);

var text = document.createTextNode("Loading");
span.appendChild(text);

var bodyArray = document.getElementsByTagName('body');
var bodyTag = bodyArray[0];
bodyTag.appendChild(outer);
//alert('bodyTag:'+bodyTag);
}
else {

disabledZone.style.visibility = 'visible';
}
});

DWREngine.setPostHook(function() {
$('busybuddy').style.visibility = 'hidden';
});
}



function init() {
useLoadingMessage();
}

if (window.addEventListener) {
window.addEventListener("load", init, false);
}
else if (window.attachEvent) {
window.attachEvent("onload", init);
}
else {
window.onload = init;
}



function updateStreets() {
var postalCodeDrop = document.getElementById("form1:postnumerDrop");
NestService.getStreetsByPostalCode(postalCodeDrop.options[postalCodeDrop.selectedIndex].value, {
callback:changeStreets,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeStreets(data) {
DWRUtil.removeAllOptions("form1:gotuDrop");
DWRUtil.addOptions("form1:gotuDrop", data);
var streetNumberFreeTextLabel = document.getElementById("form1:gotunumerLabel");
var streetNumberFreeText = document.getElementById("form1:gotunumer")
var streetNumberDropDownLabel = document.getElementById("form1:streetNumberDropLabel");
var streetNumberDropDown = document.getElementById("form1:streetNumberDrop");
var streetDrop = document.getElementById("form1:gotuDrop");
if (streetDrop.options.length == 1) {
var valueStreet = streetDrop.options[0].value;
streetNumberFreeTextLabel.style.display="block"
streetNumberFreeText.style.display="block"
streetNumberDropDownLabel.style.display="none";
streetNumberDropDown.style.display="none";
<!--
if (valueStreet != "") {
streetNumberLabel.innerHTML = "Frjáls texti"
}
else {
streetNumberLabel.innerHTML = ""
}
-->
}
else {
streetDrop.selectedIndex = 1;
streetNumberFreeTextLabel.style.display="none"
streetNumberFreeText.style.display="none"
streetNumberDropDownLabel.style.display="block";
streetNumberDropDown.style.display="block";
}
}

function updateStreetNumberLabel() {
var streetNumberFreeTextLabel = document.getElementById("form1:gotunumerLabel");
var streetNumberFreeText = document.getElementById("form1:gotunumer")
var streetNumberDropDownLabel = document.getElementById("form1:streetNumberDropLabel");
var streetNumberDropDown = document.getElementById("form1:streetNumberDrop");
var streetDrop = document.getElementById("form1:gotuDrop");
var selection = streetDrop.options[streetDrop.selectedIndex].value;
if (selection == "none_street") {
streetNumberFreeTextLabel.style.display="block"
streetNumberFreeText.style.display="block"
streetNumberDropDownLabel.style.display="none";
streetNumberDropDown.style.display="none";
}
else {
streetNumberFreeTextLabel.style.display="none"
streetNumberFreeText.style.display="none"
streetNumberDropDownLabel.style.display="block";
streetNumberDropDown.style.display="block";
}
}

function updateRealEstates() {
var postalCodeDrop = document.getElementById("form1:postnumerDrop");
var streetDrop = document.getElementById("form1:gotuDrop");
var freetext = document.getElementById("form1:gotunumer");
var streetNumberDrop = document.getElementById("form1:streetNumberDrop");
var postalCodeSelection = postalCodeDrop.options[postalCodeDrop.selectedIndex].value;
var streetSelection = streetDrop.options[streetDrop.selectedIndex].value;
var freetextValue = freetext.value;
var streetNumber = streetNumberDrop.options[streetNumberDrop.selectedIndex].value;
NestService.getRealEstatesByPostalCodeStreetStreetNumber(postalCodeSelection, streetSelection, streetNumber, freetextValue,
{callback:changeRealEstates,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeRealEstates(data) {
DWRUtil.removeAllOptions("form1:fasteignirDrop");
DWRUtil.addOptions("form1:fasteignirDrop", data);
}

function updateEnergyConsumerFields() {
var realEstateDrop = document.getElementById("form1:fasteignirDrop");
NestService.getEnergyConsumerFields(realEstateDrop.options[realEstateDrop.selectedIndex].value,
{callback:changeEnergyConsumerFields,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeEnergyConsumerFields(data) {
var realEstateDisplay = document.getElementById("form1:veitustadurDisplay");
var energyConsumer = document.getElementById("form1:orkukaupandi");
var personalIDEnergyConsumer = document.getElementById("form1:kennitalaOrkukaupanda");
var currentWorkingPlaceErrorMessage = document.getElementById("form1:currentWorkingPlaceErrorMessage");
var changeElectrician = document.getElementById("form1:changeElectrician");
var checkOutWorkingPlaceButton = document.getElementById("form1:checkOutWorkingPlaceButton");
realEstateDisplay.innerHTML = data[0];
energyConsumer.value = data[1];
personalIDEnergyConsumer.value = data[2];
currentWorkingPlaceErrorMessage.innerHTML = data[3];
checkOutWorkingPlaceButton.disabled=(data[3]!='' || data[0]=='');
changeElectrician.style.display=data[4];
}

</script>
</f:verbatim>
<!--div class="generalContent"-->

<f:verbatim><h1 class="applicationHeading">Skýrsla</h1></f:verbatim>

<x:div styleClass="header">

<f:verbatim><h1>1. Upplýsingar um neysluveitu
</h1></f:verbatim>

<!-- phases -->
<x:div styleClass="phases">

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

</x:div>
<!-- end of phases -->

</x:div>
<!-- end of header -->

<!-- form section -->
<x:div styleClass="info">

<x:div styleClass="personInfo" id="name" forceId="true">
<h:outputText value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafn}"/>
</x:div>

<x:div styleClass="personInfo" id="personalID" forceId="true">
<h:outputText value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.kennitala}"/>
</x:div>

<x:div styleClass="personInfo" id="address" forceId="true">
<h:outputText value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimilisfang.display}"/>
</x:div>

</x:div>
<!-- end of formsection-->

<f:verbatim>
<h1 class="subHeader topSubHeader">Rafverktaki</h1>
</f:verbatim>

<!-- form section -->
<x:div styleClass="formSection">
<x:div styleClass="helperText">
<f:verbatim>
Upplýsingar sem eru birtar hér eru sóttar í gagnagrunn og eru óbreytanlegar.
</f:verbatim>
</x:div>

<x:div rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}" styleClass="formItem">
<h:outputText
rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}"
value="Þetta mál er núna í vinnslu hjá: "/>
</x:div>
<x:div rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}" styleClass="formItem">
<h:outputText
rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}"
id="newOwner" value="#{TilkynningVertakaBean.newOwnerOfCase.nafn}"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="rafverktakaFyrirtaeki" value="Rafverktakafyrirtaeki"/>
<h:inputText id="rafverktakaFyrirtaeki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="loggilturRafverktaki" value="Loggiltur rafverktaki"/>
<h:inputText id="loggilturRafverktaki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafn}" disabled="true"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="heimilisfangRafverktaka" value="Heimilisfang"/>
<h:inputText id="heimilisfangRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimilisfang.display}" disabled="true"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="kennitalaRafverktaka" value="Kennitala rafverktaka"/>
<h:inputText id="kennitalaRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="heimasimiRafverktaka" value="Heimasimi"/>
<h:inputText id="heimasimiRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="vinnusimiRafverktaka" value="Vinnusimi"/>
<h:inputText id="vinnusimiRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.vinnusimi}" disabled="true" />
</x:div>

<x:div styleClass="Clear"></x:div>
</x:div>

<f:verbatim>
<h1 class="subHeader">Veitustaður</h1>
</f:verbatim>

					<!-- form section -->
					<x:div styleClass="formSection">

						<x:div styleClass="helperText">
							<f:verbatim>
Veldu fyrst póstnúmer, þá verður fellilistinn fyrir neðan endurhlaðinn með mögulegum götunöfnum.  Veldu því næst götu, skráðu inn götunúmer (má sleppa) og smelltu því næst á 'Fletta í Landskrá Fasteigna'.  Niðurstaða leitarinnar birtist þá í fellilistanum 'Fasteign'.  Veldu að lokum fasteignina sem við á. 
</f:verbatim>
						</x:div>

						<x:div styleClass="formItem "
							rendered="#{TilkynningVertakaBean.invalid['workingPlace'] != null}">
							<h:outputText value="Veldu veitustað" />
						</x:div>

						<x:div styleClass="formItem required">
							<h:outputLabel for="postnumerDrop" value="Póstnúmer" />
							<h:selectOneMenu
								disabled="#{! TilkynningVertakaBean.workingPlaceChangeable}"
								id="postnumerDrop" value="#{TilkynningVertakaBean.postnumer}"
								onchange="updateStreets();">
								<f:selectItems
									value="#{RafverktakaInitialdata.postnumeraListiSelects}" />
							</h:selectOneMenu>
						</x:div>

						<x:div styleClass="formItem required">
							<h:outputLabel for="gotuDrop" value="Gata" />
							<h:selectOneMenu
								disabled="#{! TilkynningVertakaBean.workingPlaceChangeable}"
								id="gotuDrop" value="#{TilkynningVertakaBean.gata}"
								onchange="updateStreetNumberLabel();">
								<f:selectItems value="#{TilkynningVertakaBean.gotuListiSelects}" />
							</h:selectOneMenu>
						</x:div>

						<x:div styleClass="formItem required">
							<h:outputLabel
								style="#{TilkynningVertakaBean.showFreetextGotunumer}"
								id="gotunumerLabel" for="gotunumer" value="Frjáls texti" />
							<h:inputText
								style="#{TilkynningVertakaBean.showFreetextGotunumer}"
								disabled="#{! TilkynningVertakaBean.workingPlaceChangeable}"
								id="gotunumer" value="#{TilkynningVertakaBean.freeText}" />
							<h:outputLabel
								style="#{TilkynningVertakaBean.showStreetNumberSelects}"
								id="streetNumberDropLabel" for="streetNumberDrop"
								value="Götunúmer" />
							<h:selectOneMenu
								style="#{TilkynningVertakaBean.showStreetNumberSelects}"
								disabled="#{! TilkynningVertakaBean.workingPlaceChangeable}"
								id="streetNumberDrop"
								value="#{TilkynningVertakaBean.streetNumber}">
								<f:selectItems
									value="#{RafverktakaInitialdata.streetNumberSelects}" />
							</h:selectOneMenu>
						</x:div>

						<x:div rendered="#{TilkynningVertakaBean.workingPlaceChangeable}"
							styleClass="#{TilkynningVertakaBean.invalid['workingPlace'] != null ? 'formItem required hasError' : 'formItem required'}">
							<h:outputLabel id="fasteignLabel" for="fasteignirDrop"
								value="Fasteign" />
							<h:selectOneMenu
								disabled="#{!TilkynningVertakaBean.applicationStorable}"
								styleClass="bigSelect" id="fasteignirDrop"
								value="#{TilkynningVertakaBean.fastanumer}"
								onclick="updateEnergyConsumerFields();">
								<f:selectItems
									value="#{TilkynningVertakaBean.fasteignaListiSelects}" />
							</h:selectOneMenu>
							<f:verbatim>
								<input type="button" class="floatingButton"
									name="updateRealEstatesButton"
									value="Fletta í Landskrá Fasteigna"
									onclick="updateRealEstates();" />
							</f:verbatim>
						</x:div>

						<x:div styleClass="formItem">
							<h:outputText id="veitustadurDisplay"
								value="#{TilkynningVertakaBean.veitustadurDisplay}" />
						</x:div>

						<x:div styleClass="formItem">
							<h:outputText id="currentWorkingPlaceErrorMessage"
								style="color:red"
								value="#{TilkynningVertakaBean.currentWorkingPlaceErrorMessage}" />
						</x:div>

						<x:div styleClass="formItem">
							<h:commandButton
								style="#{TilkynningVertakaBean.showChangeElectricianOption}"
								id="changeElectrician" styleClass="buttonSpan"
								actionListener="#{TilkynningVertakaBean.changeElectrician}"
								action="rafverktakaskipti" value="Ósk um rafverktakaskipti" />
						</x:div>

					</x:div>
					<!-- end of formsection-->

<f:verbatim>
<h1 class="subHeader">Orkukaupandi</h1>
</f:verbatim>

<x:div styleClass="formSection">

<x:div styleClass="helperText">
<f:verbatim>
Settu inn allar upplýsingar um orkukaupandann.  Athugaðu að fylla verður í alla reiti.
</f:verbatim>
</x:div>

<x:div style="formItem" rendered="#{TilkynningVertakaBean.invalid['name'] != null}">
<h:outputText style="color:red" value="#{TilkynningVertakaBean.invalid['name']}" />
</x:div>
<x:div styleClass="#{TilkynningVertakaBean.invalid['name'] != null ? 'formItem required hasError' : 'formItem required'}">
<h:outputLabel for="orkukaupandi" value="Nafn orkukaupanda" />
<h:inputText disabled="#{! TilkynningVertakaBean.applicationReportStorable}" id="orkukaupandi" value="#{TilkynningVertakaBean.nafnOrkukaupanda}"/>
</x:div>

<x:div style="formItem" rendered="#{TilkynningVertakaBean.invalid['kennitalaOrkukaupanda'] != null}">						
<h:outputText style="color:red" value="#{TilkynningVertakaBean.invalid['kennitalaOrkukaupanda']}" />	
</x:div>
<x:div styleClass="#{TilkynningVertakaBean.invalid['energyConsumerPersonalId'] != null ? 'formItem required hasError' : 'formItem required'}">
<h:outputLabel for="kennitalaOrkukaupanda" value="Kennitala" />
<h:inputText disabled="#{! TilkynningVertakaBean.applicationReportStorable}" id="kennitalaOrkukaupanda" value="#{TilkynningVertakaBean.kennitalaOrkukaupanda}"/>
</x:div>

<x:div style="formItem" rendered="#{TilkynningVertakaBean.invalid['energyConsumerHomePhone'] != null}">						
<h:outputText style="color:red" value="#{TilkynningVertakaBean.invalid['energyConsumerHomePhone']}" />	
</x:div>
<x:div styleClass="#{TilkynningVertakaBean.invalid['energyConsumerHomePhone'] != null ? 'formItem required hasError' : 'formItem required'}">
<h:outputLabel for="heimasimiOrkukaupanda" value="Heimasími"/>
<h:inputText disabled="#{! TilkynningVertakaBean.applicationReportStorable}" id="heimasimiOrkukaupanda" value="#{TilkynningVertakaBean.heimasimiOrkukaupanda}"/>
</x:div>

<x:div style="formItem" rendered="#{TilkynningVertakaBean.invalid['energyConsumerWorkPhone'] != null}">						
<h:outputText style="color:red" value="#{TilkynningVertakaBean.invalid['energyConsumerWorkPhone']}" />	
</x:div>
<x:div styleClass="#{TilkynningVertakaBean.invalid['energyConsumerWorkPhone'] != null ? 'formItem required hasError' : 'formItem required'}">
<h:outputLabel for="vinnusimiOrkukaupanda" value="Vinnusími" />
<h:inputText disabled="#{! TilkynningVertakaBean.applicationReportStorable}" id="vinnusimiOrkukaupanda" value="#{TilkynningVertakaBean.vinnusimiOrkukaupanda}"/>
</x:div>

</x:div>

<x:div styleClass="bottom">
<h:commandLink styleClass="button" action="next"><f:verbatim><span  class="buttonSpan">Áfram</span></f:verbatim>
</h:commandLink>
</x:div>

</h:form>

</builder:region>
</builder:page>
</f:view>
</jsp:root>