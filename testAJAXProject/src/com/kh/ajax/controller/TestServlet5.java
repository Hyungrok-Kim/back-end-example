package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.ajax.model.vo.Product;

/**
 * Servlet implementation class TestServlet5
 */
@WebServlet("/test5.do")
public class TestServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 상품 목록 준비하기 
		List<Product> list = new ArrayList<>();
		
		// 상품 10 등록하기 
		list.add(new Product(1, "왕눈이깜찍이", "국내산", "음료"));
		list.add(new Product(2, "밭두렁", "국내산", "과자류"));
		list.add(new Product(3, "신호등", "국내산", "사탕"));
		list.add(new Product(4, "차카니", "인도네시아", "과자류"));
		list.add(new Product(5, "짝꿍", "국내산", "캔디류"));
		list.add(new Product(6, "아폴로", "중국산" ,"과자류"));
		list.add(new Product(7, "꾀돌이", "국내산" , "과자류"));
		list.add(new Product(8, "쫀득이", "말레이시아", "과자류"));
		list.add(new Product(9, "맥주사탕", "중국산" ,"캔디류"));
		list.add(new Product(10, "라면땅", "인도네시아", "과자류"));
		
		int idx = Integer.parseInt(request.getParameter("pno")) - 1;
		Product p = list.get(idx);
		
		//response.getWriter().print(p);
		
		// 자바 객체는 JS객체로 직접 변환할 수 없기 때문에 
		// 라이브러리를 통해 { key : value } 형식의 문자열로 
		// 치환해야만 한다.
		JSONObject result = new JSONObject();
		
		result.put("pno", p.getPno());
		result.put("pname", p.getPname());
		result.put("origin", p.getOrigin());
		result.put("type", p.getType());
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(result.toJSONString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
