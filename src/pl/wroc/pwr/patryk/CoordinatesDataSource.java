package pl.wroc.pwr.patryk;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CoordinatesDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
			MySQLiteHelper.COLUMN_ID, 
			MySQLiteHelper.COLUMN_LATITUDE,
			MySQLiteHelper.COLUMN_LONGITUDE,
			MySQLiteHelper.COLUMN_ALTITUDE};

	public CoordinatesDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createCoordinate(double latitude, double longitude, double altitude) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_LATITUDE, latitude);
		values.put(MySQLiteHelper.COLUMN_LONGITUDE, longitude);
		values.put(MySQLiteHelper.COLUMN_ALTITUDE, altitude);
		long insertId = database.insert(MySQLiteHelper.TABLE_COORDINATES, null, values);
		
		Cursor cursor = database.query(
				MySQLiteHelper.TABLE_COORDINATES,
				allColumns, 
				MySQLiteHelper.COLUMN_ID + " = " + insertId, 
				null, null, null, null);
		cursor.moveToFirst();
		Coordinate newCoordinate = cursorToCoordinate(cursor);
		cursor.close();
		return insertId;
	}

	public void deleteCoordinate(Coordinate coordinate) {
		long id = coordinate.getId();
		System.out.println("Coordinate deleted with id: " + id);
		database.delete(
				MySQLiteHelper.TABLE_COORDINATES, 
				MySQLiteHelper.COLUMN_ID + " = " + id, 
				null);
	}

	public List<Coordinate> getAllCoordinates() {
		List<Coordinate> coordinates = new ArrayList<Coordinate>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_COORDINATES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Coordinate coordinate = cursorToCoordinate(cursor);
			coordinates.add(coordinate);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return coordinates;
	}

	private Coordinate cursorToCoordinate(Cursor cursor) {
		Coordinate coordinate = new Coordinate();
		coordinate.setId(cursor.getLong(0));
		coordinate.setLatitude(cursor.getDouble(1));
		coordinate.setLongitude(cursor.getDouble(2));
		coordinate.setAltitude(cursor.getDouble(3));
		return coordinate;
	}
}