package app

class Event (
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
    ) {



    val durationOfEvent: String
        get() = if (this.durationInMinutes < 60) {
            "short"
        } else {
            "long"
        }
}