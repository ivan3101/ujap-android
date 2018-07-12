package com.example.ujap.Controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.ujap.Fragments.Art58Fragment
import com.example.ujap.Fragments.HomeFragment
import com.example.ujap.R
import com.example.ujap.Services.AuthService
import com.example.ujap.Services.UserDataService
import com.example.ujap.Utilities.replaceFragmenty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(),
        HomeFragment.OnFragmentInteractionListener,
        Art58Fragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_home)
        onHome()

        val header = nav_view.getHeaderView(0)
        if (AuthService.isLoggedIn) {
            header.headerNameTxt.text = "${UserDataService.nombre} ${UserDataService.apellido}"
            header.headerCarreraTxt.text = UserDataService.carrera
            header.headerSemestreTxt.text = "${UserDataService.semestre} semestre"
        }
    }

    fun onLiberation(view: View) {
        if (UserDataService.solicitud) {
            Toast.makeText(this, "Ya tiene una solicitud en proceso", Toast.LENGTH_SHORT).show()
        } else {
            UserDataService.solicitud = true
            Toast.makeText(this, "Solicitud enviada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> onHome()

            R.id.nav_art58 -> onArt58()

            R.id.nav_logout -> onLogOut()
        }
        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onHome() {
        replaceFragmenty(fragment = HomeFragment(),
                allowStateLoss = true,
                containerViewId = R.id.mainContent)
        title = "Inicio"
    }

    fun onArt58() {
        replaceFragmenty(fragment = Art58Fragment(),
                allowStateLoss = true,
                containerViewId = R.id.mainContent)
        title = "Articulo 58"
    }

    fun onLogOut() {
        AuthService.logOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Sesion cerrada con exito", Toast.LENGTH_SHORT).show()
        finish()
    }
}
