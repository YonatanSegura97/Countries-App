package segura.countries.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import segura.countries.app.databinding.CountryItemLayoutBinding
import segura.countries.app.model.Country

class CountryAdapter(private val onCountryClickListener: OnCountryClickListener) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var data: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemLayoutBinding.inflate(inflater)
        return CountryViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(data[position], onCountryClickListener, position)

    fun swapData(data: MutableList<Country>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: CountryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Country,
            onCountryClickListener: OnCountryClickListener,
            position: Int
        ) {

            binding.root.setOnClickListener {
                onCountryClickListener.onCountryClickListener(position, item)
            }
            binding.country = item
            binding.executePendingBindings()
        }
    }

    interface OnCountryClickListener {
        fun onCountryClickListener(position: Int, item: Country)
    }

}