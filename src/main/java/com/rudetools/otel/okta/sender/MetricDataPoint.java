/**
 * 
 */
package com.rudetools.otel.okta.sender;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author james101
 *
 */
public class MetricDataPoint {

	private boolean isDoubleVal = true;
	private Double doubleVal = null;
	private Long longVal = null;
	private Map<String, String> attributesMap = null;
	private final boolean isStatic;
	
	/**
	 * 
	 */
	public MetricDataPoint(boolean isDouble, boolean isStatic) {
		this.isDoubleVal = isDouble;
		this.isStatic = isStatic;
	}

	
	public void addMetricAttribute(String key, String val) {
		
		if (this.attributesMap == null) {
			this.attributesMap = new HashMap<String, String>();
		}
		
		this.attributesMap.put(key, val);
	}	
	
	public double getDoubleVal() {
		if (this.isStatic) {
			return this.doubleVal;
		} else {
			Random rand = new Random();
			double tmp = this.doubleVal;
			tmp = tmp + ( rand.nextBoolean() ? 1 : -1 );
			return tmp;
		}
		
	}

	public void setDoubleVal(double doubleVal) {
		this.doubleVal = doubleVal;
	}

	public long getLongVal() {
		if (this.isStatic) {
			return this.longVal;
		} else {
			Random rand = new Random();
			long tmp = this.longVal;
			tmp = tmp + ( rand.nextBoolean() ? 1 : -1 );
			return tmp;			
		}

	}

	public void setLongVal(long longVal) {
		this.longVal = longVal;
	}

	public Map<String, String> getAttributesMap() {
		return attributesMap;
	}

	public void setAttributesMap(Map<String, String> attributesMap) {
		this.attributesMap = attributesMap;
	}

	public boolean isDoubleVal() {
		return isDoubleVal;
	}

	
	
}
