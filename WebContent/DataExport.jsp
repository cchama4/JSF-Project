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
<title>Home</title>
</head>
<body>
<f:view>
<h:form id="databaseForm">
			<h2>
				<center>s16g21 - Database Export Page</center>
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
				<h:commandButton action="#{actionBeanWorld.csvExport}"
					value="Export as CSV" id="columnList"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.xmlExport}"
					value="Export as XML"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				</h:panelGrid>
				<h:messages for="columnList" styleClass="errorMessage"
				style="color:red" />
			<h:messages for="tableList" styleClass="errorMessage"
				style="color:green" />
			<h:selectOneListbox value="#{actionBeanWorld.table}"
				rendered="#{actionBeanWorld.renderTable}" size="10">
				<f:selectItems value="#{actionBeanWorld.outputs}" />
			</h:selectOneListbox>
				</h:form>
</f:view>
</body>
</html>
</jsp:root>