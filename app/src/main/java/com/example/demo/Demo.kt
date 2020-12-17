import java.lang.IllegalArgumentException

data class ApplyEvent(val money: Int, val title: String)

class PartialFunction<in P1, out R>(
    private val definedAt: (P1) -> Boolean,
    private val f: (P1) -> R
): (P1) -> R {
    override fun invoke(p1: P1): R {
        if (definedAt(p1)) {
            return f(p1)
        }
        throw IllegalArgumentException("Value: $p1 isn't supported by this function")
    }

    fun isDefinedAt(p1: P1) = definedAt(p1)
}

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it)}) {
        when {
            this.isDefinedAt(it) -> this(it)
            else -> that(it)
        }
    }
}
// 自运行lambda
val groupLeader = {
    val definedAt: (ApplyEvent) -> Boolean = { it.money <= 200 }
    val handler: (ApplyEvent) -> Unit = {
        println("Group Leader handled application: ${it.title}")
    }
    PartialFunction(definedAt, handler)
}()

val president = {
    val definedAt: (ApplyEvent) -> Boolean = { it.money <= 500 }
    val handler: (ApplyEvent) -> Unit = {
        println("President handled application: ${it.title}")
    }
    PartialFunction(definedAt, handler)
}()

val college = {
    val definedAt: (ApplyEvent) -> Boolean = { true }
    val handler: (ApplyEvent) -> Unit = {
        when {
            it.money > 1000 -> println("College: This application is refused.")
            else -> println("College handled application ${it.title}")
        }
    }
    PartialFunction(definedAt, handler)
}()

val applyChain = groupLeader orElse president orElse college

fun main() {
    applyChain(ApplyEvent(600, "hold a debate match."))
}