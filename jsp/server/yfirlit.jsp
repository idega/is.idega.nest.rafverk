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
<builder:page id="builderpage_541" template="93">
<builder:region id="left" label="left">

<wf:container styleClass="formSection"> <!-- 1 -->

<wf:container styleClass="formItem">
<f:verbatim><h3>Nafn orkukaupanda</h3></f:verbatim>
<h:outputText
id="orkukaupandi" value="#{TilkynningVertakaBean.nafnOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Kennitala</h3></f:verbatim>
<h:outputText
id="kennitalaOrkukaupanda" value="#{TilkynningVertakaBean.kennitalaOrkukaupanda}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Veitustaður</h3></f:verbatim>
<h:outputText
id="veitustadur" value="#{TilkynningVertakaBean.veitustadurDisplay}"/>
</wf:container>

<wf:container styleClass="formItem">
<f:verbatim><h3>Staða</h3></f:verbatim>
<h:outputText
id="stada" value="#{TilkynningVertakaBean.rafverktaka.stadaDisplay}"/>
</wf:container>

<h:outputText value="#{TilkynningVertakaBean.messageStoring}"></h:outputText>

<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningVertaka}"> <!-- 3 -->

<wf:container>
<h:outputLink value="#{TilkynningVertakaBean.downloadTilkynningVertakaPDF}" target="_blank">
<f:verbatim>pjonustubeidni.pdf</f:verbatim>
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

</wf:container> <!-- 4 close -->

<wf:container rendered="#{TilkynningVertakaBean.downloadTilkynningLokVerks}">
<h:outputText value="#{TilkynningVertakaBean.messagePDF}"></h:outputText>
</wf:container>

</wf:container><!-- 1 close -->

</builder:region>
</builder:page>
</f:view>
</jsp:root>