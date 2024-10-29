package com.rum.android.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rum.android.adapter.ProductAdapter
import com.rum.android.databinding.FragmentProductBinding
import com.rum.android.models.Product
import com.rum.android.network.APIClient
import com.rum.android.network.APIInterface
import io.opentracing.util.GlobalTracer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var customerAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        loadProduct()

    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun loadProduct() {

        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val call = apiInterface.getAllProducts()

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful && response.body() != null) {
                    customerAdapter = ProductAdapter(response.body()!!)
                    binding.recyclerView.adapter = customerAdapter
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}