package borboa.andrea.reel_proyectomoviles

import androidx.recyclerview.widget.RecyclerView
import borboa.andrea.reel_proyectomoviles.CarouselAdapter.CarouselViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

class CarouselAdapter(private val carouselList: List<CarouselItem>) :
    RecyclerView.Adapter<CarouselViewHolder>() {
    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView

        init {
            mImageView = itemView.findViewById(R.id.imageCarousel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.mImageView.setImageResource(carouselList[position].image)
    }

    override fun getItemCount(): Int {
        return carouselList.size
    }
}