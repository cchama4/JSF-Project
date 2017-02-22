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
<title>login</title>
</head>
<body>
	<center>
		<h2>JSF Phase 4 - s16g21</h2>
	</center>
	<center>
		<h3>Chandan Chaman, Nikhilkumar Bachhav, Sanjeev Sukumaran</h3>
	</center>
	<hr />
	<f:view>
		<h:form>
			<h:panelGrid columns="3">
				<h:outputText value="Username*" />
				<h:inputText id="username" value="#{userBean.userName}"
					required="true" requiredMessage="Please enter your username"></h:inputText>
				<h:message for="username" style="color:red" showSummary="true"
					showDetail="false"></h:message>
				<h:outputText value="Password*" />
				<h:inputSecret id="password" value="#{userBean.password}"
					required="true" requiredMessage="Please enter your password"></h:inputSecret>
				<h:message for="password" style="color:red" showSummary="true"
					showDetail="false"></h:message>
				<h:outputText value="Database" />
				<h:selectOneListbox value="#{actionBeanWorld.dbType}" size="1">
					<f:selectItem itemValue="mysql" itemLabel="MySQL" />
					<f:selectItem itemValue="oracle" itemLabel="Oracle" />
					<f:selectItem itemValue="db2" itemLabel="DB2" />
				</h:selectOneListbox>
				<h:outputText value=" " />
				<h:outputText value="Server" />
				<h:selectOneMenu value="#{userBean.dbServer}">
					<f:selectItem itemValue="131.193.209.54:" itemLabel="Server54" />
					<f:selectItem itemValue="131.193.209.57:" itemLabel="Server57" />
					<f:selectItem itemValue="127.0.0.1:" itemLabel="localhost" />
				</h:selectOneMenu>
				<h:outputText value=" " />
				<h:outputText value="Schema*" />
				<h:inputText id="dbSchema" value="#{userBean.dbSchema}"
					required="true" requiredMessage="Please enter Database Schema"></h:inputText>
				<h:message for="dbSchema" style="color:red" showSummary="true"
					showDetail="false"></h:message>
				<h:commandButton action="#{actionBeanWorld.connect}" value="Login"></h:commandButton>
			</h:panelGrid>
			<h:outputText value='DB connection error: Invalid Details'
				style="color:red" rendered="#{actionBeanWorld.auth}" />
		</h:form>
	</f:view>
</body>
	</html>
</jsp:root>