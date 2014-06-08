package pl.wroc.pwr.patryk;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Coordinate> {
	  private final Context context;
	  private final List<Coordinate> values;

	  public MyArrayAdapter(Context context, List<Coordinate> values) {
		super(context, R.layout.table_row, values);
		this.context = context;
		this.values = values;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.table_row, parent, false);
	    
	    TextView textViewRowId = (TextView) rowView.findViewById(R.id.table_row_id);
	    TextView textViewLatitude = (TextView) rowView.findViewById(R.id.table_row_latitude);
	    TextView textViewLongitude = (TextView) rowView.findViewById(R.id.table_row_longitude);
	    TextView textViewAtitude = (TextView) rowView.findViewById(R.id.table_row_altitude);
	    
	    textViewRowId.setText(Long.toString(values.get(position).getId()));
	    textViewLatitude.setText(Double.toString(values.get(position).getLatitude()));
	    textViewLongitude.setText(Double.toString(values.get(position).getLongitude()));
	    textViewAtitude.setText(Double.toString(values.get(position).getAltitude()));

	    return rowView;
	  }

	@Override
	public void add(Coordinate object) {
		super.add(object);
		//values.add(object);
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return values.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getItem(int)
	 */
	@Override
	public Coordinate getItem(int position) {
		return values.get(position);
	}
}