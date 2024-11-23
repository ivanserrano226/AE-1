package com.example.ae_1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var salarioBrutoInput: EditText
    private lateinit var edadInput: EditText
    private lateinit var nPagasSpinner: Spinner
    private lateinit var gProfesionalSpinner: Spinner
    private lateinit var eCivilSpinner: Spinner
    private lateinit var discapacidadSpinner: Spinner
    private lateinit var nHijosSpinner: Spinner
    private lateinit var calcularButton: Button

    private var sueldoBruto : Int = 0
    private var sueldoNeto : Int = 0
    private var retenciones : Int = 0
    private var deducciones : String = ""
    private var nPagas : Int = 0
    private var gProfesional : String = ""
    private var edad : Int = 0
    private var discapacidad : Int = 0
    private var eCivil : String = ""
    private var nHijos: Int = 0

    companion object {
        const val SUELDO_NETO = "SUELDO_NETO"
        const val SUELDO_BRUTO = "SUELDO_BRUTO"
        const val RETENCIONES = "RETENCIONES"
        const val DEDUCCIONES = "DEDUCCIONES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        setSpinners()
        setValores()
    }

    private fun initListeners() {
        this.calcularButton.setOnClickListener {
            calcular()
            mostrarResultados()
        }
        edadInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                if (!editable.isNullOrEmpty()) {
                    edad = editable.toString().toIntOrNull() ?: 0
                }
            }
        })
        salarioBrutoInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                if (!editable.isNullOrEmpty()) {
                    sueldoBruto = editable.toString().toIntOrNull() ?: 0
                }
            }
        })
        nPagasSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
            override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
                nPagas = parentView?.getItemAtPosition(position).toString().toInt()
            }
        }
        nHijosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
            override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
                nHijos = parentView?.getItemAtPosition(position).toString().toInt()
            }
        }
        discapacidadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
            override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
                discapacidad = parentView?.getItemAtPosition(position).toString().toInt()
            }
        }
        eCivilSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
            override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
                eCivil = parentView?.getItemAtPosition(position).toString()
            }
        }
        gProfesionalSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
            override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
                gProfesional = parentView?.getItemAtPosition(position).toString()
            }
        }
    }

    private fun initComponents() {
        this.salarioBrutoInput = findViewById(R.id.salarioBrutoInput)
        this.edadInput = findViewById(R.id.edadInput)
        this.nPagasSpinner = findViewById(R.id.nPagasSpinner)
        this.gProfesionalSpinner = findViewById(R.id.gProfesionalSpinner)
        this.calcularButton = findViewById(R.id.calcularButton)
        this.eCivilSpinner = findViewById(R.id.eCivilSpinner)
        this.discapacidadSpinner = findViewById(R.id.discapacidadSpinner)
        this.nHijosSpinner = findViewById(R.id.nHijosSpinner)

    }

    private fun setSpinners() {
        val gProfesionalAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.grupo_profesional,
            android.R.layout.simple_spinner_item
        )
        gProfesionalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gProfesionalSpinner.adapter = gProfesionalAdapter

        val pagasAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.pagas,
            android.R.layout.simple_spinner_item
        )
        pagasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nPagasSpinner.adapter = pagasAdapter

        val eCivilAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil,
            android.R.layout.simple_spinner_item
        )
        eCivilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eCivilSpinner.adapter = eCivilAdapter

        val discapacidadAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.discapacidad,
            android.R.layout.simple_spinner_item
        )
        discapacidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        discapacidadSpinner.adapter = discapacidadAdapter

        val nHijosAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.numero_hijos,
            android.R.layout.simple_spinner_item
        )
        nHijosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nHijosSpinner.adapter = nHijosAdapter
    }

    private fun setValores() {
        this.salarioBrutoInput.setText(getString(R.string.salario_minimo))
        this.edadInput.setText(getString(R.string.edad_minima))

        this.sueldoBruto = getString(R.string.salario_minimo).toInt()
        this.edad = getString(R.string.edad_minima).toInt()

        this.nPagas = nPagasSpinner.selectedItem.toString().toInt()
        this.eCivil = eCivilSpinner.selectedItem.toString()
        this.gProfesional = gProfesionalSpinner.selectedItem.toString()
        this.discapacidad = discapacidadSpinner.selectedItem.toString().toInt()
        this.nHijos = nHijosSpinner.selectedItem.toString().toInt()
    }

    private fun calcular() {
        //Simular cálculo
        this.sueldoNeto = this.sueldoBruto - Random.nextInt(this.sueldoBruto / 3)

        this.retenciones = Random.nextInt(7, 100)

        if (this.nHijos > 0) {
            this.deducciones = "- Hijos a cargo: " + Random.nextInt(7, 20).toString() + "%\n"
        }

        if(this.discapacidad > 0) {
            this.deducciones += "\n- Discapacidad: " + Random.nextInt(7, 20).toString() + "%\n"
        }
    }

    private fun mostrarResultados() {
        //Intent
        val intent = Intent(this, MainActivity2::class.java)
        //Añadimos extra para pasar datos
        intent.putExtra(SUELDO_NETO, sueldoNeto)
        intent.putExtra(SUELDO_BRUTO, sueldoBruto)
        intent.putExtra(RETENCIONES, retenciones)
        intent.putExtra(DEDUCCIONES, deducciones)

        this.startActivity(intent)
    }
}