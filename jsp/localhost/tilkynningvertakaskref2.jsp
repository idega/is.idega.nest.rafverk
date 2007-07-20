<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"
version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_502" template="93">
<builder:region id="left" label="left">

<h:form id="form1" styleClass="rafverk">
<!--div class="generalContent"-->

<f:verbatim><h1 class="applicationHeading">Thjonustubeidni</h1></f:verbatim>

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
<h:outputText value="#{TilkynningVertakaBean.rafverktaka.rafverktaki.nafn}"/>
</wf:container>

</wf:container>
<!-- end of formsection-->

<f:verbatim>
<h1 class="subHeader topSubHeader">Tilkynning um rafverktoku
</h1>
</f:verbatim>

<!-- form section -->
<wf:container styleClass="formSection">

<wf:container styleClass="helperText">
<f:verbatim>
Adrar upplysingar
</f:verbatim>
</wf:container>


<!-- 5 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['type'] == null ? 'color:black' : 'color:red'}" for="notkunarflokkar" value="Notkunarflokkur"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneMenu
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="notkunarflokkar" value="#{TilkynningVertakaBean.notkunarflokkur}">
<f:selectItems value="#{RafverktakaInitialdata.notkunarflokkurListiSelects}"/>
</h:selectOneMenu>
</wf:container>

<!-- 6 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['currentLineModification'] == null ? 'color:black' : 'color:red'}" for="heimtaug" value="Heimtaug"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="heimtaug" value="#{TilkynningVertakaBean.heimtaug}">
<f:selectItems value="#{RafverktakaInitialdata.heimtaugListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<!-- 7 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['currentLineConnectionModification'] == null ? 'color:black' : 'color:red'}" for="heimtaugTengist" value="Heimtaug tengist i"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="heimtaugTengist" value="#{TilkynningVertakaBean.heimtaugTengist}">
<f:selectItems value="#{RafverktakaInitialdata.heimtaugTengistListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<!-- 8 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['homeLine'] == null ? 'color:black' : 'color:red'}" for="stofnKvisl1" value="Stofn/kvisl" />
</wf:container>
<wf:container styleClass="stofninput">
<h:inputText size="4"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="stofnKvisl1" value="#{TilkynningVertakaBean.stofn1}"/>
<h:outputLabel for="stofnKvisl1" value="x"/>
<h:message for="stofnKvisl1"></h:message>
<h:inputText size="4"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="stofnKvisl2" value="#{TilkynningVertakaBean.stofn2}"/>
<h:outputLabel for="stofnKvisl2" value="+"/>
<h:message for="stofnKvisl2"></h:message>
<h:inputText size="4"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="stofnKvisl3" value="#{TilkynningVertakaBean.stofn3}"/>
<h:outputLabel for="stofnKvisl3" value="mm²" />
<h:message for="stofnKvisl3"></h:message>
</wf:container>

<!-- 9 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['switchPanelModification'] == null ? 'color:black' : 'color:red'}" for="adaltafla" value="Adaltafla/Maelitafla"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="adaltafla" value="#{TilkynningVertakaBean.adaltafla}">
<f:selectItems value="#{RafverktakaInitialdata.adaltaflaListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<!-- 10 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['electronicalProtectiveMeasures'] == null ? 'color:black' : 'color:red'}" for="varnaradstoefun" value="Varnarráðstöfun"/>
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectManyCheckbox
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="varnaradstoefun" value="#{TilkynningVertakaBean.varnarradstoefun}">
<f:selectItems value="#{RafverktakaInitialdata.varnarradstoefunListiSelects}"/>
</h:selectManyCheckbox>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 11 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['application'] == null ? 'color:black' : 'color:red'}" for="beidni" value="Beiðni um" />
</wf:container>
<wf:container styleClass="rafverkcheck">
<h:selectManyCheckbox
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="beidni" value="#{TilkynningVertakaBean.beidniUm}">
<f:selectItems value="#{RafverktakaInitialdata.beidniListiSelects}"/>
</h:selectManyCheckbox>
</wf:container>

<!-- 12 -->
<wf:container styleClass="formItem">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['power'] == null ? 'color:black' : 'color:red'}" for="beidniKiloWatt" value="Uppsett afl" />
</wf:container>
<wf:container>
<h:inputText size="8"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="beidniKiloWatt" value="#{TilkynningVertakaBean.uppsett}"/>
<h:outputLabel for="beidniKiloWatt" value="kW"/>
<h:message for="beidniKiloWatt"></h:message>
</wf:container>
</wf:container>

<!-- 13a -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['placeMeter'] == null ? 'color:black' : 'color:red'}" for="stadurMaelis" value="Staður mælis"/>
<h:inputText size="24"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="stadurMaelis" value="#{TilkynningVertakaBean.stadurMaelir.stadur}"/>
</wf:container>

<!-- 13b -->
<wf:container styleClass="formItem">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['switchPanelNumber'] == null ? 'color:black' : 'color:red'}" for="numerToeflu" value="Númer töflu"/>
<h:inputText size="24"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="numerToeflu" value="#{TilkynningVertakaBean.numerToeflu}"/>
<h:message for="numerToeflu"></h:message>
</wf:container>
</wf:container>

<wf:container styleClass="fieldsetContainer">
<!-- 14 -->
<wf:container styleClass="formItem required">
<h:outputLabel style="#{TilkynningVertakaBean.invalid['voltageSystemGroup'] == null ? 'color:black' : 'color:red'}" for="spennukerfi" value="Spennukerfi" />
</wf:container>
<wf:container styleClass="checkboxItem">
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="spennukerfi" value="#{TilkynningVertakaBean.spennukerfi}">
<f:selectItems value="#{RafverktakaInitialdata.spennukerfiListiSelects}"/>
</h:selectOneRadio>
</wf:container>

<!-- 15 -->
<wf:container styleClass="formItem">
<h:outputLabel for="annadSpennukerfi" value="Annað" />
<h:inputText size="24"
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="annadSpennukerfi" value="#{TilkynningVertakaBean.annad}"/>
</wf:container>
</wf:container>

<!-- formsection end -->
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
disabled="#{! TilkynningVertakaBean.applicationStorable}"
action="#{TilkynningVertakaBean.store}"
value="geyma"/>
</wf:container>
</h:form>



</builder:region>
</builder:page>
</f:view>
</jsp:root>