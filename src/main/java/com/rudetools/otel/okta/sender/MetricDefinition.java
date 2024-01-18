/**
 * 
 */
package com.rudetools.otel.okta.sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.common.AttributesBuilder;
import io.opentelemetry.api.metrics.DoubleGaugeBuilder;
import io.opentelemetry.api.metrics.DoubleUpDownCounterBuilder;
import io.opentelemetry.api.metrics.Meter;

/**
 * @author james101
 *
 */
public class MetricDefinition {

	public static final int GAUGE_DOUBLE = 1;
	public static final int UPDOWN_DOUBLE = 3;
	
	private String metricName = "";
	private String metricDescr = "";
	private int metricType = 0;
	private List<MetricDataPoint> dataPoints = null;
	
	private Meter meter = null;
	private DoubleUpDownCounterBuilder doubleUpdownBldr = null;
	private DoubleGaugeBuilder doubleGaugeBldr = null;
	
	
	
	
	/**
	 * 
	 */
	public MetricDefinition(int type, String name, String descr, Meter meter) {
		this.metricType = type;
		this.metricName = name;
		this.metricDescr = descr;
		this.meter = meter;
		
		
	}

	// !!!!  List<MetricDataPoint> (this.dataPoints) must be populated before this method is called
	public void startRecording() {
		
		switch (this.metricType) {
		
		case GAUGE_DOUBLE:
			
			this.doubleGaugeBldr = this.meter.gaugeBuilder(this.metricName);
			
			if (this.dataPoints != null && this.dataPoints.size() > 0) {
				
				for (MetricDataPoint dp : this.dataPoints) {
					
					if (dp.getAttributesMap() != null && dp.getAttributesMap().size() > 0) {
						
						Set<String> amKeys = dp.getAttributesMap().keySet();
						
						
						if (dp.getAttributesMap().size() > 1) {
							
							AttributesBuilder attrBldr = Attributes.builder();
							
							for (String amKey : amKeys) {
								
								attrBldr.put(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey));
								
							}
							
							this.doubleGaugeBldr.setDescription(this.getMetricDescr())
							.setUnit("")
							.buildWithCallback(measurement -> {
								measurement.record(dp.getDoubleVal(), attrBldr.build());
							});								
					
						} else {
							
							for (String amKey : amKeys) {
								
								this.doubleGaugeBldr.setDescription(this.getMetricDescr())
								.setUnit("")
								.buildWithCallback(measurement -> {
									measurement.record(dp.getDoubleVal(), Attributes.of(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey)));
								});								
								
							}
							
						}

						
						
//						for (String amKey : amKeys) {
//							
//							this.doubleGaugeBldr.setDescription(this.getMetricDescr())
//							.setUnit("")
//							.buildWithCallback(measurement -> {
//								measurement.record(dp.getDoubleVal(), Attributes.of(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey)));
//							});								
//							
//						}
						
						
						

					} else {
						
						this.doubleGaugeBldr.setDescription(this.getMetricDescr())
						.setUnit("")
						.buildWithCallback(measurement -> {
							measurement.record(dp.getDoubleVal(), Attributes.empty());
						});								
					}
					
				}
				
			}
			
	
			break;
			
		case UPDOWN_DOUBLE:
			
			this.doubleUpdownBldr = this.meter.upDownCounterBuilder(this.metricName).ofDoubles();
			
			if (this.dataPoints != null && this.dataPoints.size() > 0) {
				
				for (MetricDataPoint dp : this.dataPoints) {
					
					if (dp.getAttributesMap() != null && dp.getAttributesMap().size() > 0) {
						
						Set<String> amKeys = dp.getAttributesMap().keySet();
						
						
						
						
						if (dp.getAttributesMap().size() > 1) {
							
							AttributesBuilder attrBldr = Attributes.builder();
							
							for (String amKey : amKeys) {
								
								attrBldr.put(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey));
								
							}
							
							this.doubleUpdownBldr.setDescription(this.getMetricDescr())
							.setUnit("")
							.buildWithCallback(measurement -> {
								measurement.record(dp.getDoubleVal(), attrBldr.build());
							});								
					
						} else {
							
							for (String amKey : amKeys) {
								
								this.doubleUpdownBldr.setDescription(this.getMetricDescr())
								.setUnit("")
								.buildWithCallback(measurement -> {
									measurement.record(dp.getDoubleVal(), Attributes.of(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey)));
								});								
								
							}
							
						}
						
						
						
//						for (String amKey : amKeys) {
//							
//							this.doubleUpdownBldr.setDescription(this.getMetricDescr())
//							.setUnit("")
//							.buildWithCallback(measurement -> {
//								measurement.record(dp.getDoubleVal(), Attributes.of(AttributeKey.stringKey(amKey), dp.getAttributesMap().get(amKey)));
//							});								
//							
//						}

						
						
						
						
					} else {
						
						this.doubleUpdownBldr.setDescription(this.getMetricDescr())
						.setUnit("")
						.buildWithCallback(measurement -> {
							measurement.record(dp.getDoubleVal(), Attributes.empty());
						});								
					}
					
				}
				
			}			
			
			break;


		default:
			break;
		}
		
		// TODO construct the builder and add callback for each MetricDataPoint
		
		
	}
	
	
	public void addMetricDataPoint(MetricDataPoint mdp) {
		
		if (this.dataPoints == null) {
			this.dataPoints = new ArrayList<MetricDataPoint>();
		}
		
		this.dataPoints.add(mdp);
	}	
	
	public List<MetricDataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<MetricDataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public DoubleUpDownCounterBuilder getDoubleUpdownBldr() {
		return doubleUpdownBldr;
	}

	public void setDoubleUpdownBldr(DoubleUpDownCounterBuilder doubleUpdownBldr) {
		this.doubleUpdownBldr = doubleUpdownBldr;
	}

	public DoubleGaugeBuilder getDoubleGaugeBldr() {
		return doubleGaugeBldr;
	}

	public void setDoubleGaugeBldr(DoubleGaugeBuilder doubleGaugeBldr) {
		this.doubleGaugeBldr = doubleGaugeBldr;
	}

	public String getMetricName() {
		return metricName;
	}

	public String getMetricDescr() {
		return metricDescr;
	}

	public int getMetricType() {
		return metricType;
	}


	
	
}
