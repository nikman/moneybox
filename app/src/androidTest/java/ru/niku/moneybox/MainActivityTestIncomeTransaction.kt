package ru.niku.moneybox


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.niku.main.MainActivity
import ru.niku.main.R


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestIncomeTransaction {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_reports), withContentDescription("Транзации"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.fab), withContentDescription("Добавить"),
                childAtPosition(
                    allOf(
                        withId(R.id.mainFragmentsContainer),
                        childAtPosition(
                            withId(R.id.container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())

        val materialButtonIncome = onView(
            allOf(
                withId(ru.niku.money_transaction.R.id.button_income), withText("Доход"),
                childAtPosition(
                    allOf(
                        withId(ru.niku.money_transaction.R.id.type),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButtonIncome.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(ru.niku.money_transaction.R.id.amount),
                childAtPosition(
                    childAtPosition(
                        withId(ru.niku.money_transaction.R.id.text_input_layout_amount),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("20000"), closeSoftKeyboard())

        val editText3 = onView(
            allOf(
                withId(ru.niku.money_transaction.R.id.amount), withText("20000"),
                withParent(withParent(withId(ru.niku.money_transaction.R.id.text_input_layout_amount))),
                isDisplayed()
            )
        )
        editText3.check(matches(withText("20000")))

        val textInputEditText4 = onView(
            allOf(
                withId(ru.niku.money_transaction.R.id.note),
                childAtPosition(
                    childAtPosition(
                        withId(ru.niku.money_transaction.R.id.text_input_layout_note),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("salary"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(ru.niku.money_transaction.R.id.transaction_save_buttom), withText("Сохранить"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.mainFragmentsContainer),
                        1
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun isPopupWindow(): Matcher<Root?>? {
        return isPlatformPopup()
    }
}
