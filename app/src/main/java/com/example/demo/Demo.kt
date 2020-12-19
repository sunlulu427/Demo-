import java.lang.IllegalArgumentException

data class ApplyEvent(val money: Int, val title: String)

class PartialFunction<in P1, out R>(
    private val definedAt: (P1) -> Boolean,
    private val f: (P1) -> R
) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (definedAt(p1)) {
            return f(p1)
        }
        throw IllegalArgumentException("Value: $p1 isn't supported by this function")
    }

    fun isDefinedAt(p1: P1) = definedAt(p1)
}

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
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
    val machine = WaterMachine()
    waterMachineOps(machine, Moment.INSTANCE_NOODLES)
    waterMachineOps(machine, Moment.AFTER_WORK)
    waterMachineOps(machine, Moment.DRINKING_WATER)
}

class WaterMachine {
    var state: WaterMachineState
    var off = Off(this)
    var heating = Heating(this)
    var colling = Colling(this)

    init {
        this.state = off
    }

    fun turnHeating() {
        this.state.turnHeating()
    }

    fun turnColling() {
        this.state.turningColling()
    }

    fun turnOff() {
        this.state.turnOff()
    }
}

sealed class WaterMachineState(open val machine: WaterMachine) {
    fun turnHeating() {
        if (this !is Heating) {
            println("turn heating")
        } else {
            println("The state is already heating mode.")
        }
    }

    fun turningColling() {
        if (this !is Colling) {
            println("turn colling")
        } else {
            println("The state is already colling state.")
        }
    }

    fun turnOff() {
        if (this !is Off) {
            println("turn off")
        } else {
            println("The state is already off")
        }
    }
}

class Off(override val machine: WaterMachine) : WaterMachineState(machine)

class Heating(override val machine: WaterMachine) : WaterMachineState(machine)

class Colling(override val machine: WaterMachine) : WaterMachineState(machine)

enum class Moment {
    EARLY_MORNING, // 早上上班
    DRINKING_WATER, // 日常饮水
    INSTANCE_NOODLES, // Shaw 吃泡面
    AFTER_WORK
}

fun waterMachineOps(machine: WaterMachine, moment: Moment) {
    when (moment) {
        Moment.AFTER_WORK,
        Moment.DRINKING_WATER -> when (machine.state) {
            !is Colling -> machine.turnColling()
        }
        Moment.INSTANCE_NOODLES -> when (machine.state) {
            !is Heating -> machine.turnHeating()
        }
        Moment.AFTER_WORK -> when (machine.state) {
            !is Off -> machine.turnOff()
        }
        else -> Unit
    }
}