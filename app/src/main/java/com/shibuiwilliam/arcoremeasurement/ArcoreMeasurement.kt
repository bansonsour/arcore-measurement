package com.shibuiwilliam.arcoremeasurement

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ArcoreMeasurement : AppCompatActivity() {
    private val TAG = "ArcoreMeasurement"
    private val buttonArrayList = ArrayList<String>()
    private lateinit var toMeasurement: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcore_measurement)

        val buttonArray = resources
            .getStringArray(R.array.arcore_measurement_buttons)

        buttonArray.map{it->
            buttonArrayList.add(it)
        }
        toMeasurement = findViewById(R.id.to_measurement)
        toMeasurement.text = buttonArrayList[0]
        toMeasurement.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(application, Measurement::class.java)
                startActivity(intent)
            }
        })

        isPkgInstalled(this,"com.google.arcore")
    }

    fun isPkgInstalled(context: Context, packageName:String) :Boolean {

        if (packageName == null || "".equals(packageName)) return false

        try {
            var info = context.getPackageManager().getApplicationInfo(packageName, 0)
            println("infos==================${info}")

            var infos=  context.getPackageManager().getInstalledApplications(0)
            if (!infos.isNullOrEmpty()) {
                for (item in infos) {
                    println("infos==================${item.packageName}")
                }
            }

            return info != null

        } catch (e: PackageManager.NameNotFoundException) {

            return false

        }
    }
}
