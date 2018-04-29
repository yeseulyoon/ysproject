package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Data;
import model.PeopleDAO;
import model.SongDAO;
import model.domain.PeopleVO;
import model.domain.SongVO;
public class Controller extends HttpServlet {
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String command = request.getParameter("command");
      try{
			if(command.equals("insert")){//회원가입정보저장
				clientInsert(request, response);
			}else if(command.equals("login")){//로그인
				clientLogin(request, response);
			}else if(command.equals("star")){//목차보여주기
				showContent(request, response);
			}else if(command.equals("bysong")){//노래이름 검색으로 목차보여주기
				showSongListBySongName(request, response);
			}else if(command.equals("byartist")){//아티스트 검색으로 목차보여주기
				showSongListByArtist(request, response);
			}else if(command.equals("bygenre")){//장르별 검색으로 목차보여주기
				showSongListByGenre(request, response);
			}else if(command.equals("datahandling")){//처리된 데이터를 부르는 메소드
				selectGenre(request, response);
			}
      }catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("jsonHandling.jsp").forward(request, response);
			s.printStackTrace();
      }
   }
   
   public void selectGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   try {
		request.setAttribute("data", SongDAO.selectGenre());
		request.getRequestDispatcher("jsonHandling.jsp").forward(request, response);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
   }
   public void clientInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int x= PeopleDAO.joinCheck(request.getParameter("id"));
	   request.setAttribute("joincheck", x);
	   request.getRequestDispatcher("joinAction.jsp").forward(request, response);
	}
     
   public void clientLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int x =PeopleDAO.login(request.getParameter("loginid"), request.getParameter("loginpw"));
	   request.setAttribute("login", x);
       request.getRequestDispatcher("loginAction.jsp").forward(request, response);
   }
   
   public void noshowContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.getRequestDispatcher("noshowContent.jsp").forward(request, response);
   }
   
   public void showContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.getRequestDispatcher("showContent.jsp").forward(request, response);
   }
   public void showSongListBySongName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String url = "showError.jsp";
	   String text = request.getParameter("text");
	   try {
		ArrayList<SongVO> x1 = SongDAO.selectSongsBySongName(text);
		request.setAttribute("songList", x1);
		System.out.println(x1);
	    url = "showSongListBySongName.jsp";
	   } catch (SQLException s) {
		   request.setAttribute("errorMsg", s.getMessage());
		   s.printStackTrace();
	   }	
	   request.getRequestDispatcher(url).forward(request, response);
   }
   
   public void showSongListByArtist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String url = "showError.jsp";
	   String text = request.getParameter("text");
	   try {
		ArrayList<SongVO> x2 = SongDAO.selectSongsByArtist(text);
		request.setAttribute("songList", x2);
	    url = "showSongListBySongName.jsp";
	   } catch (SQLException s) {
		   request.setAttribute("errorMsg", s.getMessage());
		   s.printStackTrace();
	   }	
	   request.getRequestDispatcher(url).forward(request, response);
   }
   
   public void showSongListByGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String url = "showError.jsp";
	   String text = request.getParameter("text");
	   try {
		ArrayList<SongVO> x3 = SongDAO.selectSongsByGenre(text);
		request.setAttribute("songList", x3);
	    url = "showSongListBySongName.jsp";
	   } catch (SQLException s) {
		   request.setAttribute("errorMsg", s.getMessage());
		   s.printStackTrace();
	   }	
	   request.getRequestDispatcher(url).forward(request, response);
   }
}