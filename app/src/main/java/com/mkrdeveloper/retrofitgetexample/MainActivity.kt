package com.mkrdeveloper.retrofitgetexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mkrdeveloper.retrofitgetexample.databinding.ActivityMainBinding
import com.mkrdeveloper.retrofitgetexample.models.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

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

       // getRequest()
        //postRequest()
      //  putRequest()
       // patchRequest()

        deleteRequest()


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getRequest() {
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

                         tvId.text = "id: ${ response.body()!!.id}"
                         tvUserId.text = "user id: ${ response.body()!!.userId}"
                         tvTitle.text = response.body()!!.title
                         tvBody.text = response.body()!!.body
                     }
                 }
             }
         }
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun postRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                //val user = User("new body",null,"new title",23)
                RetrofitInstance.api.createUrlPost(23,"url title","url body")

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    Snackbar.make(binding.root,"${response.code()}",Snackbar.LENGTH_INDEFINITE).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun putRequest(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                val user = User("put body",null,null,23)
                RetrofitInstance.api.putPost(23,user)

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    Snackbar.make(binding.root,"code: ${response.code()}",Snackbar.LENGTH_INDEFINITE).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"
                    }
                }
            }
        }
    }

    private fun patchRequest(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                val user = User("patch body",null,null,23)
                RetrofitInstance.api.patchPost(23,user)

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    Snackbar.make(binding.root,"code: ${response.code()}",Snackbar.LENGTH_INDEFINITE).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"
                    }
                }
            }
        }
    }

    private fun deleteRequest(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {


                RetrofitInstance.api.deletePost(23)

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    Snackbar.make(binding.root,"code: ${response.code()}",Snackbar.LENGTH_INDEFINITE).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"
                    }
                }
            }
        }
    }
}