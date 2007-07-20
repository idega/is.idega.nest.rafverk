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
<builder:page id="builderpage_382" template="93">
<builder:region id="left" label="left">

<wf:container styleClass="receipt">

<wf:container rendered="#{TilkynningVertakaBean.successfullyStored}" styleClass="receipt">

<wf:container styleClass="receiptImage"/>
<h:outputText value="#{TilkynningVertakaBean.messageStoring}"></h:outputText>

</wf:container>

</wf:container>


</builder:region>
</builder:page>
</f:view>
</jsp:root>