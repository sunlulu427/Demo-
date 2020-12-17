data class ApplyEvent(val money: Int, val title: String)

interface ApplyHandler {
    val successor: ApplyHandler?
    fun handleEvent(event: ApplyEvent)
}

class GroupLeader(override val successor: ApplyHandler?): ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 100 -> println("Group leader handled application: ${event.title}")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("Group Leader: This application can not be handled.")
            }
        }
    }
}

class President(override val successor: ApplyHandler?): ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 500 -> println("President handled application: ${event.title}")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("President: This application can not be handled.")
            }
        }
    }
}

class College(override val successor: ApplyHandler?): ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money > 1000 -> println("College: This application is refused.")
            else -> println("College handled application ${event.title}.")
        }
    }
}