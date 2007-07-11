<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:x="http://myfaces.apache.org/extensions"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"
version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_423" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk">

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
<li>2
</li>
<li class="current">3
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
<wf:container styleClass="formSection">

<wf:container styleClass="fieldsetContainer">
<!-- 16 -->
<wf:container styleClass="formItem">
<h:outputLabel for="hringrasarvidnam" value="Hringrásarviðnám aðal-/greinitöflu"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="hringrasarvidnam" value="#{TilkynningLokVerksBean.hringrasarvidam}" />
<h:outputLabel for="hringrasarvidnam" value="Ω"/>
<h:message for="hringrasarvidnam"></h:message>
</wf:container>

<!-- 17 -->
<wf:container styleClass="formItem">
<h:outputLabel for="skammhlaupsstraumur" value="Skammhlaupsstraumur aðal-/greinitöflu"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="skammhlaupsstraumur" value="#{TilkynningLokVerksBean.skammhlaupsstraumur}" />
<h:outputLabel for="skammhlaupsstraumur" value="A"/>
<h:message for="skammhlaupsstraumur"></h:message>
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 19 -->
<wf:container styleClass="formItem">
<h:outputLabel for="einangrunNeysluveitu" value="Einangrun neysluveitu"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="einangrunNeysluveitu" value="#{TilkynningLokVerksBean.einangrunNeysluveitu}"/>
<h:outputLabel for="einangrunNeysluveitu" value="MΩ"/>
<h:message for="einangrunNeysluveitu"></h:message>
</wf:container>
<!-- 20 -->
<wf:container styleClass="formItem">
<h:outputLabel for="hringrasarvidnamJardskaut" value="Hringrásarviðnámsmæling jarðskauts/sp.jöfnunar"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="hringrasarvidnamJardskaut" value="#{TilkynningLokVerksBean.hringrasarvidnamJardskaut}"/>
<h:outputLabel for="hringrasarvidnamJardskaut" value="Ω"/>
<h:message for="hringrasarvidnamJardskaut"></h:message>
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 21 -->
<wf:container styleClass="formItem">
<h:outputLabel for="skammhlaupsstraumurNeysluveitu" value="Skammhlaupsstraumur neysluveitu"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="skammhlaupsstraumurNeysluveitu" value="#{TilkynningLokVerksBean.skammhlaupsstraumurNeysluveitu}"/>
<h:outputLabel for="skammhlaupsstraumurNeysluveitu" value="A"/>
<h:message for="skammhlaupsstraumurNeysluveitu"></h:message>
</wf:container>
<!-- 22 -->
<wf:container styleClass="formItem">
<h:outputLabel for="hringrasarvidnamNeysluveitu" value="Hringrásarviðnámsmæling neysluveitu"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="hringrasarvidnamNeysluveitu" value="#{TilkynningLokVerksBean.hringrasarvidnamNeysluveitu}"/>
<h:outputLabel for="hringrasarvidnamNeysluveitu" value="Ω"/>
<h:message for="hringrasarvidnamNeysluveitu"></h:message>
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 23 -->
<wf:container styleClass="formItem">
<h:outputLabel for="maeldSpennaFasiN" value="Maeld spenna Fasi-N" />
</wf:container>
<wf:container>
<h:inputText size="8" id="maeldSpennaFasiN" value="#{TilkynningLokVerksBean.maeldSpennaFasiN}"/>
<h:outputLabel for="maeldSpennaFasiN" value="V"/>
<h:message for="maeldSpennaFasiN"></h:message>
</wf:container>
<!-- 24 -->
<wf:container styleClass="formItem">
<h:outputLabel for="maeldSpennaFasiFasi" value="Maeld spenna Fasi-Fasi"/>
</wf:container>
<wf:container>
<h:inputText size="8" id="maeldSpennaFasiFasi" value="#{TilkynningLokVerksBean.maeldSpennaFasiFasi}"/>
<h:outputLabel for="maeldSpennaFasiFasi" value="V"/>
<h:message for="maeldSpennaFasiFasi"></h:message>
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 25 -->
<wf:container styleClass="formItem required">
<h:outputLabel for="lekastraumsrofi" value="Lekastraumsrofi"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio id="lekastraumsrofi" value="#{TilkynningLokVerksBean.lekastraumsrofi}">
<f:selectItems value="#{RafverktakaInitialdata.lekastraumsrofiListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<!-- 27 -->
<wf:container>
<h:inputText size="4" id="spennuhaekkunUtleysingVolt"
value="#{TilkynningLokVerksBean.spennuhaekkunUtleysingVolt}"/>
<h:outputLabel for="spennuhaekkunUtleysingVolt" value="V" />
<h:message for="spennuhaekkunUtleysingVolt"></h:message>
</wf:container>

<!-- 29 -->
<wf:container>
<h:inputText size="4" id="lekastraumsrofaUtleysingMillisecond"
value="#{TilkynningLokVerksBean.lekastraumsrofaUtleysingMillisecond}"/>
<h:outputLabel for="lekastraumsrofaUtleysingMillisecond" value="ms"/>
<h:message for="lekastraumsrofaUtleysingMillisecond"></h:message>
</wf:container>
</wf:container>


<!-- 30 -->
<wf:container styleClass="formItem">
<h:outputLabel for="skyringarMaelingarLokVerks" value="Skyringar"/>
<h:inputTextarea
id="skyringarMaelingarLoksVerks"
rows="3"
cols="60"
value="#{TilkynningLokVerksBean.skyringarMaelingar}"/>
</wf:container>


<!-- formsection ends -->
</wf:container>

<wf:container styleClass="button">
<h:commandButton
action="back"
value="til baka"/>

<h:outputText value=" "/>

<h:commandButton
action="#{TilkynningLokVerksBean.store}"
value="geyma"/>
<h:commandButton
action="#{TilkynningLokVerksBean.send}"
value="senda"/>
</wf:container>


</h:form>
</builder:region>
</builder:page>
</f:view>
</jsp:root>