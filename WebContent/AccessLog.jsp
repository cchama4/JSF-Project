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
<h:form id="AccessLogForm">
			<h2>
				<center>s16g21 - Access Logs</center>
			</h2>
			<BR />
			<H4>
			<center><h:outputLink value="home.jsp" >Home</h:outputLink></center>
			</H4>
			<hr />
			<div align="right">
				<h:commandButton action="#{actionBeanWorld.logout}" value="Logout"></h:commandButton>
			</div>
			<hr />
			<div style="height: 200px; overflow: auto;">
				<t:dataTable value="#{actionBeanWorld.result}" var="row"
					rendered="#{actionBeanWorld.renderSet}" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx" width="900">
					<t:columns var="col" value="#{actionBeanWorld.columnNames}">
						<f:facet name="header">
							<t:commandLink styleClass="outputHeader" value="#{col}"
								action="#{actionBeanWorld.sort(col)}" />
						</f:facet>
						<t:outputText styleClass="outputText" value="#{row[col]}" />
					</t:columns>
				</t:dataTable>
			</div>
			</h:form>
</f:view>
</body>
</html>
</jsp:root>