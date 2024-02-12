/**
 * 
 */
package com.rudetools.otel.okta.sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Schneider
 *
 */
public class OtlpSignalSender implements ApplicationConstants {

	private static List<Thread> SENDER_THREADS = new ArrayList<Thread>();
	
	/**
	 * 
	 */
	public OtlpSignalSender() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			// Prometheus Node - DON'T NEED THIS
			//generateNode1();
			
			// Oag Master Node -nginx
			generateC1Node1();
			
			
			// ext-workernode-1
			generateC1Node2();
			
			
			// int-workernode-1
			generateC1Node3();
			

			// int-workernode-2
			generateC1Node4();
			

			// Oag Master Node -nginx
			generateC2Node1();
			
			
			// ext-workernode-1
			generateC2Node2();
			
			
			// int-workernode-1
			generateC2Node3();
			

			// int-workernode-2
			generateC2Node4();
			
			long totalTime = 0;
			while (true) {
				
				log("MAIN Sender | Sleeping for 30 seconds");
				Thread.currentThread().sleep(30000);
				totalTime = totalTime+30;
				log("MAIN Runner | Total time = " + totalTime + " seconds");
				
				
				// if (totalTime > 240) {
				if (totalTime > 5000000) {
					log("MAIN Sender | Shutting down ...");
					System.exit(1);
				}
			}
			
			
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		
	}

	// Prometheus Node
//	private static void generateC1Node1() {
//		
//		Map<String, String> resAttrs;
//		EntityInstance entityInst;
//		OtlpEntityThread entityThread;
//		Thread tempThread;
//		
//		log("MAIN Sender | Creating Node 1");
//		
//		resAttrs = new HashMap<String, String>();
//		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
//		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.9.247");
//		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.9.247:8888");
//		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8888");
//		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");
//		
//		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 1");
//		
//		
//		xgenDiskMetrics(entityInst);
//		xgenCpuMetrics(entityInst);
//		xgenMemoryMetrics(entityInst);
//		xgenNetworkMetrics(entityInst);
//		
//		entityThread = new OtlpEntityThread(entityInst, " -- NODE 1");
//		tempThread = new Thread(entityThread, " -- NODE 1");
//		SENDER_THREADS.add(tempThread);
//		tempThread.start();
//		
//		log("MAIN Sender | Creating Node 1 Done");
//		
//
//	}

	
	// oag cluster admin-1 - nginx
	private static void generateC2Node1() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 2");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.0.132");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.0.132:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 2");
		
		xgenCluster2Metrics(entityInst);
		xgenEngineMetrics(entityInst);
		//xgenDiskMetrics(entityInst);
		//xgenCpuMetrics(entityInst);
		//xgenMemoryMetrics(entityInst);
		//xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- C2 NODE 2");
		tempThread = new Thread(entityThread, " -- C2 NODE 2");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 2 Done");

	}
	
	// oag cluster ext-workernode-1 
	private static void generateC2Node2() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 3");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.5.102");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.5.101:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 3");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetricsExtWn1(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- C2 NODE 3");
		tempThread = new Thread(entityThread, " -- C2 NODE 3");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 3 Done");

	}	
	
	// oag cluster int-workernode-1 
	private static void generateC2Node3() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 4");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.8.232");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.8.232:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 4");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetrics(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- C2 NODE 4");
		tempThread = new Thread(entityThread, " -- C2 NODE 4");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 4 Done");

	}
	
	// oag cluster int-workernode-2 
	private static void generateC2Node4() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 5");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.132.222");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.132.222:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 5");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetrics(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- C2 NODE 5");
		tempThread = new Thread(entityThread, " -- C2 NODE 5");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 5 Done");

	}
	
	
	// oag cluster admin-1 - nginx
	private static void generateC1Node1() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 2");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.0.131");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.0.131:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 2");
		
		xgenCluster1Metrics(entityInst);
		xgenEngineMetrics(entityInst);
		//xgenDiskMetrics(entityInst);
		//xgenCpuMetrics(entityInst);
		//xgenMemoryMetrics(entityInst);
		//xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- NODE 2");
		tempThread = new Thread(entityThread, " -- NODE 2");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 2 Done");

	}
	
	// oag cluster ext-workernode-1 
	private static void generateC1Node2() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 3");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.5.101");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.5.101:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 3");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetricsExtWn1(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- NODE 3");
		tempThread = new Thread(entityThread, " -- NODE 3");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 3 Done");

	}	
	
	// oag cluster int-workernode-1 
	private static void generateC1Node3() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 4");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.8.231");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.8.231:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 4");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetrics(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- NODE 4");
		tempThread = new Thread(entityThread, " -- NODE 4");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 4 Done");

	}
	
	// oag cluster int-workernode-2 
	private static void generateC1Node4() {
		
		Map<String, String> resAttrs;
		EntityInstance entityInst;
		OtlpEntityThread entityThread;
		Thread tempThread;
		
		log("MAIN Sender | Creating Node 5");
		
		resAttrs = new HashMap<String, String>();
		
		resAttrs.put(ENTITY_ATTR_SVCNAME, "okta-test-sender");
		resAttrs.put(ENTITY_ATTR_NET_HOSTNAME, "10.212.132.221");
		resAttrs.put(ENTITY_ATTR_INSTID, "10.212.132.221:8889");
		resAttrs.put(ENTITY_ATTR_NET_HOSTPORT, "8889");
		resAttrs.put(ENTITY_ATTR_HTTP_SCHEME, "http");

		entityInst = new EntityInstance(resAttrs, "otelcol/prometheusreceiver 5");
		
		xgenDiskMetrics(entityInst);
		xgenCpuMetrics(entityInst);
		xgenMemoryMetrics(entityInst);
		xgenNetworkMetrics(entityInst);
		
		entityThread = new OtlpEntityThread(entityInst, " -- NODE 5");
		tempThread = new Thread(entityThread, " -- NODE 5");
		SENDER_THREADS.add(tempThread);
		tempThread.start();			
		
		log("MAIN Sender | Creating Node 5 Done");

	}
	
	private static void xgenCluster1Metrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;

		// ext-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_ext_workernode_1_new_dev_aws_glic_com_test", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-ext-workernode-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);			


		// int-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_int_workernode_1_new_dev_aws_glic_com_test", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-int-workernode-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);	

		
		// int-workernode-2
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_int_workernode_2_new_dev_aws_glic_com_test", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-int-workernode-2-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);	
	
		
		
		
		
		// ext-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_ext_workernode_1_new_dev_aws_glic_com_test", 
				"Last timestamp for HA worker node okta-oag-linux-ext-workernode-1-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621713E9, true);			
		
		
		// int-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_int_workernode_1_new_dev_aws_glic_com_test", 
				"Last timestamp for HA worker node okta-oag-linux-int-workernode-1-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621714E9, true);	

		
		// int-workernode-2
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test_okta_oag_linux_int_workernode_2_new_dev_aws_glic_com_test", 
				"Last timestamp for HA worker node okta-oag-linux-int-workernode-2-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621714E9, true);	
		
		
		
		
		
	}
	
	private static void xgenCluster2Metrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;

		// ext-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_ext_workernode_1_new_dev_aws_glic_com_test2", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-ext-workernode-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);			


		// int-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_int_workernode_1_new_dev_aws_glic_com_test2", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-int-workernode-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);	

		
		// int-workernode-2
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_validation_result_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_int_workernode_2_new_dev_aws_glic_com_test2", 
				"HA validation status between master node okta-oag-linux-admin-1-new.dev.aws.glic.com and worker node okta-oag-linux-int-workernode-2-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, true);	
	
		
		
		
		
		// ext-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_ext_workernode_1_new_dev_aws_glic_com_test2", 
				"Last timestamp for HA worker node okta-oag-linux-ext-workernode-1-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621713E9, true);			
		
		
		// int-workernode-1
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_int_workernode_1_new_dev_aws_glic_com_test2", 
				"Last timestamp for HA worker node okta-oag-linux-int-workernode-1-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621714E9, true);	

		
		// int-workernode-2
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				"OAG_ha_last_sync_timestamp_okta_oag_linux_admin_1_new_dev_aws_glic_com_test2_okta_oag_linux_int_workernode_2_new_dev_aws_glic_com_test2", 
				"Last timestamp for HA worker node okta-oag-linux-int-workernode-2-new.dev.aws.glic.com of master node okta-oag-linux-admin-1-new.dev.aws.glic.com");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.703621714E9, true);	
		
		
		
		
		
	}
	
	private static void xgenNetworkMetrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;
		
		// NETWORK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_NET_RECEIVE_DROP, 
				"Network device statistic receive_drop.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 1, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth0");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 0, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth1");
		
		
		// NETWORK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_NET_RECEIVE_ERRS, 
				"Network device statistic receive_errs.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 1, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth0");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 0, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth1");		
		
		
		// NETWORK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_NET_RECEIVE_BYTES, 
				"Network device statistic receive_bytes.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 6.014099186E9, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth0");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0194487169E10, false);	
		metricDp.addMetricAttribute(OAG_NET_ATTR_DEVICE, "eth1");				
	}

	private static void xgenMemoryMetricsExtWn1(EntityInstance entity) {
		
		MetricDefinition metricDef;
		
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_TOTAL_BYTES, 
				"Memory information field MemTotal_bytes.");	
		createDoubleMetricDataPoint(metricDef, 7.922651136E9, false);
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_AVAIL_BYTES, 
				"Memory information field MemAvailable_bytes.");	
		createDoubleMetricDataPoint(metricDef, 926077204.48, false);
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_FREE_BYTES, 
				"Memory information field MemFree_bytes.");	
		createDoubleMetricDataPoint(metricDef, 1426077204.48, false);
		
		
		
	}
	
	private static void xgenMemoryMetrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_TOTAL_BYTES, 
				"Memory information field MemTotal_bytes.");	
		createDoubleMetricDataPoint(metricDef, 7.922651136E9, false);
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_AVAIL_BYTES, 
				"Memory information field MemAvailable_bytes.");	
		createDoubleMetricDataPoint(metricDef, 4.832464896E9, false);
		
		// MEMORY metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_MEMORY_FREE_BYTES, 
				"Memory information field MemFree_bytes.");	
		createDoubleMetricDataPoint(metricDef, 6.21881344E9, false);
		
		
		
	}
	
	private static void xgenCpuMetrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;
		
		// CPU metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_CPU_SECONDS, 
				"Seconds the CPUs spent in each mode.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 2359865.46, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "idle");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 157.72, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "iowait");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 2034.24, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "irq");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 121.47, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "nice");			
		
		metricDp = createDoubleMetricDataPoint(metricDef, 684.1100000000001, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "softirq");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 366.50000000000006, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "steal");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 11785.029999999999, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "system");	
		
		metricDp = createDoubleMetricDataPoint(metricDef, 31452.980000000003, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "0");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "user");		

		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 2359149.05, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "idle");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 165.36, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "iowait");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 2027.1399999999999, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "irq");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 355.64000000000004, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "nice");			
		
		metricDp = createDoubleMetricDataPoint(metricDef, 573.8299999999999, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "softirq");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 344.32, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "steal");		
		
		metricDp = createDoubleMetricDataPoint(metricDef, 3622.26, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "system");	
		
		metricDp = createDoubleMetricDataPoint(metricDef, 32048.39, false);	
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_CPU, "1");
		metricDp.addMetricAttribute(OAG_CPU_SECONDS_ATTR_MODE, "user");		
	
		
		// CPU metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_CPU_LOAD_1, 
				"1 minute load average.");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.5, false);		
		
		// CPU metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_CPU_LOAD_5, 
				"5 minute load average.");
		metricDp = createDoubleMetricDataPoint(metricDef, 0.5, false);		

		// CPU metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_CPU_LOAD_15, 
				"15 minute load average.");
		metricDp = createDoubleMetricDataPoint(metricDef, 1.0, false);		
		
	}
	
	private static void xgenDiskMetrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;
		
		// DISK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_DISK_FILESYS_TOTAL_BYTES, 
				"Filesystem size in bytes.");
		
		double totalBytes1 = 2.25821831168E11;
		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes1, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/mapper/ol_oag-root");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/");
		
		double totalBytes2 = 6.6131968E8;
		metricDp = createDoubleMetricDataPoint(metricDef, 1.063256064E9, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/nvme0n1p1");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/boot");

		double totalBytes3 = 3.58342656E9;
		metricDp = createDoubleMetricDataPoint(metricDef, 3.96132352E9, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/run");
		

		// DISK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_DISK_FILESYS_AVAIL_BYTES, 
				"Filesystem space available to non-root users in bytes.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes1 * 0.6, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/mapper/ol_oag-root");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/");
		
		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes2 * 0.6, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/nvme0n1p1");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/boot");

		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes3 * 0.6, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/run");
		

		// DISK metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_DISK_FILESYS_FREE_BYTES, 
				"Filesystem free space in bytes.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes1 * 0.69, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/mapper/ol_oag-root");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/");
		
		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes2 * 0.69, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "/dev/nvme0n1p1");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "xfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/boot");

		metricDp = createDoubleMetricDataPoint(metricDef, totalBytes3 * 0.69, false);	
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_DEVICE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_FSTYPE, "tmpfs");
		metricDp.addMetricAttribute(OAG_DISK_FILESYS_ATTR_MOUNTPOINT, "/run");

		
	}
	
	private static void xgenEngineMetrics(EntityInstance entity) {
		
		MetricDefinition metricDef;
		MetricDataPoint metricDp;
		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_ENGINE_ACTIVE_CONNS, 
				"The current number of active client connections including Waiting connections.");
		metricDp = createDoubleMetricDataPoint(metricDef, 2, false);
		

		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_ENGINE_HANDLED_CONNS, 
				"The total number of handled connections.");
		metricDp = createDoubleMetricDataPoint(metricDef, 750000, false);
		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_ENGINE_TOTAL_REQS_NUM, 
				"The total number of client requests.");
		metricDp = createDoubleMetricDataPoint(metricDef, 780000, false);

		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_ENGINE_WAITING_NUM, 
				"The current number of idle client connections waiting for a request.");
		metricDp = createDoubleMetricDataPoint(metricDef, 1, false);

		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.GAUGE_DOUBLE, 
				OAG_ENGINE_ACCEPTED_CONNS, 
				"The total number of accepted client connections.");
		metricDp = createDoubleMetricDataPoint(metricDef, 750001, false);

		
		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_ENGINE_METRIC_HANDLER_REQS_TOTAL, 
				"Total number of scrapes by HTTP status code.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 13502, false);
		metricDp.addMetricAttribute(OAG_ENGINE_METRIC_HANDLER_REQS_TOTAL_ATTR_CODE, "200");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 112, false);
		metricDp.addMetricAttribute(OAG_ENGINE_METRIC_HANDLER_REQS_TOTAL_ATTR_CODE, "500");	
		
		metricDp = createDoubleMetricDataPoint(metricDef, 36, false);
		metricDp.addMetricAttribute(OAG_ENGINE_METRIC_HANDLER_REQS_TOTAL_ATTR_CODE, "503");			
		
		
		
		// ENGINE metric
		metricDef = createMetricDefinition(entity, MetricDefinition.UPDOWN_DOUBLE, 
				OAG_ENGINE_METRIC_HANDLER_ERRS, 
				"Total number of internal errors encountered by the promhttp metric handler.");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 1, false);
		metricDp.addMetricAttribute(OAG_ENGINE_METRIC_HANDLER_ERRORS_ATTR_CAUSE, "encoding");
		
		metricDp = createDoubleMetricDataPoint(metricDef, 2, false);
		metricDp.addMetricAttribute(OAG_ENGINE_METRIC_HANDLER_ERRORS_ATTR_CAUSE, "gathering");			
		
	}
	
	
	private static MetricDataPoint createLongMetricDataPoint(MetricDefinition mdef, long val, boolean isStatic) {
		
		MetricDataPoint mdp = new MetricDataPoint(false, isStatic);
		mdp.setLongVal(val);
		mdef.addMetricDataPoint(mdp);
		return mdp;
	}
	
	
	private static MetricDataPoint createDoubleMetricDataPoint(MetricDefinition mdef, double val, boolean isStatic) {
		
		MetricDataPoint mdp = new MetricDataPoint(true, isStatic);
		mdp.setDoubleVal(val);
		mdef.addMetricDataPoint(mdp);
		return mdp;
	}
	
	
	private static MetricDefinition createMetricDefinition(EntityInstance entity, int type, String name, String descr) {
		
		MetricDefinition def = new MetricDefinition(type, name, descr, entity.getMeter());
		entity.addMetricDefinition(def);
		return def;
		
	}
	
	
	
	private static void log(String logMsg) {
		System.out.println(logMsg);
	}

	
	
}
