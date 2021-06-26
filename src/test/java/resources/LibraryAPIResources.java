package resources;

public enum LibraryAPIResources {
	
	addBookAPI("/Library/Addbook.php"),
	getBookByIdAPI("/Library/GetBook.php"),
	getBookByAuthorAPI("/Library/GetBook.php"),
	deleteBookAPI("/Library/DeleteBook.php");

	private String API_resource;
	
	LibraryAPIResources(String API_resource) {
		this.API_resource = API_resource;
	}
	
	public String getResource()
	{
		return API_resource;
	}

}
