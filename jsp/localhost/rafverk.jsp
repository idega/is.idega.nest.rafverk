<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
xmlns="http://www.w3.org/1999/xhtml"
xmlns:h="http://java.sun.com/jsf/html"
xmlns:f="http://java.sun.com/jsf/core"
xmlns:builder="http://xmlns.idega.com/com.idega.builder"
xmlns:x="http://myfaces.apache.org/tomahawk"
xmlns:wf="http://xmlns.idega.com/com.idega.webface"
version="1.2">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>

<f:view>
<builder:page id="builderpage_424242" template="93">
<builder:region id="left" label="left">
<h:form id="form1" styleClass="rafverk">



<wf:container styleClass="formSection">
<!--h:outputText value="Mínar rafverktökur:"/-->
<f:verbatim><h2>Mínar rafverktökur:</h2></f:verbatim>
</wf:container>


<wf:container styleClass="formSection">
<wf:container styleClass="formItem">
<h:outputLabel for="filterVerktakaStatuses" value="Staða verks"/>

<h:selectOneMenu id="filterVerktakaStatuses" value="#{RafverktokuListi.selectedStatus}">
<f:selectItems value="#{RafverktokuListi.possibleStatusesSelects}"/>
</h:selectOneMenu>
</wf:container>
<h:commandButton id="filterVerktakaStatusesButton" action="#{RafverktakaListi.filter}" value="Finna"/>

</wf:container>
<wf:container styleClass="formSection">


<x:dataScroller for="myTable"
fastStep="10"
pageIndexVar="hello1"
pageCountVar="hello2"
paginator="true"
paginatorMaxPages="9">
<f:facet name="first" ><h:outputText value="first"/>
</f:facet>
<f:facet name="last"><h:outputText value="last"/>
</f:facet>
<f:facet name="previous"><h:outputText value="previous"/>
</f:facet>
<f:facet name="next"><h:outputText value="next"/>
</f:facet>
<f:facet name="fastforward"><h:outputText value="fast forward"/>
</f:facet>
<f:facet name="fastrewind"><h:outputText value="fast rewind"/>
</f:facet>

</x:dataScroller>



<h:dataTable id="myTable" rows="10" value="#{RafverktokuListi.rafverktokur}" var="verktaka" columnClasses="oddRow evenRow" styleClass="caseTable ruler" >
<h:column>
<f:facet name="header">
<h:outputText value=""/>
</f:facet>
<h:commandLink action="tilkynningvertaka" actionListener="#{RafverktokuListi.populateForms}"
value=">"/>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value=""/>
</f:facet>
<h:commandLink action="tilkynninglokverks" actionListener="#{RafverktokuListi.populateForms}"
value=">>"/>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Verknúmer"/>
</f:facet>
<h:outputText value="#{verktaka.externalProjectID}"/>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Rafveita"/>
</f:facet>
<h:outputText value="#{verktaka.orkufyrirtaeki.name}"/>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Orkukaupandi"/>
</f:facet>

<h:outputText value="#{verktaka.orkukaupandi.nafn}"/>

</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Veitustaður"/>
</f:facet>

<h:outputText value="#{verktaka.veitustadurDisplay}"/>

</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Staða"/>
</f:facet>
<h:outputText value="#{verktaka.stadaDisplay}"/>
</h:column>


</h:dataTable>
</wf:container>
<wf:container styleClass="formSection">
<h:commandButton
id="welcome_1"
action="#{TilkynningVertakaBean.startTilkynningVertaka}"
immediate="true"
value="Stofna þjónustubeiðni"/>

<h:commandButton
id="welcome_2"
action="#{TilkynningVertakaBean.startTilkynningLokVerks}"
immediate="true"
value="Skrá skýrslu um neysluveitu"/>
</wf:container>
</h:form>





</builder:region>
</builder:page>
</f:view>

</jsp:root>