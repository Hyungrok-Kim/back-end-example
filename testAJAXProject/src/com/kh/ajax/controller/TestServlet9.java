package com.kh.ajax.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Product;

/**
 * Servlet implementation class TestServlet9
 */
@WebServlet("/test9.do")
public class TestServlet9 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet9() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		Map<String, Product> map = new HashMap<>();
		
		
		// 상품 10개 
		
		map.put("왕눈이 깜찍이",	new Product(1, "왕눈이깜찍이", "국내산", "음료"));
		map.put("밭두렁",	new Product(2, "밭두렁", "국내산", "과자류"));
		map.put("신호등",	new Product(3, "신호등", "국내산", "사탕"));
		map.put("차카니",	new Product(4, "차카니", "인도네시아", "과자류"));
		map.put("짝꿍",	new Product(5, "짝꿍", "국내산", "캔디류"));
		map.put("아폴로",	new Product(6, "아폴로", "중국산" ,"과자류"));
		map.put("꾀돌이",	new Product(7, "꾀돌이", "국내산" , "과자류"));
		map.put("쫀득이",	new Product(8, "쫀득이", "말레이시아", "과자류"));
		map.put("맥주사탕",	new Product(9, "맥주사탕", "중국산" ,"캔디류"));
		map.put("라면땅",	new Product(10, "라면땅", "인도네시아", "과자류"));
		
		new Gson().toJson(map, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
