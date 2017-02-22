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
<h:form enctype="multipart/form-data" id="dataImportForm">
<h2><center>s16g21 - Database Import Page</center></h2>
			<BR />
			<H4>
			<center><h:outputLink value="home.jsp" >Home</h:outputLink></center>
			</H4>
						<div align="right">
				<h:commandButton action="#{actionBeanWorld.logout}" value="Logout"></h:commandButton>
			</div>
			<hr />
<center><h:outputLabel value="Select file to upload:" /><h:outputLabel value=" "/>
<t:inputFileUpload id="fileUpload" label="File to upload" storage="default" value="#{actionBeanWorld.uploadedFile}" size="60"/></center>
<BR />
<center><h:outputLabel value="File Label:" /><h:outputLabel value=" "/>
<h:inputText id="fileLabel" value="#{actionBeanWorld.fileLabel}" size="60" /></center>
<BR />
<center><h:commandButton id="upload" action="#{actionBeanWorld.processFileUpload}" value="Submit"/></center>
<h:messages for="fileUpload" styleClass="errorMessage"
				style="color:red" />
			<h:messages for="fileLabel" styleClass="errorMessage"
				style="color:green" />

</h:form>
</f:view>
</body>
</html>
</jsp:root>