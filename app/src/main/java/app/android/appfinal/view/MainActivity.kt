package app.android.appfinal.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager // Asegúrate de importar PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.android.appfinal.databinding.ActivityMainBinding
import app.android.appfinal.view.GrabadoraFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}
