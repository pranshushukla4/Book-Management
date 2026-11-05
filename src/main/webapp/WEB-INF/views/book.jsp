<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Books - Book Search</title>
</head>
<body>

    <h2>BOOKS</h2>
    <h2>Book Search</h2>

    <c:if test="${not empty errorMessage}">
        <div style="color:red; background:#ffe6e6; padding:10px; margin:10px 0;">
            ${errorMessage}
        </div>
    </c:if>

    <c:if test="${not empty successMessage}">
        <div style="color:green; background:#e6ffe6; padding:10px; margin:10px 0;">
            ${successMessage}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/books" method="get">

        <label>Book Type</label>

        <select name="category" required>
            <option value="BIOLOGY" ${search.category == 'BIOLOGY' ? 'selected' : ''}>biology</option>
            <option value="SOCIAL" ${search.category == 'SOCIAL' ? 'selected' : ''}>social</option>
            <option value="COMPUTER_SCIENCE" ${search.category == 'COMPUTER_SCIENCE' ? 'selected' : ''}>computer science</option>
            <option value="MATHEMATICS" ${search.category == 'MATHEMATICS' ? 'selected' : ''}>mathematics</option>
        </select>

        <label>Book ID</label>
        <input type="text" name="bookCode" value="${search.bookCode}">

        <label>Book Name</label>
        <input type="text" name="title" value="${search.title}">

        <button type="submit">Search</button>

        <a href="${pageContext.request.contextPath}/books">
            <button type="button">Reset</button>
        </a>

    </form>

    <h3>BOOK INFO</h3>

    <table border="1" cellpadding="8">
        <thead>
            <tr>
                <th>Book Type</th>
                <th>Book ID</th>
                <th>Book Name</th>
                <th>Price</th>
                <th>Comments</th>
                <th>No. of Copies</th>
                <th>Do you want to Buy?</th>
                <th>Add one more copy of Book</th>
				<th>Delete Book</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.category}</td>
                    <td>${book.bookCode}</td>
                    <td>${book.title}</td>
                    <td>${book.price}</td>
                    <td>${book.comments}</td>
                    <td>${book.copies}</td>

                    <td>
						<form action="${pageContext.request.contextPath}/books/${book.id}/buy"
						      method="post">

						    <input type="hidden" name="category" value="${search.category}">
						    <input type="hidden" name="bookCode" value="${search.bookCode}">
						    <input type="hidden" name="title" value="${search.title}">

						    <button type="submit">Buy</button>
						</form>
                    </td>

                    <td>
						<form action="${pageContext.request.contextPath}/books/${book.id}/add-copy"
						      method="post">

						    <input type="hidden" name="category" value="${search.category}">
						    <input type="hidden" name="bookCode" value="${search.bookCode}">
						    <input type="hidden" name="title" value="${search.title}">

						    <button type="submit">Add one more copy</button>
						</form>
                    </td>
					
					<td>
						<form action="${pageContext.request.contextPath}/books/${book.id}/delete"
						      method="post"
						      onsubmit="return confirm('Are you sure you want to delete this book?');">

						    <input type="hidden" name="category" value="${search.category}">
						    <input type="hidden" name="bookCode" value="${search.bookCode}">
						    <input type="hidden" name="title" value="${search.title}">

						    <button type="submit">Delete</button>
						</form>
					</td>
					
                </tr>
            </c:forEach>

            <c:if test="${empty books}">
                <tr>
                   <td colspan="9">No books found.</td>
                </tr>
            </c:if>

        </tbody>
    </table>

    <br>

    <a href="${pageContext.request.contextPath}/books/create">
        <button type="button">Create</button>
    </a>

</body>
</html>