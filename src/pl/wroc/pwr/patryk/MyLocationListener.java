package pl.wroc.pwr.patryk;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	
	private Activity activity;
	private Context context;
	private CoordinatesDataSource coordinates_data_source;
	private static final String TAG = "MyApp";

	public MyLocationListener(Activity activity, Context context,
			CoordinatesDataSource coordinates_data_source) {
		super();
		this.activity = activity;
		this.context = context;
		this.coordinates_data_source = coordinates_data_source;
	}

	@Override
    public void onLocationChanged(Location loc) {
    	//findViewById(R.id.tableLayout);
        Toast.makeText(
                context, "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                    + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        double longitude = loc.getLongitude();
        Log.v(TAG, "Longitude: " + longitude);
        double latitude = loc.getLatitude();
        Log.v(TAG, "Latitude: " + latitude);
        double altitude = loc.getAltitude();
        Log.v(TAG, "Altitude: " + altitude);
        
        long id = coordinates_data_source.createCoordinate(latitude, longitude, altitude);
        
        TableLayout table_layout = (TableLayout) activity.findViewById(R.id.tableLayout);
        TableRow table_row = new TableRow(context);
        TextView text_view_id = new TextView(context);
        text_view_id.setText(Long.toString(id));
        table_row.addView(text_view_id);
        
        TextView text_view_latitude = new TextView(context);
        text_view_latitude.setText(Double.toString(latitude));
        table_row.addView(text_view_latitude);
        
        TextView text_view_longitude = new TextView(context);
        text_view_longitude.setText(Double.toString(longitude));
        table_row.addView(text_view_longitude);
        
        TextView text_view_altitude = new TextView(context);
        text_view_altitude.setText(Double.toString(altitude));
        table_row.addView(text_view_altitude);
        
        table_layout.addView(table_row);
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {
    	Log.v(TAG, "ProviderEnabled: " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}

