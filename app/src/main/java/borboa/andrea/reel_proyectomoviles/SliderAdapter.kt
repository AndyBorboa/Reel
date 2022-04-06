package borboa.andrea.reel_proyectomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter (private var images:IntArray):SliderViewAdapter<SliderAdapter.Holder>(){
    inner class Holder (itemView: View):ViewHolder(itemView){
        var imageView: ImageView =itemView.findViewById(R.id.imageView)

    }

    override fun getCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.Holder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.slider_item, parent ,false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapter.Holder?, position: Int) {
        if(viewHolder!=null) {
            viewHolder.imageView.setImageResource(images[position])
        }
    }
}