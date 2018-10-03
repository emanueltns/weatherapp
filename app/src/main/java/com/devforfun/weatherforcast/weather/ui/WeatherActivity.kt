package com.devforfun.weatherforcast.weather.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.devforfun.weatherforcast.R
import com.devforfun.weatherforcast.R.id.toolbar
import com.devforfun.weatherforcast.api.model.WeatherResponse
import com.devforfun.weatherforcast.weather.viewmodel.WeatherViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel
    var subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        subscribeToDataStream()

        fab.setOnClickListener { view ->
            subscriptions.add(viewModel.getWeatherDisposable());
        }
    }

    private fun subscribeToDataStream() {
        viewModel.weatherSuccess.observe(this, Observer { it ->
            it?.let {
                //TODO update adapter
                Toast.makeText(this, it.name, Toast.LENGTH_LONG).show();
            }
        } )

        viewModel.weatherError.observe(this, Observer { it ->
            it?.let {
                Toast.makeText(this,it, Toast.LENGTH_LONG).show()
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
