package com.example.blinkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.blinkit.datas.Products
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CardFragment.
     */
    // TODO: Rename and change types and number of parameters
    companion object{
        fun newInstance(productData : Products) : CardFragment {
            var fragment = CardFragment()
            var data = Bundle()
            data.putString("image_url" , productData.url)
            data.putString("title", productData.title)
            fragment.arguments = data
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_card, container, false)
        var image = view.findViewById<ImageView>(R.id.card_view_image)
        var title = view.findViewById<TextView>(R.id.card_view_text)

        Picasso.with(image.context)
            .load(arguments?.getString("image_url", ""))
            .into(image)
        title.setText(arguments?.getString("title", "Default title"))
        return view
    }
}