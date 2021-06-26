Feature: Validation of Library API's 

@AddBook
Scenario Outline: Verify if Book is being successfully added using AddBookAPI
	Given AddBookAPI payload with "<name>"  "<isbn>" "<aisle>" "<author>"
	When user hits "addBookAPI" with "Post" http method
	Then The API call got success with status code 200
	And "Msg" in Response body is "successfully added"
	And "ID" in Response body is "Dem4011"
	
Examples:
		|name		|isbn		|aisle	|author			|
		|Demo		|Dem		|4011	|Charles Lec	|

@AddBook		
Scenario Outline: Verify the Error message, If we add the same book using AddBookAPI
	Given AddBookAPI payload with "<name>"  "<isbn>" "<aisle>" "<author>"
	When user hits "addBookAPI" with "Post" http method
	Then The API call got success with status code 404
	And "msg" in Response body is "Add Book operation failed, looks like the book already exists"
	
Examples:
		|name		|isbn		|aisle	|author			|
		|Demo		|Dem		|4011	|Charles Lec	|

@getBookByAuthor		
Scenario: Verify if Book is being successfully retrived using getBookByAuthor
	Given GetBookByAuhtorAPI payload with "Charles Lec"
	When user hits "getBookByAuthorAPI" with "Get" http method
	Then The API call got success with status code 200
	And "book_name" in Response body is "Demo"
	And "isbn" in Response body is "Dem"
	And "aisle" in Response body is "4011"

@getBookByAuthor	
Scenario: Verify the Error message, if we pass the wrong Author Name to GetBookByAuthorAPI
	Given GetBookByAuhtorAPI payload with "Anbu Selvan"
	When user hits "getBookByAuthorAPI" with "Get" http method
	Then The API call got success with status code 404
	And "msg" in Response body is "The book by requested bookid / author name does not exists!"

@getBookByID	
Scenario: Verify if Book is being successfully retrived using getBookByIDAPI
	Given GetBookByIdAPI payload with "Dem4011"
	When user hits "getBookByAuthorAPI" with "Get" http method
	Then The API call got success with status code 200
	And "book_name" in Response body is "Demo"
	And "isbn" in Response body is "Dem"
	And "aisle" in Response body is "4011"
	And "author" in Response body is "Charles Lec"

@getBookByID	
Scenario: Verify the Error message, if we pass the wrong Author Name to GetBookByAuthorAPI
	Given GetBookByIdAPI payload with "Dem0011"
	When user hits "getBookByAuthorAPI" with "Get" http method
	Then The API call got success with status code 404
	And "msg" in Response body is "The book by requested bookid / author name does not exists!"

@deleteBookByID	
Scenario: Verify if Book is being successfully deleted using DeleteBookByIdAPI
	Given DeleteBookByIdAPI payload with "Dem4011"
	When user hits "deleteBookAPI" with "Delete" http method
	Then The API call got success with status code 200
	And "msg" in Response body is "book is successfully deleted"
	
@deleteBookByID
Scenario: Verify the Error message, If we delete the same book using DeleteBookByIdAPI
	Given DeleteBookByIdAPI payload with "Dem4011"
	When user hits "deleteBookAPI" with "Delete" http method
	Then The API call got success with status code 404
	And "msg" in Response body is "Delete Book operation failed, looks like the book doesnt exists"
	
	
