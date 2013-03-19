package com;

import com.alibaba.fastjson.JSON;
import com.qunar.airways.obj.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.intelliliu.nikita.ProtocolBuffer;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestBike {
    static private final Log logger = LogFactory.getLog(TestBike.class);
	@Test
	public void test() throws IOException {
		MeridaBike bike=new MeridaBike();
        MeridaBike.Switch tmp= new MeridaBike.Switch();
        tmp.setGear(6);
        bike.setSwtich(tmp);
		Brake brake=new Brake();
		bike.setSeries("duke");
		brake.setRadio(12);
		bike.setBrake(brake);

        MeridaBike bike0=new MeridaBike();
        MeridaBike.Switch tmp0= new MeridaBike.Switch();
        tmp.setGear(9);
        bike0.setSwtich(tmp);
        Brake brake0=new Brake();
        bike0.setSeries("challenger");
        brake0.setRadio(14);
        bike0.setBrake(brake0);

        Map<String,MeridaBike> map=new HashMap<String,MeridaBike>();
        List<MeridaBike> blist=new LinkedList<MeridaBike>();
        MeridaBike[] barr={bike,bike0};
        map.put("g600",bike);
        map.put("t300",bike0);
        blist.add(bike);
        blist.add(bike0);
        BikeMap bi=new BikeMap();
        BikeArr arr=new BikeArr();
        BikeList list=new BikeList();
        arr.setArr(barr);
        bi.setMap(map);
        list.setList(blist);
        byte[] buf= ProtocolBuffer.toBinaryStream(list);
        logger.info("buf.length:"+buf.length);
        OutputStream out=new FileOutputStream("/tmp/meridabike");
        out.write(buf);
        out.close();
        BikeList bikePB= (BikeList) ProtocolBuffer.parseFromStream(BikeList.class,buf);

        logger.info(JSON.toJSONString(bikePB));
	}

    @Test
    public void testwengaotie(){
        MeridaBike bike=new MeridaBike();
//        MeridaBike.Switch tmp= new MeridaBike.Switch();
//        tmp.setGear(9);
//        bike.setSwtich(tmp);
        bike.forTest();
        Brake brake=new Brake();
        bike.setSeries("duke");
        brake.setRadio(12);
        bike.setBrake(brake);

        MeridaBike mb= (MeridaBike) JSON.parseObject("{\"brake\":{\"radio\":12},\"series\":\"duke\",\"swtich\":{\"gear\":12}}", MeridaBike.class);
        logger.info(JSON.toJSONString(mb));
    }
}
