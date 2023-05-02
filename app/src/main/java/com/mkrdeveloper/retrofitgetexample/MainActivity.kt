package com.mkrdeveloper.retrofitgetexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mkrdeveloper.retrofitgetexample.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvId.visibility = View.GONE
            tvUserId.visibility = View.GONE
            tvBody.visibility = View.GONE
            tvTitle.visibility = View.GONE
        }

        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getUser()

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId.toString() }"
                        tvTitle.text = response.body()!!.title
                        tvBody.text = response.body()!!.body
                    }
                }
            }
        }

    }
}