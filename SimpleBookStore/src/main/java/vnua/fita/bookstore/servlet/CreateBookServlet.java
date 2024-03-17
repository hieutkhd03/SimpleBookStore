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
import vnua.fita.bookstore.formbean.BookForm;
import vnua.fita.bookstore.model.BookDAO;

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public CreateBookServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/Views/createBookView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String priceStr = request.getParameter("price");
		String quantityInStockStr = request.getParameter("quantityInStock");

		BookForm bookForm = new BookForm(title, author, priceStr, quantityInStockStr);
		List<String> errors = bookForm.validateCreateBookForm();
		if (errors.isEmpty()) {
			int price = Integer.parseInt(priceStr);
			int quantityInStock = Integer.parseInt(quantityInStockStr);
			Book book = new Book(title, author, price, quantityInStock);

			boolean insertResult = bookDAO.insertBook(book);
			if (!insertResult) {
				errors.add("Thêm sách không thành công");
			} else {
				response.sendRedirect(request.getContextPath() + "/adminHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("book", bookForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createBookView.jsp");
			rd.forward(request, response);
		}
	}

}