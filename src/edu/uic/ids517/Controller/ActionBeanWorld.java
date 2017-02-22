package edu.uic.ids517.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.mysql.jdbc.ResultSetMetaData;
import javax.servlet.jsp.jstl.sql.Result;
import edu.uic.ids517.DAO.DbAccess;
import edu.uic.ids517.model.OutputBean;
import edu.uic.ids517.model.RegressionBean;
import edu.uic.ids517.model.UserBean;


@ManagedBean
@SessionScoped
public class ActionBeanWorld {
	private UserBean userBean;
	private ResultSetMetaData rsmd;
	private String dbType;
	private Result result;
	private ResultSet rs;
	private DbAccess dbAccess;
	private boolean renderSet;
	private boolean renderTable;
	private boolean renderColumn;
	private String column[];
	private OutputBean outputBean;
	private String table;
	private String query;
	private int numberColumns;
	private int numberRows;
	private boolean auth=false;
	private ArrayList<String> columnOutputs;
	private ArrayList<String> outputs;
	private ArrayList<String> columnNames;
	private boolean toggle=true;
	private boolean schemaFlag=false;
	private boolean schemaFlagComplement=true;
	private ArrayList<String> columnOutputsDA;
	private boolean renderColumnDA;
	private ArrayList<String> columnOutputsDAI;
	private boolean renderColumnDAI;
	private String dependent="";
	private String[] independents;
	private double[] dependentValues;
	private double[][] independentValues;
	private double[] independentValuesSingle;
	private boolean renderDiv;
	private RegressionBean regressionBean;
	private ArrayList<OutputBean> outputList = new ArrayList<OutputBean>();
	private ArrayList<RegressionBean> outputRegressionList = new ArrayList<RegressionBean>();
	private String equation="";
	private double regressand;
	private double rSquared;
	private double sigma;
	private UploadedFile uploadedFile;
	private String uploadedFileContents;
	private String fileName;
	private String fileLabel;
	private long fileSize;
	private String fileContentType;
	private boolean renderDivSingle=false;
	private String chartType;
	private boolean renderChart;
	private File chartFile;
	private String categoricalChart="";
	private boolean rendercateogaricalRender;
	private ArrayList<String> chartColumns;
	private String numericalChart;
	private boolean rendernumericalRender;
	private ArrayList<String> chartColumnsNumerical;
	private String chartFileName;
	private JFreeChart chart;
	
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	public String getChartFileName() {
		return chartFileName;
	}
	public void setChartFileName(String chartFileName) {
		this.chartFileName = chartFileName;
	}
	public ArrayList<String> getChartColumnsNumerical() {
		return chartColumnsNumerical;
	}
	public void setChartColumnsNumerical(ArrayList<String> chartColumnsNumerical) {
		this.chartColumnsNumerical = chartColumnsNumerical;
	}
	public boolean isRendernumericalRender() {
		return rendernumericalRender;
	}
	public void setRendernumericalRender(boolean rendernumericalRender) {
		this.rendernumericalRender = rendernumericalRender;
	}
	public String getNumericalChart() {
		return numericalChart;
	}
	public void setNumericalChart(String numericalChart) {
		this.numericalChart = numericalChart;
	}
	public ArrayList<String> getChartColumns() {
		return chartColumns;
	}
	public void setChartColumns(ArrayList<String> chartColumns) {
		this.chartColumns = chartColumns;
	}
	public String getCategoricalChart() {
		return categoricalChart;
	}
	public void setCategoricalChart(String categoricalChart) {
		this.categoricalChart = categoricalChart;
	}
	public boolean isRendercateogaricalRender() {
		return rendercateogaricalRender;
	}
	public void setRendercateogaricalRender(boolean rendercateogaricalRender) {
		this.rendercateogaricalRender = rendercateogaricalRender;
	}
	public boolean isRenderChart() {
		return renderChart;
	}
	public void setRenderChart(boolean renderChart) {
		this.renderChart = renderChart;
	}
	public File getChartFile() {
		return chartFile;
	}
	public void setChartFile(File chartFile) {
		this.chartFile = chartFile;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public boolean isRenderDivSingle() {
		return renderDivSingle;
	}
	public double[] getIndependentValuesSingle() {
		return independentValuesSingle;
	}
	public void setIndependentValuesSingle(double[] independentValuesSingle) {
		this.independentValuesSingle = independentValuesSingle;
	}
	public String getFileLabel() {
		return fileLabel;
	}
	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public String getUploadedFileContents() {
		return uploadedFileContents;
	}
	public void setUploadedFileContents(String uploadedFileContents) {
		this.uploadedFileContents = uploadedFileContents;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public double getSigma() {
		return sigma;
	}
	public double getrSquared() {
		return rSquared;
	}
	public double getRegressand() {
		return regressand;
	}
	public String getEquation() {
		return equation;
	}
	public ArrayList<RegressionBean> getOutputRegressionList() {
		return outputRegressionList;
	}
	public RegressionBean getRegressionBean() {
		return regressionBean;
	}
	public void setRegressionBean(RegressionBean regressionBean) {
		this.regressionBean = regressionBean;
	}
	public boolean isRenderDiv() {
		return renderDiv;
	}
	public void setRenderDiv(boolean renderDiv) {
		this.renderDiv = renderDiv;
	}
	public double[] getDependentValues() {
		return dependentValues;
	}
	public void setDependentValues(double[] dependentValues) {
		this.dependentValues = dependentValues;
	}
	public double[][] getIndependentValues() {
		return independentValues;
	}
	public void setIndependentValues(double[][] independentValues) {
		this.independentValues = independentValues;
	}

	
	public String[] getIndependents() {
		return independents;
	}
	public void setIndependents(String[] independents) {
		this.independents = independents;
	}
	public ArrayList<String> getColumnOutputsDAI() {
		return columnOutputsDAI;
	}
	public void setColumnOutputsDAI(ArrayList<String> columnOutputsDAI) {
		this.columnOutputsDAI = columnOutputsDAI;
	}
	public boolean isRenderColumnDAI() {
		return renderColumnDAI;
	}
	public void setRenderColumnDAI(boolean renderColumnDAI) {
		this.renderColumnDAI = renderColumnDAI;
	}
	public String getDependent() {
		return dependent;
	}
	public void setDependent(String dependent) {
		this.dependent = dependent;
	}
	public boolean isRenderColumnDA() {
		return renderColumnDA;
	}
	public void setRenderColumnDA(boolean renderColumnDA) {
		this.renderColumnDA = renderColumnDA;
	}
	public ArrayList<String> getColumnOutputsDA() {
		return columnOutputsDA;
	}
	public void setColumnOutputsDA(ArrayList<String> columnOutputsDA) {
		this.columnOutputsDA = columnOutputsDA;
	}
	public ArrayList<OutputBean> getOutputList() {
		return outputList;
	}
	public OutputBean getOutputBean() {
		return outputBean;
	}
	public void setOutputBean(OutputBean outputBean) {
		this.outputBean = outputBean;
	}
	public boolean isSchemaFlagComplement() {
		return schemaFlagComplement;
	}
	public boolean isSchemaFlag() {
		return schemaFlag;
	}
	public boolean isAuth() {
		return auth;
	}
	public int getNumberColumns() {
		return numberColumns;
	}
	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}
	public int getNumberRows() {
		return numberRows;
	}
	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isRenderTable() {
		return renderTable;
	}
	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}
	public boolean isRenderColumn() {
		return renderColumn;
	}
	public void setRenderColumn(boolean renderColumn) {
		this.renderColumn = renderColumn;
	}
	public String[] getColumn() {
		return column;
	}
	public void setColumn(String[] column) {
		this.column = column;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public ArrayList<String> getColumnOutputs() {
		return columnOutputs;
	}
	public void setColumnOutputs(ArrayList<String> columnOutputs) {
		this.columnOutputs = columnOutputs;
	}
	public ArrayList<String> getOutputs() {
		return outputs;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbType() {
		return dbType;
	}
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public Result getResult() {
		return result;
	}
	public DbAccess getDbAccess() {
		return dbAccess;
	}
	public boolean isRenderSet() {
		return renderSet;
	}
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		userBean = (UserBean) m.get("userBean");
		dbAccess= (DbAccess)m.get("dbAccess");
	}
	public ActionBeanWorld() {
		// TODO Auto-generated constructor stub
	}
	public String connect()
	{
		dbAccess = new DbAccess(dbType,userBean.getUserName(),userBean.getPassword(),userBean.getDbSchema(),userBean.getDbServer());
		this.setAccessLogs();
		if(dbAccess.isStatus()&&dbAccess!=null){
			return "SUCCESS";
		}
		else {
			this.auth=true;
			return "FAIL";
		}
	}
	public void displayOutput(ResultSet rs){
		if(rs!=null)
		{
			try {
				rsmd=(ResultSetMetaData) rs.getMetaData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				renderSet = false;
			}
			result = ResultSupport.toResult(rs);
			numberColumns = 0;
			try {
				numberColumns = rsmd.getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				renderSet = false;
			}
			numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(int i=0; i<numberColumns; i++) 
			{
				columnNames.add(columnNameList[i]);
				renderSet = true;
			}
		}

	}
	public void showTables(ResultSet rs){

		if(rs!=null)
		{
			try {
				outputs= new ArrayList();
				while(rs.next()){
					outputs.add(rs.getString(1));
					renderTable=true;
					renderColumn = false;
					renderSet=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				renderTable = false;
			}

		}

	}
	public void showColumns(ResultSet rs){

		if(rs!=null)
		{
			try {
				columnOutputs= new ArrayList();
				while(rs.next()){
					String s=rs.getString(2);
					s=s.substring(0, s.indexOf('('));
					columnOutputs.add(rs.getString(1)+"("+s+")");
					renderColumn=true;
					renderSet=false;
					renderColumnDAI=false;
					renderColumnDA=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				renderColumn = false;
			}

		}

	}
	public void tableQuery(){
		renderColumnDAI=false;
		renderColumnDA=false;
		renderDiv=false;
		renderDivSingle=false;
		if(userBean.getDbSchema().equals(userBean.getUserName())){
			this.schemaFlag=true;
			this.schemaFlagComplement=false;
		}
		if(userBean.getDbSchema().equals("world")){
			this.schemaFlag=false;
			this.schemaFlagComplement=true;
		}
		query="show tables from "+userBean.getDbSchema()+";";
		rs=dbAccess.processSelect(query);
		this.showTables(rs);
	}
	public void columnQuery(){
		renderDiv=false;
		renderDivSingle=false;
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else if(this.getTable().isEmpty()){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else{
			query="desc "+userBean.getDbSchema()+"."+this.getTable()+";";
			rs=dbAccess.processSelect(query);
			this.showColumns(rs);
		}

	}
	public void displayTable(){
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
			else{
				query="SELECT * from "+userBean.getDbSchema()+"."+this.getTable()+";";
				rs=dbAccess.processSelect(query);
				this.displayOutput(rs);
			}
	}
	public void displayColumn(){
		if(this.column==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else if(this.column.length==0){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Columns selected"));
		}
		else{
			int i=0; String s="";String temp="";
			while(i<this.column.length){
				temp=column[i].substring(0, column[i].indexOf('('));
				s+=temp;
				if(i!=this.column.length-1)
					s+=", ";
				i++;
			}
			query="SELECT "+s+ " from "+userBean.getDbSchema()+"."+this.getTable()+";";
			rs=dbAccess.processSelect(query);
			this.displayOutput(rs);
		}
	}
	public double[] getColumn(double[][] array, int index){
	    double[] column = new double[array.length]; 
	    for(int i=0; i<column.length; i++){
	       column[i] = array[i][index];
	    }
	    return column;
	}
	public void displayStatistics(){
		//renderSet = true;
		renderDiv=false;
		renderDivSingle=false;
		outputList = new ArrayList<OutputBean>();
		if(this.column==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Columns selected"));
		}
		else if(this.column.length==0){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Columns selected"));
		}
		else{
			int i=0; String s="";String temp="";
			while(i<this.column.length){
				temp=column[i].substring(0, column[i].indexOf('('));
				s+=temp;
				if(i!=this.column.length-1)
					s+=", ";
				i++;
			}
			query="SELECT "+s+ " from "+userBean.getDbSchema()+"."+this.getTable()+";";
			rs=dbAccess.processSelect(query);
			if(rs!=null){
			try {
				rsmd=(ResultSetMetaData) rs.getMetaData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result = ResultSupport.toResult(rs);
			numberColumns = 0;
			try {
				numberColumns = rsmd.getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(i=0; i<numberColumns; i++) 
			{
				columnNames.add(columnNameList[i]);
				
			}
			rs=dbAccess.processSelect(query);
			int n=0,k=0;
			double[][] temp1=new double[numberRows][numberColumns];
			try {
				while(rs.next()){
					for(k=0; k<numberColumns;k++){
						temp1[n][k]=rs.getInt(columnNames.get(k));
					}
					n++;
				}
				for(k=0; k<numberColumns;k++){
				outputBean=new OutputBean();
				double columnArray[]=getColumn(temp1,k);
				outputBean.setTable(columnNames.get(k));
				outputBean.setMean(StatUtils.mean(columnArray));
				outputBean.setMedian(StatUtils.percentile(columnArray,50));
				outputBean.setMinimum(StatUtils.min(columnArray));
				outputBean.setMaximum(StatUtils.max(columnArray));
				outputBean.setVariance(StatUtils.variance(columnArray, StatUtils.mean(columnArray)));
				outputBean.setStddev(Math.sqrt(outputBean.getVariance()));
				outputBean.setQuartile1(StatUtils.percentile(columnArray,25));
				outputBean.setQuartile3(StatUtils.percentile(columnArray,75));
				outputBean.setIqr(outputBean.getQuartile3()-outputBean.getQuartile1());
				outputBean.setRange(outputBean.getMaximum()-outputBean.getMinimum());
				outputList.add(outputBean);
				}
				renderSet = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				renderSet = false;
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("Please select only Numerical variables."));
			}
			}else{
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("Please click Column List"));
			}
		}
			
		}
	public void processQuery(){
		rs=dbAccess.processSelect(this.getQuery());
		if(rs!=null)
			this.displayOutput(rs);
		else{
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("Please enter a valid query."));
		}
	}
	public void sort(String s){
		String sortQuery="";
		if(toggle==true){
			sortQuery=query.substring(0,query.indexOf(';'))+ " order by "+s+" asc;";
			toggle=false;
		}else{
			sortQuery=query.substring(0,query.indexOf(';'))+ " order by "+s+" desc;";
			toggle=true;
		}
		rs=dbAccess.processSelect(sortQuery);
		this.displayOutput(rs);
	}
	public void tableCopy(){
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
			else{
				query="CREATE TABLE "+userBean.getUserName()+"."+this.getTable()+" AS SELECT * FROM world."+this.getTable()+";";
				int rows=dbAccess.processUpdate(query);
				if(rows!=0){
					FacesContext.getCurrentInstance().addMessage("databaseForm:tableList",
							new FacesMessage("Table copied successfully."));
				}
				else{
					FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
							new FacesMessage("Table already exists"));
				}
			}
	}
	public void tableCreate(){
		String words[]=this.getQuery().split(" ");
		String temp="";
		for(int i=0; i<words.length;i++){
			if(i!=2){
				temp=temp+words[i]+" ";
			}
			else{				
				if(words[i].indexOf(".")==-1){
					temp=temp+userBean.getUserName()+"."+words[i]+" ";
				}
				else temp=temp+words[i]+" ";
			}
		}
		boolean flag=dbAccess.processExecute(temp);
		if(flag==true)
			FacesContext.getCurrentInstance().addMessage("databaseForm:tableList",
					new FacesMessage("Table created successfully."));
		else{
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("Please enter a valid Create query."));
		}
	}
	public void tableDrop(){
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
			else{
				query="DROP TABLE "+userBean.getUserName()+"."+this.getTable()+";";
				boolean flag=dbAccess.processExecute(query);
				if(flag==true){
					FacesContext.getCurrentInstance().addMessage("databaseForm:tableList",
							new FacesMessage("Table dropped successfully."));
				}
				else{
					FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
							new FacesMessage("Table already dropped."));
				}
			}
	}
	public void selectDependentVariable(){
		this.renderColumn=false;
		renderColumnDAI=false;
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
			else{
				columnOutputsDA=new ArrayList<String>();
				this.columnQuery();
				this.renderColumn=false;
				for(int i=0; i<this.columnOutputs.size(); i++){
					String type=columnOutputs.get(i).substring(columnOutputs.get(i).indexOf('(')+1, columnOutputs.get(i).indexOf(')'));
					if(type.equals("char")||type.equals("enum")||type.equals("varchar")){
						renderColumnDA=true;
					}
					else{
						columnOutputsDA.add(columnOutputs.get(i).substring(0,columnOutputs.get(i).indexOf('(')));
						renderColumnDA=true;
						renderColumn=false;
					}
				}
			}
	}
	public void selectIndependentVariable(){
		this.renderColumn=false;
		renderColumnDA=true;
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
		else 
			if(this.getColumnOutputsDA()==null){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("Please Click dependent variable"));
			}
			else 
				if(this.getColumnOutputsDA().isEmpty()){
					FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
							new FacesMessage("Please Click dependent variable"));
				}
				else 
					if(this.getDependent().equals(null)||this.getDependent().equals("")){
						FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
								new FacesMessage("Please select One dependent variable"));
					}
			else{
				columnOutputsDAI=new ArrayList<String>();
				for(int i=0; i<this.columnOutputsDA.size(); i++){
					if(columnOutputsDA.get(i).equals(dependent)){
						renderColumnDAI=true;
					}
					else{
						columnOutputsDAI.add(columnOutputsDA.get(i));
						renderColumnDAI=true;
						renderColumn=false;
					}
				}
			}
	}
	public void calculateRegression(){
		this.renderColumn=false;
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
		else 
			if(this.getColumnOutputsDA()==null){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("Please Click dependent variable"));
			}
			else 
				if(this.getColumnOutputsDA().isEmpty()){
					FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
							new FacesMessage("Please Click dependent variable"));
				}
				else 
					if(this.getDependent().equals(null)||this.getDependent().equals("")){
						FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
								new FacesMessage("Please select One dependent variable"));
					}
					else 
						if(this.getIndependents()==null||this.getIndependents().length==0){
							FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
									new FacesMessage("Please select atleast One Independent variable"));
						}
						else{
							int i=0;
							equation="";
							OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
							String queryDependent="SELECT "+this.getDependent()+ " from "+userBean.getDbSchema()+"."+this.getTable()+";";
							rs=dbAccess.processSelect(queryDependent);
							result = ResultSupport.toResult(rs);
							numberRows = result.getRowCount();
							numberColumns=this.independents.length;
							dependentValues=new double[numberRows];
							rs=dbAccess.processSelect(queryDependent);
							try {
								while(rs.next()){
									dependentValues[i]=rs.getDouble(this.getDependent());
									i++;
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String str="";
							for(i=0;i<numberColumns;i++){
								if(i!=numberColumns-1)
								str+=this.getIndependents()[i]+", ";
								else
									str+=this.getIndependents()[i];
							}                    
							String queryIndependent="SELECT "+str+" from "+userBean.getDbSchema()+"."+this.getTable()+";";
							rs=dbAccess.processSelect(queryIndependent);
							outputRegressionList = new ArrayList<RegressionBean>();
							if(this.getIndependents().length==1){
								independentValuesSingle=new double[numberRows];
								PearsonsCorrelation c = new PearsonsCorrelation();
								SimpleRegression sr = new SimpleRegression();
								sr.clear();
								try {
									i=0;
									while(rs.next()){
										independentValuesSingle[i]=rs.getDouble(this.getIndependents()[0]);
										i++;
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								for(int k=0; k<independentValuesSingle.length; k++) {
									sr.addData(independentValuesSingle[k], dependentValues[k]);
									}
								regressionBean = new RegressionBean();
								regressionBean.setIntercept(sr.getIntercept());
								regressionBean.setSlope(sr.getSlope());
								regressionBean.setrSquare(sr.getRSquare());
								regressionBean.setSignificance(sr.getSignificance());
								regressionBean.setInterceptStdErr(sr.getInterceptStdErr());
								regressionBean.setSlopeStdErr(sr.getSlopeStdErr());
								regressionBean.setColumns(this.getIndependents()[0]);
								regressionBean.setMeanSquaredError(sr.getMeanSquareError());
								regressionBean.setSlopeConfidenceInterval(sr.getSlopeConfidenceInterval());
								outputRegressionList.add(regressionBean);
								equation+=sr.getIntercept()+" + "+sr.getSlope()+"*"+this.getIndependents()[0];
								renderDivSingle=true;
								renderDiv=false;
							}
							else{
							independentValues=new double[numberRows][numberColumns];
							try {
								while(rs.next()){
									int j=0;
									for(i=0;i<numberColumns;i++){
										independentValues[i][j]=rs.getDouble(this.getIndependents()[i]);
										j++;	
									}
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							regression.newSampleData(dependentValues, independentValues);
							this.regressand=regression.estimateRegressandVariance();
							this.rSquared=regression.calculateRSquared();
							this.sigma=regression.estimateRegressionStandardError();
							double beta[]=regression.estimateRegressionParameters();
							double residuals[]=regression.estimateResiduals();
							String columns[]=this.getIndependents();
							double standardErrors[]=regression.estimateRegressionParametersStandardErrors();
							for(i=0;i<numberColumns;i++){
							regressionBean = new RegressionBean();
							regressionBean.setBeta(beta[i]);
							regressionBean.setColumns(columns[i]);
							regressionBean.setResidual(residuals[i]);
							regressionBean.setStandardErrors(standardErrors[i]);
							outputRegressionList.add(regressionBean);
							equation+=beta[i]+"*"+columns[i];
							}
							renderDivSingle=false;
							renderDiv=true;
						}
						}
				}
	public void processFileUpload() {
		if(this.getUploadedFile()==null){
			FacesContext.getCurrentInstance().addMessage("dataImportForm:fileUpload",
					new FacesMessage("No File selected"));
		}
		else 
			if(this.getFileLabel().equals(null)||this.getFileLabel().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("dataImportForm:fileUpload",
						new FacesMessage("Please enter a File Label"));
			}
		else{			
		uploadedFileContents = null;
		FacesContext context = FacesContext.getCurrentInstance();
		String path = context.getExternalContext().getRealPath("/temp");
		File tempFile = null;
		FileOutputStream fos = null;
		try {
		fileName = uploadedFile.getName();
		fileSize = uploadedFile.getSize();
		fileContentType = uploadedFile.getContentType();
		uploadedFileContents = new String(uploadedFile.getBytes());
		tempFile = new File(path + "/" + fileName);
		fos = new FileOutputStream(tempFile);
		fos.write(uploadedFile.getBytes());
		fos.close();
		// process file contents
		} // end try
		catch (IOException e) {
		
		} // end catch
		if(uploadedFileContents != null){
			try {
				if(fileContentType.equals("text/csv")){
				BufferedReader br = new BufferedReader (new FileReader(tempFile));
				String header;
				header = br.readLine();
				String[] headers=header.split(",");
				int i=0; String query="",pk="";
				while(i<headers.length){
					if(i!=headers.length-1){
					query+=headers[i]+ " VARCHAR(50), ";
					pk+=headers[i]+ ", ";
					}
					else{
						query+=headers[i]+ " VARCHAR(50), ";
						pk+=headers[i];
					}
					i++;
				}				
				query="CREATE TABLE "+ userBean.getUserName()+"."+ this.getFileLabel()+ " ( " + query + "PRIMARY KEY("+pk+" ));";
				dbAccess.processExecute(query);
				String value="";
				while((value=br.readLine())!=null){
					String[] values=value.split(",");
					i=0;query="";String queryHeader="";
					while(i<headers.length){
						if(i!=headers.length-1){
						queryHeader+=headers[i]+", ";
						query+="'"+values[i]+ "', ";
						}
						else{
							queryHeader+=headers[i];
							query+="'"+values[i]+"'";
						}
						i++;
					}
					query="INSERT INTO "+ userBean.getUserName()+"."+ this.getFileLabel()+" ("+queryHeader + ")"+ " VALUES (" + query + " );";
					dbAccess.processUpdate(query);
				}
				FacesContext.getCurrentInstance().addMessage("dataImportForm:fileLabel",
						new FacesMessage("File Imported Successfully"));
			}
				else if(fileContentType.equals("text/xml")){
					File fXmlFile = tempFile;
			        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			        Document doc = dBuilder.parse(fXmlFile); 
			        doc.getDocumentElement().normalize(); 
			        NodeList nList = doc.getDocumentElement().getChildNodes();
			        NodeList eList = nList.item(0).getChildNodes();
			        int columnNumber=eList.getLength();
			        String query="",pk="";
			        for(int k=0; k<columnNumber; k++){
			        	if(k!=columnNumber-1){
			        	query+=eList.item(k).getNodeName()+" VARCHAR(50), ";
			        	pk+=eList.item(k).getNodeName()+ ", ";
			        	}
			        	else{
			        		query+=eList.item(k).getNodeName()+ " VARCHAR(50), ";
							pk+=eList.item(k).getNodeName();
			        	}
			        }
			        query="CREATE TABLE "+ userBean.getUserName()+"."+ this.getFileLabel()+ " ( " + query + "PRIMARY KEY("+pk+" ));";
					dbAccess.processExecute(query);
			        for (int temp = 0; temp < nList.getLength(); temp++) {
			        	eList = nList.item(temp).getChildNodes();
			        	query="";String queryHeader="";
				        for(int k=0; k<columnNumber; k++){
				        	if(k!=columnNumber-1){
									queryHeader+=eList.item(k).getNodeName()+", ";
									query+="'"+eList.item(k).getTextContent()+ "', ";
									}
									else{
										queryHeader+=eList.item(k).getNodeName();
										query+="'"+eList.item(k).getTextContent()+"'";
									}
				        }
				        query="INSERT INTO "+ userBean.getUserName()+"."+ this.getFileLabel()+" ("+queryHeader + ")"+ " VALUES (" + query + " );";
						dbAccess.processUpdate(query);
			        }
			        FacesContext.getCurrentInstance().addMessage("dataImportForm:fileLabel",
							new FacesMessage("File Imported Successfully"));
				}
				else{
					FacesContext.getCurrentInstance().addMessage("dataImportForm:fileUpload",
							new FacesMessage("Please slelect csv/xml files"));
				}
			}catch (FileNotFoundException e) {
				FacesContext.getCurrentInstance().addMessage("dataImportForm:fileUpload",
						new FacesMessage(e.getMessage()));
				e.printStackTrace();
			} catch (IOException e) {
				FacesContext.getCurrentInstance().addMessage("dataImportForm:fileUpload",
						new FacesMessage(e.getMessage()));
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		} 
	}
	public void csvExport() {

		try {
			if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
		else{
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();
				FileOutputStream fos = null;
				String path = fc.getExternalContext().getRealPath("/temp");
				String fileName = path + "/" + userBean.getUserName() + "_" + this.getTable()+ ".csv";
				File f = new File(fileName);
				if (!f.exists()) {
					f.getParentFile().mkdirs();
					// f.createNewFile();

				}
				query = "select * from " + userBean.getDbSchema() + "." + this.getTable() + ";";
				rs = dbAccess.processSelect(query);
				try {
					rsmd = (ResultSetMetaData) rs.getMetaData();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// renderSet = false;
				}
				result = ResultSupport.toResult(rs);
				numberColumns = 0;
				try {
					numberColumns = rsmd.getColumnCount();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// renderSet = false;
				}

				Object[][] sData = result.getRowsByIndex();
				StringBuffer sb = new StringBuffer();

				try {
					fos = new FileOutputStream(fileName);

					for (int i = 1; i <= numberColumns; i++) {
						sb.append(rsmd.getColumnName(i) + ",");
					}
					sb.append("\n");
					fos.write(sb.toString().getBytes());
					String val;
					for (int i = 0; i < sData.length; i++) {
						sb = new StringBuffer();
						for (int j = 0; j < sData[0].length; j++) {
							if (sData[i][j] == null || sData[i][j].toString().isEmpty() == true
									|| sData.toString().isEmpty() == true || sData[i][j].toString() == "") {
								val = "null" + ",";
							} else {

								val = sData[i][j].toString() + ",";
								val = StringEscapeUtils.escapeHtml(val);
							}
							sb.append(val);
						}
						sb.append("\n");
						fos.write(sb.toString().getBytes());
					}
					fos.flush();
					fos.close();
					String mmt = ec.getMimeType(fileName);
					FileInputStream in = null;
					byte bt;
					ec.responseReset();
					ec.setResponseContentType(mmt);
					ec.setResponseContentLength((int) f.length());
					ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					// try {
					in = new FileInputStream(f);
					OutputStream output = ec.getResponseOutputStream();
					while (true) {
						bt = (byte) in.read();
						if (bt < 0)
							break;
						output.write(bt);
					}
					in.close();
					fc.responseComplete();

				} catch (Exception e) {
					e.printStackTrace();

				}

			} 

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("databaseAccessForm:listError",
					new FacesMessage("Export Failed"));

		}
	}
	public void xmlExport() {

		try {
			if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseForm:columnList",
						new FacesMessage("No Tables selected"));
			}
		else{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.newDocument();
				Element results = doc.createElement("Results");
				doc.appendChild(results);

				String query = "select * from " + userBean.getDbSchema() + "." + this.getTable() + ";";
				rs = dbAccess.processSelect(query);

				try {
					rsmd = (ResultSetMetaData) rs.getMetaData();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// renderSet = false;
				}
				result = ResultSupport.toResult(rs);
				numberColumns = 0;
				try {
					numberColumns = rsmd.getColumnCount();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// renderSet = false;
				}
				rs = dbAccess.processSelect(query);
				int id=1;
				while (rs.next()) {
					Element row = doc.createElement("Row");
					row.setAttribute("id",Integer.toString(id));
					results.appendChild(row);
					for (int i = 1; i <= numberColumns; i++) {
						String columnName = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						String valueToPass;
						if (value != null) {
							valueToPass = value.toString();
							valueToPass = StringEscapeUtils.escapeHtml(valueToPass);
						} else {
							valueToPass = "null";
						}
						Element node = doc.createElement(columnName);
						node.appendChild(doc.createTextNode(valueToPass));
						row.appendChild(node);
					}
					id++;
				}
				DOMSource domSource = new DOMSource(doc);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				StringWriter sw = new StringWriter();
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();
				FileOutputStream fos = null;
				String path = fc.getExternalContext().getRealPath("/temp");
				String fileName = path + "/" + userBean.getUserName() + "_" + this.getTable().toString()+ ".xml";

				File file = new File(fileName);
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
				StreamResult sr = new StreamResult(new File(fileName));
				transformer.transform(domSource, sr);
				String mmt = ec.getMimeType(fileName);
				FileInputStream in = null;
				byte bt;
				ec.responseReset();
				ec.setResponseContentType(mmt);
				ec.setResponseContentLength((int) file.length());
				ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				// try {
				in = new FileInputStream(file);
				OutputStream output = ec.getResponseOutputStream();
				while (true) {
					bt = (byte) in.read();
					if (bt < 0)
						break;
					output.write(bt);
				}
				in.close();
				fc.responseComplete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void setAccessLogs(){
		String clientIpAddress="";
		try {
			clientIpAddress = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()).toString();
		query="CREATE TABLE IF NOT EXISTS " + userBean.getUserName()+ ".AccessLog (user varchar(20), dbschema varchar(20), ipAddress varchar(30), loginTime varchar(50), logOutTime varchar(50));";
		dbAccess.processExecute(query);
		query="INSERT INTO "+ userBean.getUserName()+ ".AccessLog (user, dbschema, ipAddress, loginTime) VALUES('" +userBean.getUserName()+"', '"+userBean.getDbSchema()+"', '"+clientIpAddress+"', '"+timeStamp+"');";
		dbAccess.processUpdate(query);
}
	public String showAccessLogs(){
		query="SELECT * from "+userBean.getUserName()+".AccessLog;";
		rs=dbAccess.processSelect(query);
		this.displayOutput(rs);
		return "AccessLog";
	}
	public String logout() {
		String outTimeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()).toString();
		String query="UPDATE " +userBean.getUserName()+".AccessLog SET logOutTime= '"+outTimeStamp+"' ORDER BY loginTime DESC LIMIT 1;";
		dbAccess.processUpdate(query);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Logout";
	}
	public void populateChart(){
		if(this.getTable()==null||this.getTable().isEmpty()){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
					new FacesMessage("No Tables selected"));
		}else
		if(this.getCategoricalChart().equals("")||this.getCategoricalChart()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
					new FacesMessage("No Columns selected"));
		}
		else{
		if(chartType.equals("pichart")){
			query="Select "+categoricalChart+", COUNT("+categoricalChart+ ") as Total from "+userBean.getDbSchema()+"."+this.getTable()+" GROUP BY "+categoricalChart+";";
			DefaultPieDataset dataset = new DefaultPieDataset();
			rs=dbAccess.processSelect(query);
			try {
				while(rs.next()){
					if(rs.getString(1)!=null){
					dataset.setValue(rs.getString(1),rs.getInt("Total"));
				}
				}
				JFreeChart chart = ChartFactory.createPieChart("Pie Chart", dataset, true, true, false);
				FacesContext context = FacesContext.getCurrentInstance();
				String path = context.getExternalContext().getRealPath("/temp");
				chartFileName = path + "/Chart.png";
				chartFile = new File(chartFileName);
				chartFile.delete();
				ChartUtilities.saveChartAsPNG(chartFile, chart, 600, 450);
				renderChart=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(this.getNumericalChart()==null||this.getNumericalChart().equals("")){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
						new FacesMessage("No Columns selected"));
			}else{
			final XYSeries object1 = new XYSeries("Series 1");
            final XYSeries object2 = new XYSeries("Series 2");
            query="SELECT "+categoricalChart+ " from "+userBean.getDbSchema()+"."+this.getTable()+";";
            rs=dbAccess.processSelect(query);
            try {int i=0;
				while(rs.next()){
					object1.add(i,rs.getDouble(1));
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            query="SELECT "+numericalChart+ " from "+userBean.getDbSchema()+"."+this.getTable()+";";
            rs=dbAccess.processSelect(query);
            try {int i=0;
				while(rs.next()){
					object2.add(i,rs.getDouble(1));
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            final XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(object1);
            dataset.addSeries(object2);
            if(chartType.equals("scatterPlot")){
            chart = ChartFactory.createScatterPlot("Scatter Plot",	categoricalChart, numericalChart.toString(), dataset, PlotOrientation.VERTICAL, true, true, false );
            }else if(chartType.equals("histogram")){
            	chart = ChartFactory.createHistogram("Histogram",	categoricalChart, numericalChart.toString(), dataset, PlotOrientation.VERTICAL, true, true, false );
            }else if(chartType.equals("lineChart")){
            	chart = ChartFactory.createXYLineChart("Line Chart",	categoricalChart, numericalChart.toString(), dataset, PlotOrientation.VERTICAL, true, true, false );
            }
            FacesContext context = FacesContext.getCurrentInstance();
			String path = context.getExternalContext().getRealPath("/temp");
			chartFileName = path + "/Chart.png";
			chartFile = new File(chartFileName);
			chartFile.delete();
			try {
				ChartUtilities.saveChartAsPNG(chartFile, chart, 600, 450);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			renderChart=true;
		}
	}
		}
}
	public void valueChangedColumn1() {
		rendernumericalRender=false;
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
						new FacesMessage("No Tables selected"));
			}
			else{
				chartColumns=new ArrayList<String>();
				this.columnQuery();
				if(chartType.equals("pichart")){
					for(int i=0; i<this.columnOutputs.size(); i++){
						String type=columnOutputs.get(i).substring(columnOutputs.get(i).indexOf('(')+1, columnOutputs.get(i).indexOf(')'));
						if(type.equals("char")||type.equals("enum")||type.equals("varchar")){
							chartColumns.add(columnOutputs.get(i).substring(0,columnOutputs.get(i).indexOf('(')));
						}
						}
					}
				else{
				for(int i=0; i<this.columnOutputs.size(); i++){
					String type=columnOutputs.get(i).substring(columnOutputs.get(i).indexOf('(')+1, columnOutputs.get(i).indexOf(')'));
					if(type.equals("char")||type.equals("enum")||type.equals("varchar")){
					}
					else{
						chartColumns.add(columnOutputs.get(i).substring(0,columnOutputs.get(i).indexOf('(')));
						
					}
				}
			}
				rendercateogaricalRender=true;
	}
	}
	public void valueChangedColumn2(){
		if(this.getTable()==null){
			query="";
			FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
					new FacesMessage("No Tables selected"));
		}
		else 
			if(this.getTable().isEmpty()){
				query="";
				FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
						new FacesMessage("No Tables selected"));
			}
			else
			if(this.getCategoricalChart().equals("")){
				FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
						new FacesMessage("Please select one Column"));
			}
			else
				if(chartType.equals("pichart")||chartType.equals("barGraph")){
					FacesContext.getCurrentInstance().addMessage("databaseChart:columnList",
							new FacesMessage("Please click Populate Chart"));
				}
				else{
					chartColumnsNumerical=new ArrayList<String>();
					for(int i=0; i<this.chartColumns.size(); i++){
						if(chartColumns.get(i).equals(categoricalChart)){
							rendernumericalRender=true;
						}
						else{
							chartColumnsNumerical.add(chartColumns.get(i));
							rendernumericalRender=true;
						}
					}
				}
	}
}

	

