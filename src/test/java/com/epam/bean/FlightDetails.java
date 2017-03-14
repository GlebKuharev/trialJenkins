package com.epam.bean;

/**
 * Created by PC on 03.03.2017.
 */
public class FlightDetails {
    private String airportDep;
    private String airportArriv;
    private String flightDate;
    private double numberOfPassengers;
    
    private String airportOutboundFrom;
    private String airportOutboundTo;
    private String airportInboundFrom;
    private String airportInboundTo;
    private String dataOutbound;
    private String dataInbound;
    
    private String companyName;
    private String videoName;
    
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public FlightDetails(){
    }	
	
    public String getDataOutbound() {
		return dataOutbound;
	}

	public void setDataOutbound(String dataOutbound) {
		this.dataOutbound = dataOutbound;
	}

	public String getDataInbound() {
		return dataInbound;
	}

	public void setDataInbound(String dataInbound) {
		this.dataInbound = dataInbound;
	}

	public String getAirportOutboundFrom() {
		return airportOutboundFrom;
	}

	public void setAirportOutboundFrom(String airportOutboundFrom) {
		this.airportOutboundFrom = airportOutboundFrom;
	}

	public String getAirportOutboundTo() {
		return airportOutboundTo;
	}

	public void setAirportOutboundTo(String airportOutboundTo) {
		this.airportOutboundTo = airportOutboundTo;
	}

	public String getAirportInboundFrom() {
		return airportInboundFrom;
	}

	public void setAirportInboundFrom(String airportInboundFrom) {
		this.airportInboundFrom = airportInboundFrom;
	}

	public String getAirportInboundTo() {
		return airportInboundTo;
	}

	public void setAirportInboundTo(String airportInboundTo) {
		this.airportInboundTo = airportInboundTo;
	}

    public String getAirportDep() {
        return airportDep;
    }

    public void setAirportDep(String airportDep) {
        this.airportDep = airportDep;
    }

    public String getAirportArriv() {
        return airportArriv;
    }

    public void setAirportArriv(String airportArriv) {
        this.airportArriv = airportArriv;
    }

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public double getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(double numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}
