package cn.study.lhzh.crawler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;

import cn.study.lhzh.crawler.bean.Chapter;
import cn.study.lhzh.crawler.bean.Course;
import cn.study.lhzh.crawler.bean.Lesson;
import cn.study.lhzh.crawler.bean.PostResult;
import cn.study.lhzh.crawler.bean.Result;
import cn.study.lhzh.crawler.bean.Term;
import cn.study.lhzh.crawler.bean.Unit;
import cn.study.lhzh.crawler.bean.Video;
import cn.study.lhzh.crawler.bean.VideoSignDto;
import cn.study.lhzh.http.HttpRequest;

/**
 * 爬虫爬取视频网站视频 1.获取视频的id 2.获取视频的playList.m3u8 3.解析获取其中的ts地址
 * 
 * @author 11875
 *
 */
public class CrawlerVideo {

	private static String baseFileFolder = "static/file/";

	static int i;
	// 单个字符的正则表达式
	private static final String singlePattern = "[0-9|a-f|A-F]";
	// 4个字符的正则表达式
	private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

	public static void main(String[] args) throws Exception {
		// String
		// allUrl="http://jdvodrvfb210d.vod.126.net/jdvodrvfb210d/nos/hls/2019/12/10/1215480494_3fd7d1e4de8248ab99a80fd56fff24e2_sd28.ts";
		// HttpRequest.downLoad(allUrl);
		// if(false) {
		CrawlerVideo cl = new CrawlerVideo();
		// 获取课程ID
		String url = "https://www.icourse163.org/course/PKU-1207130813";
		String html = cl.GetHTML(url);
		System.out.println(html);
		// 获取课程名称
		String courseName = getCourseNameByHtml(html);
		// 新建一个文件夹
		String filePath = System.getProperty("user.dir");
		File path = new File(filePath);
		File baseDirectory = new File(path, baseFileFolder);
		File toDirectory = new File(baseDirectory, courseName);
		boolean existDircetory = false;
		if (toDirectory.exists()) {
			existDircetory = true;
		} else {
			existDircetory = toDirectory.mkdirs();
		}
		System.out.println(toDirectory.getAbsolutePath());
		System.out.println(toDirectory.exists());
		// 获取课程的信息；可能有多个但是他们的termId是一样的
		List<Term> termList = getTermByHtml(html);
		// 根据tid获取每一集的id
		List<Unit> bizList = new ArrayList<>();
		for (Term term : termList) {
			bizList.addAll(getBizIdListByTid(term.getId()));
		}
		if (CollectionUtils.isNotEmpty(bizList)) {
			//
			for (Unit unit : bizList) {
				VideoSignDto videoSignDto = getVideoSignDtoByUnitId(unit.getId());
				String videoUrl = "https://vod.study.163.com/eds/api/v1/vod/video";
				String param = "videoId=" + videoSignDto.getVideoId() + "&signature=" + videoSignDto.getSignature()
						+ "&clientType=1";
				String sendPost = HttpRequest.sendPost(videoUrl, param);
				// PostResult postResult=JSON.parseObject(sendPost, PostResult.class);
				JSONObject parsePostResult = JSON.parseObject(sendPost);
				Object object = parsePostResult.get("result");
				JSONObject parseResult = JSON.parseObject(object.toString());
				Object videos = parseResult.get("videos");
				System.out.println(parseResult.get("name"));
				String videoName = parseResult.get("name").toString();
				List<Video> parseArray = JSON.parseArray(videos.toString(), Video.class);
				boolean flag = false;
				for (Video video : parseArray) {
					String baseUrl = video.getVideoUrl().substring(0, video.getVideoUrl().lastIndexOf("/"));
					System.out.println(video.getVideoUrl());
					List<String> tsList = new ArrayList<>();
					if (!flag && video.getQuality() == 1) {
						flag = true;
						System.out.println(
								"-----------------------------------------------------------------------------");
						String sendGet = HttpRequest.sendGet(video.getVideoUrl(), "");
						// System.out.println(sendGet);
						System.out.println(baseUrl);
						tsList = getTsList(sendGet);
					} else if (!flag && video.getQuality() == 2) {
						flag = true;
						System.out.println(
								"-----------------------------------------------------------------------------");
						String sendGet2 = HttpRequest.sendGet(video.getVideoUrl(), "");
						// System.out.println(sendGet);
						System.out.println(baseUrl);
						tsList = getTsList(sendGet2);

					} else if (!flag && video.getQuality() == 3) {
						flag = true;
						System.out.println(
								"-----------------------------------------------------------------------------");
						String sendGet3 = HttpRequest.sendGet(video.getVideoUrl(), "");
						// System.out.println(sendGet);
						System.out.println(baseUrl);
						tsList = getTsList(sendGet3);

					} else if (!flag && video.getQuality() == 4) {
						flag = true;
						System.out.println(
								"-----------------------------------------------------------------------------");
						String sendGet4 = HttpRequest.sendGet(video.getVideoUrl(), "");
						// System.out.println(sendGet);
						System.out.println(baseUrl);
						tsList = getTsList(sendGet4);
					}
					// 去下载ts文件
					toDownLoadTs(baseUrl, tsList, toDirectory, videoName);
				}

			}
			// }
			// }
		}
	}

	private static void toDownLoadTs(String baseUrl, List<String> tsList, File toDirectory, String videoName) {
		// File file=new File(toDirectory,videoName);
		for (String tsStr : tsList) {
			String allUrl = baseUrl + "/" + tsStr;
			File tsFile = new File(toDirectory, tsStr);
			HttpRequest.downLoad(allUrl, tsFile);
		}
	}

	private static void toDownLoadTsAll(String baseUrl, List<String> tsList, File toDirectory, String videoName) {
		File file = new File(toDirectory, videoName);

		List<BufferedInputStream> array = new ArrayList<BufferedInputStream>();
		for (String tsStr : tsList) {
			String allUrl = baseUrl + "/" + tsStr;
			File tsFile = new File(toDirectory, tsStr);
			BufferedInputStream bufferedInputStream = HttpRequest.getBufferedInputStream(allUrl);
			// HttpRequest.downLoad(allUrl, tsFile);
			array.add(bufferedInputStream);
		}
		Enumeration<BufferedInputStream> en = Collections.enumeration(array); // 枚举
		SequenceInputStream sis = new SequenceInputStream(en);
		toCreateMp4(sis, file);

	}

	private static void toCreateMp4(SequenceInputStream sis, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);// 指定目录创建合并的文件
			int num;
			byte[] bytes1 = new byte[1024];

			while ((num = sis.read(bytes1)) != -1) {
				fos.write(bytes1, 0, num);
				fos.flush();
			}
			fos.close();
			sis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从html源码(字符串)中去掉标题
	 * 
	 * @param htmlSource
	 * @return
	 */
	public static String getCourseNameByHtml(String htmlSource) {
		List<String> list = new ArrayList<String>();
		String title = "";

		// Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
		Pattern pa = Pattern.compile("<title>.*?</title>");// 源码中标题正则表达式
		Matcher ma = pa.matcher(htmlSource);
		while (ma.find())// 寻找符合el的字串
		{
			list.add(ma.group());// 将符合el的字串加入到list中
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return outTag(title);
	}

	/**
	 * 去掉html源码中的标签
	 * 
	 * @param s
	 * @return
	 */
	public static String outTag(String s) {
		return s.replaceAll("<.*?>", "");
	}

	private static List<String> getTsList(String sendGet) {
		String reg = ",[^`]*?\\.ts";
		List<String> tsList = new ArrayList<>();
		Matcher m = Pattern.compile(reg).matcher(sendGet);
		while (m.find()) {
			String info = m.group(0);
			tsList.add(info.substring(1));
			System.out.println(info);
			// String allUrl=baseUrl+"/"+info.substring(1);
			// HttpRequest.downLoad(allUrl);
		}
		return tsList;

	}

	private static VideoSignDto getVideoSignDtoByUnitId(String id) {
		String url = "https://www.icourse163.org/web/j/resourceRpcBean.getResourceToken.rpc?csrfKey=b2dc9c47194e4e97a711b7bde5e281b2";
		String param = "bizId=" + id + "&bizType=1&contentType=1";
		String sendPost = HttpRequest.sendMyPost(url, param);
		// System.out.println(sendPost);
		PostResult parseObject = JSON.parseObject(sendPost, PostResult.class);
		// System.out.println(parseObject);
		// System.out.println(parseObject.getResult());
		Result result = JSON.parseObject(parseObject.getResult().toString(), Result.class);
		// System.out.println(result.getVideoSignDto().toString());
		VideoSignDto videoSignDto = result.getVideoSignDto();
		return videoSignDto;
	}

	private static List<Unit> getBizIdListByTid(String tid) throws Exception {
		String url = "https://www.icourse163.org/dwr/call/plaincall/CourseBean.getLastLearnedMocTermDto.dwr";
		String param = "callCount=1&scriptSessionId=${scriptSessionId}190&httpSessionId=b2dc9c47194e4e97a711b7bde5e281b2&c0-scriptName=CourseBean&c0-methodName=getLastLearnedMocTermDto&c0-id=0&c0-param0=number:"
				+ tid + "&batchId=1585985756309";
		String sendPost = HttpRequest.sendPost(url, param);
		String strNeed = removeHeadAndLast(sendPost);
		// System.out.println(strNeed);
		List<Unit> bizList = getBizIdList(strNeed);
		return bizList;
	}

	private static String removeHeadAndLast(String sendPost) {
		String reg = "var[^`]*;dwr";
		Matcher m = Pattern.compile(reg).matcher(sendPost);
		String info = null;
		while (m.find()) {
			info = m.group(0);
		}
		return info.substring(0, info.length() - 3);
	}

	private static List<Unit> getBizIdList(String data) throws Exception {
		List<Unit> bizList = new ArrayList<>();
		// System.out.println(unicodeToCn(data));
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		engine.eval(unicodeToCn(data));
		// javax.script.Invocable 是一个可选的接口
		// 检查你的script engine 接口是否已实现!
		// 注意：JavaScript engine实现了Invocable接口
		// Invocable inv = (Invocable) engine;
		// 获取我们想调用那个方法所属的js对象 8
		Object obj = engine.get("s0");
		// System.out.println(JSON.toJSONString(obj));
		Course c = JSON.parseObject(JSON.toJSONString(obj), Course.class);
		Map<String, Chapter> chapters = c.getChapters();
		Set<Entry<String, Chapter>> entrySet = chapters.entrySet();
		Iterator<Entry<String, Chapter>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Chapter chapter = iterator.next().getValue();
			// System.out.println(chapter);
			Map<String, Lesson> lessons = chapter.getLessons();
			Set<Entry<String, Lesson>> lessonSet = lessons.entrySet();
			Iterator<Entry<String, Lesson>> lessonIterator = lessonSet.iterator();
			while (lessonIterator.hasNext()) {
				Entry<String, Lesson> next = lessonIterator.next();
				Lesson value = next.getValue();
				// System.out.println(value);
				Map<String, Unit> units = value.getUnits();
				Set<Entry<String, Unit>> unitSet = units.entrySet();
				Iterator<Entry<String, Unit>> unitIterator = unitSet.iterator();
				while (unitIterator.hasNext()) {
					Entry<String, Unit> unitEntry = unitIterator.next();
					Unit unit = unitEntry.getValue();
					// System.out.println(unit);
					bizList.add(unit);
				}
			}

		}

		// 执行obj对象的名为hello的方法
		// inv.invokeMethod(obj, "hello", "Script Method !!" );
		return bizList;
	}

	/**
	 * 字符串中，所有以 \\u 开头的UNICODE字符串，全部替换成汉字
	 * 
	 * @param strParam
	 * @return
	 */
	public static String unicodeToCn(String str) {
		// 用于构建新的字符串
		StringBuilder sb = new StringBuilder();
		// 从左向右扫描字符串。tmpStr是还没有被扫描的剩余字符串。
		// 下面有两个判断分支：
		// 1. 如果剩余字符串是Unicode字符开头，就把Unicode转换成汉字，加到StringBuilder中。然后跳过这个Unicode字符。
		// 2.反之， 如果剩余字符串不是Unicode字符开头，把普通字符加入StringBuilder，向右跳过1.
		int length = str.length();
		for (int i = 0; i < length;) {
			String tmpStr = str.substring(i);
			if (isStartWithUnicode(tmpStr)) { // 分支1
				sb.append(ustartToCn(tmpStr));
				i += 6;
			} else { // 分支2
				sb.append(str.substring(i, i + 1));
				i++;
			}
		}
		return sb.toString();
	}

	/**
	 * 把 \\u 开头的单字转成汉字，如 \\u6B65 -> 步
	 * 
	 * @param str
	 * @return
	 */
	private static String ustartToCn(final String str) {
		StringBuilder sb = new StringBuilder().append("0x").append(str.substring(2, 6));
		Integer codeInteger = Integer.decode(sb.toString());
		int code = codeInteger.intValue();
		char c = (char) code;
		return String.valueOf(c);
	}

	/**
	 * 字符串是否以Unicode字符开头。约定Unicode字符以 \\u开头。
	 * 
	 * @param str
	 *            字符串
	 * @return true表示以Unicode字符开头.
	 */
	private static boolean isStartWithUnicode(final String str) {
		if (null == str || str.length() == 0) {
			return false;
		}
		if (!str.startsWith("\\u")) {
			return false;
		}
		// \u6B65
		if (str.length() < 6) {
			return false;
		}
		String content = str.substring(2, 6);

		boolean isMatch = Pattern.matches(pattern, content);
		return isMatch;
	}

	private static List<Term> getTermByHtml(String html) {
		String reg = "window.termInfoList = \\[[^`]*?\\]";
		Matcher m = Pattern.compile(reg).matcher(html);
		String info = null;
		while (m.find()) {
			// System.out.println(m.end());
			// System.out.println(m.group(0));
			info = m.group(0);
		}
		System.out.println(info);

		String[] split = info.split("=");
		List<Term> parseArray = JSON.parseArray("[" + split[1].substring(2, split[1].length() - 1) + "]", Term.class);
		return parseArray;
	}

	// 获取所有uuu9妹子采访的集合
	// "http://dota2.uuu9.com/mm/01/"
	// "http://dota2.uuu9.com/mm/19/"
	// "http://dota2.uuu9.com/zt/guo1mini/"
	// "http://dota2.uuu9.com/zt/qingsi/"
	private static TreeSet<String> GteURLs(CrawlerVideo cl, String str) {
		String html = cl.GetHTML(str);
		TreeSet<String> ts = new TreeSet<String>();
		String reg = "http://dota2.uuu9.com/\\w{2}/\\w+/";
		Matcher m = Pattern.compile(reg).matcher(html);
		while (m.find()) {
			String urlo = m.group();
			// System.out.println(urlo);
			ts.add(urlo);
		}
		return ts;
	}

	// <title>精选cosplay:千娇百媚的小黑你爱哪款_现场实拍_游久网DOTA2.UUU9.COM</title>
	// 获取网页标题
	private String GetName(String html) {
		int start = html.indexOf("<title>");
		int end = html.indexOf("</title>");
		String title = html.substring(start + 7, end);
		// 女解说嘤嘤：波多野结衣是对我颜值的认可 - 浮梦第二十期
		// 女解说嘤嘤："波多野结衣"是对我颜值的认可 - 浮梦第二十期
		// 精选cosplay:千娇百媚的小黑你爱哪款_现场实拍_游久网DOTA2.UUU9.COM
		return title.replace(":", "").replace("\"", "");
	}

	// 3、下载图片到相应的文件夹中
	private void DownLoad(TreeSet<String> picsrc, File file) {

		if (!file.exists()) {
			file.mkdirs();
		}
		URLConnection conn;
		File fileadd;
		FileOutputStream fos = null;
		int len;
		byte[] b = new byte[1024];
		System.out.println(file.toString() + "开始下载");
		for (String pics : picsrc) {
			try {
				System.out.println(" 下载中..." + pics);
				fileadd = new File(file, i + ".jpg");
				fos = new FileOutputStream(fileadd);
				conn = new URL(pics).openConnection();
				InputStream in = conn.getInputStream();
				while ((len = in.read(b)) != -1) {
					fos.write(b, 0, len);
				}
				i++;
			} catch (IOException e) {
				throw new RuntimeException("图片下载失败");
			} finally {
				try {
					if (fos != null)
						fos.close();
				} catch (IOException e) {
					throw new RuntimeException("写出图片流关闭失败");
				}
			}

		}
		System.out.println("图片下载完成");

	}

	// 2、从网页源码中获取图片地址存入ArrayList中
	private TreeSet<String> GetPicSrc(String html, String picreg) {
		TreeSet<String> arr = new TreeSet<String>();
		Pattern p = Pattern.compile(picreg);
		Matcher m = p.matcher(html);
		while (m.find()) {
			String pic = m.group();
			// System.out.println(pic);
			arr.add(pic);
		}
		return arr;
	}

	// 1、由网址获取网页源码String
	private String GetHTML(String url) {
		URL u;
		URLConnection ur;
		BufferedReader bufr;
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			u = new URL(url);
			ur = u.openConnection();
			bufr = new BufferedReader(new InputStreamReader(ur.getInputStream()));

		} catch (IOException e) {
			throw new RuntimeException("连接不上所给的网址");
		}

		try {
			while ((line = bufr.readLine()) != null) {
				// System.out.println(line);
				sb.append(line);
				sb.append("\r\n");
			}
		} catch (IOException e) {
			throw new RuntimeException("读取网页源码失败");
		}

		return sb.toString();
	}

}
