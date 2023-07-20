package com.gitOsca.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gitOsca.member.controller.LoginOkController;
import com.gitOsca.member.controller.SignUpOkController;
import com.gitOsca.Result;
import com.gitOsca.member.controller.AuthenticationNumberController;
import com.gitOsca.member.controller.CheckEmailOkController;
import com.gitOsca.member.controller.FindPasswordController;
import com.gitOsca.member.controller.GotoMainController;
//import com.gitOsca.member.controller.MyPageOkController;
import com.gitOsca.member.controller.SwitchAccountResutPageController;
import com.gitOsca.member.controller.findAccountOkController;

public class MemberFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String target = req.getRequestURI().replace(req.getContextPath() + "/", "").split("\\.")[0];
		Result result = null;
		System.out.println("멤버 프론트 콘트롤러");
//		System.out.println(target);
		// ---------------------------------- 김동엽 ------------------------------------
		if ( target.equals("sign_infind_account") ) {
			// 계정 찾기 페이지로 이동
			result = new Result();
			result.setPath("templates/findAccount/find-account.jsp");
		} else if ( target.equals("get_authentication_number") ) {
			// 인증번호 받기
			result = new AuthenticationNumberController().execute(req, resp);
		} else if ( target.equals("sign_infind_account_next") ) {
			// 계정을 찾은 페이지로 갈지 못 찾은 페이지로 갈지 분기처리
			result = new SwitchAccountResutPageController().execute(req, resp);
		} else if ( target.equals("find_account") ) {
			// 계정 찾기
			result = new findAccountOkController().execute(req, resp);
		} else if ( target.equals("go_to_main") ) {
			// 메인으로 가기 전에 req초기화
			result = new GotoMainController().execute(req, resp);
		} else if ( target.equals("find_paassword") ) {
			result = new FindPasswordController().execute(req, resp);
		} // -------------------------------- 김동엽 ----------------------------
		
		else if (target.equals("mypageOk")) {
			//���������� ������
//			result = new MyPageOkController().execute(req, resp);
		} 	
		// -------------------------------- 선희원 ----------------------------
		else if(target.equals("emailCheck")){
		System.out.println("멤버 프론트 콘트롤러 이멜 쳌");
		result = new CheckEmailOkController().execute(req,resp);
	}
		else if ( target.equals("password") ) {
			System.out.println("비번입력 쳌");
			result = new Result();
			result.setPath("templates/login/password.jsp");
		} 	
		else if ( target.equals("signUp") ) {
			System.out.println("signUp 쳌");
			result = new Result();
			result.setPath("templates/signUp/signUp.jsp");
		} 
		else if(target.equals("LoginOk")) {
			System.out.println("loginOk 쳌");
			result = new LoginOkController().execute(req, resp);
		}
		else if(target.equals("loginSucess")) {
			System.out.println("loginSucess 쳌 메인페이지로 이동");
			result = new Result();
			result.setPath("/templates/mainPage/mainPage.jsp");
		}
		else if(target.equals("SignUpOk")) {
			System.out.println("SignUpOk 쳌");
			result = new SignUpOkController().execute(req, resp);
		}		
		else if(target.equals("SignUpSucess")) {
			System.out.println("SignUpSucess 쳌 로긴페이지로 이동");
			result = new Result();
			result.setPath("templates/login/login.jsp");
		}
		
		// ---------------------------------- 선희원 ------------------------------------

		if (result != null) {
			if (result.isRedirect()) {
				resp.sendRedirect(result.getPath());
			} else {
				req.getRequestDispatcher(result.getPath()).forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
