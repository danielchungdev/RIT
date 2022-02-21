package com.example.favoritefees

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.favoritefees.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var savedFeeds: SharedPreferences
    private var previous: String = ""
    private var editIndex : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedFeeds = getSharedPreferences("feeds", Context.MODE_PRIVATE)

        binding.saveButton.setOnClickListener { handleSaveButtonClick() }

        binding.clearTagsButton.setOnClickListener { handleClearTagsButtonClick() }

        refreshButtons(null)

    }

    private fun refreshButtons(newTag: String?){
        val tags: Array<String> = savedFeeds.all.keys.toTypedArray()
        tags.sortWith(String.CASE_INSENSITIVE_ORDER)
        if (newTag != null ){
            var index = tags.indexOf(newTag)
            makeTagGUI(newTag, index)
        }
        else{
            for (index in tags.indices){
                makeTagGUI(tags[index], index)
            }
        }
    }

    private fun makeTag(query: String, tag: String){
        val originalQuery = savedFeeds.getString(tag, "")
        val editor = savedFeeds.edit()
        editor.putString(tag, query)
        editor.apply()

        if (previous != tag && previous != ""){
            binding.queryLinearLayout.removeViewAt(editIndex)
            editor.remove(previous)
        }
        editor.putString(tag, query).apply()

        if (originalQuery == ""){
            refreshButtons(tag)
        }
    }

    private fun makeTagGUI(tag: String, index: Int){
        val newTagView = layoutInflater.inflate(R.layout.new_tag_view, null, false)
        val newTagButton = newTagView.findViewById<Button>(R.id.newTagButton)
        newTagButton.text = tag
        newTagButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                handleQueryButtonClicked(v as Button)
            }
        })

        val newEditButton = newTagView.findViewById<Button>(R.id.newEditButton)
        newEditButton.setText(R.string.edit)
        newEditButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                handleEditButtonClicked(v as Button)
            }
        })

        binding.queryLinearLayout.addView(newTagView, index)
    }

    private fun clearButtons(){
        binding.queryLinearLayout.removeAllViews()
    }

    private fun handleSaveButtonClick(){
        if( binding.queryEditText.text.isNotEmpty() &&  binding.tagEditText.text.isNotEmpty()){
            makeTag(binding.queryEditText.text.toString(), binding.tagEditText.text.toString())
            binding.queryEditText.setText("")
            binding.tagEditText.setText("")

            (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(binding.tagEditText.windowToken, 0)
        }
        else{
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(R.string.missingTitle)
            builder.setPositiveButton(R.string.OK, null)
            builder.setMessage(R.string.missingMessage)
            var errorDialog = builder.create()
            errorDialog.show()
        }
    }

    private fun handleClearTagsButtonClick(){
        val builder = AlertDialog.Builder(this@MainActivity)

        builder.setTitle(R.string.confirmTitle)

        builder.setPositiveButton(R.string.erase){ dialong, which ->
            clearButtons()
            savedFeeds.edit().clear().apply()
        }

        builder.setCancelable(true)
        builder.setNegativeButton(R.string.cancel, null)

        builder.setMessage(R.string.confirmMessage)

        builder.create().show()
    }

    private fun handleQueryButtonClicked(v: Button){
        val buttonText = v.text.toString()
        val query = savedFeeds.getString(buttonText, "")
        val urlString = getString(R.string.searchURL) + query
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        startActivity(webIntent)

    }

    private fun handleEditButtonClicked(v: Button){
        val buttonRow = v.parent as ConstraintLayout
        val searchButton = buttonRow.findViewById<Button>(R.id.newTagButton)
        val tag = searchButton.text.toString()
        binding.tagEditText.setText(tag)
        binding.queryEditText.setText(savedFeeds.getString(tag, ""))

        previous = binding.tagEditText.text.toString()

        val allTags: Array<String> = savedFeeds.all.keys.toTypedArray()
        allTags.sortWith(String.CASE_INSENSITIVE_ORDER)
        editIndex = allTags.indexOf(previous)
    }

}