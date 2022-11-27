package com.com.jnu.recycleview.data;

import com.com.jnu.recycleview.data.HttpDataLoader;
import com.com.jnu.recycleview.data.ShopLocation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
public class HttpDataLoaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHttpData() {
        HttpDataLoader httpDataLoader=new HttpDataLoader();
        String fileContent=httpDataLoader.getHttpData("http://file.nidama.net/class/mobile_develop/data/bookstore2022.json");
        Assert.assertTrue(fileContent.contains("\"name\": \"暨大珠海\","));//断言字符串里面是否包含
        Assert.assertTrue(fileContent.contains("\"memo\": \"珠海二城广场\""));
    }

    @Test
    public void parseJsonData() {
        HttpDataLoader httpDataLoader=new HttpDataLoader();
        String fileContent="{\n" +
                "  \"shops\": [{\n" +
                "    \"name\": \"暨大珠海\",\n" +
                "    \"latitude\": \"22.255925\",\n" +
                "    \"longitude\": \"113.541112\",\n" +
                "    \"memo\": \"暨南大学珠海校区\"\n" +
                "  },\n" +
                "    {\n" +
                "      \"name\": \"沃尔玛(前山店)\",\n" +
                "      \"latitude\": \"22.261365\",\n" +
                "      \"longitude\": \"113.532989\",\n" +
                "      \"memo\": \"沃尔玛(前山店)\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"明珠商业广场\",\n" +
                "      \"latitude\": \"22.251953\",\n" +
                "      \"longitude\": \"113.526421\",\n" +
                "      \"memo\": \"珠海二城广场\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        List<ShopLocation> locations= httpDataLoader.ParseJsonData(fileContent);

        Assert.assertEquals(3,locations.size());//断言解析出来的数据有三个对象
        Assert.assertEquals("暨大珠海",locations.get(0).getName());//assertEquals/assertTrue判断是否相等
        Assert.assertEquals(22.251953,locations.get(2).getLatitude(),1e-6);//比较浮点数doubel/float的时候要给一个偏差
        Assert.assertEquals(113.526421,locations.get(2).getLongitude(),1e-6);
        Assert.assertEquals("珠海二城广场",locations.get(2).getMemo());
    }
}