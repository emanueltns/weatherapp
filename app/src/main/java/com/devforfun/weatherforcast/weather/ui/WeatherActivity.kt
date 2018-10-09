package com.devforfun.weatherforcast.weather.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.devforfun.weatherforcast.BuildConfig
import com.devforfun.weatherforcast.R
import com.devforfun.weatherforcast.R.id.toolbar
import com.devforfun.weatherforcast.api.model.ContentItem
import com.devforfun.weatherforcast.api.model.WeatherResponse
import com.devforfun.weatherforcast.databinding.ActivityWeatherBinding
import com.devforfun.weatherforcast.databinding.ContentItemBinding
import com.devforfun.weatherforcast.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel
    var subscriptions = CompositeDisposable()
    lateinit var binding  : ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        subscribeToDataStream()

        subscriptions.add(viewModel.getWeatherDisposable());

        fab.setOnClickListener { view ->
            binding.progress.visibility = View.VISIBLE
            subscriptions.add(viewModel.getWeatherDisposable());
        }
    }

    private fun subscribeToDataStream() {
        viewModel.weatherSuccess.observe(this, Observer { it ->
            it?.let {
                binding.empty.visibility = View.GONE
                binding.setCondition(ContentItem(getString(R.string.current_condition),it.weather[0].main))
                binding.setTemperature(ContentItem(getString(R.string.temperature), it.main.temp.toString()))
                binding.setWindSpeed(ContentItem(getString(R.string.wind_speed), it.wind.speed.toString()))
                binding.setWindDirection (ContentItem(getString(R.string.wind_direction), it.wind.deg.toString()))
                val str = "${BuildConfig.IMAGE_URL}${it.weather[0].icon}.png"
                Picasso.get().load("${BuildConfig.IMAGE_URL}${it.weather[0].icon}.png")
                        .into(binding.weatherIcon)
                binding.progress.visibility = View.GONE
            }
        } )

        viewModel.weatherError.observe(this, Observer { it ->
            it?.let {
                Toast.makeText(this,it, Toast.LENGTH_LONG).show()
                binding.progress.visibility = View.GONE
                binding.empty.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_weather, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
