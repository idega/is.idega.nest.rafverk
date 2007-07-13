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
<builder:page id="builderpage_346" template="93">
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
<wf:container styleClass="formItem required">
<h:outputLabel for="tilkynnt" value="Tilkynnt er"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="tilkynnt" value="#{TilkynningLokVerksBean.tilkynnt}">
<f:selectItems value="#{RafverktakaInitialdata.tilkynntListiSelects}"/>
</h:selectOneRadio>
</wf:container>
<wf:container styleClass="formItem">
<h:outputLabel for="annadTilkynnt" value="Annað"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
size="24" id="annadTilkynnt" value="#{TilkynningLokVerksBean.tilkynntAnnad}"/>
</wf:container>
</wf:container>


<wf:container styleClass="formItem required">
<h:outputLabel for="husnaedis" value="Notkun húsnæðis"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneMenu
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="notkunarflokkar" value="#{TilkynningLokVerksBean.notkunarflokkur}">
<f:selectItems value="#{RafverktakaInitialdata.notkunarflokkurListiSelects}"/>
</h:selectOneMenu>
</wf:container>



<wf:container styleClass="formItem">
<h:outputLabel for="skyringLokVerks" value="Nánari skyring á þvi sem tilkynnt er"/>
<h:inputTextarea
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="skyringLokVerks" value="#{TilkynningLokVerksBean.skyring}" rows="3" cols="60"/>
</wf:container>


<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem required">
<h:outputLabel for="veitukerfi" value="Veitukerfi"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="veitukerfi" value="#{TilkynningLokVerksBean.spennukerfi}">
<f:selectItems value="#{RafverktakaInitialdata.spennukerfiListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<wf:container styleClass="formItem">
<h:outputLabel for="annadVeitukerfi" value="Annað"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
size="8" id="annadVeitukerfi" value="#{TilkynningLokVerksBean.annad}"/>
</wf:container>
</wf:container>


<wf:container styleClass="formItem required">
<h:outputLabel for="varnarradstoefunLokVerks" value="Varnarráðstöfun"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectManyCheckbox
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="varnarradstoefunLokVerks" value="#{TilkynningLokVerksBean.varnarradstoefun}">
<f:selectItems value="#{RafverktakaInitialdata.varnarradstoefunListiSelects}"/>
</h:selectManyCheckbox>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<h:outputLabel for="jardskaut" value="Jarðskaut/sp.jöfnun"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectManyCheckbox
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="jardskaut" value="#{TilkynningLokVerksBean.jardskaut}">
<f:selectItems value="#{RafverktakaInitialdata.jardskautListiSelects}"/>
</h:selectManyCheckbox>
</wf:container>


<wf:container styleClass="formItem">
<h:outputLabel for="annadJardskaut" value="Annað"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
size="8" id="annadJardskaut" value="#{TilkynningLokVerksBean.jardskautAnnad}" />
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">


<wf:container styleClass="formItem">
<h:outputLabel for="maelisNumerLokVerks" value="Númer mælis rafveitu"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
id="maelisNumerLokVerks" value="#{TilkynningLokVerksBean.maelir.numer}"/>
<h:message for="maelisNumerLokVerks"></h:message>
</wf:container>


<wf:container styleClass="formItem">
<h:outputLabel for="stadurMaelisLokVerks" value="Staður mælis"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
size="8" id="stadurMaelisLokVerks" value="#{TilkynningLokVerksBean.maelir.stadur}"/>
</wf:container>
</wf:container>


<wf:container styleClass="formItem">
<h:outputLabel for="skyringarLokVerks" value="Skyringar"/>
<h:inputTextarea
disabled="#{! TilkynningVertakaBean.applicationReportStorable}"
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