class CampusSystem {
    val students = mutableListOf<Student>()
    val administrators = mutableListOf<Administrator>()
    val events = mutableListOf<Event>()
    val reservations = mutableListOf<Reservation>()

    init {
        events.add(
            Event(

                "E001",
                "Python Coding Workshop",
                "Academic",
                "Helen Moore",
                "14 Oct 2026",
                "14:00",
                "Computing Lab B12",
                6,
                "Completed"
            )
        )

        events.add(
            Event(
                "E002",
                "Five-a-Side Football",
                "Sport",
                "Alex Turner",
                "16 Dec 2026",
                "17:00",
                "Sports Hall",
                4,
                "Upcoming"
            )
        )

        events.add(
            Event(
                "E003",
                "Careers In Technology",
                "Careers",
                "Rachel Adams",
                "20 Dec 2026",
                "13:00",
                "Lecture Hall 2",
                6,
                "Upcoming"
            )
        )
        events.add(
            Event(
                "E004",
                "Student Wellbeing Workshop",
                "Wellbeing",
                "Sarah Lewis",
                "05 Dec 2026",
                "11:00",
                "Room C05",
                6,
                "Upcoming"
            )
        )
        events.add(
            Event(
                "E005",
                "Community Volunteering Day",
                "Volunteering",
                "James Wilson",
                "05 Oct 2026",
                "13:00",
                "Community Hub",
                6,
                "Completed"
            )
        )
        events.add(
            Event(
                "E006",
                "International Student Social",
                "Social",
                "Maria Costa",
                "20 Oct 2026",
                "18:00",
                "Student Union",
                5,
                "Cancelled"
            )
        )
    }
}
