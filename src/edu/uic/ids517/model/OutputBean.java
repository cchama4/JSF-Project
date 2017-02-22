package edu.uic.ids517.model;

public class OutputBean {
private String table;
private double mean;
private double median;
private double minimum;
private double maximum;
private double stddev;
private double variance;
private double quartile1;
private double quartile3;
private double iqr;
private double range;
public String getTable() {
	return table;
}
public void setTable(String table) {
	this.table = table;
}
public double getMean() {
	return mean;
}
public void setMean(double mean) {
	this.mean = mean;
}
public double getMedian() {
	return median;
}
public void setMedian(double median) {
	this.median = median;
}
public double getMinimum() {
	return minimum;
}
public void setMinimum(double minimum) {
	this.minimum = minimum;
}
public double getMaximum() {
	return maximum;
}
public void setMaximum(double maximum) {
	this.maximum = maximum;
}
public double getStddev() {
	return stddev;
}
public void setStddev(double stddev) {
	this.stddev = stddev;
}
public double getVariance() {
	return variance;
}
public void setVariance(double variance) {
	this.variance = variance;
}
public double getQuartile1() {
	return quartile1;
}
public void setQuartile1(double quartile1) {
	this.quartile1 = quartile1;
}
public double getQuartile3() {
	return quartile3;
}
public void setQuartile3(double quartile3) {
	this.quartile3 = quartile3;
}
public double getIqr() {
	return iqr;
}
public void setIqr(double iqr) {
	this.iqr = iqr;
}
public double getRange() {
	return range;
}
public void setRange(double range) {
	this.range = range;
}
}
