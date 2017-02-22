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
<h:form id="databaseHome">
			<h3>
				<center>s16g21 - Home</center>
			</h3>
			<hr />
			<center>
				<h:outputLink value="DataBase.jsp" >Database Access</h:outputLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:outputLink value="DataAnalysis.jsp" >Data Analysis Page</h:outputLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:outputLink value="Charts.jsp" >Charts</h:outputLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:outputLink value="DataImport.jsp" >Data Import</h:outputLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:outputLink value="DataExport.jsp" >Data Export</h:outputLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:commandLink action="#{actionBeanWorld.showAccessLogs}" >Access Logs</h:commandLink>
				<h:outputText value="&#160;&#160;&#160;&#160;&#160;" />
				<h:outputLink value="Documents/WelcometoDataAnalysisTool.pdf" target="_blank">Help Documents</h:outputLink>
			</center>
			<div align="right">
				<h:commandButton action="#{actionBeanWorld.logout}" value="Logout"></h:commandButton>
			</div>
			<hr />
			</h:form>
</f:view>
</body>
</html>
</jsp:root>