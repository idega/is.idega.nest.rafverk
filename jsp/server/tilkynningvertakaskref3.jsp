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
<builder:page id="builderpage_344" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk">


<wf:container rendered="#{TilkynningVertakaBean.applicationInvalid}" styleClass="errorLayer">
<wf:container rendered="#{TilkynningVertakaBean.applicationInvalid}" styleClass="errorImage"/>
<h:outputText rendered="#{TilkynningVertakaBean.applicationInvalid}" value="Sending mistókst: Fylla verður í alla skilyrta reiti"/>
<h:outputText value=" "/>
<h:commandButton
styleClass="jsfButton"
rendered="#{TilkynningVertakaBean.applicationInvalid}"
action="firstWizardPage"
value="Fara á fyrsta skref"/>
</wf:container>

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


<!-- taka -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="takaAddAnchor" name="takaAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['taka'] == null ? 'color:black' : 'color:red'}" id="takaAddAnchor" value="Taka mæli..."/>
</wf:container>
<h:dataTable id='taka' value="#{TilkynningVertakaBean.list['taka']}" var="maelir">
<h:column>

<h:commandLink
id="takaAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{(! maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="takaAddAnchor" />
</h:commandLink>

<h:commandLink
id="takaDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{(maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="takaAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>

<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="taka"
value="#{maelir.numer}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="taka"
value="mæli numer"
rendered="#{maelir.valid}"/>
<h:message for="taka"></h:message>
</h:column>
</h:dataTable>
</wf:container>

<!-- fyrir -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="fyrirAddAnchor" name="fyrirAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['fyrir'] == null ? 'color:black' : 'color:red'}" value="Fyrir er..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['fyrir']}" var="maelir">
<h:column>

<h:commandLink
id="fyrirAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{(! maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fyrirAddAnchor" />
</h:commandLink>

<h:commandLink
id="fyrirDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{(maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fyrirAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>

<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="fyrir"
value="#{maelir.numer}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="fyrir"
value="mæli numer"
rendered="#{maelir.valid}"/>
<h:message for="fyrir"></h:message>
</h:column>
</h:dataTable>
</wf:container>

<!-- setja maeli -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="setjaAddAnchor" name="setjaAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['setja'] == null ? 'color:black' : 'color:red'}" value="Setja mæli..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['setja']}" var="maelir">
<h:column>

<h:commandLink
id="setjaAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{(! maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="setjaAddAnchor" />
</h:commandLink>
<h:commandLink
id="setjaDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="setjaAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>
<h:selectOneRadio
disabled="#{! TilkynningVertakaBean.applicationStorable}"
value="#{maelir.fasa}"
rendered="#{maelir.valid}">
<f:selectItems value="#{RafverktakaInitialdata.maeliListiSelects}"/>
</h:selectOneRadio>
<h:outputLabel
for="setja"
value="Stærð"
rendered="#{maelir.valid}"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
size="4"
id="setja"
value="#{maelir.ampere}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="setja"
value="A"
rendered="#{maelir.valid}"/>
<h:message for="setja"></h:message>
<h:outputLabel
for="setjaT"
value="Taxti"
rendered="#{maelir.valid}"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="setjaT"
value="#{maelir.taxti}"
rendered="#{maelir.valid}"/>
</h:column>
</h:dataTable>
</wf:container>

<!-- flutt a -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="fluttAAddAnchor" name="fluttAAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['fluttA'] == null ? 'color:black' : 'color:red'}" value="Flutt á..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['fluttA']}" var="maelir">
<h:column>

<h:commandLink
id="fluttAAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{! maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fluttAAddAnchor" />
</h:commandLink>

<h:commandLink
id="fluttADelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fluttAAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>

<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="fluttA"
value="#{maelir.numer}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="fluttA"
value="mæli numer"
rendered="#{maelir.valid}"/>
<h:message for="fluttA"></h:message>
</h:column>
</h:dataTable>
</wf:container>

<!-- flutt af -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="fluttAfAddAnchor" name="fluttAfAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['fluttAf'] == null ? 'color:black' : 'color:red'}" value="Flutt af..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['fluttAf']}" var="maelir">
<h:column>

<h:commandLink
id="fluttAfAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{! maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fluttAfAddAnchor" />
</h:commandLink>

<h:commandLink
id="fluttAfDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="fluttAfAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="fluttAf"
value="#{maelir.numer}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="fluttAf"
value="mæli numer"
rendered="#{maelir.valid}"/>
<h:message for="fluttAf"></h:message>
</h:column>
</h:dataTable>
</wf:container>

<!-- hjalpataeki -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="hjalpataekiAddAnchor" name="hjalpataekiAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['hjalpataeki'] == null ? 'color:black' : 'color:red'}" value="Setja hjálpatæki..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['hjalpataeki']}" var="maelir">
<h:column>
<h:commandLink
id="hjalpataekiAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{(! maelir.valid) and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="hjalpataekiAddAnchor" />
</h:commandLink>
<h:commandLink
id="hjalpataekiDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="hjalpataekiAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="hjalpataeki"
value="#{maelir.hjalpataeki}"
rendered="#{maelir.valid}"/>
</h:column>
</h:dataTable>
</wf:container>

<!-- setja maeli -->
<wf:container styleClass="fieldsetContainer">
<wf:container styleClass="formItem">
<f:verbatim><a id="straumspennaAddAnchor" name="straumspennaAddAnchor" style="color:white">&#160;</a></f:verbatim>
<h:outputLabel style="#{TilkynningVertakaBean.invalid['straumspenna'] == null ? 'color:black' : 'color:red'}" value="Setja straumspennamæli..."/>
</wf:container>
<h:dataTable value="#{TilkynningVertakaBean.list['straumspenna']}" var="maelir">
<h:column>
<h:commandLink
id="straumspennaAdd"
action="#{maelir.add}"
value="bæta við"
rendered="#{! maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="straumspennaAddAnchor" />
</h:commandLink>
<h:commandLink
id="straumspennaDelete"
action="#{maelir.delete}"
value="fjarlægja"
rendered="#{maelir.valid and (TilkynningVertakaBean.applicationStorable)}">
<f:param name="anchorName" value="straumspennaAddAnchor" />
</h:commandLink>
<h:outputText value=" "/>
<h:outputLabel
for="straumspenna"
value="Stærð"
rendered="#{maelir.valid}"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
size="4"
id="straumspenna"
value="#{maelir.ampere}"
rendered="#{maelir.valid}"/>
<h:outputLabel
for="straumspenna"
value="A"
rendered="#{maelir.valid}"/>
<h:message for="straumspenna"></h:message>
<h:outputLabel
for="straumspennaT"
value="Taxti"
rendered="#{maelir.valid}"/>
<h:inputText
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="straumspennaT"
value="#{maelir.taxti}"
rendered="#{maelir.valid}"/>
</h:column>
</h:dataTable>
</wf:container>

<!-- 26 -->
<wf:container styleClass="formItem">
<h:outputLabel for="skyringar" value="Skyringar"/>
<h:inputTextarea
disabled="#{! TilkynningVertakaBean.applicationStorable}"
id="skyringar" rows="3" cols="60" value="#{TilkynningVertakaBean.skyringar}"/>
</wf:container>

<wf:container styleClass="button">

<h:commandButton
action="back"
value="til baka"/>

<h:outputText value=" "/>

<h:commandButton
disabled="#{! TilkynningVertakaBean.applicationStorable}"
action="#{TilkynningVertakaBean.store}"
value="geyma"/>
<h:commandButton
disabled="#{! TilkynningVertakaBean.applicationSendable}"
action="#{TilkynningVertakaBean.send}"
value="senda"/>

<h:outputText value=" "/>

<h:commandButton
action="#{TilkynningVertakaBean.goToTilkynningLokVerks}"
value="Tilkynning um lok verks"/>

</wf:container>

</wf:container>
</h:form>



</builder:region></builder:page>
</f:view>
</jsp:root>