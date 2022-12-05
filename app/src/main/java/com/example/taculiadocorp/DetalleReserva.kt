package com.example.taculiadocorp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detallereserva.*

class DetalleReserva:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detallereserva)

        var IdReserva = intent.getStringExtra("iIdReserva")
        var nombreHotel = intent.getStringExtra("inombreHotel")
        var nombreCliente = intent.getStringExtra("inombreCliente")
        var telefonoCliente = intent.getStringExtra("itelefonoCliente").toString()
        var tipoHabitacion = intent.getStringExtra("itipoHabitacion")
        var numeroHabitaciones = intent.getStringExtra("inumeroHabitaciones").toString()
        var totalAdultos = intent.getStringExtra("itotalAdultos").toString()
        var totalMenores = intent.getStringExtra("itotalMenores").toString()
        var complementos = intent.getStringExtra("icomplementos")
        var totalReserva = intent.getStringExtra("itotalReserva")
        var pagosAplicados = intent.getStringExtra("ipagosAplicados")
        var montoPendiente = intent.getStringExtra("imontoPendiente")
        var ImgHotel = intent.getStringExtra("iImgHotel")
        var CheckIn = intent.getStringExtra("iCheckIn")
        var CheckOut = intent.getStringExtra("iCheckOut")
        var EstadoReserva = intent.getStringExtra("iEstadoReserva")
        var Huespedes = intent.getStringExtra("iHuespedes")
        var googlelink = intent.getStringExtra("iGLink")


        Glide.with(this).load(ImgHotel).into(ivHotelDetalle)
        tvNumeroReservaDetalle.append(IdReserva)
        tvNombreHotelDetalle.append(nombreHotel)
        tvEstadoReservaDetalle.append(EstadoReserva)
        tvNombreClienteDetalle.append(nombreCliente)
        tvTelefonoClienteDetalle.append(telefonoCliente)
        tvHabitacionDetalle.append(tipoHabitacion)
        tvNumeroHabitacionesDetalle.append(numeroHabitaciones)
        tvNumeroAdultosDetalle.append(totalAdultos)
        tvNumeroMenoresDetalle.append(totalMenores)
        tvCheckInDetalle.append(CheckIn)
        tvCheckOutDetalle.append(CheckOut)
        tvComplementosDetalle.append(complementos)
        tvTotalReservaDetalle.append(totalReserva)
        tvPagosAplicadosDetalle.append(pagosAplicados)
        tvMontoFaltante.append(montoPendiente)
        Glide.with(this).load(ImgHotel).into(ivHotelDetalle)

        btn_ubicacion.setOnClickListener(){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(googlelink))
            startActivity(i)
        }
    }
}