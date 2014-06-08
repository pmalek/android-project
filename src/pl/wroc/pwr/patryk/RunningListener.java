package pl.wroc.pwr.patryk;

import java.util.EventListener;

public interface RunningListener extends EventListener {
	
	public void onRunningChange(boolean running);

}
