fun main() {

    val system = CampusSystem()

    println("CAMPUS COMMUNITY MANAGEMENT SYSTEM")
    println("----------------------------------")

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