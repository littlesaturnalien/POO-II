package com.example.homework_xml

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_xml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        inicializar()
    }

    private fun inicializar() {
        binding.inclCapitalContable.btnCalcularCapital.setOnClickListener() {
            val activos = binding.inclCapitalContable.etActivosTotales.text.toString().toDoubleOrNull()
            val pasivos = binding.inclCapitalContable.etPasivosTotales.text.toString().toDoubleOrNull()
            if (activos == null || activos < 0 || pasivos == null || pasivos < 0){
                mostrarError("El valor no puede ser negativo")
                return@setOnClickListener
            }
            val capital = "C$ %.2f".format(calcularCapital(activos, pasivos))
            binding.inclCapitalContable.tvCapitalContable.text = capital
        }

        binding.inclNotaFinal.btnCalcularPromedio.setOnClickListener() {
            val corteI = binding.inclNotaFinal.etNotaCorteI.text.toString().toIntOrNull()
            val corteII = binding.inclNotaFinal.etNotaCorteII.text.toString().toIntOrNull()
            val corteIII = binding.inclNotaFinal.etNotaCorteIII.text.toString().toIntOrNull()
            if (corteI == null || corteI !in 0..100 ||
                corteII == null || corteII !in 0..100 ||
                corteIII == null || corteIII !in 0..100) {
                mostrarError("Las notas deben estar entre 0 y 100.")
                return@setOnClickListener
            }
            val promedio = calcularPromedio(corteI, corteII, corteIII).toString()
            binding.inclNotaFinal.tvPromedio.text = promedio
        }

        binding.inclIvaProducto.btnCalcularIVA.setOnClickListener() {
            val price = binding.inclIvaProducto.etPrecioProducto.text.toString().toDoubleOrNull()
            if (price == null || price < 0){
                mostrarError("El valor no puede ser negativo")
                return@setOnClickListener
            }
            val iva = "IVA: C$ %.2f".format(calcularIVA(price))
            val priceWithIVA = "Precio con IVA aplicado: C$ %.2f".format(calcularPrecioConIVA(price, calcularIVA(price)))
            binding.inclIvaProducto.tvResultadoIVA.text = iva
            binding.inclIvaProducto.tvPrecioConIVA.text = priceWithIVA
        }

        binding.inclInssEmpleado.btnCalcularINSS.setOnClickListener() {
            val salary = binding.inclInssEmpleado.etSalario.text.toString().toDoubleOrNull()
            if (salary == null || salary < 0){
                mostrarError("El valor no puede ser negativo")
                return@setOnClickListener
            }
            val inss = "C$ %.2f".format(calcularINSS(salary))
            binding.inclInssEmpleado.tvResultadoINSS.text = inss
        }

        binding.inclCantidadLetrasFrase.btnCalcularLetras.setOnClickListener() {
            val phrase = binding.inclCantidadLetrasFrase.etFrase.text.toString()
            val letterQty = "La frase tiene " + calcularCantidadLetras(phrase).toString() + " letras."
            binding.inclCantidadLetrasFrase.tvResultado.text = letterQty
        }
    }

    private fun calcularCantidadLetras(phrase : String) : Int {
        return phrase.count {it.isLetter()}
    }

    private fun calcularINSS(salary : Double) : Double {
        return salary * 0.07
    }

    private fun calcularIVA(price: Double): Double {
        return price * 0.15
    }

    private fun calcularPrecioConIVA(price : Double, iva : Double) : Double {
        return price + iva
    }

    private fun calcularPromedio(corteI: Int, corteII: Int, corteIII: Int): Int {
        return (corteI + corteII + corteIII)/3
    }

    private fun calcularCapital(activos: Double, pasivos: Double): Double {
        return activos - pasivos
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}