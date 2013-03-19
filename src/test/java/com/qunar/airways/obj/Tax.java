package com.qunar.airways.obj;

public class Tax implements java.io.Serializable{
	String adult;
	String child;
	String infant;
	
	public Tax(String adult, String child, String infant){
		this.adult = adult;
		this.child = child;
		this.infant = infant;
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("<adult>").append(this.adult).append("</adult>")
		.append("<child>").append(this.child).append("</child>")
		.append("<infant>").append(this.infant).append("</infant>");
		return result.toString();
	}

	public String getAdult() {
		return adult;
	}

	public String getChild() {
		return child;
	}

	public String getInfant() {
		return infant;
	}

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

}
