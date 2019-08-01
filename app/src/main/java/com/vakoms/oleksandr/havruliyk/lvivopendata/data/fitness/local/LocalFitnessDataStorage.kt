package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord


class LocalFitnessDataStorage(context: Context) : FitnessDataStorage {

    companion object {
        const val TAG = "LocalFitnessDataStorage"

        private var INSTANCE: LocalFitnessDataStorage? = null

        fun getInstance(context: Context): LocalFitnessDataStorage? {
            if (INSTANCE == null) {
                INSTANCE = LocalFitnessDataStorage(context)
            }
            return INSTANCE
        }
    }

    private var fitnessDao: FitnessDao
    init {
        val roomDB: FitnessRoomDatabase = Room.databaseBuilder(
            context,
            FitnessRoomDatabase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

        fitnessDao = roomDB.fitnessDao()
    }

    override fun getFitnessData(): LiveData<List<FitnessCentersRecord>> {
        return fitnessDao.getAll()
    }

    override fun saveFitnessData(data: List<FitnessCentersRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAllData() {
        fitnessDao.deleteAll()
    }

    override fun destroyInstance() {
        INSTANCE = null
    }
}
