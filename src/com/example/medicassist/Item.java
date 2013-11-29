package com.example.medicassist;

public class Item {

	private String headline;
	private String doctorName;
	private String date;

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "HEADLINE=" + headline + ", DOCTORNAME=" + 
				doctorName + " , DATE=" + date ;
	}
}