package com.com.jnu.recycleview.data;

import android.util.Log;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpDataLoader {
    @NonNull
    public String getHttpData(String path){ // 下载文件，返回字符串
        try{
            URL url = new URL(path);//传递URL链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//获取HttpURLConnection对象实例
            conn.setConnectTimeout(10000);//连接超时时间
            conn.setReadTimeout(5000);//读取超时时间
            conn.setRequestMethod("GET");//设置HTTP请求使用的方法
            conn.setUseCaches(false);//不使用缓存，每次刷新都从网站上取数据，而不是从本地缓存取数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { //相等则连接成功
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());//读取输入流
                BufferedReader reader = new BufferedReader(inputStreamReader);//用缓存来整块读取
                String tempLine = null;
                StringBuffer resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");//一行一行读取，不为空就追加
                }
                Log.i("test data",resultBuffer.toString());
                return resultBuffer.toString();//返回字符串
            }
        }catch(Exception exception)
        {
            Log.e("error",exception.getMessage());
        }
        return "";//如果出错或者其他情况返回空
    }

    @NonNull
    public List<ShopLocation> ParseJsonData(String JsonText)//解析Josn数据
    {
        List<ShopLocation> locations=new ArrayList<>();
        try {
            JSONObject root = new JSONObject(JsonText);//获得外面的对象
            JSONArray shops = root.getJSONArray("shops");//获得shops属性，它的值是一个数组
            for(int i=0;i<shops.length();++i){
                JSONObject shop=shops.getJSONObject(i);

                ShopLocation shopLocation=new ShopLocation();
                shopLocation.setName(shop.getString("name"));
                shopLocation.setLatitude(shop.getDouble("latitude"));
                shopLocation.setLongitude(shop.getDouble("longitude"));
                shopLocation.setMemo(shop.getString("memo"));

                locations.add(shopLocation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locations;
    }
}