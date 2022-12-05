package com.example.taculiadocorp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.cardview.view.*

class MyAdapter (val arrayList: ArrayList<Model>, val context:Context):
    RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    var aux = ""
        class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

                fun bintItems(model: Model, context: Context) {
                    itemView.tvNumeroReserva.append(model.idReservas.toString())
                    itemView.tvCheckIn.append(model.checkin.toString())
                    itemView.tvCheckOut.append(model.checkout.toString())
                    Glide.with(context).load(model.imghotel).into(itemView.ivHotel)

            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return ViewHolder(v)
         }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bintItems(arrayList[position],context)

        holder.itemView.setOnClickListener{

            val model = arrayList.get(position)

            var gidReservas:Int=model.idReservas
            var gnombreHotel:String=model.nombreHotel
            var gnombreCliente:String=model.nombreCliente
            var gtelefonoCliente:String=model.telefonoCliente
            var gtipoHabitacion:String=model.tipoHabitacion
            var gnumeroHabitaciones:String=model.numeroHabitaciones
            var gtotalAdultos:String=model.totalAdultos
            var gtotalMenores:String=model.totalMenores
            var gcomplementos:String=model.complementos
            var gtotalReserva:String=model.totalReserva
            var gpagosAplicados:String=model.pagosAplicados
            var gmontoPendiente:String=model.montoPendiente
            var gimghotel:String=model.imghotel
            var gcheckin:String=model.checkin
            var gcheckout:String=model.checkout
            var gestadoreserva:String=model.estadoreserva
            var ghuespedes:String=model.huespedes
            var gLink:String = model.googleLink
            val intent = Intent(context,DetalleReserva::class.java)

            intent.putExtra("iIdReserva",gidReservas.toString())
            intent.putExtra("inombreHotel",gnombreHotel)
            intent.putExtra("inombreCliente",gnombreCliente)
            intent.putExtra("itelefonoCliente",gtelefonoCliente)
            intent.putExtra("itipoHabitacion",gtipoHabitacion)
            intent.putExtra("inumeroHabitaciones",gnumeroHabitaciones)
            intent.putExtra("itotalAdultos",gtotalAdultos)
            intent.putExtra("itotalMenores",gtotalMenores)
            intent.putExtra("icomplementos",gcomplementos)
            intent.putExtra("itotalReserva",gtotalReserva)
            intent.putExtra("ipagosAplicados",gpagosAplicados)
            intent.putExtra("imontoPendiente",gmontoPendiente)
            intent.putExtra("iImgHotel",gimghotel)
            intent.putExtra("iCheckIn",gcheckin)
            intent.putExtra("iCheckOut",gcheckout)
            intent.putExtra("iEstadoReserva",gestadoreserva)
            intent.putExtra("iHuespedes",ghuespedes)
            intent.putExtra("iGLink",gLink)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    }




