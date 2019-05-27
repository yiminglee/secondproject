package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import resource.LcsvDAO;
import resource.Lcsvbean;

@WebServlet("/webapp/insert.do")
public class control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, String> errorMessage;
	HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
//		System.out.println(submit);
		request.setCharacterEncoding("UTF-8");
		session=request.getSession();
		RequestDispatcher rd;
		errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		switch (submit) {
		case "insert":
			if (checkdetal(request)) {
//				System.out.println("success");
				if (insert(request)) {
					response.sendRedirect(response.encodeRedirectURL("InsertSuccess.jsp"));
					return;
				}
			}
			 rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
			
		case "delete":
			if(delete(request)) {
				response.sendRedirect(response.encodeRedirectURL("deletesuccess.jsp"));
				return;
			}
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;

		case "find":
			if(find(request)!=null) {
				response.sendRedirect(response.encodeRedirectURL("Findsuccess.jsp"));
				return;
			}
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		case "update":
			if (checkdetal(request)) {
				System.out.println("success");
				if (update(request)) {
					response.sendRedirect(response.encodeRedirectURL("UpdateSuccess.jsp"));
					return;
				}
			}
			 rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;		
			
			
			
		default:
			break;
		}

	}

	boolean checkdetal(HttpServletRequest request) throws UnsupportedEncodingException {
		session = request.getSession();
		Date createdate = null;
		try {
			createdate = Date.valueOf(request.getParameter("createdate"));
			System.out.println(createdate);
		} catch (IllegalArgumentException e) {
		}
		if (createdate==null||!request.getParameter("createdate").equals(createdate.toString())) {
			errorMessage.put("createdateeer", "此日期不存在");
		}
		String cmdname = request.getParameter("cmdname");
		cmdname = new String(cmdname.getBytes("ISO-8859-1"), "UTF-8");
		if(cmdname==null||cmdname.trim().length()==0) {
			errorMessage.put("cmdnameerr", "請輸入公司名稱");
		}
		String indcat = request.getParameter("indcat");
		indcat = new String(indcat.getBytes("ISO-8859-1"), "UTF-8");
		if(indcat==null||indcat.trim().length()==0) {
			errorMessage.put("indcaterr", "請輸入產業別");
		}
		String addr = request.getParameter("addr");
		addr = new String(addr.getBytes("ISO-8859-1"), "UTF-8");
		if(addr==null||addr.trim().length()==0) {
			errorMessage.put("addrerr", "請輸入地址");
		}
		String chairname = request.getParameter("chairname");
		chairname = new String(chairname.getBytes("ISO-8859-1"), "UTF-8");
		if(chairname==null||chairname.trim().length()==0) {
			errorMessage.put("chairnameerr", "請輸入董事長名稱");
		}
		if(request.getParameter("income")==null||request.getParameter("income").trim().length()==0) {
			errorMessage.put("incomeerr", "請輸入收益");
		}	
		if(!errorMessage.isEmpty()) {
			System.out.println("hava err");
			return false;
		}
		return true;
	}

	boolean insert(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		LcsvDAO dao = new LcsvDAO();
		Lcsvbean bean;
		try {

			dao.getConnection();
			bean = new Lcsvbean();
			Integer cmdid = Integer.parseInt(request.getParameter("cmdid"));
			
			String cmdname = request.getParameter("cmdname");
			cmdname = new String(cmdname.getBytes("ISO-8859-1"), "UTF-8");
			String indcat = request.getParameter("indcat");
			indcat = new String(indcat.getBytes("ISO-8859-1"), "UTF-8");
			String addr = request.getParameter("addr");
			addr = new String(addr.getBytes("ISO-8859-1"), "UTF-8");
			String chairname = request.getParameter("chairname");
			chairname = new String(chairname.getBytes("ISO-8859-1"), "UTF-8");
			Long income = Long.parseLong(request.getParameter("income"));
			Date createdate = Date.valueOf(request.getParameter("createdate"));
			bean.setCmdid(cmdid);
			bean.setCmdname(cmdname);
			bean.setIndcat(indcat);
			bean.setAddr(addr);
			bean.setChairname(chairname);
			bean.setIncome(income);
			bean.setCreatedate(createdate);
			if (dao.insert(bean) == 0) {
				return false;
			}
		} catch (SQLException e) {
			if (e.getMessage().indexOf("重複的索引鍵") != -1 
					|| e.getMessage().indexOf("Duplicate entry") != -1) {
					errorMessage.put("inserterr", "此公司代號資料已經存在");
				} else {
					errorMessage.put("inserterr", "資料庫存取錯誤:" + e.getMessage());
				}		
			return false;
		} finally {
			try {
				dao.closeConn();
			} catch (SQLException e) {
			}
		}
		session.setAttribute("Lcsvbean", bean);
		return true;

	}

	boolean delete(HttpServletRequest request) throws UnsupportedEncodingException {
		LcsvDAO dao = new LcsvDAO();
		try {
			dao.getConnection();
			Integer cmdid = Integer.parseInt(request.getParameter("cmdid"));
			if (dao.delete(cmdid) == 0) {
				errorMessage.put("deleteerr", "資料刪除失敗");
				return false;
			}	
		} catch (SQLException e) {
			errorMessage.put("deleteerr", "資料刪除失敗");
			return false;
			
		} finally {
			try {
				dao.closeConn();
			} catch (SQLException e) {
			}
		}
		session.setAttribute("deletesuccesscmdid", request.getParameter("cmdid"));
		return true;
	}

	Lcsvbean find(HttpServletRequest request) throws UnsupportedEncodingException {
		LcsvDAO dao = new LcsvDAO();
		Lcsvbean bean = null;
		try {
			dao.getConnection();
			Integer cmdid = Integer.parseInt(request.getParameter("cmdid"));
			bean = dao.findByEmpno(cmdid);
		} catch (SQLException e) {
			e.printStackTrace();
			errorMessage.put("finderr", "資料查詢失敗");
		} finally {
			try {
				dao.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(bean == null) {
			errorMessage.put("finderr", "資料不存在");
		}
		System.out.println(session);
		session.setAttribute("Lcsvbean", bean);
		return bean;
	}

	boolean update(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		LcsvDAO dao = new LcsvDAO();
		Lcsvbean bean;
		try {

			dao.getConnection();
			bean = new Lcsvbean();
			Integer cmdid = Integer.parseInt(request.getParameter("cmdid"));
			
			String cmdname = request.getParameter("cmdname");
			cmdname = new String(cmdname.getBytes("ISO-8859-1"), "UTF-8");
			String indcat = request.getParameter("indcat");
			indcat = new String(indcat.getBytes("ISO-8859-1"), "UTF-8");
			String addr = request.getParameter("addr");
			addr = new String(addr.getBytes("ISO-8859-1"), "UTF-8");
			String chairname = request.getParameter("chairname");
			chairname = new String(chairname.getBytes("ISO-8859-1"), "UTF-8");
			Long income = Long.parseLong(request.getParameter("income"));
			Date createdate = Date.valueOf(request.getParameter("createdate"));
			bean.setCmdid(cmdid);
			bean.setCmdname(cmdname);
			bean.setIndcat(indcat);
			bean.setAddr(addr);
			bean.setChairname(chairname);
			bean.setIncome(income);
			bean.setCreatedate(createdate);
			if (dao.update(bean) == 0) {
				errorMessage.put("updateerr", "資料更新失敗");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errorMessage.put("updateerr", "資料更新失敗");
			return false;
		} finally {
			try {
				dao.closeConn();
			} catch (SQLException e) {
			}
		}
		session.setAttribute("Lcsvbean", bean);
		return true;

	}	
	
}

