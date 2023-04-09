<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid bg-light myBorder">
    <form action="/student/search" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="text" placeholder="Name" name="name" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group ">
                    <input type="number" placeholder="Entry Year" name="EntryYear" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="Age" name="age" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="Grade" name="grade" class="form-control">
                </div>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary btn-block" target=" ">Search</button>
            </div>
        </div>
    </form>
</div>
<div><br></div>

<div class="container-fluid bg-light myBorder">
    <h4>Students:</h4>
    <table class="table table-bordered table-striped text-center">
        <thead>
        <tr class="text-center">
            <%--<th><b>Student Id</b></th>--%>
            <th class="text-center"><b>Full Name</b></th>
            <th class="text-center"><b>BirthDate</b></th>
            <th class="text-center"><b>Entry Year</b></th>
            <th class="text-center"><b>Age</b></th>
            <th class="text-center"><b>Grade</b></th>
            <th class="text-center" colspan='2'><b> Operation</b></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="student">
            <tr>
                <%--<td><c:out value="${student.studentId}"></c:out></td>--%>
                <td><c:out value="${student.name}"></c:out></td>
                <td><c:out value="${student.birthDate}"></c:out></td>
                <td><c:out value="${student.entryYear}"></c:out></td>
                <td><c:out value="${student.age}"></c:out></td>
                <td><c:out value="${student.grade}"></c:out></td>
                <td>
                    <a href="/student/edit?studentId=${student.studentId}">
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </a>

                    <a href="/student/delete?studentId=${student.studentId}">
                        <button type="submit" class="btn btn-danger"
                                onclick="alert(' !! Alarm !! Student data will be deleted.')">Delete
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</div>
<div><br></div>
<jsp:include page="footer.jsp"></jsp:include>