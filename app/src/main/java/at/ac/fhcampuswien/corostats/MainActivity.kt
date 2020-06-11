package at.ac.fhcampuswien.corostats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import at.ac.fhcampuswien.corostats.stats.StatsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container,
                    NewsFragment()
                )
                .commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_stats -> {
                    // Respond to navigation item 1 click
                    navigateTo(StatsFragment(), false) // Navigate to the next Fragment)
                    true
                }
                R.id.page_feed -> {
                    navigateTo(NewsFragment(), false)
                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_about -> {
                    navigateTo(AboutFragment(), false)
                    // Respond to navigation item 2 click
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}
