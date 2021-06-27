package com.aaryaman.ninjamail

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import android.widget.Toast

class DbManager(context: Context) {

    val dbName = "NinjaMail"
    val dbVersion= 2

    //regular task table
    val dbTable= "Contacts"
    val colID= "ID"
    val colName= "Name"
    val colEmail= "Email"
    val colGroup= "ContactGroup"

    val sqlCreateTable= "Create Table if not exists $dbTable (" +
            "$colID INTEGER PRIMARY KEY, "+
            "$colName TEXT, " +
            "$colEmail TEXT, " +
            "$colGroup TEXT);"

    var sqlDB : SQLiteDatabase? = null

    init {
        var db= DatabaseHelperTasks(context)
        sqlDB= db.writableDatabase
    }

    inner class DatabaseHelperTasks: SQLiteOpenHelper {

        var context: Context? = null

        constructor(context: Context):super(context,dbName, null, dbVersion){
            this.context= context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            try {
                db!!.execSQL(sqlCreateTable)
                Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show()
            }
           catch (e: Exception){
               Log.e("TAG", e.message.toString() + "UUFFF")
           }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//            db!!.execSQL("Drop table if exists $dbTable")
            db!!.execSQL(sqlCreateTable)
        }

    }

    fun InsertRegularTask(values: ContentValues): Long{
        val ID= sqlDB!!.insert(dbTable, "", values)
        return ID
    }

    fun UpdateRegularTaskTick(values: ContentValues, name: String): Int{
        val ID= sqlDB!!.update(dbTable, values, "Name = ?", arrayOf(name))
        return ID
    }

    fun Query(): Cursor {
        val qb= SQLiteQueryBuilder()
        qb.tables= dbTable
        return qb.query(sqlDB, null, null, null, null, null, null)
    }


    fun deleteRegularTask(name: String): Int {
        return sqlDB!!.delete(dbTable, "$colName = '$name' ", null)
    }



}