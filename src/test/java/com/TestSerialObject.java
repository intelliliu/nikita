package com;

import com.alibaba.fastjson.JSON;
import com.qunar.airways.obj.Cabin;
import com.qunar.airways.obj.Fare;
import com.qunar.airways.obj.Flight;
import com.qunar.airways.obj.Tax;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.intelliliu.nikita.ProtocolBuffer;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;



public class TestSerialObject {
	static private final Log LOGGER = LogFactory.getLog("TestSerialObject");
	@Test
	public void testFlight() throws IOException, InterruptedException{
		Flight flt=new Flight();
		final Flight flt1=new Flight();
		Tax airportTax=new Tax("10", "11", "12");
		Tax fuelTax=new Tax("10", "11", "12");
		Fare fare=new Fare("1", "adult", "123", "awed", "123", "awd", "ew", "wer", "123", "swdqw", "wqe", "qwe", "qwd", "ewer", "qwed", "qwd");
		Fare fare1=new Fare("1", "adult", "123", "awed", "234", "awd", "ew", "wer", "123", "swdqw", "wqe", "qwe", "qwd", "ewer", "qwed", "qwd");
		Cabin cabin=new Cabin("12312", "Y", "10", "on sales", "true", "2", "1362736302988", "EW7889WEAS0D8QWE98");
		cabin.setFare(fare);
		Cabin cabin1=new Cabin("12313", "Z", "10", "on sales", "true", "2", "1362736302988", "EW7889WEAS0D8QWE98");
		cabin1.setFare(fare1);
		
		flt.setAirline("HU");
		flt.setAirportTax(airportTax);
		flt.setArrivalDateTime("2013-05-01 19:00");
		flt.setCodeShare("false");
		flt.setDst("SEA");
		flt.setETicket("true");
		flt.setFltNo("7181");
		flt.setFuelTax(fuelTax);
		flt.setIsAsr("true");
		flt.setMeal("false");
		flt.setOrg("PEK");
		flt.setPlaneStyle("747");
		flt.setSignature("SADEFE89AW7F98EWGFVWSWE324R324");
		flt.setSpecCabin(null);
		flt.setStop("false");
		flt.setTakeoffDateTime("2013-04-30 22:00");
		flt.setTimestamp("1362736302988");
		flt.addCabins(cabin);
		flt.addCabins(cabin1);
		
		flt1.setAirline("HU");
		flt1.setAirportTax(airportTax);
		flt1.setArrivalDateTime("2013-05-01 19:00");
		flt1.setCodeShare("false");
		flt1.setDst("SEA");
		flt1.setETicket("true");
		flt1.setFltNo("7181");
		flt1.setFuelTax(fuelTax);
		flt1.setIsAsr("true");
		flt1.setMeal("false");
		flt1.setOrg("PEK");
		flt1.setPlaneStyle("747");
		flt1.setSignature("SADEFE89AW7F98EWGFVWSWE324R324");
		flt1.setSpecCabin(null);
		flt1.setStop("false");
		flt1.setTakeoffDateTime("2013-04-30 22:00");
		flt1.setTimestamp("1362736302988");
		flt1.addCabins(cabin);
		flt1.addCabins(cabin1);
		

		String json=null;
		byte[] buf=null;
		
		long flag=0l;
		
		
		

		flag=System.currentTimeMillis();
		json=JSON.toJSONString(flt);
		LOGGER.info("fastjson time:"+(System.currentTimeMillis()-flag)+" size:"+json.getBytes("utf-8").length);

        flag=System.currentTimeMillis();
        buf= ProtocolBuffer.toBinaryStream(flt);
        LOGGER.info("protocol-buf time:"+(System.currentTimeMillis()-flag)+" size:"+buf.length);
	}
}
