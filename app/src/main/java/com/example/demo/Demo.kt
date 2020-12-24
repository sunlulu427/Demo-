import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Collectors.toList
import java.util.stream.Stream

data class ApplyEvent(val money: Int, val title: String)

class PartialFunction<in P1, out R>(
    val definedAt: (P1) -> Boolean,
    private val f: (P1) -> R
) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (definedAt(p1)) {
            return f(p1)
        }
        throw IllegalArgumentException("Value: $p1 isn't supported by this function")
    }
}

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction({ this.definedAt(it) || that.definedAt(it) }) {
        when {
            this.definedAt(it) -> this(it)
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

suspend fun searchItemOne(): String {
    delay(1000L)
    return "item-one"
}

suspend fun searchItemTwo(): String {
    delay(1000L)
    return "item-two"
}

suspend fun search() {
    delay(1000L)
    println("World!")
}

interface Kind<out F, out A>

interface Functor<F> {
    fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
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

interface MacBook {
    fun getCost(): Int
    fun getDesc(): String
    fun getProdDate(): String
}

class MacBookPro: MacBook {
    override fun getCost() = 10000
    override fun getDesc() = "MacBookPro"
    override fun getProdDate() = "Late 2011"
}

// 装饰类
class ProcessorUpgradeMacBookPro(val macBook: MacBook): MacBook by macBook {
    override fun getCost() = macBook.getCost() + 219
    override fun getDesc() = macBook.getDesc() + ", +1G Memory"
}

sealed class Format
data class Print(val text: String): Format()
object NewLine: Format()

val string = listOf<Format>(Print("Hello"), NewLine, Print("Kotlin"))

fun unsafeInterpreter(str: List<Format>) {
    str.forEach {
        when (it) {
            is Print -> print(it.text)
            is NewLine -> println()
        }
    }
}

fun stringInterpreter(str: List<Format>) = str.fold("") { fulltext, s ->
    when (s) {
        is Print -> fulltext + s.text
        is NewLine -> fulltext + "\n"
    }
}

class Shop {
    val goods = hashMapOf<Long, Int>()

    init {
        goods[1] = 10
        goods[2] = 15
    }

    @Synchronized fun buyGoods(id: Long) {
        val stock = goods.getValue(id)
        goods[id] = stock - 1
    }

    fun buyGoods2(id: Long) {
        synchronized(this) {
            val stock = goods.getValue(id)
            goods[id] = stock - 1
        }
    }

    var lock = ReentrantLock()
    fun butGoods3(id: Long) {
        lock.lock()
        try {
            val stock = goods.getValue(id)
            goods[id] = stock - 1
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            lock.unlock()
        }
    }
}

fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        lock.unlock()
    }
    return action()
}


data class Goods(val id: Long, val name: String, val stock: Int)
data class Address(val userId: Long, val location: String)

@RequiresApi(Build.VERSION_CODES.N)
fun doOrder(goods: Goods, address: Address): CompletableFuture<Long> { // 进行下单操作
    return CompletableFuture.supplyAsync {
        Thread.sleep(1000)              // 模拟IO操作
        1L
    }
}
@RequiresApi(Build.VERSION_CODES.N)
fun main() {

}

val threadCount = Runtime.getRuntime().availableProcessors()
val threadPoolExecutor = Executors.newFixedThreadPool(threadCount) // 线程池
val scheduler = Schedulers.from(threadPoolExecutor)

fun getGoodsFromDB(goodsId: Long): Observable<Goods> {
    return Observable.defer {
        Thread.sleep(1000)
        Observable.just(Goods(goodsId, "深入Kotlin", 10))
    }
}

fun getAddressFromDB(userId: Long): Observable<Address> {
    return Observable.defer {
        Thread.sleep(1000)
        Observable.just(Address(userId, "杭州"))
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun rxOrder(goodsId: Long, userId: Long) {
    var goods: Goods? = null
    var address: Address? = null

    val goodsF: Observable<Goods> = getGoodsFromDB(1).subscribeOn(scheduler)
    val addressF = getAddressFromDB(1).subscribeOn(scheduler)

    Observable.merge(goodsF, addressF).subscribe( {
        when (it) {
            is Goods -> goods = it
            is Address -> address = it
        }
    }, {}, {
        doOrder(goods!!, address!!)
    })
}

