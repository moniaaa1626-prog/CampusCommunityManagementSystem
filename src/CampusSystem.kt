class CampusSystem {
    val students = mutableListOf<Student>()
    val administrators = mutableListOf<Administrator>()
    val events = mutableListOf<Event>()
    val reservations = mutableListOf<Reservation>()

    init {
        students.add(
            Student(
                "aisha", "student123", "S001", "Aisha Khan", "aisha.khan@solent.ac.uk",
                "Computing")
        )
        students.add(
            Student("daniel", "Student123", "S002", "Daniel Green", "daniel.green@solent.ac.uk",
                "Business Management")
        )
        students.add(
            Student("maya", "Student123", "S004","Maya Patel", "maya.patel@solent.ac.uk",
                "Software Engineering")
        )
        students.add(
            Student("omar", "Student123", "S005", "Omar Hassan", "omar.hass@solent.ac.uk",
                "Mathematics")
        )
        students.add(
            Student("lucas", "Student123", "S006", "Lucas Martin", "lucas.martin@solent.ac.uk",
                "Digital Marketing")
        )
        students.add(
            Student("emily","Student123","S007", "Emily Carter", "emily.carter@solent.ac.uk",
                "Psychology")
        )

        events.add(
            Event("E001", "Python Coding Workshop", "Academic", "Helen Moore", "14 Oct 2026", "14:00",
                "Computing Lab B12", 6,
                "Upcoming")
        )

        events.add(
            Event("E002", "Five-a-Side Football", "Sports", "Alex Turner", "16 Oct 2026", "17:00",
                "Sports Hall", 4, "Upcoming")
        )

        events.add(
            Event("E003", "Careers In Technology", "Careers", "Rachel Adams", "20 Oct 2026", "13:00",
                "Lecture Hall 2", 6, "Upcoming")
        )
        events.add(
            Event("E004", "Student Wellbeing Workshop", "Wellbeing", "Sarah Lewis", "05 Oct 2026", "11:00",
                "Room C05", 6, "Completed")
        )
        events.add(
            Event("E005", "Community Volunteering Day", "Volunteering", "James Wilson", "02 Oct 2026", "13:00",
                "Community Hub", 6, "Completed")
        )
        events.add(
            Event("E006", "International Student Social", "Social", "Maria Costa", "18 Oct 2026", "18:00",
                "Student Union", 5, "Cancelled")
        )
        reservations.add(
            Reservation("R001", students[0], events[0], "Active")
        )
        reservations.add(
            Reservation("R002", students[1], events[0], "Active")
        )
        reservations.add(
            Reservation("R003", students[2], events[0], "User Cancelled")
        )
        reservations.add(
            Reservation("R004", students[0], events[1], "Active")
        )
        reservations.add(
            Reservation("R005", students[1], events[1], "Active")
        )
        reservations.add(
            Reservation("R006", students[3], events[1], "Active")
        )
        reservations.add(
            Reservation("R007", students[4], events[1], "Active")
        )
        reservations.add(
            Reservation("R008", students[5], events[2], "Active")
        )
        reservations.add(
            Reservation("R009", students[0], events[3], "Completed")
        )
        reservations.add(
            Reservation("R010", students[2], events[3], "Completed")
        )
        reservations.add(
            Reservation("R011", students[3], events[4], "Completed")
        )
        reservations.add(
            Reservation("R012", students[4], events[4], "User Cancelled")
        )
        reservations.add(
            Reservation("R013", students[1], events[5], "Event Cancelled")
        )
        reservations.add(
            Reservation("R014", students[5], events[5], "Event Cancelled")
        )
    }
}
