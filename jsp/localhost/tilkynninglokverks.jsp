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

dwr.engine.setPreHook(function() {
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

dwr.engine.setPostHook(function() {
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
dwr.util.removeAllOptions("form1:gotuDrop");
dwr.util.addOptions("form1:gotuDrop", data);
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
NestService.getRealEstatesByPostalCodeStreetStreetNumber(postalCodeSelection, streetSelection, streetNumberValue,
{callback:changeRealEstates,
errorHandler:function(message) { alert("Error: " + message); }
});
}

function changeRealEstates(data) {
dwr.util.removeAllOptions("form1:fasteignirDrop");
dwr.util.addOptions("form1:fasteignirDrop", data);
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
realEstateDisplay.innerHTML = data[0];
energyConsumer.value = data[1];
personalIDEnergyConsumer.value = data[2];
}

</script>
</f:verbatim>
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
<h:outputText value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafn}"/>
</wf:container>

</wf:container>
<!-- end of formsection-->

<f:verbatim>
<h1 class="subHeader topSubHeader">Skyrsla um neysluveitu
</h1>
</f:verbatim>

<!-- form section -->
<!--wf:container styleClass="formSection">

<wf:container styleClass="helperText">
<f:verbatim>
Veldu rafverktoku
</f:verbatim>
</wf:container>

<wf:container styleClass="formItem required">
<h:outputLabel for="verktokuDrop" value="Veitustadur"/>
<h:selectOneMenu onchange="this.form.submit()" id="verktokuDrop" valueChangeListener="#{SkyrslaRafverktaka.onChangeRafverktaka}">
<f:selectItem itemLabel="Veldu:" itemValue=""/>
<f:selectItems value="#{RafverktokuListi.rafverktokuListiSelects}">

</f:selectItems>
</h:selectOneMenu>
</wf:container>
</wf:container-->

<wf:container styleClass="formSection">
<wf:container styleClass="helperText">
<f:verbatim>
Upplysingar um rafverktaka
</f:verbatim>
</wf:container>

<wf:container
rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}"
styleClass="formItem">
<h:outputText
rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}"
value="Þetta mál er núna í vinnslu hjá: "/>
</wf:container>
<wf:container styleClass="formItem">
<h:outputText
rendered="#{TilkynningVertakaBean.newOwnerOfCase != null}"
id="newOwner" value="#{TilkynningVertakaBean.newOwnerOfCase.nafn}"/>
</wf:container>



<wf:container styleClass="formItem required floated">
<h:outputLabel for="rafverktakaFyrirtaeki" value="Rafverktakafyrirtaeki"/>
<h:inputText id="rafverktakaFyrirtaeki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required floated">
<h:outputLabel for="loggilturRafverktaki" value="Loggiltur rafverktaki"/>
<h:inputText id="loggilturRafverktaki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafn}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required floated">
<h:outputLabel for="heimilisfangRafverktaka" value="Heimilisfang"/>
<h:inputText id="heimilisfangRafverktaka"
value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimilisfang.display}"
disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required floated">
<h:outputLabel for="kennitalaRafverktaka" value="Kennitala rafverktaka"/>
<h:inputText id="kennitalaRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required floated">
<h:outputLabel for="heimasimiRafverktaka" value="Heimasimi"/>
<h:inputText id="heimasimiRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required floated">
<h:outputLabel for="vinnusimiRafverktaka" value="Vinnusimi"/>
<h:inputText id="vinnusimiRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.vinnusimi}" disabled="true" />
</wf:container>
</wf:container>
<wf:container styleClass="formSection">
<wf:container styleClass="helperText">
<f:verbatim>
Upplysingar um veitustad
</f:verbatim>

</wf:container>
<wf:container styleClass="formItem"
rendered="#{TilkynningVertakaBean.invalid['workingPlace'] != null}">
<h:outputText
style="#{TilkynningVertakaBean.invalid['workingPlace'] == null ? 'color:black' : 'color:red'}"
value="Veldu veitustað"/>
</wf:container>

<wf:container styleClass="formItem required">
<h:outputLabel for="postnumerDrop" value="Póstnúmer"/>
<h:selectOneMenu
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="postnumerDrop" value="#{TilkynningVertakaBean.postnumer}" onchange="updateStreets();">
<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
</h:selectOneMenu>
<h:outputLabel for="gotuDrop" value="Gata" />
<h:selectOneMenu
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="gotuDrop" value="#{TilkynningVertakaBean.gata}" onchange="updateStreetNumberLabel();">
<f:selectItems value="#{TilkynningVertakaBean.gotuListiSelects}"/>
</h:selectOneMenu>
<h:outputLabel id="gotunumerLabel" for="gotunumer" value="Götunúmer" />
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="gotunumer" value="#{TilkynningVertakaBean.gotunumer}"/>
</wf:container>

<wf:container
rendered="#{TilkynningVertakaBean.applicationReportStorable}"
styleClass="formItem required">
<f:verbatim><input type="button" name="Text 1" value="Fletta upp í Landskrá Fasteigna" onclick="updateRealEstates();"></input></f:verbatim>
<!--h:commandButton id="flettaUppIFasteignaskraButton" action="#{TilkynningVertakaBean.flettaUppIFasteignaskra}" value="Fletta upp í Landskrá Fasteigna"/-->
<!--h:selectOneMenu id="fasteignirDrop" rendered="#{TilkynningVertakaBean.availablefasteign}" value="#{TilkynningVertakaBean.fastanumer}" onchange="submit();"-->
<h:selectOneMenu
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="fasteignirDrop" value="#{TilkynningVertakaBean.fastanumer}" onclick="updateEnergyConsumerFields();" >
<f:selectItems value="#{TilkynningVertakaBean.fasteignaListiSelects}"/>
</h:selectOneMenu>
</wf:container>
<wf:container styleClass="formItem">
<h:outputText id="veitustadurDisplay" value="#{TilkynningVertakaBean.veitustadurDisplay}" />
</wf:container>


<wf:container styleClass="formItem required floated">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['name'] == null ? 'color:black' : 'color:red'}" for="orkukaupandi" value="Nafn orkukaupanda" />
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="orkukaupandi" value="#{TilkynningVertakaBean.nafnOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem required floated">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['energyConsumerPersonalId'] == null ? 'color:black' : 'color:red'}" for="kennitalaOrkukaupanda" value="Kennitala" />
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="kennitalaOrkukaupanda" value="#{TilkynningVertakaBean.kennitalaOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem required floated">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['energyConsumerHomePhone'] == null ? 'color:black' : 'color:red'}" for="heimasimiOrkukaupanda" value="Heimasími"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="heimasimiOrkukaupanda" value="#{TilkynningVertakaBean.heimasimiOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem required floated">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['energyConsumerWorkPhone'] == null ? 'color:black' : 'color:red'}" for="vinnusimiOrkukaupanda" value="Vinnusími" />
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="vinnusimiOrkukaupanda" value="#{TilkynningVertakaBean.vinnusimiOrkukaupanda}"/>
</wf:container>

</wf:container>

<wf:container styleClass="button">
<h:commandButton
action="next"
value="áfram"/>

<h:outputText value=" "/>

<h:commandButton
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
action="#{TilkynningLokVerksBean.store}"
value="geyma"/>
</wf:container>

</h:form>

</builder:region>
</builder:page>
</f:view>
</jsp:root>