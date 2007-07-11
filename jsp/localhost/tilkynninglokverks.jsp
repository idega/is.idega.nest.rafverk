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
<builder:page id="builderpage_421" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk">
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
<wf:container styleClass="formItem required">
<h:outputLabel for="rafverktakaFyrirtaeki" value="Rafverktakafyrirtaeki"/>
<h:inputText id="rafverktakaFyrirtaeki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafnFyrirtaekis}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required">
<h:outputLabel for="loggilturRafverktaki" value="Loggiltur rafverktaki"/>
<h:inputText id="loggilturRafverktaki" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.nafn}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required">
<h:outputLabel for="heimilisfangRafverktaka" value="Heimilisfang"/>
<h:inputText id="heimilisfangRafverktaka"
value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimilisfang.display}"
disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required">
<h:outputLabel for="kennitalaRafverktaka" value="Kennitala rafverktaka"/>
<h:inputText id="kennitalaRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.kennitala}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required">
<h:outputLabel for="heimasimiRafverktaka" value="Heimasimi"/>
<h:inputText id="heimasimiRafverktaka" value="#{TilkynningLokVerksBean.rafverktaka.rafverktaki.heimasimi}" disabled="true"/>
</wf:container>
<wf:container styleClass="formItem required">
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
<wf:container styleClass="formItem required">
<h:outputLabel for="postnumerDrop" value="Postnumer"/>
<h:selectOneMenu id="postnumerDrop" value="#{TilkynningVertakaBean.postnumer}" onchange="submit();">
<f:selectItems value="#{RafverktakaInitialdata.postnumeraListiSelects}"/>
</h:selectOneMenu>
<h:outputLabel for="gotuDrop" value="Gata" />
<h:selectOneMenu id="gotuDrop" value="#{TilkynningVertakaBean.gata}">
<f:selectItems value="#{TilkynningVertakaBean.gotuListiSelects}"/>
</h:selectOneMenu>
<h:outputLabel for="gotunumer" value="Gotunumer" />
<h:inputText id="gotunumer" value="#{TilkynningVertakaBean.gotunumer}"/>
</wf:container>

<wf:container styleClass="formItem required">
<h:commandButton id="flettaUppIFasteignaskraButton" action="#{TilkynningVertakaBean.flettaUppIFasteignaskra}" value="Fletta upp í Landskrá Fasteigna"/>
<h:selectOneMenu id="fasteignirDrop" rendered="#{TilkynningVertakaBean.availablefasteign}" value="#{TilkynningVertakaBean.fastanumer}" onchange="submit();" >
<f:selectItems value="#{TilkynningVertakaBean.fasteignaListiSelects}"/>
</h:selectOneMenu>
</wf:container>
<wf:container styleClass="formItem">
<h:outputText value="#{TilkynningVertakaBean.veitustadurDisplay}" />
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
</builder:region>
</builder:page>
</f:view>
</jsp:root>