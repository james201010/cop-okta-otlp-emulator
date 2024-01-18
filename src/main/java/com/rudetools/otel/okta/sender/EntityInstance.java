/**
 * 
 */
package com.rudetools.otel.okta.sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter;
import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import io.opentelemetry.proto.common.v1.AnyValue;
import io.opentelemetry.proto.common.v1.ArrayValue;
import io.opentelemetry.proto.common.v1.KeyValue;
import io.opentelemetry.proto.common.v1.KeyValueList;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.resources.ResourceBuilder;

/**
 * 
 * @author james101
 *
 */
public class EntityInstance {

	
	private Map<String, String> attributesMap = null;
	private List<MetricDefinition> metricDefinitions = null;
	
	private Resource resource = null;
	private OpenTelemetry otelsdk = null;
	private Meter meter = null;
	
	
	/**
	 * Each instance will have its own OpenTelemetry instance, set with the entity instance resource attributes
	 * 
	 */
	public EntityInstance(Map<String, String> resourceAttrs, String scope) {
		
		this.attributesMap = resourceAttrs;
		this.resource = generateEntityResourceAttributes(this.attributesMap);
		this.otelsdk = generateOtelSdk(this.resource);
		this.meter = this.otelsdk.getMeterProvider().get(scope);
		
	}

	// !!!!  List<MetricDefinition> (this.metricDefinitions) must be populated before this method is called
	// !!!!  List<MetricDataPoint> (this.dataPoints) must be populated in each MetricDefinition before this method is called
	public void startRecording() {
		
		for (MetricDefinition def : this.metricDefinitions) {
			def.startRecording();
		}
		
	}
	
	
	public void addMetricDefinition(MetricDefinition def) {
		
		if (this.metricDefinitions == null) {
			this.metricDefinitions = new ArrayList<MetricDefinition>();
		}
		
		this.metricDefinitions.add(def);
	}
	
	public List<MetricDefinition> getMetricDefinitions() {
		return metricDefinitions;
	}


	public void setMetricDefinitions(List<MetricDefinition> metricDefinitions) {
		this.metricDefinitions = metricDefinitions;
	}


	public Meter getMeter() {
		return this.meter;
	}


	private static Resource generateEntityResourceAttributes(final Map<String, String> attrsMap) {
		
		ResourceBuilder rb = Resource.getDefault().toBuilder();
		
		Set<String> attrsKeys = attrsMap.keySet();
		
		for(String key : attrsKeys) {
			rb.put(key, attrsMap.get(key));
		}
		
//		// working from inside out to create this
//		// create the inner key value attributes first
//		KeyValueList.Builder kvListBldr = KeyValueList.newBuilder();
//		
//		KeyValue.Builder kvBldr1;
//		
//		kvBldr1 = KeyValue.newBuilder();
//		kvBldr1.setKey("ugottaridedev.ride.rideid");
//		kvBldr1.setValue(AnyValue.newBuilder().setStringValue("2200006").build());
//		kvListBldr.addValues(kvBldr1.build());
//		
//		kvBldr1 = KeyValue.newBuilder();
//		kvBldr1.setKey("ugottaridedev.type");
//		kvBldr1.setValue(AnyValue.newBuilder().setStringValue("ride").build());
//		kvListBldr.addValues(kvBldr1.build());		
//		
//		
//		// now add the kv list to an any value
//		AnyValue.Builder anyValBldr = AnyValue.newBuilder();
//		anyValBldr.setKvlistValue(kvListBldr.build());
//		
//		// create the array to hold the any value containing the kv list
//		ArrayValue.Builder arrBldr = ArrayValue.newBuilder();
//		arrBldr.addValues(anyValBldr.build());
//		
//		// create new any value to hold the array
//		anyValBldr = AnyValue.newBuilder();
//		anyValBldr.setArrayValue(arrBldr.build());
//		
//		
//		
//		// now create the outermost attribute
//		//KeyValue.Builder attrib = KeyValue.newBuilder();
//		//attrib.setKey("appd.fmm.entity.relations");
//		//attrib.setValue(anyValBldr.build());
//		
//		
//		
//		// rb.
//		AttributeKey<String> attrKey =  AttributeKey.stringKey("appd.fmm.entity.relations");
//		
//		Attributes.of(attrKey, anyValBldr.build());
//		
//		//rb.put(attrKey, attrib.build().getValue());
//		
//		//Resource resource = rb.build();
//		//resource.
//		
//		//rb.put(null, null)
		
		return rb.build();
	}
	
	
	
	private static OpenTelemetry generateOtelSdk(final Resource res) {
		
	    SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder().setResource(res)
	            .registerMetricReader(PeriodicMetricReader.builder(OtlpHttpMetricExporter.builder().build()).build())
	            //.setResource(resource)
	            .build();
	    

	    SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder().setResource(res)
	            .addLogRecordProcessor(
	                    BatchLogRecordProcessor.builder(OtlpHttpLogRecordExporter.builder().build()).build())
	            //.setResource(resource)
	            .build();

	    OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
	        //.setTracerProvider(sdkTracerProvider)
	        .setMeterProvider(sdkMeterProvider)
	        .setLoggerProvider(sdkLoggerProvider)
	        
	        // TODO comment out line below and test
	        .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
	        
	        .build();
	        //.buildAndRegisterGlobal();	
	    
	    return openTelemetry;		
	}
	
}
