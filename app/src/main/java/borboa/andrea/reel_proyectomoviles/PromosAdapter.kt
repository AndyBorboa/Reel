package borboa.andrea.reel_proyectomoviles

import androidx.recyclerview.widget.RecyclerView
import borboa.andrea.reel_proyectomoviles.PromosAdapter.PromosViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

class PromosAdapter(val promosList: List<PromosItem>) :
    RecyclerView.Adapter<PromosViewHolder>() {
    inner class PromosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val mImageView: ImageView

        init {
            mImageView = itemView.findViewById(R.id.imagePromo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.promos_item, parent, false)
        return PromosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromosViewHolder, position: Int) {
        holder.mImageView.setImageResource(promosList[position].image)
    }

    override fun getItemCount(): Int {
        return promosList.size
    }
}