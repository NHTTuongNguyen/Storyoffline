package com.example.projectappdoctruyen.Service

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.projectappdoctruyen.R

class NetworkErrorDialogFragment : DialogFragment(), View.OnClickListener {
    /**
     * Method invoked by the system to create the Dialog to be shown.
     * The layout 'R.layout.network_error_dialog' for Network Error
     * will be inflated and returned as Dialog.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Building the Dialog using the AlertDialog.Builder
        val dialogBuilder = AlertDialog.Builder(activity)

        //Inflating the Network Error Dialog Layout 'R.layout.network_error_dialog'
        //(Passing null as we are attaching the layout ourselves to a Dialog)
        val networkErrorLayoutView = LayoutInflater.from(activity).inflate(R.layout.network_error_dialog, null)

        //Retrieving the dialog's message to embed an icon in the text
        val networkErrorMsgTextView = networkErrorLayoutView.findViewById<TextView>(R.id.network_error_text_id)
        TextAppearanceUtility.replaceTextWithImage(context, networkErrorMsgTextView)

        //Retrieving the action buttons
        val positiveButton = networkErrorLayoutView.findViewById<Button>(R.id.network_error_settings_btn_id)
        val negativeButton = networkErrorLayoutView.findViewById<Button>(R.id.network_error_cancel_btn_id)

        //Setting the click listener on the Buttons
        positiveButton.setOnClickListener(this)
        negativeButton.setOnClickListener(this)

        //Setting this prepared layout onto the dialog's builder
        dialogBuilder.setView(networkErrorLayoutView)

        //Returning the Dialog instance built
        return dialogBuilder.create()
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    override fun onClick(view: View) {
        //Evaluating based on View's id
        when (view.id) {
            R.id.network_error_settings_btn_id -> {
                //When the Positive Button is clicked

                //Creating an Intent to launch the Network Settings
                val networkIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                //Verifying that the Intent will resolve to an Activity
                if (networkIntent.resolveActivity(requireActivity().packageManager) != null) {
                    //Launching the Activity if resolved
                    startActivity(networkIntent)
                }
                dismiss() //Dismissing the dialog in the end
            }
            R.id.network_error_cancel_btn_id ->                 //When the Negative Button is clicked

                //Dismissing the dialog without doing any other operation
                dismiss()
        }
    }

    companion object {
        //Constant used as a Fragment Tag identifier
        val DIALOG_FRAGMENT_TAG = NetworkErrorDialogFragment::class.java.simpleName

        //Constant used for Logs
        private val LOG_TAG = NetworkErrorDialogFragment::class.java.simpleName
        fun newInstance(): NetworkErrorDialogFragment {
            //Returning the DialogFragment Instance
            return NetworkErrorDialogFragment()
        }
    }
}