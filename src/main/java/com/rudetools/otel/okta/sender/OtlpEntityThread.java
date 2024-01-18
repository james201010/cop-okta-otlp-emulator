/**
 * 
 */
package com.rudetools.otel.okta.sender;

/**
 * @author james101
 *
 */
public class OtlpEntityThread implements Runnable {

	
	private EntityInstance entity = null;
	private final String threadName;
	/**
	 * 
	 */
	public OtlpEntityThread(EntityInstance entityInst, String threadName) {
		
		this.entity = entityInst;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		
		try {
			
			this.entity.startRecording();
			
			while (true) {
				
				Thread.currentThread().sleep(30000);
				log(this.threadName + " | Okta Otel Entity Thread | Sleeping for 30 seconds");
					
		
			}			
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}

	}

	
	
	public void log(String logMsg) {
		System.out.println(logMsg);
	}	
}
