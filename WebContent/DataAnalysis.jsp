<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org/tomahawk" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>DataBase Access</title>
</head>
<body>
	<f:view>
		<h:form id="databaseForm">
			<h2>
				<center>s16g21 - Data Analysis Page</center>
			</h2>
			<BR />
			<H4>
			<center><h:outputLink value="home.jsp" >Home</h:outputLink></center>
			</H4>
			<hr />
			<center>

				<h:selectOneMenu value="#{userBean.dbSchema}">
					<f:selectItem itemValue="world" itemLabel="World" />
					<f:selectItem itemValue="#{userBean.userName}"
						itemLabel="#{userBean.userName}" />
				</h:selectOneMenu>
			</center>
			<div align="right">
				<h:commandButton action="#{actionBeanWorld.logout}" value="Logout"></h:commandButton>
			</div>
			<hr />
			<h:panelGrid columns="15">
				<h:commandButton action="#{actionBeanWorld.tableQuery}"
					value="Table List" id="tableList"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.columnQuery}"
					value="Column List" id="columnList"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.displayStatistics}"
					value="Display Statistics"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.selectDependentVariable}"
					value="Dependent Variable"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.selectIndependentVariable}"
					value="Independent Variables"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.calculateRegression}"
					value="Calculate Regression Model"></h:commandButton>	
			</h:panelGrid>
			<h:messages for="columnList" styleClass="errorMessage"
				style="color:red" />
			<h:messages for="tableList" styleClass="errorMessage"
				style="color:green" />
				<h:selectOneListbox value="#{actionBeanWorld.table}"
				rendered="#{actionBeanWorld.renderTable}" size="10">
				<f:selectItems value="#{actionBeanWorld.outputs}" />
			</h:selectOneListbox>
			<h:selectManyListbox value="#{actionBeanWorld.column}"
				rendered="#{actionBeanWorld.renderColumn}" size="10">
				<f:selectItems value="#{actionBeanWorld.columnOutputs}" />
			</h:selectManyListbox>
			<h:selectOneListbox value="#{actionBeanWorld.dependent}"
				rendered="#{actionBeanWorld.renderColumnDA}" size="10">
				<f:selectItems value="#{actionBeanWorld.columnOutputsDA}" />
			</h:selectOneListbox>
			<h:selectManyListbox value="#{actionBeanWorld.independents}"
				rendered="#{actionBeanWorld.renderColumnDAI}" size="10">
				<f:selectItems value="#{actionBeanWorld.columnOutputsDAI}" />
			</h:selectManyListbox>
			<br></br>
			<br></br>
			<t:dataTable value="#{actionBeanWorld.outputList}" var="row"
					rendered="#{actionBeanWorld.renderSet}" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx" width="900">
					<t:column>
						<f:facet name="header">
							<h:outputText></h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.table}" />
						</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Mean</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.mean}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Median</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.median}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Minimum</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.minimum}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Maximum</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.maximum}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Standard Deviation</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.stddev}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Variance</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.variance}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Quartile 1</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.quartile1}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Quartile 3</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.quartile3}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Inter Quartile Range</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.iqr}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Range</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.range}" />
					</t:column>
				</t:dataTable>
				<br></br>
				<h:panelGroup layout="block" rendered="#{actionBeanWorld.renderDiv}">
				<center><h3><h:outputText>Equation : y(</h:outputText><h:outputText value="#{actionBeanWorld.dependent}"/><h:outputText>)=</h:outputText><h:outputText value="#{actionBeanWorld.equation}"></h:outputText></h3></center>
				<center><h4><h:outputText>Regression Parameters</h:outputText></h4></center>
				<center><t:dataTable value="#{actionBeanWorld.outputRegressionList}" var="row" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx" width="900">
					<t:column>
						<f:facet name="header">
							<h:outputText>Columns</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.columns}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Beta Value</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.beta}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Residuals</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.residual}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Standard Errors</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.standardErrors}" />
						</t:column>
					</t:dataTable></center>
					<BR />
					<center><t:dataTable value="" var="row" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx">
					<t:column>
						<f:facet name="header">
							<h:outputText>Regressand</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{actionBeanWorld.regressand}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>R Squared</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{actionBeanWorld.rSquared}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Sigma</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{actionBeanWorld.sigma}" />
						</t:column>
					</t:dataTable></center>
				</h:panelGroup>
				<h:panelGroup layout="block" rendered="#{actionBeanWorld.renderDivSingle}">
				<center><h3><h:outputText>Equation : y(</h:outputText><h:outputText value="#{actionBeanWorld.dependent}"/><h:outputText>)=</h:outputText><h:outputText value="#{actionBeanWorld.equation}"></h:outputText></h3></center>
				<center><h4><h:outputText>Regression Parameters</h:outputText></h4></center>
				<center><t:dataTable value="#{actionBeanWorld.outputRegressionList}" var="row" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx" width="900">
					<t:column>
						<f:facet name="header">
							<h:outputText>Columns</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.columns}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Intercept</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.intercept}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Slope</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.slope}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>R-Square</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.rSquare}" />
						</t:column>
					</t:dataTable></center>
					<BR />
					<center><t:dataTable value="#{actionBeanWorld.outputRegressionList}" var="row" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx">
					<t:column>
						<f:facet name="header">
							<h:outputText>Confidence Interval(95%)</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.slopeConfidenceInterval}" />
						</t:column>
					<t:column>
						<f:facet name="header">
							<h:outputText>Significance</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.significance}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Intercept Std-Error</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.interceptStdErr}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Slope Std-Error</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.slopeStdErr}" />
						</t:column>
						<t:column>
						<f:facet name="header">
							<h:outputText>Mean Squared Error</h:outputText>
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row.meanSquaredError}" />
						</t:column>
					</t:dataTable></center>
				</h:panelGroup>
		</h:form>
	</f:view>
</body>
	</html>
</jsp:root>