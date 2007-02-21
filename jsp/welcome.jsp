<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page">
	<jsp:directive.page contentType="text/html;charset=UTF-8"
		pageEncoding="UTF-8" />
	<f:view>
		<html id="html1">
		<head id="head1">
		<jsp:include page="head.jsp" />
		</head>
		<body>
		<jsp:include page="top.jsp" />
		<h:form id="form1">
			<div class="generalContent">Rafverktakar:
				<ul>
					<li><h:outputLink id="hyperlink2"
						value="/pages/tilkynningVerktaka.jsf">
						<h:outputText id="hyperlink2Text" value="Tilkynning um rafverktoku (Tilkynning um nytt verk)" />
					</h:outputLink></li>
					<li><h:outputLink id="hyperlink1"
						value="/pages/tilkynningLokVerks.jsf">
						<h:outputText id="hyperlink2Text1" value="Skyrsla um neysluveitu (Tilkynning um lok verks)" />
					</h:outputLink></li>
				</ul>
			</div>
			<div class="generalContent">Skodunarstofur:
				<ul>
					<li>
						<h:outputLink id="skodunarstodvarLink" value="/pages/skyrslaSkodunarstodvar1.jsf">
							<h:outputText id="skodunarstodvarText" value="Skyrsla skodunarstofu" />
						</h:outputLink>
					</li>
				</ul>
			</div>
			<div class="generalContent">Veitufyrirtaeki:
				<ul>
					<li>
						<h:outputLink id="rafveitaSkyrslaLink" value="/pages/rafveitaSkyrsla.jsf">
							<h:outputText id="rafveitaSkyrslaText" value="Saekja thjonustubeidnir fra Rafverktokugatt Neytendastofa" />
						</h:outputLink>
					</li>
					<li>
						<h:outputLink id="rafveitaInnlesturLink" value="/pages/rafveitaInnlestur.jsf">
							<h:outputText id="rafveitaInnlesturText" value="Senda inn tilkynningu um rafverktoku til Neytendastofu" />
						</h:outputLink>
					</li>
				</ul>
			</div>
			<div class="generalContent">Neytendastofa:
				<ul>
					<li>
						<h:outputLink id="Rafverktakalisti" value="/pages/rafverktakaListi.jsf">
							<h:outputText id="RafverktakalistiText" value="Rafverktakaskra" />
						</h:outputLink>
					</li>
					<li>
						<h:outputLink id="skyrslaRafverktokurLink" value="/pages/yfirlitRafverktokurNest1.jsf">
							<h:outputText id="skyrslaRafverktokurText" value="Yfirlit yfir rafverktokur" />
						</h:outputLink>
					</li>
					<li>
						<h:outputLink id="SkilabodLink" value="/pages/skilabodTilRafverktaka.jsf">
							<h:outputText id="SkilabodLinkText" value="Senda fyrirspurn til rafverktaka" />
						</h:outputLink>
					</li>					
					<li>
						<h:outputLink id="MalaskrarLink" value="???">
							<h:outputText id="MalaskrarTexti" value="Fara yfir i malaskrarkerfi Neytendastofu" />
						</h:outputLink>
					</li>
				</ul>
			</div>
		</h:form>
		<jsp:include page="bottom.jsp" />
		</body>
		</html>
	</f:view>
</jsp:root>
