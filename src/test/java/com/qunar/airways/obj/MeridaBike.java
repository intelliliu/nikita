package com.qunar.airways.obj;

public class MeridaBike {
	private String series;
	private Brake brake;
    private Switch swtich;
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public Brake getBrake() {
		return brake;
	}
	public void setBrake(Brake brake) {
		this.brake = brake;
	}

    public Switch getSwtich() {
        return swtich;
    }

    public void setSwtich(Switch swtich) {
        this.swtich = swtich;
    }

    public static class Switch{
        private int gear;

        public int getGear() {
            return gear;
        }

        public void setGear(int gear) {
            this.gear = gear;
        }
    }

    public void forTest(){
        Switch swt=new Switch();
        swt.setGear(12);
        swtich=swt;
    }
}
