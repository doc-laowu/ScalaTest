package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 父与女	53358	剧情/动画/短片	英国/比利时/荷兰	2001/5/27	60	2001	9.2	美国
  *
  * 统计每个国家的电影数量
  */

object AnalizeByCity {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("AnalizeByCategory").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{

      val strarr = line.split("\t")
      val cityarr = strarr(3).split("/")
      cityarr.map(x=>{
        (x, 1)
      })
    }).flatMap(x=>x.map(x=>(x._1, x._2))).reduceByKey(_ + _).foreachPartition(insertMysql)

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
      val ps = conn.prepareStatement("insert into movienumsByCity(category, number) values (?, ?)")
      ps.setString(1, data._1)
      ps.setInt(2, data._2)
      ps.executeUpdate()
    })
  }

}
