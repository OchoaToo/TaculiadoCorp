package com.example.taculiadocorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import com.android.volley.Request
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val auth = Firebase.auth
    val arrayList = ArrayList<Model>()
    val displayList = ArrayList<Model>()
    private lateinit var imgIniciarSesion: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var localemail = intent.getStringExtra("email")
        println(localemail)

        btnLogOut.setOnClickListener{
            auth.signOut()
            val intentLogin: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }


        val url = "https://lookitmmr.000webhostapp.com/Reservas/php/consultar.php"

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET,url, { response ->
            val jsonArray = JSONArray(response)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = JSONObject(jsonArray.getString(i))
                var idReservas = jsonObject.get("idReservas").toString().toInt()
                var nombreHotel = jsonObject.get("nombreHotel").toString()
                var nombreCliente = jsonObject.get("nombreCliente").toString()
                var telefonoCliente = jsonObject.get("telefonoCliente").toString()
                var tipoHabitacion = jsonObject.get("tipoHabitacion").toString()
                var numeroHabitaciones = jsonObject.get("numeroHabitaciones").toString()
                var totalAdultos = jsonObject.get("totalAdultos").toString()
                var totalMenores = jsonObject.get("totalMenores").toString()
                var complementos = jsonObject.get("complementos").toString()
                var totalReserva = jsonObject.get("totalReserva").toString()
                var pagosAplicados = jsonObject.get("pagosAplicados").toString()
                var montoPendiente = jsonObject.get("montoPendiente").toString()
                var imghotel = jsonObject.get("imghotel").toString()
                var checkin = jsonObject.get("checkin").toString()
                var checkout = jsonObject.get("checkout").toString()
                var estadoreserva = jsonObject.get("estadoreserva").toString()
                var huespedes = jsonObject.get("huespedes").toString()
                var usuario = jsonObject.get("usuario").toString()

                if(usuario == localemail){
                    arrayList.add(Model(idReservas, nombreHotel, nombreCliente, telefonoCliente, tipoHabitacion,
                        numeroHabitaciones,totalAdultos,totalMenores,complementos,totalReserva,pagosAplicados,montoPendiente,
                        imghotel,checkin,checkout,estadoreserva,huespedes))

                    print(nombreHotel)
                }
                println("CLIENTE $nombreCliente")
                println("EMAIL $localemail")


            }
            displayList.addAll(arrayList)

            val myAdapter = MyAdapter(displayList,this)

            recyclerView1.layoutManager = LinearLayoutManager(this)
            recyclerView1.adapter = myAdapter
        }, { error->
            Toast.makeText(this,"Algo salio mal: ${error}", Toast.LENGTH_LONG).show()
            println("ERROR"+error)

        })
        queue.add(stringRequest)

    }


}

