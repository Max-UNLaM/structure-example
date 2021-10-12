package ar.edu.unlam.structureexample.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import ar.edu.unlam.structureexample.AppDatabase
import ar.edu.unlam.structureexample.MIGRATION_1_2
import ar.edu.unlam.structureexample.RobotAdapter
import ar.edu.unlam.structureexample.databinding.ActivityMainBinding
import ar.edu.unlam.structureexample.data.RobotDao

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var robotDao: RobotDao
    private lateinit var robotAdapter: RobotAdapter
    private lateinit var robotBinding: ActivityMainBinding

    companion object {
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "room-showroom-db"
            )
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }

    /**
     * Manual dependency injection
     */
    private fun injectDependencies() {
        this.database = buildDatabase(this.applicationContext)
        this.robotDao = this.database.robotDao()
        this.robotAdapter = RobotAdapter { }
    }

    private fun setupRecyclerView() {
        robotBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        robotBinding.recyclerView.adapter = robotAdapter
    }

    private fun populate() {
        var robots = robotDao.getAll()
        if (robots.isEmpty()) {
            this.robotDao.populate()
            robots = this.robotDao.getAll()
        }
        robotAdapter.updateRobots(robots)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        robotBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        injectDependencies()
        setContentView(robotBinding.root)
        setupRecyclerView()
        populate()
    }
}