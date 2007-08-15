<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:x="http://myfaces.apache.org/extensions"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"

version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_621" template="93">
<builder:region id="left" label="left">

<h:form id="form1" styleClass="citizenForm">
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
var streetDrop = document.getElementById("form1:gotuDrop");
var streetNumberLabel = document.getElementById("form1:gotunumerLabel");
if (streetDrop.options.length == 1) {
var valueStreet = streetDrop.options[0].value;
if (valueStreet != "") {
streetNumberLabel.innerHTML = "Frjáls texti"
}
else {
streetNumberLabel.innerHTML = ""
}
}
else {
streetDrop.selectedIndex = 1;
streetNumberLabel.innerHTML= "Götunúmer"
}
}

function updateStreetNumberLabel() {
var streetDrop = document.getElementById("form1:gotuDrop");
var streetNumberLabel = document.getElementById("form1:gotunumerLabel");
var selection = streetDrop.options[streetDrop.selectedIndex].value;
if (selection == "none_street") {
streetNumberLabel.innerHTML = "Frjáls texti"
}
else {
streetNumberLabel.innerHTML = "Götunúmer"
}
}

function updateRealEstates() {
var postalCodeDrop = document.getElementById("form1:postnumerDrop");
var streetDrop = document.getElementById("form1:gotuDrop");
var streetNumber = document.getElementById("form1:gotunumer");
var postalCodeSelection = postalCodeDrop.options[postalCodeDrop.selectedIndex].value;
var streetSelection = streetDrop.options[streetDrop.selectedIndex].value;
var streetNumberValue = streetNumber.value;
NestService.getRealEstatesByPostalCodeStreetStreetNumberForChangeElectricianBean(postalCodeSelection, streetSelection, streetNumberValue,
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
}

</script>
</f:verbatim>
<!--div class="generalContent"-->

<f:verbatim><h1 class="applicationHeading">Þjónustubeiðni</h1></f:verbatim>

<wf:container styleClass="header">

<f:verbatim><h1>1. Upplýsingar um neysluveitu
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
<h:outputText value="#{TilkynningVertakaBean.rafverktaka.rafverktaki.nafn}"/>
</wf:container>

</wf:container>
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
<wf:container styleClass="formSection">

<wf:container styleClass="helperText">
<f:verbatim>
Upplysingar um veitustað
</f:verbatim>
</wf:container>

<wf:container styleClass="formItem">
<h:outputText
value="Veldu veitustað"/>
</wf:container>

<wf:container styleClass="formItem required">
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
<h:outputLabel id="gotunumerLabel" for="gotunumer" value="Götunúmer" />
<h:inputText
id="gotunumer" value="#{ChangeElectricianBean.gotunumer}"/>
</wf:container>

<wf:container
styleClass="formItem required">
<f:verbatim><input type="button" name="Text 1" value="Fletta upp í Landskrá Fasteigna" onclick="updateRealEstates();"></input></f:verbatim>
<!--h:commandButton id="flettaUppIFasteignaskraButton" action="#{TilkynningVertakaBean.flettaUppIFasteignaskra}" value="Fletta upp í Landskrá Fasteigna"/-->
<!--h:selectOneMenu id="fasteignirDrop" rendered="#{TilkynningVertakaBean.availablefasteign}" value="#{TilkynningVertakaBean.fastanumer}" onchange="submit();"-->
<h:selectOneMenu
id="fasteignirDrop" value="#{ChangeElectricianBean.fastanumer}" onclick="updateElectricInstallationList();" >
<f:selectItems value="#{ChangeElectricianBean.fasteignaListiSelects}"/>
</h:selectOneMenu>
</wf:container>
<wf:container styleClass="formItem">
<h:outputText id="veitustadurDisplay" value="#{ChangeElectricianBean.veitustadurDisplay}" />
</wf:container>

<wf:container styleClass="formItem">
<h:outputLabel for="electricalInstallationDrop" value="Rafverktaka" />
<h:selectOneMenu
style="size=40;"
id="electricalInstallationDrop" value="#{ChangeElectricianBean.electricalInstallationIDSelection}">
<f:selectItems value="#{ChangeElectricianBean.electricalInstallationListiSelects}"/>
</h:selectOneMenu>
</wf:container>



</wf:container>
<!-- end form section -->

<wf:container styleClass="bottom">
<h:commandButton
styleClass="buttonSpan"
id="tilkynningVertaka"
action="next"
value="Áfram"/>

<h:outputText value=" "/>

<h:commandButton
action="#{TilkynningVertakaBean.store}"
value="Geyma"/>
</wf:container>
</h:form>



</builder:region>

</builder:page>
</f:view>
</jsp:root>