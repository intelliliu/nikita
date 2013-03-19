package com.qunar.airways.obj;

public class Cabin implements Comparable<Cabin>, CabinInfo, java.io.Serializable {

	private String agreementID;   //产品代码
	private String cabin;         //舱位
	private String cabinNum;      //剩余座位数
	private String description;   //舱位描述
	private String isSa;          //是否候补订座
	private String fareIndex;     //适用的运价对象的索引
	private String timestamp;     //时间戳
	private String signature;     //签名信息
	private Fare fare;            //运价对象
	
	public static void main(String[] args) {
		
		Cabin cabin1;
		Cabin cabin2;
		
		cabin1 = createCabin(100, "Y");
		cabin2 = createCabin(200, "Y");
		System.out.println(cabin1.compareTo(cabin2));
		
		
		cabin1 = createCabin(100, "Y");
		cabin2 = createCabin(100, "Y");
		System.out.println(cabin1.compareTo(cabin2));

		
		cabin1 = createCabin(100, "P");
		cabin2 = createCabin(100, "Y");
		System.out.println(cabin1.compareTo(cabin2));

	}

	private static Cabin createCabin(int price, String c) {
		Fare fare1 = new Fare();
		fare1.setfSalePrice(price);
		Cabin cabin1 = new Cabin();
		cabin1.setFare(fare1);
		cabin1.setCabin(c);
		return cabin1;
	}

	@Override
	public int compareTo(Cabin o) {
		if(o.getFare().getFsalePrice()>this.getFare().getFsalePrice())return -1;
		if(o.getFare().getFsalePrice()<this.getFare().getFsalePrice())return 1;
		
		if ("A".equals(cabin) && "Y".equals(o.cabin)) {
			return -1;
		}
		
		if ("Y".equals(cabin) && "A".equals(o.cabin)) {
			return 1;
		}
		
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
	
	public Cabin() {
	}
	
	public Cabin(String agreementID, String cabin, String cabinNum, String description, 
			String isSa, String fareIndex, String timestamp, String signature) {
		super();
		this.agreementID = agreementID;
		this.cabin = cabin;
		this.cabinNum = cabinNum;
		this.description = description;
		this.isSa = isSa;
		this.fareIndex = fareIndex;
		this.timestamp = timestamp;
		this.signature = signature;
	}
	
	public String toString(){
		
		StringBuilder result = new StringBuilder();
		
		result.append("<wsProductCabinSelected>")
	    .append("<agreementID>").append(this.agreementID).append("</agreementID>")
	    .append("<cabin>").append(this.cabin).append("</cabin>")
	    .append("<cabinNum>").append(this.cabinNum).append("</cabinNum>")
	    .append("<description>").append(this.description).append("</description>");
		if(!"".equals(this.isSa)) {
		    result.append("<isSa>").append(this.isSa).append("</isSa>");			
		}else {
			result.append("<isSa>").append("false").append("</isSa>");
		}
	    result.append("<fareIndex>").append(this.fareIndex).append("</fareIndex>")
	    .append("<timestamp>").append(this.timestamp).append("</timestamp>")
	    .append("<signature>").append(this.signature).append("</signature>")
	    .append("</wsProductCabinSelected>");
		
		return result.toString();
	}

	public String getAgreementID() {
		return agreementID;
	}

	public String getCabin() {
		return cabin;
	}

	public String getCabinNum() {
		return cabinNum;
	}

	public String getDescription() {
		return description;
	}

	public String getIsSa() {
		return isSa;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setAgreementID(String agreementID) {
		this.agreementID = agreementID;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public void setCabinNum(String cabinNum) {
		this.cabinNum = cabinNum;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsSa(String isSa) {
		this.isSa = isSa;
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

	@Override
	public boolean isSpecCabin() {
		return false;
	}


    public String getSa() {
        return isSa;
    }

    public void setSa(String sa) {
        isSa = sa;
    }


}
