package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.model.BookDAO;

@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public ClientHomeServlet() {
		super();
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<Book> list = null;
		
		String keyword = request.getParameter("keyword"); //lấy keyword nếu có
		if(keyword != null && !keyword.isEmpty()) { //người dùng bấm tìm kiếm
			list = bookDAO.listAllBooks(keyword);
		}else {
			list = bookDAO.listAllBooks();
		}
		if (list.isEmpty()) {
			errors = "Không thể lấy dữ liệu";
		}

		//Lưu thông tin vào request attribute trước khi forward sang Views
		request.setAttribute("errors", errors);
		request.setAttribute("bookList", list);
		
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher("/views/clientHomeView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}