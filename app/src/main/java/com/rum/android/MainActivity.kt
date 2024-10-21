package com.rum.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.*
import com.datadog.android.log.Logger
import com.datadog.android.log.Logs
import com.datadog.android.log.LogsConfiguration
import com.datadog.android.okhttp.DatadogEventListener
import com.datadog.android.okhttp.DatadogInterceptor
import com.datadog.android.okhttp.trace.TracingInterceptor
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.Rum
import com.datadog.android.rum.RumConfiguration
import com.datadog.android.rum.tracking.FragmentViewTrackingStrategy
import com.datadog.android.trace.AndroidTracer
import com.datadog.android.trace.Trace
import com.datadog.android.trace.TraceConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rum.android.databinding.ActivityMainBinding
import io.opentracing.util.GlobalTracer
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val clientToken = "pub27b608edd879434b2ebdcc150ff56432"
        val environmentName = "dev"
        val appVariantName = "rum-android"
        Datadog.setVerbosity(Log.INFO)
        val configuration = Configuration.Builder(
            clientToken = clientToken,
            env = environmentName,
            variant = appVariantName,
            service = "rum-android"

        )
            .useSite(DatadogSite.US1)
            .build()

        Datadog.initialize(this, configuration,TrackingConsent.GRANTED)

        val logsConfig = LogsConfiguration.Builder().build()
        Logs.enable(logsConfig)

        val logger = Logger.Builder()
            .setNetworkInfoEnabled(true)
            .setLogcatLogsEnabled(true)
            .setRemoteSampleRate(100f)
            .setBundleWithTraceEnabled(true)
            .setName("<LOGGER_NAME>")
            .build()

        val traceConfig = TraceConfiguration.Builder().build()
        Trace.enable(traceConfig)

        val tracer = AndroidTracer.Builder().build()
        GlobalTracer.registerIfAbsent(tracer)

        val applicationId = "138b9ea7-418d-4d0a-a000-5bff08c03aab"

        val rumConfiguration = RumConfiguration.Builder(applicationId)
            .trackUserInteractions()
            .build()

        Rum.enable(rumConfiguration)

        // Usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando o BottomNavigationView
        val navView: BottomNavigationView = binding.navView

        // Configurando o NavController com os fragmentos de navegação
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Configurando o AppBar com os fragmentos de nível superior
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_product, R.id.navigation_customers
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
