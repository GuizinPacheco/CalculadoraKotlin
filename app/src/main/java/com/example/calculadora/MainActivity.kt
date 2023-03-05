package com.example.calculadora

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculadora.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {



    private lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbRef = FirebaseDatabase.getInstance().getReference("historicoCalculadora")



        val num1 = findViewById(R.id.editTextTextPersonName) as EditText
        val num2 = findViewById(R.id.editTextTextPersonName2) as EditText
        val tot = findViewById(R.id.editTextTextPersonName3) as EditText
        val add = findViewById(R.id.button) as Button
        val subtract = findViewById(R.id.button2) as Button
        val divide = findViewById(R.id.button4) as Button
        val multiply = findViewById(R.id.button6) as Button

        fun  salveHistoricCalc(operation:Int) {
            //getting values

            val primeiroValor = num1.text.toString().toFloat()
            val segundoValor = num2.text.toString().toFloat()
            val conta = ""
            when {
                operation == 1 -> { val conta = "Soma" }
                operation == 2 -> { val conta = "Subtração" }
                operation == 3 -> { val conta = "Divisão" }
                operation == 4 -> { val conta = "Multiplicação" }
            }



            val save_result = tot.text.toString()
            val empId = dbRef.push().key!!

            val calculadoree = CalculadoraModel(empId,primeiroValor,segundoValor,conta,save_result)

            dbRef.child(empId).setValue(calculadoree)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{ err ->
                    Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
                }


        }


        add.setOnClickListener {
            val val1 = num1.text.toString().toFloat()
            val val2 = num2.text.toString().toFloat()

            val result = val1 + val2

            tot.setText(result.toString())

            salveHistoricCalc(1)

        }

        subtract.setOnClickListener {
            val val1 = num1.text.toString().toFloat()
            val val2 = num2.text.toString().toFloat()

            val result = val1 - val2

            tot.setText(result.toString())

            salveHistoricCalc(2)

        }

        divide.setOnClickListener {
            val val1 = num1.text.toString().toFloat()
            val val2 = num2.text.toString().toFloat()

            val result = val1 / val2

            tot.setText(result.toString())

            salveHistoricCalc(3)
        }


        multiply.setOnClickListener {
            val val1 = num1.text.toString().toFloat()
            val val2 = num2.text.toString().toFloat()

            val result = val1 * val2

            tot.setText(result.toString())

            salveHistoricCalc(4)


        }






    }

}

