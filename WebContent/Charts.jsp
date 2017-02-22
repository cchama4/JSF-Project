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
		<h:form id="databaseChart">
			<h2>
				<center>s16g21 - Charts Page</center>
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
			<h:panelGrid columns="16">
				<h:commandButton action="#{actionBeanWorld.tableQuery}"
					value="Table List" id="tableList"></h:commandButton>
				<h:outputText value=" "></h:outputText>
					<h:selectOneListbox id="columnList" value="#{actionBeanWorld.chartType}" size="1" >
					<f:selectItem itemValue="pichart" itemLabel="Pi Chart" />
					<f:selectItem itemValue="lineChart" itemLabel="Line Chart" />
					<f:selectItem itemValue="histogram" itemLabel="Histogram" />
					<f:selectItem itemValue="scatterPlot" itemLabel="Scatter Plot" />
			</h:selectOneListbox>
			<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.valueChangedColumn1}"
					value="Populate Response"></h:commandButton>
					<h:outputText value=" "></h:outputText>
					<h:commandButton action="#{actionBeanWorld.valueChangedColumn2}"
					value="Populate Predictor"></h:commandButton>
								<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.populateChart}"
					value="Populate Chart"></h:commandButton>
			</h:panelGrid>
			<h:messages for="columnList" styleClass="errorMessage"
				style="color:red" />
			<h:messages for="tableList" styleClass="errorMessage"
				style="color:green" />
				<h:selectOneListbox value="#{actionBeanWorld.table}"
				rendered="#{actionBeanWorld.renderTable}" size="10">
				<f:selectItems value="#{actionBeanWorld.outputs}" />
			</h:selectOneListbox>
			<h:selectOneListbox value="#{actionBeanWorld.categoricalChart}"
				rendered="#{actionBeanWorld.rendercateogaricalRender}" size="10">
				<f:selectItems value="#{actionBeanWorld.chartColumns}" />
			</h:selectOneListbox>
			<h:selectOneListbox value="#{actionBeanWorld.numericalChart}"
				rendered="#{actionBeanWorld.rendernumericalRender}" size="10">
				<f:selectItems value="#{actionBeanWorld.chartColumnsNumerical}" />
			</h:selectOneListbox>
			<BR />
			<center><h:graphicImage value="/temp/Chart.png" height="450" width="600" rendered="#{actionBeanWorld.renderChart}" alt="xySeriesChart"/></center>
				</h:form>
</f:view>
</body>
</html>
</jsp:root>