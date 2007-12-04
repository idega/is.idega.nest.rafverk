<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:builder="http://xmlns.idega.com/com.idega.builder"
	xmlns:wf="http://xmlns.idega.com/com.idega.webface"
	xmlns:x="http://myfaces.apache.org/tomahawk" version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<f:view>
<builder:page id="builderpage_382" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk citizenForm">


<x:div styleClass="receipt">

<x:div styleClass="receiptImage"/>
<x:htmlTag value="h1"><f:verbatim>Skráning geymd</f:verbatim></x:htmlTag>
<x:htmlTag value="p">
<h:outputText escape="false" value="#{TilkynningVertakaBean.messageStoring}"></h:outputText>
</x:htmlTag>

<x:div styleClass="buttonLayer rafverkButtons">

<h:commandLink id="welcome_1" styleClass="button" action="rafverk">

<f:verbatim><span>Rafverktökur</span></f:verbatim>

</h:commandLink>

<h:commandLink id="welcome_2" styleClass="button" action="#{TilkynningVertakaBean.startTilkynningVertaka}">

<f:verbatim><span>Stofna þjónustubeiðni</span></f:verbatim>

</h:commandLink>


<h:commandLink id="welcome_3" styleClass="button" action="#{TilkynningVertakaBean.startTilkynningLokVerks}">

<f:verbatim><span>Skrá skýrslu um neysluveitu</span></f:verbatim>

</h:commandLink>

</x:div>
</x:div>

</h:form>

</builder:region>
</builder:page>
</f:view>
</jsp:root>