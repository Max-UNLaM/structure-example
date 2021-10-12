package ar.edu.unlam.structureexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.structureexample.databinding.ListItemBinding
import ar.edu.unlam.structureexample.data.RobotEntity


class RobotAdapter(private val clickListener: (RobotEntity) -> Unit) :
    RecyclerView.Adapter<RobotViewHolder>() {

    fun updateRobots(robotEntities: List<RobotEntity>?) {
        robotList.clear()
        if (robotEntities != null) {
            robotList.addAll(robotEntities)
        }
    }

    private val robotList = mutableListOf<RobotEntity>()

    override fun getItemCount() = robotList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RobotViewHolder {
        return RobotViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RobotViewHolder, position: Int) {
        val robot = robotList[position]
        holder.binding.name.text = robot.name

        holder.itemView.setOnClickListener { clickListener(robot) }
    }

}

class RobotViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)