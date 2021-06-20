package be.vives.philipshue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import be.vives.philipshue.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Instantiate the RequestQueue.
    lateinit var queue : RequestQueue;
    val get_url = "http://10.198.120.60/api/QScScRGIUH581BZOxzAoTrW76rN38GfgXd9QIFyz/lights"
    val put_url = "http://httpbin.org/put?param_ap=2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        queue=Volley.newRequestQueue(this);

        binding.getButton.setOnClickListener {
            Log.i("TEST", "Get button pressed")
            Log.i("TEST", "Get URL: "+get_url)
            // Request a string response from the provided URL.
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, get_url,null,
                Response.Listener<JSONObject> { response ->
                    val lamp1=response.getJSONObject("1")
                    val lamp1State=lamp1.getJSONObject("state")
                    binding.textView.setText("lamp 1 is on: ${lamp1State.getBoolean("on")}")
                },
                Response.ErrorListener { error-> binding.textView.setText("That didn't work! Error is: ${error.toString()}") })

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)
        }


        binding.putButton.setOnClickListener {
            Log.i("TEST", "PUT button pressed")
            Log.i("TEST", "PUT URL: "+put_url)
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.PUT, put_url,
                Response.Listener<String> { response ->
                    binding.textView.setText("Response is: ${response}")
                },
                Response.ErrorListener { error-> binding.textView.setText("That didn't work! Error is: ${error.toString()}") })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

    }
}