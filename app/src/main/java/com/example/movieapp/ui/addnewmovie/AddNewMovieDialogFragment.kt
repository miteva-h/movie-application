package com.example.movieapp.ui.addnewmovie

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.movieapp.R

class AddNewMovieDialogFragment : DialogFragment() {

    interface AddMovieDialogListener {
        fun onDialogPositiveClick(
            title: String,
            description: String,
            director: String,
            actors: ArrayList<String>
        )
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    lateinit var listener: AddMovieDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            var inflater = requireActivity().layoutInflater
            val view: View = inflater.inflate(R.layout.dialog_add_movie, null);

            val editDialogMovieTitle: EditText = view.findViewById<EditText>(R.id.movieTitleLabel)
            val editDialogMovieDescription: EditText =
                view.findViewById<EditText>(R.id.movieDescriptionLabel)
            val editDialogMovieDirector: EditText =
                view.findViewById<EditText>(R.id.movieDirectorLabel)
            val editDialogMovieActors: EditText = view.findViewById<EditText>(R.id.movieActorsLabel)

            builder.setView(view)
                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                    listener.onDialogPositiveClick(
                        editDialogMovieTitle.text.toString(),
                        editDialogMovieDescription.text.toString(),
                        editDialogMovieDirector.text.toString(),
                        editDialogMovieActors.text.toString().split(",").toMutableList().toCollection(ArrayList())
                    )
                })
                .setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick(this)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as AddMovieDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} must implement AddMovieDialogListener.")
        }
    }

}



