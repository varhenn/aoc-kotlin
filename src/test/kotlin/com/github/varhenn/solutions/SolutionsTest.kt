package com.github.varhenn.solutions

import com.github.varhenn.common.solutions.Solution
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SolutionsTest {
    @Test
    fun `validate solutions for days 1 to 24`() {
        val packageName = "com.github.varhenn.solutions.year2024"
        val dayPrefix = "Day"

        for (year in 2024..2024) {
            for (day in 1..24) {
                val dayClassName = "$packageName.$dayPrefix${day.toString().padStart(2, '0')}"
                try {
                    val clazz = Class.forName(dayClassName).kotlin
                    val instance = clazz.createInstance() as Solution<*>

                    assertEquals(year, clazz.getVariable<Int>(instance, "year"))
                    assertEquals(day, clazz.getVariable<Int>(instance, "day"))

                    val part1Answers = clazz.getVariable<Map<*, *>>(instance, "part1answers")
                    val part2Answers = clazz.getVariable<Map<*, *>>(instance, "part2answers")
                    assertTrue(part1Answers?.containsKey("puzzle") == true, "part1answers does not contain 'puzzle'")
                    assertTrue(part2Answers?.containsKey("puzzle") == true, "part2answers does not contain 'puzzle'")
                    val regex = Regex("sample.*")
                    assertTrue(
                        part1Answers!!.keys.any { regex.matches(it.toString()) },
                        "part1answers does not contain keys matching 'sample.*'"
                    )
                    assertTrue(
                        part2Answers!!.keys.any { regex.matches(it.toString()) },
                        "part2answers does not contain keys matching 'sample.*'"
                    )
                } catch (_: ClassNotFoundException) {
                    // Intentionally swallowing exception because missing classes are expected in this test
                } catch (e: Exception) {
                    throw AssertionError("Error validating class $dayClassName: ${e.message}", e)
                }
            }
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <R> KClass<*>.getVariable(instance: Any, propertyName: String): R? =
            this.memberProperties.find { it.name == propertyName }?.call(instance) as? R
    }
}
