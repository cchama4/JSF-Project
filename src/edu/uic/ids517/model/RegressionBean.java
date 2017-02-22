package edu.uic.ids517.model;

public class RegressionBean {
private double beta;
private double residual;
private String columns;
private double standardErrors;
private double intercept;
private double slope;
private double rSquare;
private double significance;
private double interceptStdErr;
private double slopeStdErr;
private double slopeConfidenceInterval;
private double meanSquaredError;

public double getSlopeConfidenceInterval() {
	return slopeConfidenceInterval;
}
public void setSlopeConfidenceInterval(double slopeConfidenceInterval) {
	this.slopeConfidenceInterval = slopeConfidenceInterval;
}
public double getMeanSquaredError() {
	return meanSquaredError;
}
public void setMeanSquaredError(double meanSquaredError) {
	this.meanSquaredError = meanSquaredError;
}
public double getIntercept() {
	return intercept;
}
public void setIntercept(double intercept) {
	this.intercept = intercept;
}
public double getSlope() {
	return slope;
}
public void setSlope(double slope) {
	this.slope = slope;
}
public double getrSquare() {
	return rSquare;
}
public void setrSquare(double rSquare) {
	this.rSquare = rSquare;
}
public double getSignificance() {
	return significance;
}
public void setSignificance(double significance) {
	this.significance = significance;
}
public double getInterceptStdErr() {
	return interceptStdErr;
}
public void setInterceptStdErr(double interceptStdErr) {
	this.interceptStdErr = interceptStdErr;
}
public double getSlopeStdErr() {
	return slopeStdErr;
}
public void setSlopeStdErr(double slopeStdErr) {
	this.slopeStdErr = slopeStdErr;
}
public double getBeta() {
	return beta;
}
public void setBeta(double beta) {
	this.beta = beta;
}
public double getResidual() {
	return residual;
}
public void setResidual(double residual) {
	this.residual = residual;
}
public String getColumns() {
	return columns;
}
public void setColumns(String columns) {
	this.columns = columns;
}
public double getStandardErrors() {
	return standardErrors;
}
public void setStandardErrors(double standardErrors) {
	this.standardErrors = standardErrors;
}
}
