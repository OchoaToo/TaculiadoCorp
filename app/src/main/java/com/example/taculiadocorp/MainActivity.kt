package com.example.taculiadocorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import com.android.volley.Request
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

//TODO: HAY UN TOAST QUE APARECE AL INICIAR SESION SE VE FEO Y SER REPITE 2 VECES QUITALO (yo no lo encotnre)
//TODO: Al iniciar sesion figura como si abriera 2 activitys  (REVISAR)

class MainActivity : AppCompatActivity() {
    private val auth = Firebase.auth
    val arrayList = ArrayList<Model>()
    val displayList = ArrayList<Model>()
    private lateinit var imgIniciarSesion: ImageView
    var existereserva:String? = null
    var maplink = ""
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


                    existereserva = "true"
                    //hotelImagen(nombreHotel)


                if(usuario == localemail){
                    var user = intent.getStringExtra("email")
                    txtUsuario.setText("Bienvenido $user !")
                    hotelUbicacion(nombreHotel)
                    println("DEBUG NAME:$nombreHotel LINK:$maplink")
                    arrayList.add(Model(idReservas, nombreHotel, nombreCliente, telefonoCliente, tipoHabitacion,
                        numeroHabitaciones,totalAdultos,totalMenores,complementos,totalReserva,pagosAplicados,montoPendiente,
                        imghotel,checkin,checkout,estadoreserva,huespedes,usuario, maplink!!))



                }else{
                    existereserva = "false"
                    println(existereserva)
                }


            }
            displayList.addAll(arrayList)

            //ImagenView si hay reservas se vuelve invisible
            if(arrayList.isNotEmpty()){
                imgViewupps.setVisibility(View.INVISIBLE)
                var a: Int = Random.nextInt(3)
                if(a.equals(1)){
                    txtViewAgenteDeVentas.text="El viaje sera grandioso"
                }else if(a.equals(2)){
                    txtViewAgenteDeVentas.text="En el mar la vida es mas sabrosa"
                }else{
                    txtViewAgenteDeVentas.text="Viajar alimenta el alma"
                }

            }//okto

            val myAdapter = MyAdapter(displayList,this)

            recyclerView1.layoutManager = LinearLayoutManager(this)
            recyclerView1.adapter = myAdapter
        }, { error->
            Toast.makeText(this,"Algo salio mal: ${error}", Toast.LENGTH_LONG).show()
            println("ERROR"+error)

        })
        queue.add(stringRequest)

    }
    private fun hotelUbicacion(nombre:String?){
        val links: List<String> = listOf(
            /*[0]Azul Ixtapa*/"https://www.google.com.mx/maps/place/Azul+Ixtapa+Grand+All+Suites+-+Spa+%26+Convention+Center+All+inclusive/@17.6751455,-101.6437903,17z/data=!3m1!4b1!4m8!3m7!1s0x843475be4b9d8db3:0xa21952d29c0f0216!5m2!4m1!1i2!8m2!3d17.6751404!4d-101.6416016",
            /*[1]Barcelo Ixtapa*/"https://www.google.com.mx/maps/place/Barcel%C3%B3+Ixtapa/@17.6565878,-101.6023277,17z/data=!3m1!4b1!4m8!3m7!1s0x843476628ced5361:0x340fd4da988cf74e!5m2!4m1!1i2!8m2!3d17.6565827!4d-101.600139",
            /*[2]Club Med Ixtapa*/"https://www.google.com.mx/maps/place/Club+Med+Ixtapa+Pacific/@17.6712506,-101.6431112,17z/data=!3m1!4b1!4m8!3m7!1s0x84347713b2714b53:0xe21aa374ff5dcf7e!5m2!4m1!1i2!8m2!3d17.6712455!4d-101.6409225",
            /*[3]Hotel Porton del Cieloa*/"https://www.google.com.mx/maps/place/HOTEL+PORTON+DEL+CIELO/@19.9377913,-101.7505114,8.25z/data=!4m8!3m7!1s0x842d942f178f6145:0x26f0078851616311!5m2!4m1!1i2!8m2!3d19.515639!4d-101.622924",
            /*[4]Hotel Krystal Ixtapa*/"https://www.google.com.mx/maps/place/Krystal+Ixtapa/@17.6616352,-101.6079227,17z/data=!3m1!4b1!4m8!3m7!1s0x8434767b48d0ed45:0x1ff94674740c2d23!5m2!4m1!1i2!8m2!3d17.6616301!4d-101.605734",
            /*[5]Palmetto*/"https://www.google.com.mx/maps/place/Palmetto+Ixtapa/@17.6438421,-101.6002187,17z/data=!3m1!4b1!4m8!3m7!1s0x8434769205643c13:0x7458ccefe1f9aadc!5m2!4m1!1i2!8m2!3d17.643837!4d-101.59803",
            /*[6]Hotel Fontan*/"https://www.google.com.mx/maps/place/Hotel+Font%C3%A1n+Ixtapa/@17.6586576,-101.6028444,17z/data=!3m1!4b1!4m8!3m7!1s0x843476624c855cd5:0xd1359a49f8a494bc!5m2!4m1!1i2!8m2!3d17.6586525!4d-101.6006557",
            /*[7]Hotel las brisas*/"https://www.google.com.mx/maps/place/Hotel+Las+Brisas+Ixtapa/@17.64798,-101.598185,17z/data=!3m1!4b1!4m8!3m7!1s0x8434918f9d29ee95:0xf3505d3ef35d12b5!5m2!4m1!1i2!8m2!3d17.6479749!4d-101.5959963",
            /*[8]Hotel Coral*/"https://www.google.com.mx/maps/place/Coral+Ixtapa/@17.6586807,-101.5891653,17z/data=!3m1!4b1!4m8!3m7!1s0x843476f605c3aaab:0xe4bb4dd90b32533d!5m2!4m1!1i2!8m2!3d17.6586756!4d-101.5869766",
            /*[9]Villa las Garzas*/"https://www.google.com.mx/maps/place/Villa+Las+Garzas/@17.6687262,-101.6055637,17z/data=!3m1!4b1!4m5!3m4!1s0x8434773c06aa9b49:0x54072ed228e7b1fe!8m2!3d17.6687211!4d-101.603375")
       // var char = nombre!!.filter{ !it.isWhitespace()}.lowercase()
        if(nombre == "Hotel Grand Azul"){
            maplink = links[0]
        }
        if(nombre == "Hotel Barcelo"){
            maplink = links[1]
        }
        if(nombre == "Club Med"){
            maplink = links[2]
        }
        if(nombre == "Hotel Port"){
            maplink = links[3]
        }
        if(nombre == "Hotel Krystal"){
            maplink = links[4]
        }
        if(nombre == "Hotel Palmetto"){
            maplink = links[5]
        }
        if(nombre == "Hotel Fontan"){
            maplink = links[6]
        }
        if(nombre == "Hotel Las Brisas"){
            maplink = links[7]
        }
        if(nombre == "Hotel Coral"){
            maplink = links[8]
        }
        if(nombre == "Hotel Villa las Garzas"){
            maplink = links[9]
        }
    }

}

