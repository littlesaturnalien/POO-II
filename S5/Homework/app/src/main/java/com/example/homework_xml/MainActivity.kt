package com.example.homework_xml

import android.os.Bundle
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
            val activos = binding.inclCapitalContable.etActivosTotales.text.toString().toDouble()
            val pasivos = binding.inclCapitalContable.etPasivosTotales.text.toString().toDouble()
            val capital = "C$ " + ".2f".format(calcularCapital(activos, pasivos).toString())
            binding.inclCapitalContable.tvCapitalContable.text = capital
        }

        binding.inclNotaFinal.btnCalcularPromedio.setOnClickListener() {
            val corteI = binding.inclNotaFinal.etNotaCorteI.text.toString().toInt()
            val corteII = binding.inclNotaFinal.etNotaCorteII.text.toString().toInt()
            val corteIII = binding.inclNotaFinal.etNotaCorteIII.text.toString().toInt()
            val promedio = calcularPromedio(corteI, corteII, corteIII).toString()
            binding.inclNotaFinal.tvPromedio.text = promedio
        }

        binding.inclIvaProducto.btnCalcularIVA.setOnClickListener() {
            val price = binding.inclIvaProducto.etPrecioProducto.text.toString().toDouble()
            val iva = "IVA: C$ .2f".format(calcularIVA(price).toString())
            val priceWithIVA = "Precio con IVA aplicado: C$ .2f".format(calcularPrecioConIVA(price, calcularIVA(price)).toString())
            binding.inclIvaProducto.tvResultadoIVA.text = iva
            binding.inclIvaProducto.tvPrecioConIVA.text = priceWithIVA
        }

        binding.inclInssEmpleado.btnCalcularINSS.setOnClickListener() {
            val salary = binding.inclInssEmpleado.etSalario.text.toString().toDouble()
            val inss = "C$ .2f".format(calcularINSS(salary).toString())
            binding.inclInssEmpleado.tvResultadoINSS.text = inss
        }

        binding.inclCantidadLetrasFrase.btnCalcularLetras.setOnClickListener() {
            val phrase = binding.inclCantidadLetrasFrase.etFrase.text.toString()
            val letterQty = "La frase tiene " + calcularCantidadLetras(phrase).toString() + " letras."
            binding.inclCantidadLetrasFrase.tvResultado.text = letterQty
        }
    }

    private fun calcularCantidadLetras(phrase : String) : Int {
        return phrase.replace(" ", "").length
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
}