package com.kosta.mavenApp.section3;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kosta.model.dao.BoardService;
import com.kosta.model.dao.EMPService;
import com.kosta.model.dto.BoardVO;
import com.kosta.model.dto.EMPVO;

public class DBUsingTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di5.xml");
		
//		BoardService service = ctx.getBean("boardService", BoardService.class);
//		List<BoardVO> blist = service.select();
//		for(BoardVO b : blist) {
//			System.out.println(b);
//		}
		
		EMPService service2 = ctx.getBean("eservice", EMPService.class);
		List<EMPVO> elist = service2.selectAll();
		for(EMPVO e : elist) {
			System.out.println(e);
		}
	}

}
