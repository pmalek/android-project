package pl.wroc.pwr.patryk;

import java.util.EventObject;

public class RunningChangeEvent extends EventObject {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean running;

	public RunningChangeEvent(Object source){
        super(source);
    }

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
}
