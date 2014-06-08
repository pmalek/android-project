package pl.wroc.pwr.patryk;

public class Coordinate {
	private long id;
	private double altitude;
	private double latitude;
	private double longitude;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return 	"Coordinate [id=" + id + ", altitude=" + altitude + 
				", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
