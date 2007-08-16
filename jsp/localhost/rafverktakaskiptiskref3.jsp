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
<builder:page id="builderpage_661" template="93">
<builder:region id="left" label="left">

<wf:container styleClass="receipt"> <!-- 1 -->

<wf:container styleClass="receiptImage"/> <!-- 2 and 2 close -->
<h:outputText value="#{ChangeElectricianBean.messageStoring}"></h:outputText>



</wf:container><!-- 1 close -->

</builder:region>
</builder:page>
</f:view>
</jsp:root>