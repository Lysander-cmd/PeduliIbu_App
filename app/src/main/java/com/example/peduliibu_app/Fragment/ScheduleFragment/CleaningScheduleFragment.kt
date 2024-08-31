package com.example.peduliibu_app.Fragment.ScheduleFragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.peduliibu_app.R
import com.example.peduliibu_app.databinding.FragmentCleaningScheduleBinding
import java.util.Calendar

class CleaningScheduleFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var eventCardView: View
    private lateinit var tvEventTime: TextView
    private lateinit var tvEventTitle: TextView
    private lateinit var tvEventDescription: TextView
    private lateinit var btnAddCustomReminder: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cleaning_schedule, container, false)

        calendarView = view.findViewById(R.id.calendarView)
        eventCardView = view.findViewById(R.id.eventCardView)
        tvEventTime = view.findViewById(R.id.tvEventTime)
        tvEventTitle = view.findViewById(R.id.tvEventTitle)
        tvEventDescription = view.findViewById(R.id.tvEventDescription)
        btnAddCustomReminder = view.findViewById(R.id.btnAddCustomReminder)

        setupCalendar()
        setupAddCustomReminderButton()
        scheduleWeeklyBathroomCleaning()

        return view
    }

    private fun setupCalendar() {
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            updateEventCard(year, month, dayOfMonth)
        }
    }

    private fun updateEventCard(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            eventCardView.visibility = View.VISIBLE
            tvEventTime.text = "09:00 - 11:00"
            tvEventTitle.text = "Pembersihan Mingguan Kamar Mandi"
            tvEventDescription.text = "Bersihkan kamar mandi secara menyeluruh, termasuk toilet, wastafel, dan lantai."
        } else {
            eventCardView.visibility = View.GONE
        }
    }

    private fun setupAddCustomReminderButton() {
        btnAddCustomReminder.setOnClickListener {
            // Open a dialog to add a custom reminder
            showAddCustomReminderDialog()
        }
    }

    private fun showAddCustomReminderDialog() {
        // Implement a dialog to add a custom reminder
        // This is where you'd create a custom AlertDialog or use a DialogFragment
        // to get user input for the custom reminder
    }

    private fun scheduleWeeklyBathroomCleaning() {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, CleaningReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY * 7,
            pendingIntent
        )
    }
}