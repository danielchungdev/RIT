package com.example.leagueoflegends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.content) == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content, FirstFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_info -> InfoDialog().show(supportFragmentManager, "info")
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayChampion(view: View){
        val fragment = DisplayFragment()
        val args = Bundle()
        when(view.id){
            R.id.champion_1 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.ashe)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE FROST ARCHER")
            }
            R.id.champion_2 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.draven)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE GLORIOUS EXECUTIONER")
            }
            R.id.champion_3 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.ezreal)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE PRODIGAL EXPLORER")
            }
            R.id.champion_4 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.jhin)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE VIRTUOSO")
            }
            R.id.champion_5 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.jinx)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE LOOSE CANNON")
            }
            R.id.champion_6 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.sivir)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE BATTLE MISTRESS")
            }
            R.id.champion_7 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.yasuo)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE UNFORGIVEN")
            }
            R.id.champion_8 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.ahri)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE NINE-TAILED FOX")
            }
            R.id.champion_9 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.drmundo)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE MADMAN OF ZAUN")
            }
            R.id.champion_10 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.garen)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE MIGHT OF DEMACIA")
            }
            R.id.champion_11 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.katarina)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE SINISTER BLADE")
            }
            R.id.champion_12 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.shen)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE EYE OF TWILIGHT")
            }
            R.id.champion_13 -> {
                args.putInt(DisplayFragment.ARG_IMG_ID, R.drawable.volibear)
                args.putString(DisplayFragment.ARG_TEXT_ID, "THE RELENTLESS STORM")
            }
        }
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .commit()
    }


}