package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord


class LocalFitnessDataStorage(context: Context) : FitnessDataStorage {

    companion object {
        const val TAG = "RemoteMarketDataStorage"

        private var INSTANCE: LocalFitnessDataStorage? = null

        fun getInstance(context: Context): LocalFitnessDataStorage? {
            if (INSTANCE == null) {
                INSTANCE = LocalFitnessDataStorage(context)
            }
            return INSTANCE
        }
    }

    private var fitnessDao: FitnessDao
    private var fitnessData: LiveData<List<FitnessCentersRecord>>

    init {
        val roomDB: FitnessRoomDatabase = Room.databaseBuilder(
            context,
            FitnessRoomDatabase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

        fitnessDao = roomDB.fitnessDao()
        fitnessData = fitnessDao.getAll()
    }

    override fun getFitnessData(): MutableLiveData<List<FitnessCentersRecord>> {
        return fitnessData as MutableLiveData<List<FitnessCentersRecord>>
    }

    override fun saveFitnessData(data: List<FitnessCentersRecord>) {
        fitnessDao.insert(data)
    }

    override fun destroyInstance() {
        INSTANCE = null
    }
}
