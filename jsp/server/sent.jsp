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
<builder:page id="builderpage_381" template="93">
<builder:region id="left" label="left">

<wf:container rendered="#{TilkynningVertakaBean.successfullyStored}" styleClass="receipt"> <!-- 1 -->

<wf:container styleClass="receiptImage"/>  <!-- 2 and 2 close -->
<h:outputText value="#{TilkynningVertakaBean.messageStoring}"></h:outputText>

<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningVertaka}"> <!-- 3 -->

<wf:container>
<h:outputLink value="#{TilkynningVertakaBean.downloadTilkynningVertakaPDF}" target="_blank">
  <f:verbatim>pjonustubeidni.pdf</f:verbatim>
</h:outputLink>
</wf:container>

<wf:container>
<h:outputLink value="#{TilkynningVertakaBean.downloadTilkynningVertakaXML}" target="_blank">
<f:verbatim>pjonustubeidni.xml</f:verbatim>
</h:outputLink>
</wf:container>

</wf:container> <!-- 3 close -->
 
<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningVertaka}">
<h:outputText value="#{TilkynningVertakaBean.messagePDF}"></h:outputText>
</wf:container>

<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningLokVerks}"> <!-- 4 -->

<wf:container>
<h:outputLink value="#{TilkynningVertakaBean.downloadTilkynningLokVerksPDF}" target="_blank">
  <f:verbatim>skyrsla.pdf</f:verbatim>
</h:outputLink>
</wf:container>

<wf:container>
<h:outputLink value="#{TilkynningVertakaBean.downloadTilkynningLokVerksXML}" target="_blank">
<f:verbatim>skyrsla.xml</f:verbatim>
</h:outputLink>
</wf:container>

</wf:container> <!-- 4 close -->

<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningLokVerks}">
<h:outputText value="#{TilkynningVertakaBean.messagePDF}"></h:outputText>
</wf:container>

</wf:container><!-- 1 close -->

</builder:region>
</builder:page>
</f:view>
</jsp:root>