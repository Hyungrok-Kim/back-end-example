package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.ajax.model.vo.Product;

/**
 * Servlet implementation class TestServlet6
 */
@WebServlet("/test6.do")
public class TestServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");   //객체단위로 데이터를 보낼거기 때문에 response도 UTF-8만
		
		List<Product> list = new ArrayList<>();
		
		// 상품 10개 
		
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
		
		String pnoList = request.getParameter("pnoList");
		
		String[] pno = pnoList.split(","); // 1,3,5 --> 1 / 3 / 5
		
		Product p = new Product();
		// "{ pno : " + p.getPno() + ", pname ; " + p.getPname() ... + "} ";
		JSONObject result = new JSONObject();
		JSONArray proArr = new JSONArray();
		
		for(int i = 0; i < pno.length; i++) {
			int idx = Integer.parseInt(pno[i]) - 1; // 1, 3, 5 --> 0, 2, 4
		
			p = list.get(idx);
			
			//System.out.println(idx + " : " + p);
			
			result = new JSONObject();
			result.put("pno", p.getPno());			// 자바 --> 자바스크립트화
			result.put("pname", p.getPname());		
			result.put("origin", p.getOrigin());
			result.put("type", p.getType());
			
			proArr.add(result);
			
			
		}
		// 배열 전달 전용 JSON 객체 
		JSONObject sendResult = new JSONObject();
		sendResult.put("list", proArr);
		
		response.setContentType("application/json; charset=UTF-8");
		
		response.getWriter().print(sendResult.toJSONString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
