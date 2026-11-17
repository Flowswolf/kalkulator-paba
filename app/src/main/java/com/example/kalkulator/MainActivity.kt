package com.example.kalkulator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etAngka1 = findViewById<EditText>(R.id.etAngka1)
        val etAngka2 = findViewById<EditText>(R.id.etAngka2)
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnKurang = findViewById<Button>(R.id.btnKurang)
        val btnKali = findViewById<Button>(R.id.btnKali)
        val btnBagi = findViewById<Button>(R.id.btnBagi)
        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        btnTambah.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull() ?: 0.0
            val angka2 = etAngka2.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = angka1 + angka2

            cekBackground(hasil, mainLayout)
            DataHistory.historyTambah += "$angka1 + $angka2 = $hasil\n"
            gantiFragment(TambahFragment())
        }

        btnKurang.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull() ?: 0.0
            val angka2 = etAngka2.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = angka1 - angka2

            cekBackground(hasil, mainLayout)
            DataHistory.historyKurang += "$angka1 - $angka2 = $hasil\n"
            gantiFragment(KurangFragment())
        }

        btnKali.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull() ?: 0.0
            val angka2 = etAngka2.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = angka1 * angka2

            cekBackground(hasil, mainLayout)
            DataHistory.historyKali += "$angka1 * $angka2 = $hasil\n"
            gantiFragment(KaliFragment())
        }

        btnBagi.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull() ?: 0.0
            val angka2 = etAngka2.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = if (angka2 != 0.0) angka1 / angka2 else 0.0

            cekBackground(hasil, mainLayout)
            DataHistory.historyBagi += "$angka1 / $angka2 = $hasil\n"
            gantiFragment(BagiFragment())
        }
    }
    private fun cekBackground(hasil: Double, layout: LinearLayout) {
        if (hasil < 0) {
            layout.setBackgroundColor(Color.RED)
        } else {
            layout.setBackgroundColor(Color.WHITE)
        }
    }

    private fun gantiFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}