package com.sumit.bookdatabase.ui.addUser

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sumit.bookdatabase.R
import com.sumit.bookdatabase.data.User
import com.sumit.bookdatabase.databinding.ActivityMainBinding
import com.sumit.bookdatabase.other.Messages
import com.sumit.bookdatabase.ui.showusers.ShowUsersActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val addUserViewModel: AddUserViewModel by viewModels()
    private lateinit var typeOfBook: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        spinnerListener()
        setupClickListener()

    }

    private fun setupClickListener() {
        binding.btnSubmit.setOnClickListener {
            if (setValidation()) {
                startActivityForResult(Intent(this, ShowUsersActivity::class.java), RESULT_OK)
            }
        }

        binding.btnShowUsers.setOnClickListener {
            startActivity(Intent(this, ShowUsersActivity::class.java))
        }
    }

    private fun spinnerListener() {
        binding.spinnerBooks.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val books = resources.getStringArray(R.array.books_array)
                typeOfBook = books[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
    }

    private fun setupUi() {
        ArrayAdapter.createFromResource(
            this,
            R.array.books_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerBooks.adapter = adapter
        }
    }

    private fun setValidation(): Boolean {
        val message = addUserViewModel.checkValidation(
            binding.etName.text.toString(),
            binding.etNumber.text.toString(),
            typeOfBook
        )
        Toast.makeText(
            this, message, Toast.LENGTH_LONG
        ).show()

        return message.contains(Messages.ADDED_TO_DATABASE)
    }
}