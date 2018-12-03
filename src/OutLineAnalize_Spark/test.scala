package OutLineAnalize_Spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object test {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("test")
    val ssc = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()

    val spark = ssc.read.load("E:\\IdeaProjects\\commerce_basic\\spark-warehouse\\user_visit_action")
    spark.as

    ssc.stop()
  }
}
