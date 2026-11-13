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
            event.date.contains(search, ignoreCase = true))  {


            println("Found: " + event.eventId + " - " + event.title)
            found = true
        }
    }

    if (!found) {
        println("No matching events found.")

    }
    println()
    print("Enter event ID to view details: ")
    val selectedId = readln()

    val selectedEvent = system.events.find {
        it.eventId.equals(selectedId, ignoreCase = true)

    }
     if (selectedEvent != null) {

         val booked = system .reservations.count {
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

         if (selectedEvent.status =="Upcoming" && available == 0) {
             println("Status: Full")
         } else {
             println("Status: ${selectedEvent.status}")
         }
     } else {
         println("No matching events found.")
     }

}