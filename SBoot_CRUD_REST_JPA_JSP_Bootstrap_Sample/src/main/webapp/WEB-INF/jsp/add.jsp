<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container myBorder">
    <form action='/student/add' method='post' modelAttribute="add">
        <h4> Add New Student Form:</h4>
        <table class='table table-hover table-responsive table-bordered rounded-lg'>
            <tr>
                <td><b>Name</b></td>
                <td><input type='text' name='name' class='form-control' size="50" required value="${student.name}"/>
                </td>
            </tr>
            <tr>
                <td><b>BirthDate</b></td>
                <td><input type='date' name='birthDate' class='form-control' required size="20"
                           value="${student.birthDate}"/></td>

            </tr>
            <tr>
                <td><b>Age</b></td>
                <td><input type='number' name='age' class='form-control' required value="${student.age}"/></td>
            </tr>
            <tr>
                <td><b>entry Year</b></td>
                <td><input type='number' name='entryYear' class='form-control' min="1300" max="1402" required
                           value="${student.entryYear}"/></td>
            </tr>
            <tr>
                <td><b>grade</b></td>
                <td><input type='number' name='grade' class='form-control' required min="1" max="20" size="20"
                           value="${student.grade}"/></td>

            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" class="btn btn-primary">Save</button>
                </td>
            </tr>

        </table>
    </form>
</div>

<jsp:include page="footer.jsp"></jsp:include>