package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}


/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 肖申克的救赎	692795	剧情/犯罪	美国	1994/9/10	142	1994	9.6	多伦多电影节
  *
  * 统计每个时代的电影的数量
  */

object AnalizeByYear {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("AnalizeByCategory").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{
      val strarr = line.split("\t")
      //Tuple(1994, 1)
      (strarr(6), 1)
    }).reduceByKey(_ + _).foreachPartition(insertMysql)

    sc.stop()
  }

  /**
    * 将结果写入数据库中
    *
    * @param iterator
    */
  def insertMysql(iterator: Iterator[(String, Int)])={
    Class.forName ("com.mysql.jdbc.Driver").newInstance()
    val conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/AnalizeMovie", "root", "123456")
    iterator.foreach(data => {
      val ps = conn.prepareStatement("insert into movienumsByYear(year, number) values (?, ?)")
      ps.setString(1, data._1)
      ps.setInt(2, data._2)
      ps.executeUpdate()
    })
  }
}
