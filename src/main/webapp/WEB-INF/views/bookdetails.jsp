<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Books - Book Details</title>
</head>

<body>

    <h2>BOOKS</h2>
    <h2>Book Details</h2>

    <c:if test="${not empty errorMessage}">
        <div style="color:red; background:#ffe6e6; padding:10px; margin:10px 0;">
            ${errorMessage}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/books/create"
          method="post">

        <fieldset>
            <legend>Book Details</legend>

            <table cellpadding="8">

                <tr>
                    <td>Book type</td>
                    <td>Book ID</td>
                    <td>Book Name</td>
                    <td>Price</td>
                    <td>Comments</td>
                    <td>No of Copies</td>
                </tr>

                <tr>
                    <td>
                        <select name="category" required>
                            <option value="BIOLOGY"
                                ${book.category == 'BIOLOGY' ? 'selected' : ''}>BIOLOGY</option>
                            <option value="SOCIAL"
                                ${book.category == 'SOCIAL' ? 'selected' : ''}>SOCIAL</option>
                            <option value="COMPUTER_SCIENCE"
                                ${book.category == 'COMPUTER_SCIENCE' ? 'selected' : ''}>Computer Science</option>
                            <option value="MATHEMATICS"
                                ${book.category == 'MATHEMATICS' ? 'selected' : ''}>MATHEMATICS</option>
                        </select>
                    </td>

                    <td>
                        <input type="text" name="bookCode"
                               value="${book.bookCode}" required>
                    </td>

                    <td>
                        <input type="text" name="title"
                               value="${book.title}" required>
                    </td>

                    <td>
                        <input type="number" name="price"
                               value="${book.price}" step="0.01" min="0" required>
                    </td>

                    <td>
                        <input type="text" name="comments"
                               value="${book.comments}" required>
                    </td>

                    <td>
                        <input type="number" name="copies"
                               value="${book.copies}" min="0" required>
                    </td>
                </tr>

            </table>
        </fieldset>

        <button type="submit">Submit</button>

        <a href="${pageContext.request.contextPath}/books">
            <button type="button">Cancel</button>
        </a>

    </form>

</body>
</html>