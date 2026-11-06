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
        println("Status: " + event.status)


    }
}