@file:Suppress("UNUSED_PARAMETER", "UNREACHABLE_CODE")

package fr.xebia.xke

/**
 * Properties
 *
 * var name: Type = ... // read-write
 *
 * val name: Type = ... // read-only once initialized
 *
 * const val name: Type = ... // when value is known at compile-time
 *
 * val x = 10 // Type is optional when it can be inferred
 */
// TODO initialize this value to "xebia.fr"
val host: String = "xebia.fr"

/**
 * Kotlin has common types like Boolean, Int, Long, Float, Double, ...
 *
 * Kotlin has NO primitive types like in Java (int, long, ...).
 */
// TODO initialize this value to default https port
val port: Int = 443

/**
 * Kotlin has String-templates for property interpolation.
 *
 * "Hello $userName, today is ${localDate.format(...)}"
 */
// TODO initialize this url to xebia's blog URL using string template (specify https port)
val url: String = "https://blog.$host:${port}"

/**
 * Functions... are fun!
 *
 * fun name(argName: ArgType): ReturnType = ... // function with an expression body
 *
 * fun name(argName: ArgType): ReturnType {...} // function with a body
 *
 * fun name(argName: ArgType) {...} // function with no return (same as returning Unit)
 */
// TODO return true if given url is secured (use function expression short syntax)
fun isSecured(url: String): Boolean = if (url.startsWith("https")) true else false

/**
 * Conditional expressions
 *
 * val result = if (...) someValue else if (...) otherValue else defaultValue
 */
// TODO use conditional expression to compute factorial of n
fun factorial(n: Int): Int = if (n <= 1) 1 else factorial(n-1)*n

/**
 * When expressions
 *
 * when {
 *   x < 0 -> -x
 *   x = 0 -> 0
 *   else -> x
 * }
 */
// TODO use when expression to compute a message expressing duration in seconds, minutes, hours or days
fun remainingTime(durationInSeconds: Int): String = when {
    durationInSeconds < 60 -> "$durationInSeconds second(s)"
    durationInSeconds < 3600 -> "${durationInSeconds / 60} minute(s)"
    durationInSeconds < 86400 -> "${durationInSeconds / 3600} hour(s)"
    else -> "${durationInSeconds / 86400} day(s)"
}

/**
 * Lambdas
 *
 * An anonymous function can be typed and declared using following syntax:
 *
 * val isStrictlyPositive : (Int) -> Boolean = { it -> it > 0 }
 *
 * Lambdas can of course be passed as function parameters
 *
 * names.filter({ it -> it.name == searchedName })
 *
 */
// TODO define a lambda returning true when its parameter is pair, false otherwise
val pair: (Int) -> Boolean = { it % 2 == 0 }


/**
 * When lambda has only one parameter it can be ommited and 'it' is the default name:
 *  { it > 0 }
 * When lambda has more than one parameters you must give them proper names:
 *  { name, age -> "$name is $age year(s) old" }
 */
// TODO define a lambda returning the product of its two arguments
val product: (Int, Int) -> Int = { a, b -> a * b }


/**
 * Type checks and Smart Casts
 *
 * We can check whether an object conforms to a given type by using the 'is' operator or its negated form '!is'.
 * It allows using smart casts (no cast in branches where type can be guessed)
 *
 * fun size(any: Any): Int = if (any is String) any.length else if (any is List<*>) any.size else -1
 */
interface Price

class StandardPrice(val value: Int) : Price

class PromotionalPrice(val value: Int, val discount: Int) : Price

// TODO return value for standard prices and (value - discount) for promotional prices, otherwise 0
fun computePrice(price: Price): Int = if (price is PromotionalPrice) price.value - price.discount else if (price is StandardPrice) price.value else 0


/**
 * Null references
 *
 * Kotlin references are not-null by default
 * To allow null references one must suffix type with '?'
 *
 * val name: String? = null
 *
 * For strings it is possible to use for example toIntOrNull to convert a String to an Integer
 *
 */
// TODO return last url parameter converted to an Int or null if param is not found or not a valid number
fun convertURLParam(url: String): Int? {
    var split = url.split("?")
    if (split.size == 1)
        return null;
    var params = split[1].split("&")
    var str = params.last().split("=")
    return str[1].toIntOrNull()
}

/**
 * Null references: Elvis operator
 *
 * To avoid super classic if (.. != null) structures Kotlin has built-in Elvis operator
 *  val user : User? = ...
 *  val city: String = user?.address?.city ?: "Unknown"
 */
// TODO return price.value.toLong(), mind references nullability, 0 if all references are null
fun convertPriceToLong(price: StandardPrice?): Long {
    return price?.value?.toLong() ?: 0L
}


/**
 * Ranges
 *
 * You can generate ranges with '..' operator
 *  val oneToTen: IntRange = 1..10
 *
 * You can also generate ranges using any range type (IntRange, LongRange, ClosedRange<T>, ...)
 *  val oneToTen: IntRange = IntRange(1, 10)
 */
// TODO generate a list of integers from 1 to max number using ranges
fun generateIntegerList(max: Int): List<Int> = IntRange(1, max).toList()

/**
 * Ranges: Progressions
 *
 * Ranges rely on *Progression and Kotlin provide useful operations on *Progressions like steps
 */
// TODO generate a range from 0 to max and sum all numbers which are dividers of 3
fun sumSequenceNumbers(max: Int): Int = IntRange(0, max).filter { it % 3 == 0}.sum()


/**
 * Loops
 *
 * for loop iterates through anything that provides an iterator:
 *  for (item in items) { ... }
 *
 * Ranges can also be used in for loops:
 *  for (i in 1..10) { ... }
 *  for (i in 1..10 step 2) { ... }
 *  for (i in 10 downTo 1) { ... }
 *  for (i in 10 downTo 1 step 2) { ... }
 */
// TODO compute prices total amount, if 2 or more prices are promotional prices, apply a 5.0 discount to total amount
fun computeTotalPrice(prices: List<Price>): Int {
    val amount = prices.map {
        if (it is PromotionalPrice) it.value - it.discount
        else if (it is StandardPrice) it.value else 0
    }.sum()

    val promo = prices.filter { it is PromotionalPrice } .count();

    return if (promo >= 2) amount - 5 else amount
}
