package segura.countries.app.common

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("app:loadImage")
fun loadImageBinding(imageView: ImageView, url: String) {
    GlideToVectorYou.init().with(imageView.context).load(Uri.parse(url), imageView)

}
