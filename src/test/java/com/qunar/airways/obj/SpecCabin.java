package com.qunar.airways.obj;

public class SpecCabin implements Comparable<SpecCabin>, CabinInfo, java.io.Serializable {

	private String fltNo;
	private String cabin;
	private String cabinNum;
	private String fareIndex;
	private String timestamp;
	private String signature;
	private Fare fare;
  
	public static void main(String[] args) {
	}

	@Override
	public int compareTo(SpecCabin o) {
		if(o.fare.getFsalePrice()>this.fare.getFsalePrice())return -1;
		if(o.fare.getFsalePrice()<this.fare.getFsalePrice())return 1;
		return 0;
	}
	
	public String getFareIndex(){
		return fareIndex;
	}
	
	public void setFare(Fare fare){
		this.fare = fare;
	}
	
	public Fare getFare(){
		return this.fare;
	}
	
	public SpecCabin(String fltNo, String cabin, String cabinNum,String fareIndex, String timestamp, String signature) {
		super();
		this.fltNo = fltNo;
		this.cabin = cabin;
		this.cabinNum = cabinNum;
		this.fareIndex = fareIndex;
		this.signature = signature;
		this.timestamp = timestamp;	
	}
	
	public String toString(){
		
		StringBuilder result = new StringBuilder(200);
		result.append("<flightList><fltNo>").append(this.fltNo).append("</fltNo></flightList>");
		result.append("<cabinList><cabin>").append(this.cabin).append("</cabin></cabinList>");
		result.append("<cabinNum>").append(this.cabinNum).append("</cabinNum>");
		result.append("<fareIndex>").append(this.fareIndex).append("</fareIndex>");
		result.append("<timestamp>").append(this.timestamp).append("</timestamp>");
		result.append("<signature>").append(this.signature).append("</signature>");
		
		return result.toString();
	}

	public String getFltNo() {
		return fltNo;
	}

	public String getCabin() {
		return cabin;
	}

	public String getCabinNum() {
		return cabinNum;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}

	@Override
	public boolean isSpecCabin() {
		return true;
	}

    public void setFltNo(String fltNo) {
        this.fltNo = fltNo;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setCabinNum(String cabinNum) {
        this.cabinNum = cabinNum;
    }

    public void setFareIndex(String fareIndex) {
        this.fareIndex = fareIndex;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
