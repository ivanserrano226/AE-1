package com.example.ae_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ae_1.MainActivity.Companion.DEDUCCIONES
import com.example.ae_1.MainActivity.Companion.RETENCIONES
import com.example.ae_1.MainActivity.Companion.SUELDO_BRUTO
import com.example.ae_1.MainActivity.Companion.SUELDO_NETO

class MainActivity2 : AppCompatActivity() {

    private lateinit var sueldoNetoText: TextView
    private lateinit var sueldoBrutoText: TextView
    private lateinit var deduccionesText: TextView
    private lateinit var retencionesText: TextView
    private lateinit var volverButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI() {
        obtenerValores()
    }

    private fun initListeners() {
        this.volverButton.setOnClickListener {
            volver()
        }
    }

    private fun initComponents() {
        this.sueldoNetoText = findViewById(R.id.salarioNeto)
        this.sueldoBrutoText = findViewById(R.id.salarioBruto)
        this.retencionesText = findViewById(R.id.retenciones)
        this.deduccionesText = findViewById(R.id.deducciones)
        this.volverButton = findViewById(R.id.volverButton)
    }

    private fun obtenerValores() {
        val sueldoNeto = intent.extras?.getInt(SUELDO_NETO)
        val sueldoBruto = intent.extras?.getInt(SUELDO_BRUTO)
        val retenciones = intent.extras?.getInt(RETENCIONES)
        val deducciones = intent.extras?.getString(DEDUCCIONES)

        sueldoNetoText.text = sueldoNeto.toString()
        sueldoBrutoText.text = sueldoBruto.toString()
        retencionesText.text = retenciones.toString()
        if (!deducciones.isNullOrEmpty()) {
            deduccionesText.text = deducciones
        }
    }
    private fun volver() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}