package segura.countries.app.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import segura.countries.app.R
import segura.countries.app.common.setDarkOrDayMode
import segura.countries.app.model.Country
import segura.countries.app.model.networkHandler.Resource

class MainActivity : AppCompatActivity(), CountryAdapter.OnCountryClickListener {
    companion object {
        private const val TAG = "MainActivityDebug"
    }

    private val countriesViewModel: CountriesViewModel by viewModel()
    lateinit var countryAdapter: CountryAdapter
    private var shortAnimationDuration = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDarkOrDayMode()
        setContentView(R.layout.activity_main)
        setupRecycler()
        countriesViewModel.countriesList.observe(this, countriesObserver)


    }

    private val countriesObserver = Observer<Resource<List<Country>>> { response ->
        when (response.status) {
            Resource.Status.ERROR -> {
                hideLoading()
            }
            Resource.Status.SUCCESS -> {
                countryAdapter.swapData(response.data as MutableList<Country>)
                hideLoading()

            }
            Resource.Status.LOADING -> {
                showLoading()
            }
        }
    }

    private fun setupRecycler() {
        countryAdapter = CountryAdapter(this)
        rvCountries.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            adapter = countryAdapter
        }

    }

    private fun showLoading() {

    }

    private fun hideLoading() {
        showViewWithCrossFade()
    }

    override fun onCountryClickListener(position: Int, item: Country) {
        Toast.makeText(this, item.nativeName, Toast.LENGTH_SHORT).show()
    }
    private fun showViewWithCrossFade() {
        rvCountries.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate().alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }

        loadingProgressBar.animate().alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    loadingProgressBar.visibility = View.GONE
                }
            })

    }
}
