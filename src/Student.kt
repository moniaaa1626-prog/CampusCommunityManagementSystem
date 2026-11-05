class Student(
    username: String,
    password: String,
    val studentId: String,
    val fullName: String,
    val email: String,
    val course: String,
) : User(username, password, "Student"){
}