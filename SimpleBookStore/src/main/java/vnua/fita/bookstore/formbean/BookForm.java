package vnua.fita.bookstore.formbean;

import java.util.ArrayList;
import java.util.List;

import vnua.fita.bookstore.utils.Constant;

public class BookForm {
	private String bookId;
	private String title;
	private String author;
	private String price;
	private String quantityInStock;
	
	public BookForm(String bookId, String title, String author, String price,
			String quantityInStock) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}


	public BookForm(String title, String author, String price, String quantityInStock) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}



	public String getBookId() {
		return bookId;
	}



	public void setBookId(String bookId) {
		this.bookId = bookId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getQuantityInStock() {
		return quantityInStock;
	}



	public void setQuantityInStock(String quantityInStock) {
		this.quantityInStock = quantityInStock;
	}



	// validate(Errors)
		public List<String> validateCreateBookForm() {
			List<String> errors = new ArrayList<String>();
			if (title == null || title.trim().isEmpty()) {
				errors.add(Constant.TITLE_EMPTY_VALIDATE_MSG);
			}
			if (author == null || author.trim().isEmpty()) {
				errors.add(Constant.AUTHOR_EMPTY_VALIDATE_MSG);
			}
			if (price == null || price.trim().isEmpty()) {
				errors.add(Constant.PRICE_EMPTY_VALIDATE_MSG);
			} else {
				try {
					Integer.parseInt(price);
				} catch (NumberFormatException e) {
					errors.add(Constant.PRICE_INVALID_VALIDATE_MSG);
				}
			}
			if (quantityInStock == null || quantityInStock.trim().isEmpty()) {
				errors.add(Constant.QUANTITY_IN_STOCK_EMPTY_VALIDATE_MSG);
			} else {
				try {
					Integer.parseInt(quantityInStock);
				} catch (NumberFormatException e) {
					errors.add(Constant.QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG);
				}
			}
			return errors;
		}
		
		public List<String> validateEditBookForm() {
			List<String> errors = new ArrayList<String>();
			if (title == null || title.trim().isEmpty()) {
				errors.add(Constant.TITLE_EMPTY_VALIDATE_MSG);
			}
			if (author == null || author.trim().isEmpty()) {
				errors.add(Constant.AUTHOR_EMPTY_VALIDATE_MSG);
			}
			if (bookId == null || bookId.trim().isEmpty()) {
				errors.add(Constant.BOOK_ID_EMPTY_VALIDATE_MSG);
			} else {
				try {
					Integer.parseInt(bookId);
				} catch (NumberFormatException e) {
					errors.add(Constant.BOOK_ID_INVALID_VALIDATE_MSG);
				}
			}
			if (price == null || price.trim().isEmpty()) {
				errors.add(Constant.PRICE_EMPTY_VALIDATE_MSG);
			} else {
				try {
					Integer.parseInt(price);
				} catch (NumberFormatException e) {
					errors.add(Constant.PRICE_INVALID_VALIDATE_MSG);
				}
			}
			if (quantityInStock == null || quantityInStock.trim().isEmpty()) {
				errors.add(Constant.QUANTITY_IN_STOCK_EMPTY_VALIDATE_MSG);
			} else {
				try {
					Integer.parseInt(quantityInStock);
				} catch (NumberFormatException e) {
					errors.add(Constant.QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG);
				}
			}
			return errors;
		}
}