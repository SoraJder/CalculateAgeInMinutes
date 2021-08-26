package com.example.calculatorage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker=findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view:View){

        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The selected date is $selectedDayOfMonth/${1+selectedMonth}/$selectedYear",
                Toast.LENGTH_LONG).show()
            val selectedDate="$selectedDayOfMonth/${1+selectedMonth}/$selectedYear"

            val tvSelectedDate=findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.setText(selectedDate)

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)

            val selectedDateInMinutes=theDate!!.time/60000

            val curentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMinutes=curentDate!!.time/60000

            val differenceInMinutes=currentDateToMinutes-selectedDateInMinutes

            val tvSelectedDateInMinutes=findViewById<TextView>(R.id.tvSelectedDateInMinute)

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }

}