package com.qunar.airways.obj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight implements java.io.Serializable {

	private String airline;
	private String fltNo;
	private String org;
	private String dst;
	private String takeoffDateTime;
	private String arrivalDateTime;
	private String planeStyle;	
	private String meal;	
	private String eTicket;
	private String codeShare;
	private String isAsr;
	private String stop;
	private String timestamp;
	private String signature;

	private List<Cabin> cabins = new ArrayList<Cabin>();
	private SpecCabin specCabins = null;
	private Tax airportTax = null;
	private Tax fuelTax = null;

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public String getAirline() {
		return this.airline;
	}

	public void setFltNo(String flightNo) {
		this.fltNo = flightNo;
	}
	
	public String getFltNo() {
		return this.fltNo;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
	public String getOrg() {
		return this.org;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}
	
	public String getDst() {
		return this.dst;
	}

	public void setTakeoffDateTime(String takeoffDateTime) {
		this.takeoffDateTime = takeoffDateTime;
	}
	
	public String getTakeoffDateTime() {
		return this.takeoffDateTime;
	}

	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	public String getArrivalDateTime() {
		return this.arrivalDateTime;
	}

	public void setPlaneStyle(String planeStyle) {
		this.planeStyle = planeStyle;
	}
	
	public String getPlaneStyle() {
		return this.planeStyle;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	
	public String getMeal() {
		return this.meal;
	}

	public void setETicket(String ticket) {
		this.eTicket = ticket;
	}
	
	public String getETicket() {
		return this.eTicket;
	}

	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}
	
	public String getCodeShare() {
		return this.codeShare;
	}

	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}
	
	public String getIsAsr() {
		return this.isAsr;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getStop() {
		return this.stop;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return this.signature;
	}
	
	public void setFuelTax(Tax fuelTax) {
		this.fuelTax = fuelTax;
	}

	public Tax getFuelTax() {
		return this.fuelTax;
	}
	
	public void setAirportTax(Tax airportTax) {
		this.airportTax = airportTax;
	}

	public Tax getAirportTax() {
		return this.airportTax;
	}
	
	public void addCabins(Cabin cabin) {
		this.cabins.add(cabin);
	}
	
	public void setSpecCabin(SpecCabin cabin) {
		this.specCabins = cabin;
	}
	
	public List<Cabin> getCabins() {
		return this.cabins;
	}

	
	public SpecCabin getSpecCabin() {
		return this.specCabins;
	}
	
	public void addAllFares(List<Cabin> cabin) {
		this.cabins.addAll(cabin);
	}
	
//	private Cabin getLowestCabin(){		
//		if(specCabins != null) return null;
//		
//		Cabin[] allfare = cabins.toArray(new Cabin[0]);
//		Arrays.sort(allfare);
//		return allfare[0];
//	}

	private Cabin getLowestCabinWithoutCheckSpec() {
		return Collections.min(cabins);
	}
	
	public String toString(CabinInfo lowestCabin) {
		StringBuilder result = new StringBuilder();

		result.append("<airline>").append(this.airline).append("</airline>")
		.append("<fltNo>").append(this.fltNo).append("</fltNo>")
		.append("<org>").append(this.org).append("</org>")
		.append("<dst>").append(this.dst).append("</dst>")
		.append("<takeoffDateTime>").append(this.takeoffDateTime).append("</takeoffDateTime>")
		.append("<arrivalDateTime>").append(this.arrivalDateTime).append("</arrivalDateTime>")
		.append("<planeStyle>").append(this.planeStyle).append("</planeStyle>")
		.append("<meal>").append(this.meal).append("</meal>")
		.append("<eTicket>").append(this.eTicket).append("</eTicket>")
		.append("<codeShare>").append(this.codeShare).append("</codeShare>")
		.append("<isAsr>").append(this.isAsr).append("</isAsr>")
		.append("<stop>").append(this.stop).append("</stop>")
//		.append("<wsProductCabins>").append("</wsProductCabins>")
		.append("<timestamp>").append(this.timestamp).append("</timestamp>")
		.append("<signature>").append(this.signature).append("</signature>");
		if(airportTax != null){
			result.append("<airportTax>").append(airportTax.toString()).append("</airportTax>");
		}
		if(fuelTax != null){
			result.append("<fuelTax>").append(fuelTax.toString()).append("</fuelTax>");
		}
		if(lowestCabin != null){
			result.append(lowestCabin.toString());
		}
		
		return result.toString();
	}

	public CabinInfo getLowestCabinFromAllCabins() {
		
		CabinInfo cabin = getLowestCabinWithoutCheckSpec();

		float realPrice = cabin.getFare().getfSalePrice();
		if (getSpecCabin() != null) {
			SpecCabin specCabin = getSpecCabin();
			if (specCabin.getFare().getfSalePrice() > 0 && specCabin.getFare().getfSalePrice() < realPrice) {
				cabin = specCabin;
			}
		}
		
		return cabin;
	}
	
	public String getCabinYPrice() {
		String yprice = "0";
		
		for(Cabin cabin: cabins) {
			if("Y".equals(cabin.getCabin())) {
				yprice = cabin.getFare().getPmPrice();
				break;
			}
		}
		
		return yprice;
	}


    public String geteTicket() {
        return eTicket;
    }

    public void seteTicket(String eTicket) {
        this.eTicket = eTicket;
    }

    public String getAsr() {
        return isAsr;
    }

    public void setAsr(String asr) {
        isAsr = asr;
    }

    public SpecCabin getSpecCabins() {
        return specCabins;
    }

    public void setSpecCabins(SpecCabin specCabins) {
        this.specCabins = specCabins;
    }

    public void setCabins(List<Cabin> cabins) {
        this.cabins = cabins;
    }

}
