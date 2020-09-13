package com.example.gdgandroidwebinar11

import android.widget.DatePicker
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("date")
fun setDate(view: TextView, date: Date) {
    val dateText = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(date)
    view.text = dateText
}

@BindingAdapter("date")
fun setDate(picker: DatePicker, date: Date?) {
    if (date == null)
        return

    val oldDate = Date(picker.year - 1900, picker.month, picker.dayOfMonth)
    if (oldDate.year == date.year && oldDate.month == date.month && oldDate.date == date.date)
        return

    picker.updateDate(date.year, date.month, date.date)
}

@InverseBindingAdapter(attribute = "date", event = "dateAttrChanged")
fun getDate(picker: DatePicker): Date{
    return Date(picker.year - 1900, picker.month, picker.dayOfMonth)
}

@BindingAdapter("dateAttrChanged")
fun onDateChangedAdapter(picker: DatePicker, dateAttrChanged: InverseBindingListener?){
    val newValue: DatePicker.OnDateChangedListener? =
        if (dateAttrChanged == null) {
            null
        } else {
            DatePicker.OnDateChangedListener { _, _, _, _ ->
                dateAttrChanged.onChange()
            }
        }

    picker.setOnDateChangedListener(newValue)
}