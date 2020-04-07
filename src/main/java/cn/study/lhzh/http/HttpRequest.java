package cn.study.lhzh.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendMyGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
//            connection.setRequestProperty("cookie", "EDUWEBDEVICE=62516c8a4ae14250b51beaa7f9010479; __yadk_uid=C9HDIv6USrnWxyYCQ1sBVEGyFyhiN5Lj; WM_TID=xplx6oZPQSFFFUVURQN%2BU0oBLueH6rQg; hasVolume=true; videoVolume=0.8; hb_MA-A976-948FFA05E931_source=www.baidu.com; NTES_YD_PASSPORT=DlgrwnyM4MMaaIdzp_32omYE80jzTlz4GXKPQO5ylOTluWdmu57zsxaHda6XKXucCywS3_6oS67GBQzNOO94yYw1DZ2K06CYV52sN_kUdgXYaF5mrEme8knnsRKma.96BnTxKX7O16CMlYCB4K1L.glnCGSUptCqneTQ3i8o0Lg20kBR0z_qoNdc4tzeQxKwocUT5fA.sidJpzyShH9MdFmvc; P_INFO=13644910397|1585849196|1|imooc|00&99|null&null&null#lin&210200#10#0#0|&0|null|13644910397; NTESSTUDYSI=b2dc9c47194e4e97a711b7bde5e281b2; STUDY_INFO=\"yd.f2a5c27982a24abeb@163.com|8|1434279836|1585983736642\"; STUDY_SESS=\"Jerk+fVQH0d3j6vHlp4wg7d6OS+gePTdf0OVWCJxz9s6nu6PH3RQsGpmcN5Hl6h7CJ/z/Wj/NAfwf1l43QvDX/cUyrI+kQG0YZz9szi0k79frpWQG2pZHZlEUYYSQv/lxYD//eACCHQa2/knuvEKKZSvdIx4BDdY+d4DWTCvYwoLhur2Nm2wEb9HcEikV+3FTI8+lZKyHhiycNQo+g+/oA==\"; STUDY_PERSIST=\"YS2dqymujiqtl3PPFwCNaA7QzAA3Ke7kJOkA3faUrq51PKWhsDay+Oe6S9Qw74Y8Poqm5jXhGr56Wdv13LWOAdqZR5FRU8Z6GxRdmW3UKkz4FUq99xCg3SPni8XIkXz0NRYPGFY+V+S+7nD/1Nu4LK/SRg28MRZATB20vXqRlgdue+N15h37nn2gEGz0mkJmwvlwJk9pvh6EzcRsUwQjoMVdBl+LoX/+U4dcOXaPp2zZgpjCC7Iso4RP9U87vJE8LtaQzUT1ovP2MqtW5+L3Hw+PvH8+tZRDonbf7gEH7JU=\"; NETEASE_WDA_UID=1434279836#|#1585666023769; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1585656463,1585849142,1585983737; WM_NI=nCZajxciMTp9XeZ5omiZCAFHBttl0Q08NxIYg689xhCtTJP4l9uvWVettXNP0pXBmHYwnWTrWl90fIsVimllTlwWBjHHkJSEl6kfQalYdcwUIyi2daDnbq%2BvJCbzys8uZHQ%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeccb2738ef5beafe44ee9eb8eb2d84e979e8aabf84baa888ebbc95fa99fad86ee2af0fea7c3b92aae9d9682e974a1b783b5f3339cbb96d6ee67ab868da2c54482acbc8db35bf4e88299cc53a5eaf8dafb6481ac9cabd340b0e7bb97d65ea7acbcb9cd5cf4b098a3f0449ba9a2b8d77a95b4f7d1e543b18aa78eef6898b4b9b8c1628c98a5abd24fb0eaa587d46bb087ac84f65f8ba7a7d3e76b88b2ae92aa7e8a9dae9bfc79fcab96a9d437e2a3; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1586080963");
//            connection.setRequestProperty("cookie","EDUWEBDEVICE=62516c8a4ae14250b51beaa7f9010479; __yadk_uid=C9HDIv6USrnWxyYCQ1sBVEGyFyhiN5Lj; WM_TID=xplx6oZPQSFFFUVURQN%2BU0oBLueH6rQg; hasVolume=true; videoVolume=0.8; hb_MA-A976-948FFA05E931_source=www.baidu.com; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1585656463,1585849142,1585983737; WM_NI=nCZajxciMTp9XeZ5omiZCAFHBttl0Q08NxIYg689xhCtTJP4l9uvWVettXNP0pXBmHYwnWTrWl90fIsVimllTlwWBjHHkJSEl6kfQalYdcwUIyi2daDnbq%2BvJCbzys8uZHQ%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeccb2738ef5beafe44ee9eb8eb2d84e979e8aabf84baa888ebbc95fa99fad86ee2af0fea7c3b92aae9d9682e974a1b783b5f3339cbb96d6ee67ab868da2c54482acbc8db35bf4e88299cc53a5eaf8dafb6481ac9cabd340b0e7bb97d65ea7acbcb9cd5cf4b098a3f0449ba9a2b8d77a95b4f7d1e543b18aa78eef6898b4b9b8c1628c98a5abd24fb0eaa587d46bb087ac84f65f8ba7a7d3e76b88b2ae92aa7e8a9dae9bfc79fcab96a9d437e2a3; NTESSTUDYSI=6eeb5c40bd7b4f3682e387bea8b3115c; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1586085190");
            connection.setRequestProperty("origin", "https://www.icourse163.org");
            connection.setRequestProperty("referer", "https://www.icourse163.org/learn/PKU-1207132813?tid=1207476216");
            
            
            
            
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendMyPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
//            conn.setRequestProperty(":authority", "www.icourse163.org");
//            conn.setRequestProperty(":method", "POST");
//            conn.setRequestProperty(":path", "/web/j/resourceRpcBean.getResourceToken.rpc?csrfKey=b2dc9c47194e4e97a711b7bde5e281b2");
//            conn.setRequestProperty(":scheme", "https");
            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
            conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "EDUWEBDEVICE=62516c8a4ae14250b51beaa7f9010479; __yadk_uid=C9HDIv6USrnWxyYCQ1sBVEGyFyhiN5Lj; WM_TID=xplx6oZPQSFFFUVURQN%2BU0oBLueH6rQg; hasVolume=true; videoVolume=0.8; hb_MA-A976-948FFA05E931_source=www.baidu.com; NTES_YD_PASSPORT=DlgrwnyM4MMaaIdzp_32omYE80jzTlz4GXKPQO5ylOTluWdmu57zsxaHda6XKXucCywS3_6oS67GBQzNOO94yYw1DZ2K06CYV52sN_kUdgXYaF5mrEme8knnsRKma.96BnTxKX7O16CMlYCB4K1L.glnCGSUptCqneTQ3i8o0Lg20kBR0z_qoNdc4tzeQxKwocUT5fA.sidJpzyShH9MdFmvc; P_INFO=13644910397|1585849196|1|imooc|00&99|null&null&null#lin&210200#10#0#0|&0|null|13644910397; NTESSTUDYSI=b2dc9c47194e4e97a711b7bde5e281b2; STUDY_INFO=\"yd.f2a5c27982a24abeb@163.com|8|1434279836|1585983736642\"; STUDY_SESS=\"Jerk+fVQH0d3j6vHlp4wg7d6OS+gePTdf0OVWCJxz9s6nu6PH3RQsGpmcN5Hl6h7CJ/z/Wj/NAfwf1l43QvDX/cUyrI+kQG0YZz9szi0k79frpWQG2pZHZlEUYYSQv/lxYD//eACCHQa2/knuvEKKZSvdIx4BDdY+d4DWTCvYwoLhur2Nm2wEb9HcEikV+3FTI8+lZKyHhiycNQo+g+/oA==\"; STUDY_PERSIST=\"YS2dqymujiqtl3PPFwCNaA7QzAA3Ke7kJOkA3faUrq51PKWhsDay+Oe6S9Qw74Y8Poqm5jXhGr56Wdv13LWOAdqZR5FRU8Z6GxRdmW3UKkz4FUq99xCg3SPni8XIkXz0NRYPGFY+V+S+7nD/1Nu4LK/SRg28MRZATB20vXqRlgdue+N15h37nn2gEGz0mkJmwvlwJk9pvh6EzcRsUwQjoMVdBl+LoX/+U4dcOXaPp2zZgpjCC7Iso4RP9U87vJE8LtaQzUT1ovP2MqtW5+L3Hw+PvH8+tZRDonbf7gEH7JU=\"; NETEASE_WDA_UID=1434279836#|#1585666023769; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1585656463,1585849142,1585983737; WM_NI=nCZajxciMTp9XeZ5omiZCAFHBttl0Q08NxIYg689xhCtTJP4l9uvWVettXNP0pXBmHYwnWTrWl90fIsVimllTlwWBjHHkJSEl6kfQalYdcwUIyi2daDnbq%2BvJCbzys8uZHQ%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeccb2738ef5beafe44ee9eb8eb2d84e979e8aabf84baa888ebbc95fa99fad86ee2af0fea7c3b92aae9d9682e974a1b783b5f3339cbb96d6ee67ab868da2c54482acbc8db35bf4e88299cc53a5eaf8dafb6481ac9cabd340b0e7bb97d65ea7acbcb9cd5cf4b098a3f0449ba9a2b8d77a95b4f7d1e543b18aa78eef6898b4b9b8c1628c98a5abd24fb0eaa587d46bb087ac84f65f8ba7a7d3e76b88b2ae92aa7e8a9dae9bfc79fcab96a9d437e2a3; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1586080963");
//            conn.setRequestProperty("cookie","EDUWEBDEVICE=62516c8a4ae14250b51beaa7f9010479; __yadk_uid=C9HDIv6USrnWxyYCQ1sBVEGyFyhiN5Lj; WM_TID=xplx6oZPQSFFFUVURQN%2BU0oBLueH6rQg; hasVolume=true; videoVolume=0.8; hb_MA-A976-948FFA05E931_source=www.baidu.com; Hm_lvt_77dc9a9d49448cf5e629e5bebaa5500b=1585656463,1585849142,1585983737; WM_NI=nCZajxciMTp9XeZ5omiZCAFHBttl0Q08NxIYg689xhCtTJP4l9uvWVettXNP0pXBmHYwnWTrWl90fIsVimllTlwWBjHHkJSEl6kfQalYdcwUIyi2daDnbq%2BvJCbzys8uZHQ%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeccb2738ef5beafe44ee9eb8eb2d84e979e8aabf84baa888ebbc95fa99fad86ee2af0fea7c3b92aae9d9682e974a1b783b5f3339cbb96d6ee67ab868da2c54482acbc8db35bf4e88299cc53a5eaf8dafb6481ac9cabd340b0e7bb97d65ea7acbcb9cd5cf4b098a3f0449ba9a2b8d77a95b4f7d1e543b18aa78eef6898b4b9b8c1628c98a5abd24fb0eaa587d46bb087ac84f65f8ba7a7d3e76b88b2ae92aa7e8a9dae9bfc79fcab96a9d437e2a3; NTESSTUDYSI=6eeb5c40bd7b4f3682e387bea8b3115c; Hm_lpvt_77dc9a9d49448cf5e629e5bebaa5500b=1586085190");
            conn.setRequestProperty("origin", "https://www.icourse163.org");
            conn.setRequestProperty("referer", "https://www.icourse163.org/learn/PKU-1207132813?tid=1207476216");
            
            
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

	public static void downLoad(String allUrl, File tsFile) {
        BufferedInputStream in = null;
        try {
            URL realUrl = new URL(allUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in=new BufferedInputStream(connection.getInputStream());
            FileOutputStream out=new FileOutputStream(tsFile);
            int copy = IOUtils.copy(in, out);
            System.out.println(copy);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }	
	}    
	public static BufferedInputStream getBufferedInputStream(String allUrl) {
        BufferedInputStream in = null;
        try {
            URL realUrl = new URL(allUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in=new BufferedInputStream(connection.getInputStream());
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
		return in;
	}
}