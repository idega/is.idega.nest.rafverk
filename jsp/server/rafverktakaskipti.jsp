<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns="http://www.w3.org/1999/xhtml"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:x="http://myfaces.apache.org/tomahawk"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"
 version="1.2">

<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_581" template="93">
<builder:region id="left" label="left">

<h:form id="form1" styleClass="rafverk">
<f:verbatim>
<script type="text/javascript" src="/dwr/interface/NestService.js"><!-- dont remove --></script>

<script type="text/javascript" src="/dwr/engine.js"><!-- dont remove --></script>

<script type='text/javascript' src='/dwr/util.js'><!-- dont remove --></script>

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
NestService.getStreetsByPostalCodeForChangeElectricianBean(postalCodeDrop.options[postalCodeDrop.selectedIndex].value, {
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
NestService.getRealEstatesByPostalCodeStreetStreetNumberForChangeElectricianBean(postalCodeSelection, streetSelection, streetNumber, freetextValue,
{callback:changeRealEstates,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeRealEstates(data) {
DWRUtil.removeAllOptions("form1:fasteignirDrop");
DWRUtil.addOptions("form1:fasteignirDrop", data);
}

function updateElectricInstallationList() {
var realEstateDrop = document.getElementById("form1:fasteignirDrop");
NestService.getElectricInstallationList(realEstateDrop.options[realEstateDrop.selectedIndex].value,
{callback:changeElectricInstallationList,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeElectricInstallationList(data) {
var realEstateDisplay = document.getElementById("form1:veitustadurDisplay");
realEstateDisplay.innerHTML = data[0];
DWRUtil.removeAllOptions("form1:electricalInstallationDrop");
DWRUtil.addOptions("form1:electricalInstallationDrop", data[1]);
enableNextButton();
}

function enableNextButton() {
var electricalInstallationDrop = document.getElementById("form1:electricalInstallationDrop");
var selectedValue = electricalInstallationDrop.options[electricalInstallationDrop.selectedIndex].value;
var nextButton = document.getElementById("form1:nextButton");
nextButton.disabled=(selectedValue=='none_electric_installation_selection');
}

</script>
</f:verbatim>

<f:verbatim><h1 class="applicationHeading">Þjónustubeiðni</h1></f:verbatim>

<x:div styleClass="header">

<f:verbatim><h1>1. Upplýsingar um neysluveitu</h1></f:verbatim>

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

<x:div styleClass="personInfo" id="name">
<h:outputText value="#{TilkynningVertakaBean.rafverktaka.rafverktaki.nafn}"/>
</x:div>

</x:div>
<!-- end of formsection-->

<f:verbatim>
<h1 class="subHeader topSubHeader">Grunnskráning veitu
</h1>
</f:verbatim>

<!-- form section -->

<!-- end of formsection-->

<!-- form section -->

<!-- end of formsection -->

<!-- form section -->

<!-- end of formsection-->

<!-- form section -->
<x:div styleClass="formSection">

<x:div styleClass="helperText">
<f:verbatim>
Upplysingar um veitustað
</f:verbatim>
</x:div>

<x:div styleClass="formItem">
<h:outputText
value="Veldu veitustað"/>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel for="postnumerDrop" value="Póstnúmer"/>
<h:selectOneMenu
id="postnumerDrop" value="#{ChangeElectricianBean.postnumer}" onchange="updateStreets();">
<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
</h:selectOneMenu>
<h:outputLabel for="gotuDrop" value="Gata" />
<h:selectOneMenu
id="gotuDrop" value="#{ChangeElectricianBean.gata}" onchange="updateStreetNumberLabel();">
<f:selectItems value="#{ChangeElectricianBean.gotuListiSelects}"/>
</h:selectOneMenu>
</x:div>

<x:div styleClass="formItem required">
<h:outputLabel
style="#{ChangeElectricianBean.showFreetextGotunumer}"
id="gotunumerLabel" for="gotunumer" value="Frjáls texti" />
<h:inputText
style="#{ChangeElectricianBean.showFreetextGotunumer}"
id="gotunumer" value="#{ChangeElectricianBean.freeText}"/>
<h:outputLabel
style="#{ChangeElectricianBean.showStreetNumberSelects}"
id="streetNumberDropLabel" for="streetNumberDrop" value="Götunúmer" />
<h:selectOneMenu
style="#{ChangeElectricianBean.showStreetNumberSelects}"
id="streetNumberDrop" value="#{ChangeElectricianBean.streetNumber}">
<f:selectItems value="#{RafverktakaInitialdata.streetNumberSelects}"/>
</h:selectOneMenu>
</x:div>

<x:div
styleClass="formItem required">
<f:verbatim><input type="button" name="Text 1" value="Fletta upp í Landskrá Fasteigna" onclick="updateRealEstates();"></input></f:verbatim>
<!--h:commandButton id="flettaUppIFasteignaskraButton" action="#{TilkynningVertakaBean.flettaUppIFasteignaskra}" value="Fletta upp í Landskrá Fasteigna"/-->
<!--h:selectOneMenu id="fasteignirDrop" rendered="#{TilkynningVertakaBean.availablefasteign}" value="#{TilkynningVertakaBean.fastanumer}" onchange="submit();"-->
<h:selectOneMenu
id="fasteignirDrop" value="#{ChangeElectricianBean.fastanumer}" onchange="updateElectricInstallationList();" >
<f:selectItems value="#{ChangeElectricianBean.fasteignaListiSelects}"/>
</h:selectOneMenu>
</x:div>
<x:div styleClass="formItem">
<h:outputText id="veitustadurDisplay" value="#{ChangeElectricianBean.veitustadurDisplay}" />
</x:div>

<x:div styleClass="formItem">
<h:outputLabel for="electricalInstallationDrop" value="Rafverktaka" />
<h:selectOneMenu
style="width: 500px;"
id="electricalInstallationDrop"
value="#{ChangeElectricianBean.electricalInstallationIDSelection}"
onchange="enableNextButton();">
<f:selectItems value="#{ChangeElectricianBean.electricalInstallationListiSelects}"/>
</h:selectOneMenu>
</x:div>



</x:div>
<!-- end form section -->

<x:div styleClass="bottom">
<h:commandButton
disabled="#{ChangeElectricianBean.currentElectricalInstallationSelection==null}"
styleClass="buttonSpan"
id="nextButton"
action="next"
value="Áfram"/>

</x:div>
</h:form>



</builder:region>
</builder:page>
</f:view>
</jsp:root>