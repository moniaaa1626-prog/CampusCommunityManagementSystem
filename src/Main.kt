fun main() {

    val system = CampusSystem()


    while (true) {
        println()
        println("===== MAIN MENU =====")
        println("1. View and Search Events")
        println("2. Make Event Reservation")
        println("3. View and Cancel My Reservation")
        println("0. Exit")

        println("Choose an option: ")
        val choice = readln()

        println("CAMPUS COMMUNITY MANAGEMENT SYSTEM")
        println("----------------------------------")

        when (choice) {

            "0" -> {
                println("Goodbye!")
                return
            }
            "1" -> {


                for (event in system.events) {
                    println("------------------------------")
                    println("Event ID: " + event.eventId)
                    println("Title: " + event.title)
                    println("Category: " + event.category)
                    println("Organiser: " + event.organiser)
                    println("Date: " + event.date)
                    println("Time: " + event.time)
                    println("Location: " + event.location)
                    println("Capacity: " + event.maxCapacity)

                    val booked = system.reservations.count {
                        it.event.eventId == event.eventId &&
                                (it.status == "Active" || it.status == "Completed")
                    }
                    val available = event.maxCapacity - booked

                    println("Booked places: " + booked)
                    println("Available places: " + available)


                    if (event.status == "Upcoming" && available == 0) {
                        println("Status: Full")
                    } else {
                        println("Status: " + event.status)
                    }
                }



                println()
                print("Search by tittle, category, organiser or date: ")
                val search = readln()

                var found = false

                for (event in system.events) {
                    if (
                        event.eventId.equals(search, ignoreCase = true) ||
                        event.title.contains(search, ignoreCase = true) ||
                        event.category.contains(search, ignoreCase = true) ||
                        event.organiser.contains(search, ignoreCase = true) ||
                        event.date.contains(search, ignoreCase = true)
                    ) {


                        println("Found: " + event.eventId + " - " + event.title)
                        found = true
                    }
                }

                if (!found) {
                    println("No matching events found.")

                }


                var selectedEvent: Event? = null

                while (selectedEvent == null) {
                    print("Enter event Id to view details: ")
                    val selectedId = readln()

                    selectedEvent = system.events.find {
                        it.eventId.equals(selectedId, ignoreCase = true)
                    }

                    if (selectedEvent == null) {
                        println("Event ID does not exist. Please try again.")

                    }

                }
                if (selectedEvent != null) {

                    val booked = system.reservations.count {
                        it.event.eventId == selectedEvent.eventId &&
                                (it.status == "Active" || it.status == "Completed")
                    }

                    val available = selectedEvent.maxCapacity - booked


                    println("Category: ${selectedEvent.category}")
                    println("Organiser: ${selectedEvent.organiser}")
                    println("Date: ${selectedEvent.date}")
                    println("Time: ${selectedEvent.time}")
                    println("Location: ${selectedEvent.location}")
                    println("Maximum capacity: ${selectedEvent.maxCapacity}")
                    println("Booked places: $booked")
                    println("Available places: $available")

                    if (selectedEvent.status == "Upcoming" && available == 0) {
                        println("Status: Full")
                    } else {
                        println("Status: ${selectedEvent.status}")
                    }
                } else {
                    println("No matching events found.")
                }

                val reservedPlaces = system.reservations.count {
                    it.event.eventId == selectedEvent.eventId &&
                            (it.status == "Active" || it.status == "Completed")
                }
                val remainingPlaces = selectedEvent.maxCapacity - reservedPlaces


                if (selectedEvent.status != "Upcoming") {
                    println("This event is not available for reservation.")

                } else if (remainingPlaces <= 0) {
                    println("This event is full. No places available")

                } else {
                    println()
                    println("Make event reservation")


                    var selectedStudent: Student? = null

                    while (selectedStudent == null) {
                        print("Enter your Student ID: ")
                        val studentId = readln()

                        selectedStudent = system.students.find {
                            it.studentId.equals(studentId, ignoreCase = true)
                        }

                        if (selectedStudent == null) {
                            println("Student Id does not exist. Please try again.")
                        } else {
                            println("Student found:  ${selectedStudent.fullName}")

                            val alreadyReserved = system.reservations.any {
                                it.student.studentId == selectedStudent.studentId &&
                                        it.event.eventId == selectedEvent.eventId &&
                                        it.status == "Active"
                            }
                            if (alreadyReserved) {
                                println("You already have reservation for this event. ")
                            } else {
                                println("You can make reservation for this event.")


                                val reservationId = "R%03d".format(
                                    (system.reservations.mapNotNull {
                                        it.reservationId.removePrefix("R").toIntOrNull()
                                    }.maxOrNull() ?: 0) + 1
                                )

                                val newReservation = Reservation(
                                    reservationId,
                                    selectedStudent,
                                    selectedEvent,
                                    "Active"

                                )

                                system.reservations.add(newReservation)

                                println("Reservation created successfully.")
                                println("Reservation ID: $reservationId")

                                println("*********************************")
                                println("CAMPUS COMMUNITY")
                                println("EVENT RESERVATION")
                                println("Student: ${selectedStudent.fullName}")
                                println("Student ID: ${selectedStudent.studentId}")
                                println("Event: ${selectedEvent.title}")
                                println("Category: ${selectedEvent.category}")
                                println("Date: ${selectedEvent.date}")
                                println("Time: ${selectedEvent.time}")
                                println("Location: ${selectedEvent.location}")
                                println("Reservation ID: $reservationId")
                                println("**********************************")


                            }
                        }
                    }


                }
            }

            "3" -> {
                println("===== MY RESERVATION =====")
                println("Enter your Student ID: ")

                val studentId = readln()

                val student = system.students.find {
                    it.studentId.equals(studentId, ignoreCase = true)

                }

                if (student == null) {
                    println("Student ID does not exist. Please try again.")
                } else {
                    println("Student found:  ${student.fullName}")

                    val studentReservations = system.reservations.filter {
                        it.student.studentId == student.studentId
                    }

                    if (studentReservations.isEmpty()) {
                        println("You have no reservations.")
                    } else {
                        println("====== Your Reservations ======")

                        for (reservation in studentReservations) {
                            println("------------------------------")
                            println("Reservation ID: ${reservation.reservationId}")
                            println("Event: ${reservation.event.title}")
                            println("Date: ${reservation.event.date}")
                            println("Time: ${reservation.event.time}")
                            println("Status: ${reservation.status}")


                        }
                        println()
                        print("Enter Reservation ID to cancel (or ) to go back): ")
                        val cancelId = readln()

                        if (cancelId == "0") {
                            println("No reservation cancelled")
                        } else {
                            val reservationToCancel = studentReservations.find {
                                it.reservationId.equals(cancelId, ignoreCase = true)
                            }

                            if (reservationToCancel == null) {
                                println("Reservation ID not found for this student.")
                            } else {
                                println("Reservation found: ${reservationToCancel.event.title}")
                                println("Current status: ${reservationToCancel.status}")

                                if (reservationToCancel.status != "Active" ||
                                    reservationToCancel.event.status != "Upcoming"
                                ) {

                                    println("This reservation cannot be cancel")

                                } else {
                                    print("Are you sure you want to cancel this reservation? (Y/N): ")
                                    val confirm = readln()

                                    if (confirm.equals("Y", ignoreCase = true)) {
                                        reservationToCancel.status = "User Cancelled"
                                        println("Reservation cancelled successfully.")
                                    } else {
                                        println("Cancellation not completed.")
                                    }

                                }


                            }

                        }
                    }
                }

            }
        }

    }
}