package com.qunar.airways.obj;

public class Fare implements java.io.Serializable{

	private String index;            //价格对象ID
	private String type;             //价格类型（暂无用）
	private String price;            //舱位标准价
	private String pmPrice;           //票面价
	private String salePrice;         //销售价
	private float fSalePrice;         //销售价float型
	private String discount;         //折扣率
	private String ei;               //签注说明
	private String canChangeTimes;   //允许改期次数
	private String upgradable;       //是否可升舱
	private String refundable;       //是否可退票
	private String baseFeePCT;       //基础代理费率
	private String addFeePCT;        //附加代理费率
	private String addFee;			 //附加代理费
	private String baseCabin;        //基础舱位
	private String baseCabinPrice;   //基础舱位价
	private String timestamp;        //时间戳
	private String signature;        //签名信息
	
	public Fare() {
	}
	
	public Fare(String index, String type, String price, String pmPrice, String salePrice,
			String discount, String ei, String canChangeTimes, String upgradable, String refundable,
			String baseFeePCT, String addFeePCT, String baseCabin, String baseCabinPrice, 
			String timestamp, String signature){
		this.index = index;
		this.type = type;
		this.price = price;
		this.pmPrice = pmPrice;
		this.salePrice = salePrice;
		this.fSalePrice = Float.parseFloat(salePrice);
		this.discount = discount;
		this.ei = ei;
		this.canChangeTimes = canChangeTimes;
		this.upgradable = upgradable;
		this.refundable = refundable;
		this.baseFeePCT = baseFeePCT;
		this.addFeePCT = addFeePCT;
		this.baseCabin = baseCabin;
		this.baseCabinPrice = baseCabinPrice;
		this.timestamp = timestamp;
		this.signature = signature;
	}
	
	public float getFsalePrice(){
		return this.fSalePrice;
	}
	
	public String toString(){
		
		StringBuilder result = new StringBuilder(500);		
		result.append("<index>").append(this.index).append("</index>");
		result.append("<type>").append(this.type).append("</type>");
		result.append("<price>").append(this.price).append("</price>");
		result.append("<pmPrice>").append(this.pmPrice).append("</pmPrice>");
		result.append("<salePrice>").append(this.salePrice).append("</salePrice>");
		result.append("<discount>").append(this.discount).append("</discount>");
		result.append("<ei>").append(this.ei).append("</ei>");
		result.append("<canChangeTimes>").append(this.canChangeTimes).append("</canChangeTimes>");
		result.append("<upgradable>").append(this.upgradable).append("</upgradable>");
		result.append("<refundable>").append(this.refundable).append("</refundable>");
		result.append("<baseFeePCT>").append(this.baseFeePCT).append("</baseFeePCT>");
		result.append("<addFeePCT>").append(this.addFeePCT).append("</addFeePCT>");
		result.append("<addFee>").append(this.addFee==null?"0.0":this.addFee).append("</addFee>");
		result.append("<baseCabin>").append(this.baseCabin).append("</baseCabin>");
		result.append("<baseCabinPrice>").append(this.baseCabinPrice).append("</baseCabinPrice>");
		result.append("<timestamp>").append(this.timestamp).append("</timestamp>");
		result.append("<signature>").append(this.signature).append("</signature>");
		
		return result.toString();
	}

	public String getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}

	public String getPrice() {
		return price;
	}

	public String getPmPrice() {
		return pmPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public float getfSalePrice() {
		return fSalePrice;
	}

	public String getDiscount() {
		return discount;
	}

	public String getEi() {
		return ei;
	}

	public String getCanChangeTimes() {
		return canChangeTimes;
	}

	public String getUpgradable() {
		return upgradable;
	}

	public String getRefundable() {
		return refundable;
	}

	public String getBaseFeePCT() {
		return baseFeePCT;
	}

	public String getAddFeePCT() {
		return addFeePCT;
	}

	public String getBaseCabin() {
		return baseCabin;
	}

	public String getBaseCabinPrice() {
		return baseCabinPrice;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPmPrice(String pmPrice) {
		this.pmPrice = pmPrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public void setfSalePrice(float fSalePrice) {
		this.fSalePrice = fSalePrice;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setEi(String ei) {
		this.ei = ei;
	}

	public void setCanChangeTimes(String canChangeTimes) {
		this.canChangeTimes = canChangeTimes;
	}

	public void setUpgradable(String upgradable) {
		this.upgradable = upgradable;
	}

	public void setRefundable(String refundable) {
		this.refundable = refundable;
	}

	public void setBaseFeePCT(String baseFeePCT) {
		this.baseFeePCT = baseFeePCT;
	}

	public void setAddFeePCT(String addFeePCT) {
		this.addFeePCT = addFeePCT;
	}

	public void setBaseCabin(String baseCabin) {
		this.baseCabin = baseCabin;
	}

	public void setBaseCabinPrice(String baseCabinPrice) {
		this.baseCabinPrice = baseCabinPrice;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAddFee() {
		return addFee;
	}

	public void setAddFee(String addFee) {
		this.addFee = addFee;
	}
	
}
