package com.gz.lss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gz.lss.entity.BookInfo;

public class BookUtils {
	public static final int ALL = 0;
	public static final int AUTHOR = 1;
	public static final int BOOK = 2;
	private String anywords;
	private int searchType;
	private int page;
	private int resultPages;
	private String URL_PATH;

	public BookUtils(String url) {
		this.URL_PATH = url;
	}

	public String getHtml(String encoding) {

		InputStream inputStream = null;
		String html = null;

		try {
			URL url = new URL(URL_PATH);
			if (url != null) {
				try {
					HttpURLConnection httpURLConnection = (HttpURLConnection) url
							.openConnection();
					
					httpURLConnection.setConnectTimeout(3000);
					
					httpURLConnection.setRequestMethod("GET");

					int responsecode = httpURLConnection.getResponseCode();

					if (responsecode == HttpURLConnection.HTTP_OK) {
						inputStream = httpURLConnection.getInputStream();
						html = getStringByStream(inputStream, encoding);
					} else if (responsecode == 404){
						System.out.println("网络连接中断");
						return null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return html;
	}


	private String getStringByStream(InputStream is, String encoding)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				encoding));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}


	public List<BookInfo> getSearchBookMessage() {
		List<BookInfo> list = new ArrayList<BookInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		String html = getHtml("utf-8");
		if (html != null) {
			Document doc = Jsoup.parse(html);
			Elements trs = doc.getElementsByTag("tr");

			if (trs.size() == 0)
				return null;

			Elements option = doc.getElementsByTag("option");

			resultPages = Integer.parseInt(option.last().text());

			trs.remove(0);
			for (Element tr : trs) {
				Elements td = tr.getElementsByTag("td");
				String book_name = td.get(1).text();
				String author = td.get(2).text();
				String publish = td.get(3).text();
				String publish_year = td.get(4).text();
				String book_num = td.get(5).text();
				int holding_num = Integer.parseInt(td.get(6).text());
				int can_borrow = Integer.parseInt(td.get(7).text());
				int ctrlno = Integer.parseInt(td.get(0)
						.getElementsByTag("input").attr("value"));
				BookInfo book = new BookInfo(ctrlno, book_name, author, publish,
						publish_year, book_num, holding_num, can_borrow);
				list.add(book);
			}
		} else {
			list = null;
		}
		return list;
	}
	public int getResultPages() {
		return resultPages;
	}
}
