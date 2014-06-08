package pl.wroc.pwr.patryk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.TextView;

public class RunningListenerImpl implements RunningListener {

	private MainActivity activity;
	private TextView textViewStatus;
	
	public RunningListenerImpl(MainActivity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onRunningChange(boolean running) {
		if(textViewStatus == null)
			textViewStatus = (TextView) activity.findViewById(R.id.textview_status);
		
		if (running) {
			activity.locationManger = (LocationManager) activity.getSystemService( Context.LOCATION_SERVICE );

		    if ( !activity.locationManger.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
		        buildAlertMessageNoGps();
		    } else {
		    	textViewStatus.setText(R.string.status_working);
		    }
		}else {
			textViewStatus.setText(R.string.status_off);
		}
	}
	
	

	private void buildAlertMessageNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(
				"Your GPS seems to be disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(
									final DialogInterface dialog,
									final int id) {
								activity.startActivityForResult(
										new Intent(
												android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
										MainActivity.LOCATION_SETTINGS_REQUEST_CODE);
								// startActivity(new
								// Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(
									final DialogInterface dialog,
									final int id) {
								dialog.cancel();
							}
						});
		final AlertDialog alert = builder.create();
		alert.show();
	}
}
