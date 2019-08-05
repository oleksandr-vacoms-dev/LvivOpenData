package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalCateringDataStorage(context: Context) : DataStorage<CateringRecord> {

    private var cateringDao: CateringDao

    init {
        val roomDB: CateringRoomDatabase = Room.databaseBuilder(
            context,
            CateringRoomDatabase::class.java, "catering_db"
        )
            .allowMainThreadQueries()
            .build()

        cateringDao = roomDB.cateringDao()
    }

    override fun getAll(): LiveData<List<CateringRecord>> {
        return cateringDao.getAll()
    }

    override fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    override fun deleteAll() {
        cateringDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<CateringRecord> = cateringDao.getById(id)
}