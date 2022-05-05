package com.example.mvvmdemo.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.models.NicePlace




/**
 * You need to use a singleton pattern when you want to access you database
 */
class NicePlaceRepository {

    companion object {
        val instance = NicePlaceRepository()
    }

    private val dataSet: ArrayList<NicePlace> = ArrayList()

    /*
    mimic retrieve data from a web server/db and set it to variable
     */
    fun getNicePlaces(): MutableLiveData<List<NicePlace>> {
        setNicePlaces()

        val data = MutableLiveData<List<NicePlace>>()
        data.value = dataSet
        return data
    }

    /*
    method that retrieves the data from a db/online source
     */
    private fun setNicePlaces() {
//        dataSet.add(
//            NicePlace(
//                imageUrl = "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
//                title = "Havasu Falls"
//            )
//        )
        dataSet.add(
            NicePlace(
                imageUrl = "https://i.redd.it/tpsnoz5bzo501.jpg",
                title = "Trondheim"
            )
        )
        dataSet.add(
            NicePlace(
                imageUrl = "https://i.redd.it/qn7f9oqu7o501.jpg",
                title = "Portugal"
            )
        )
        dataSet.add(
            NicePlace(
                imageUrl = "https://i.redd.it/j6myfqglup501.jpg",
                title = "Rocky Mountain National Park"
            )
        )
//        dataSet.add(
//            NicePlace(
//                imageUrl = "https://i.redd.it/0h2gm1ix6p501.jpg",
//                title = "Havasu Falls"
//            )
//        )
//        dataSet.add(
//            NicePlace(
//                imageUrl = "https://i.redd.it/k98uzl68eh501.jpg",
//                title = "Mahahual"
//            )
//        )
//        dataSet.add(
//            NicePlace(
//                imageUrl = "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
//                title = "Frozen Lake"
//            )
//        )
//        dataSet.add(
//            NicePlace(
//                imageUrl = "https://i.redd.it/obx4zydshg601.jpg",
//                title = "Austrailia"
//            )
//        )
    }


}