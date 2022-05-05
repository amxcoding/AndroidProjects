package com.example.mvvmdemo.viewmodels

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.models.NicePlace
import com.example.mvvmdemo.repositories.NicePlaceRepository

class MainActivityViewModel : ViewModel() {

    private val TAG = "MainActivityViewModel"
    private var nicePlaceRepository: NicePlaceRepository? = null

    // used when a query is made from a db, to show a progressbar on screen
    @Volatile
    var isUpdating =  MutableLiveData<Boolean>()

    var nicePlacesViewModel: MutableLiveData<List<NicePlace>>? = null


    fun init() {
        if (this.nicePlacesViewModel != null) {
            return
        }
        nicePlaceRepository = NicePlaceRepository.instance
        nicePlacesViewModel = nicePlaceRepository!!.getNicePlaces()
    }



    fun addNewValue(nicePlace: NicePlace, value: Boolean) {
        // when adding something new show progressbar
        isUpdating.value = value

        Log.d(TAG, "addNewValue: ${isUpdating.value}")

        val async = object : AsyncTask<Void, Void, Void>() {
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                val currentPlaces = nicePlacesViewModel?.value as ArrayList<NicePlace>
                currentPlaces.add(nicePlace);
                nicePlacesViewModel?.postValue(currentPlaces);
                isUpdating.postValue(!value);
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return null
            }
        }.execute()

        Log.d(TAG, "addNewValue: ${isUpdating.value}")

    }


//    fun addNewValue(niceplace: NicePlace, value: Boolean) {
//        // when adding something new show progressbar
//        isUpdating.value = value
//
//        //if (isUpdating?.value == null) return
//
//        Log.d(TAG, "addNewValue: ${isUpdating.value}")
//
//        // to mimic a server request we will us her a thread and set it to sleep
//        val thread = Thread()
//        thread.run {
//            val currentNicePlaces = nicePlacesViewModel?.value as ArrayList<NicePlace>
//            currentNicePlaces.add(niceplace)
//            nicePlacesViewModel?.postValue(currentNicePlaces)
//
//            Thread.sleep(2000)
//        }
//
//        thread.start()
//        isUpdating.value = !value
//
//        Log.d(TAG, "addNewValue: ${isUpdating.value}")
//
//    }

}