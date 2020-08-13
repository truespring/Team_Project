import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Naver {
	
	public static void main(String[] args) {
		ArrayList<Cartoon> list = get_cartoonList("https://comic.naver.com/webtoon/weekday.nhn");		//현재 연재중인 네이버 웹툰 리스트  
		ArrayList<Cartoon> list_finish = get_cartoonList("https://comic.naver.com/webtoon/finish.nhn");	//완결 네이버 웹툰 리스트 (최초 한번만 불러오면 됨) 
		
		
		list.addAll(list_finish);
		
		for(Cartoon c : list) {
			System.out.println("제목: " + c.getTitle());
			System.out.println("작가: " + c.getWriter());
			System.out.println("스토리: " + c.getStory());
			System.out.println("이미지: " + c.getImg());
			System.out.println("링크: " + c.getLink());
			System.out.println("장르: " + c.getGenre());
			System.out.println();
			System.out.println("----------------------------------------------");
			System.out.println();
		}
	}
	
	//각 url에서 상세정보 리스트로 저장
	public static ArrayList<Cartoon> get_cartoonList(String url) {
		
		ArrayList<String> list = gethref(url);
		ArrayList<Cartoon> cartoon_list = new ArrayList<Cartoon>();
				
		try {
		for(String u : list) {
			
			Connection conn = Jsoup.connect("https://comic.naver.com" + u);
			String img="",title="",writ="",wri_d="", wri_s="", story="",genre="";
			Cartoon car = new Cartoon();
			
			Document html = conn.get();
			Element comicinfo = html.getElementsByClass("comicinfo").first();
			Element img_tag = comicinfo.getElementsByTag("img").first();
			Element detail = comicinfo.getElementsByClass("detail").first();
			
			img = img_tag.attr("src").toString();
			title = detail.getElementsByTag("h2").first().toString().split("<")[1].split("> ")[1];
			writ = detail.getElementsByClass("wrt_nm").first().text();
			if(writ.contains("/")) {
				wri_s = writ.split("/")[0];
				wri_d = writ.split("/ ")[1];
			}else {
				wri_s = writ;
				wri_d = writ;
			}
			
			story = detail.getElementsByTag("p").first().text();
	
			genre = detail.getElementsByClass("genre").first().text();
		
			car.setLink("https://comic.naver.com" + u);
			car.setTitle(title);
			car.setImg(img);
			car.setWriter(wri_d);
			car.setWriter(wri_s);
			car.setStory(story);
			car.setGenre(genre);
		
			cartoon_list.add(car);
		}
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return cartoon_list;
	}
	
	
	//네이버 웹툰 메인화면에서 웹툰 상세페이지 url 따오기
	public static ArrayList<String> gethref(String u){
		ArrayList<String> list = new ArrayList<String>();
		
		try{
			Connection conn = Jsoup.connect(u);	
			
			Document html = conn.get();
			
			Elements list_area = html.getElementsByClass("list_area");	//class=list_area 부분 Element집합
			
			Elements thumb = list_area.first().getElementsByClass("thumb"); //class=thumb 부분 Element 집합
			
			for(Element t : thumb) {
				String href = t.getElementsByTag("a").attr("href");	//각 a태그 href 속성값을 리스트에 저장ㅇㅇㅇ  
				list.add(href);
			}
		}catch(Exception e) {
		
		}
		return list;
	}
	
}
