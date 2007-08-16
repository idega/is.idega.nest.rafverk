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
<builder:page id="builderpage_641" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="citizenForm">

<wf:container styleClass="formSection"> <!-- 1 -->

<wf:container styleClass="formItem">
<f:verbatim><h3>Nafn orkukaupanda</h3></f:verbatim>
<h:outputText
id="orkukaupandi" value="#{ChangeElectricianBean.nafnOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Kennitala</h3></f:verbatim>
<h:outputText
id="kennitalaOrkukaupanda" value="#{ChangeElectricianBean.kennitalaOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Veitustaður</h3></f:verbatim>
<h:outputText
id="veitustadur" value="#{ChangeElectricianBean.veitustadurDisplay}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Staða</h3></f:verbatim>
<h:outputText
id="stada" value="#{ChangeElectricianBean.stadaDisplay}"/>
</wf:container>

</wf:container><!-- 1 close -->

<wf:container styleClass="bottom">
<h:commandButton
styleClass="buttonSpan"
action="cancel"
value="Hætta við"/>

<h:outputText value=" "/>

<h:commandButton
action="#{ChangeElectricianBean.changeElectrician}"
value="Viltu senda beiðni um breytingu?"/>
</wf:container>

</h:form>
</builder:region>
</builder:page>
</f:view>
</jsp:root>