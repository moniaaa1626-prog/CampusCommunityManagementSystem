class Reservation(
    val reservationId: String,
    val student: Student,
    val event: Event,
    var status: String
) {
}