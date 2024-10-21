package com.rum.android.ui.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
//import com.datadog.trace.api.GlobalTracer
import com.rum.android.adapter.CustomerAdapter
import com.rum.android.databinding.FragmentCustomersBinding
import com.rum.android.models.Customer
import com.rum.android.network.APIClient
import com.rum.android.network.APIInterface
import io.opentracing.util.GlobalTracer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomersFragment : Fragment() {

    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private lateinit var customerAdapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        loadCustomers()

    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun loadCustomers() {

        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val call = apiInterface.getAllCustomers()

        call.enqueue(object : Callback<List<Customer>> {
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                if (response.isSuccessful && response.body() != null) {
                    customerAdapter = CustomerAdapter(response.body()!!)
                    binding.recyclerView.adapter = customerAdapter
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
