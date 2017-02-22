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
				<center>s16g21 - Database Access Menu</center>
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
				<h:commandButton action="#{actionBeanWorld.displayTable}"
					value="Display Table"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.displayColumn}"
					value="Display Selected Columns"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.processQuery}"
					value="Process SQL Query"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.tableCopy}"
					value="Copy Tables"
					rendered="#{actionBeanWorld.schemaFlagComplement}"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.tableCreate}"
					value="Create Tables" rendered="#{actionBeanWorld.schemaFlag}"></h:commandButton>
				<h:outputText value=" "></h:outputText>
				<h:commandButton action="#{actionBeanWorld.tableDrop}"
					value="Drop Tables" rendered="#{actionBeanWorld.schemaFlag}"></h:commandButton>
				<br></br>
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
			<h:inputTextarea value="#{actionBeanWorld.query}" rows="10" cols="80"></h:inputTextarea>
			<br></br>
			<br></br>
			<div style="height: 200px; overflow: auto;">
				<h:dataTable id="dataTable" value="Query Data" var="country"
					rendered="#{actionBeanWorld.renderSet}" border="1" cellspacing="0"
					cellpadding="1" columnClasses="columnClass1 border"
					headerClass="headerClass" footerClass="footerClass"
					rowClasses="rowClass2" styleClass="dataTableEx"
					style="background-color:navyblue" width="800">

					<h:column>
						<f:facet name="header">
							<h:outputText>Number of Columns: </h:outputText>
						</f:facet>
						<div align="center">
							<h:outputText value="#{actionBeanWorld.numberColumns}" />
						</div>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText>Number of rows: </h:outputText>
						</f:facet>
						<div align="center">
							<h:outputText value="#{actionBeanWorld.numberRows}" />
						</div>
					</h:column>
				</h:dataTable>
				<br></br>
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